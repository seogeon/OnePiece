package com.hanaset.onepiecesanji.service;

import com.hanaset.onepiececommon.entity.ExchangeEntity;
import com.hanaset.onepiececommon.entity.NoticeEntity;
import com.hanaset.onepiececommon.model.NoticeExchange;
import com.hanaset.onepiececommon.model.NoticeKind;
import com.hanaset.onepiececommon.repository.NoticeRepository;
import com.hanaset.onepiecesanji.client.binance.SanjiBinanceRestApiClient;
import com.hanaset.onepiecesanji.client.binance.model.BinanceResponse;
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
public class SanjiBinanaceService {

    private final SanjiBinanceRestApiClient sanjiBinanceRestApiClient;
    private final NoticeRepository noticeRepository;

    public SanjiBinanaceService(SanjiBinanceRestApiClient sanjiBinanceRestApiClient,
                                NoticeRepository noticeRepository) {
        this.noticeRepository = noticeRepository;
        this.sanjiBinanceRestApiClient = sanjiBinanceRestApiClient;
    }

    public void searchBinanceEvent() {

        BigDecimal standardId = noticeRepository.getMaxNoticeId(NoticeExchange.BINANCE.getExchange()).orElse(BigDecimal.ZERO);
        System.out.println(standardId.toPlainString());

        try {
            Response<BinanceResponse> response = sanjiBinanceRestApiClient.getNotice(1).execute();

            if (response.isSuccessful()) {

                System.out.println(response.body());
                List<NoticeEntity> entityList = response.body().getActivities().stream()
                        .filter(binanceNoticeInfo -> BigDecimal.valueOf(binanceNoticeInfo.getId()).compareTo(standardId) > 0 && (binanceNoticeInfo.getBreadcrumbs().get(0).getName().equals("New Listings") || binanceNoticeInfo.getBreadcrumbs().get(0).getName().equals("Latest News")))
                        .map(binanceNoticeInfo ->
                                NoticeEntity.builder()
                                        .noticeId(BigDecimal.valueOf(binanceNoticeInfo.getId()))
                                        .exchangeCode(ExchangeEntity.builder().code(NoticeExchange.BINANCE).build())
                                        .kind(NoticeKind.NOTICE)
                                        .createdAt(ZonedDateTime.parse(binanceNoticeInfo.getTimestamp()))
                                        .updatedAt(ZonedDateTime.parse(binanceNoticeInfo.getTimestamp()))
                                        .title(binanceNoticeInfo.getTitle())
                                        .url("https://www.binance.com" + binanceNoticeInfo.getUrl())
                                        .build()
                        ).collect(Collectors.toList());

                noticeRepository.saveAll(entityList);
            } else {
                log.error("Binance Notice search ERROR : {}", response.errorBody().byteString().toString());
            }
        } catch (IOException e) {
            log.error("Binance Notice search IOException : {}", e.getMessage());
        }
    }
}
