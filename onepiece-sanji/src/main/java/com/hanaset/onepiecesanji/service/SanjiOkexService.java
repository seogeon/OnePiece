package com.hanaset.onepiecesanji.service;

import com.hanaset.onepiececommon.entity.ExchangeEntity;
import com.hanaset.onepiececommon.entity.NoticeEntity;
import com.hanaset.onepiececommon.model.NoticeExchange;
import com.hanaset.onepiececommon.model.NoticeKind;
import com.hanaset.onepiececommon.repository.NoticeRepository;
import com.hanaset.onepiecesanji.client.okex.SanjiOkexRestApiClient;
import com.hanaset.onepiecesanji.client.okex.model.OkexNoticeInfo;
import com.hanaset.onepiecesanji.properties.SanjiUrlProperties;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;
import retrofit2.Response;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class SanjiOkexService {

    private final SanjiUrlProperties sanjiUrlProperties;
    private final NoticeRepository noticeRepository;
    private final SanjiOkexRestApiClient sanjiOkexRestApiClient;

    public SanjiOkexService(SanjiUrlProperties sanjiUrlProperties,
                            NoticeRepository noticeRepository,
                            SanjiOkexRestApiClient sanjiOkexRestApiClient) {
        this.sanjiUrlProperties = sanjiUrlProperties;
        this.noticeRepository = noticeRepository;
        this.sanjiOkexRestApiClient = sanjiOkexRestApiClient;
    }

    public void searchOkexEvent() {

        BigDecimal standardId = noticeRepository.getMaxNoticeId(NoticeExchange.OKEX.getExchange()).orElse(BigDecimal.ZERO);
        System.out.println(standardId.toPlainString());

        try {
            Response<List<OkexNoticeInfo>> response = sanjiOkexRestApiClient.getNotice().execute();

            if(response.isSuccessful()) {

                List<NoticeEntity> entityList = response.body().stream().filter(okexNoticeInfo -> BigDecimal.valueOf(Long.parseLong(okexNoticeInfo.getUrl().replaceAll("[^0-9]", ""))).compareTo(standardId) > 0)
                        .map(okexNoticeInfo ->
                                NoticeEntity.builder()
                                        .noticeId(BigDecimal.valueOf(Long.parseLong(okexNoticeInfo.getUrl().replaceAll("[^0-9]", ""))))
                                        .exchangeCode(ExchangeEntity.builder().code(NoticeExchange.OKEX).build())
                                        .kind(okexNoticeInfo.getTitle().contains("이벤트") ? NoticeKind.EVENT : NoticeKind.NOTICE)
                                        .createdAt(ZonedDateTime.parse(okexNoticeInfo.getCreateTime()))
                                        .updatedAt(ZonedDateTime.parse(okexNoticeInfo.getUpdateTime()))
                                        .title(okexNoticeInfo.getTitle())
                                        .url(okexNoticeInfo.getUrl())
                                        .build()
                        ).collect(Collectors.toList());

//                System.out.println(entityList);
                noticeRepository.saveAll(entityList);

            } else {
                log.error("Okex Notice search ERROR : {}", response.errorBody().byteString().toString());
            }
        } catch (IOException e) {
            log.error("Okex Notice search IOException : {}", e.getMessage());
        }
    }
}
