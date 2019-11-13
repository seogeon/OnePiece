package com.hanaset.onepiecesanji.service;

import com.hanaset.onepiececommon.entity.ExchangeEntity;
import com.hanaset.onepiececommon.entity.NoticeEntity;
import com.hanaset.onepiececommon.model.NoticeExchange;
import com.hanaset.onepiececommon.model.NoticeKind;
import com.hanaset.onepiececommon.repository.NoticeRepository;
import com.hanaset.onepiecesanji.client.coinone.SanjiCoinoneRestApiClient;
import com.hanaset.onepiecesanji.client.coinone.model.CoinoneResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import retrofit2.Response;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class SanjiCoinoneService {

    private final SanjiCoinoneRestApiClient sanjiCoinoneRestApiClient;
    private final NoticeRepository noticeRepository;

    public SanjiCoinoneService(SanjiCoinoneRestApiClient sanjiCoinoneRestApiClient,
                               NoticeRepository noticeRepository) {
        this.noticeRepository = noticeRepository;
        this.sanjiCoinoneRestApiClient = sanjiCoinoneRestApiClient;
    }

    public void searchCoinoneEvent() {

        BigDecimal standardId = noticeRepository.getMaxNoticeId(NoticeExchange.COINONE.getExchange()).orElse(BigDecimal.ZERO);
        System.out.println(standardId.toPlainString());

        try {

            Response<CoinoneResponse> response = sanjiCoinoneRestApiClient.getNotice().execute();
            if (response.isSuccessful()) {

                List<NoticeEntity> entityList = response.body().getResults().stream().filter(coinoneNoticeInfo -> BigDecimal.valueOf(coinoneNoticeInfo.getId()).compareTo(standardId) > 0)
                        .map(coinoneNoticeInfo ->
                                NoticeEntity.builder()
                                        .noticeId(BigDecimal.valueOf(coinoneNoticeInfo.getId()))
                                        .exchangeCode(ExchangeEntity.builder().code(NoticeExchange.COINONE).build())
                                        .kind(coinoneNoticeInfo.getTitle().contains("이벤트") ? NoticeKind.EVENT : NoticeKind.NOTICE)
                                        .createdAt(ZonedDateTime.parse(coinoneNoticeInfo.getCreatedAt()))
                                        .updatedAt(ZonedDateTime.parse(coinoneNoticeInfo.getUpdatedAt()))
                                        .title(coinoneNoticeInfo.getTitle())
                                        .url("https://coinone.co.kr/" + coinoneNoticeInfo.getGetAbsoluteUrl())
                                        .build()
                        ).collect(Collectors.toList());

                noticeRepository.saveAll(entityList);
            } else {
                log.error("Gdac Notice search ERROR : {}", response.errorBody().byteString().toString());
            }
        } catch (IOException e) {
            log.error("Gdac Notice search IOException : {}", e.getMessage());
        }
    }
}
