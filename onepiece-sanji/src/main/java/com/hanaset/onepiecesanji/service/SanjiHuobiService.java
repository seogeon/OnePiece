package com.hanaset.onepiecesanji.service;

import com.hanaset.onepiececommon.entity.ExchangeEntity;
import com.hanaset.onepiececommon.entity.NoticeEntity;
import com.hanaset.onepiececommon.model.NoticeExchange;
import com.hanaset.onepiececommon.model.NoticeKind;
import com.hanaset.onepiececommon.repository.NoticeRepository;
import com.hanaset.onepiecesanji.client.huobi.SanjiHuobiRestApiClient;
import com.hanaset.onepiecesanji.client.huobi.model.HuobitResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import retrofit2.Response;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class SanjiHuobiService {

    private final NoticeRepository noticeRepository;
    private final SanjiHuobiRestApiClient sanjiHuobiRestApiClient;

    public SanjiHuobiService(NoticeRepository noticeRepository,
                             SanjiHuobiRestApiClient sanjiHuobiRestApiClient) {
        this.noticeRepository = noticeRepository;
        this.sanjiHuobiRestApiClient = sanjiHuobiRestApiClient;
    }

    public void searchHuobiEvent() {

        BigDecimal standardId = noticeRepository.getMaxNoticeId(NoticeExchange.HUOBI.getExchange()).orElse(BigDecimal.ZERO);
        System.out.println(standardId.toPlainString());

        try {

            Response<HuobitResponse> response = sanjiHuobiRestApiClient.getNotice().execute();

            if(response.isSuccessful()) {

                List<NoticeEntity> entityList = response.body().getData().stream().filter(huobiNoticeInfo -> BigDecimal.valueOf(Long.parseLong(huobiNoticeInfo.getUrl().replaceAll("[^0-9]", ""))).compareTo(standardId) > 0)
                        .map(huobiNoticeInfo ->
                                NoticeEntity.builder()
                                        .noticeId(BigDecimal.valueOf(Long.parseLong(huobiNoticeInfo.getUrl().replaceAll("[^0-9]", ""))))
                                        .exchangeCode(ExchangeEntity.builder().code(NoticeExchange.HUOBI).build())
                                        .kind(huobiNoticeInfo.getTitle().contains("이벤트") ? NoticeKind.EVENT : NoticeKind.NOTICE)
                                        .createdAt(ZonedDateTime.ofInstant(Instant.ofEpochMilli(huobiNoticeInfo.getCreateTime()),
                                                ZoneId.of("Asia/Seoul")))
                                        .updatedAt(ZonedDateTime.ofInstant(Instant.ofEpochMilli(huobiNoticeInfo.getCreateTime()),
                                                ZoneId.of("Asia/Seoul")))
                                        .title(huobiNoticeInfo.getTitle())
                                        .url(huobiNoticeInfo.getUrl())
                                        .build()
                        ).collect(Collectors.toList());

                noticeRepository.saveAll(entityList);

            } else {
                log.error("Huobi Notice search ERROR : {}", response.errorBody().byteString().toString());
            }

        }catch (IOException e) {
            log.error("Huobi Notice search IOException : {}", e.getMessage());
        }
    }
}
