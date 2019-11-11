package com.hanaset.onepiecesanji.service;

import com.hanaset.onepiececommon.converter.ZonedDateTimeConverter;
import com.hanaset.onepiececommon.entity.ExchangeEntity;
import com.hanaset.onepiececommon.entity.NoticeEntity;
import com.hanaset.onepiececommon.model.NoticeExchange;
import com.hanaset.onepiececommon.model.NoticeKind;
import com.hanaset.onepiececommon.repository.NoticeRepository;
import com.hanaset.onepiecesanji.client.upbit.SanjiUpbitRestApiClient;
import com.hanaset.onepiecesanji.client.upbit.model.UpbitNoticeData;
import com.hanaset.onepiecesanji.client.upbit.model.UpbitNoticeResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import retrofit2.Response;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class SanjiUpbitService {

    private final SanjiUpbitRestApiClient sanjiUpbitRestApiClient;
    private final NoticeRepository noticeRepository;

    public SanjiUpbitService(SanjiUpbitRestApiClient sanjiUpbitRestApiClient,
                             NoticeRepository noticeRepository) {
        this.sanjiUpbitRestApiClient = sanjiUpbitRestApiClient;
        this.noticeRepository = noticeRepository;
    }

    public void searchUpbit() {
        try {
            Response<UpbitNoticeResponse<UpbitNoticeData>> response = sanjiUpbitRestApiClient.getNotices("general").execute();
            BigDecimal standardId = noticeRepository.getMaxNoticeId(NoticeExchange.UPBIT.getExchange()).orElse(BigDecimal.ZERO);
            System.out.println(standardId.toPlainString());
            if (response.isSuccessful()) {
                List<NoticeEntity> noticeEntities = response.body().getData().getList().stream().filter(upbitNoticeInfo -> BigDecimal.valueOf(upbitNoticeInfo.getId()).compareTo(standardId) > 0)
                        .map(upbitNoticeInfo ->
                                NoticeEntity.builder()
                                        .exchangeCode(ExchangeEntity.builder().code(NoticeExchange.UPBIT).build())
                                        .noticeId(BigDecimal.valueOf(upbitNoticeInfo.getId()))
                                        .kind(upbitNoticeInfo.getTitle().contains("이벤트") ? NoticeKind.EVENT : NoticeKind.NOTICE)
                                        .title(upbitNoticeInfo.getTitle())
                                        .url("https://www.upbit.com/service_center/notice?id=" + upbitNoticeInfo.getId())
                                        .createdAt(ZonedDateTime.parse(upbitNoticeInfo.getCreatedAt()))
                                        .updatedAt(ZonedDateTime.parse(upbitNoticeInfo.getUpdatedAt()))
                                        .build()
                        ).collect(Collectors.toList());

                //System.out.println(noticeEntities);
                noticeRepository.saveAll(noticeEntities);
            } else {
                log.error("Upbit Notice search ERROR : {}", response.errorBody().byteString().toString());
            }

        } catch (IOException e) {
            log.error("Upbit Notice search IOException : {}", e.getMessage());
        }
    }
}
