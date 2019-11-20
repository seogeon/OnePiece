package com.hanaset.onepiecesanji.service;

import com.google.common.collect.Lists;
import com.hanaset.onepiececommon.entity.ExchangeEntity;
import com.hanaset.onepiececommon.entity.NoticeEntity;
import com.hanaset.onepiececommon.model.NoticeExchange;
import com.hanaset.onepiececommon.model.NoticeKind;
import com.hanaset.onepiececommon.repository.NoticeRepository;
import com.hanaset.onepiecesanji.properties.SanjiUrlProperties;
import jdk.nashorn.internal.parser.DateParser;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Slf4j
@Service
public class SanjiBitfinexService {

    private final SanjiUrlProperties sanjiUrlProperties;
    private final NoticeRepository noticeRepository;

    public SanjiBitfinexService(SanjiUrlProperties sanjiUrlProperties,
                                NoticeRepository noticeRepository) {
        this.sanjiUrlProperties = sanjiUrlProperties;
        this.noticeRepository = noticeRepository;
    }

    public void searchBitfinexNotice() {
        try{

            BigDecimal standardId = noticeRepository.getMaxNoticeId(NoticeExchange.BITFINEX.getExchange()).orElse(BigDecimal.ZERO);

            System.out.println(standardId);
            Connection.Response response = Jsoup.connect(sanjiUrlProperties.getBitfinexEventUrl()).method(Connection.Method.GET).execute();
            Document bitfinexDocument = response.parse();

            List<Element> noticeElements = bitfinexDocument.select("a[href*=post][class=ajax]");
            List<Element> dateElements = bitfinexDocument.select("[class=smaller]");

            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MMMM dd, yyyy");

            List<NoticeEntity> noticeEntities = Lists.newArrayList();
            for(int i = 0 ; i < noticeElements.size() ; i ++) {

                if(standardId.compareTo(BigDecimal.valueOf(Long.parseLong(noticeElements.get(i).attr("href").replaceAll("[^0-9]", "")))) < 0) {
                    noticeEntities.add(NoticeEntity.builder()
                            .exchangeCode(ExchangeEntity.builder().code(NoticeExchange.BITFINEX).build())
                            .noticeId(BigDecimal.valueOf(Long.parseLong(noticeElements.get(i).attr("href").replaceAll("[^0-9]", ""))))
                            .title(noticeElements.get(i).text())
                            .kind(NoticeKind.NOTICE)
                            .url("https://www.bitfinex.com" + noticeElements.get(i).attr("href"))
                            .createdAt(LocalDate.parse(dateElements.get(i).text(), dateTimeFormatter).atStartOfDay(ZoneId.of("Asia/Seoul")))
                            .updatedAt(LocalDate.parse(dateElements.get(i).text(), dateTimeFormatter).atStartOfDay(ZoneId.of("Asia/Seoul")))
                            .build());
                }
            }

            noticeRepository.saveAll(noticeEntities);
        }catch (IOException e) {
            log.error("Bitfinex Jsoup Parser IOException : {}", e.getMessage());
        }
    }
}
