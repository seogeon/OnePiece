package com.hanaset.onepiecesanji.service;

import com.hanaset.onepiececommon.entity.ExchangeEntity;
import com.hanaset.onepiececommon.entity.NoticeEntity;
import com.hanaset.onepiececommon.model.NoticeExchange;
import com.hanaset.onepiececommon.model.NoticeKind;
import com.hanaset.onepiececommon.repository.NoticeRepository;
import com.hanaset.onepiecesanji.properties.SanjiUrlProperties;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@SuppressWarnings("Duplicates")
public class SanjiBitsonicService {

    private final SanjiUrlProperties sanjiUrlProperties;

    private final NoticeRepository noticeRepository;

    public SanjiBitsonicService(SanjiUrlProperties sanjiUrlProperties,
                                NoticeRepository noticeRepository) {
        this.sanjiUrlProperties = sanjiUrlProperties;
        this.noticeRepository = noticeRepository;
    }

    public void searchBitsonicEvent() {
        try {
            Connection.Response response = Jsoup.connect(sanjiUrlProperties.getBitsonicEventUrl() + "event").method(Connection.Method.GET).execute();
            Document bitsonicDocument = response.parse();
            BigDecimal standardId = noticeRepository.getMaxNoticeIdAndKind(NoticeExchange.BITSONIC.getExchange(), NoticeKind.EVENT.getKind()).orElse(BigDecimal.ZERO);

            System.out.println(standardId.toPlainString());

            List<Element> elements = bitsonicDocument.select("[class=notice-list-link]");

            List<NoticeEntity> noticeEntities = elements.stream().filter(element -> BigDecimal.valueOf(Long.parseLong(element.attr("href").replaceAll("[^0-9]", ""))).compareTo(standardId) > 0)
                    .map(element ->
                            NoticeEntity.builder()
                                    .noticeId(BigDecimal.valueOf(Long.parseLong(element.attr("href").replaceAll("[^0-9]", ""))))
                                    .exchangeCode(ExchangeEntity.builder().code(NoticeExchange.BITSONIC).build())
                                    .kind(NoticeKind.EVENT)
                                    .title(element.children().select("[class=notice-list-link-title]").text())
                                    .url("https://bitsonic.co.kr" + element.attr("href"))
                                    .createdAt(LocalDateTime.parse((element.children().select("[class=notice-list-date]").text() + "T00:00:00")).atZone(ZoneId.of("Asia/Seoul")))
                                    .updatedAt(LocalDateTime.parse((element.children().select("[class=notice-list-date]").text() + "T00:00:00")).atZone(ZoneId.of("Asia/Seoul")))
                                    .build()
                    ).collect(Collectors.toList());

            noticeRepository.saveAll(noticeEntities);
        } catch (IOException e) {
            log.error("Bitsonic Jsoup Parser IOException : {}", e.getMessage());
        }
    }

    public void searchBitsoicNotice() {
        try {
            Connection.Response response = Jsoup.connect(sanjiUrlProperties.getBitsonicEventUrl() + "notice").method(Connection.Method.GET).execute();
            BigDecimal standardId = noticeRepository.getMaxNoticeIdAndKind(NoticeExchange.BITSONIC.getExchange(), NoticeKind.NOTICE.getKind()).orElse(BigDecimal.ZERO);
            Document bitsonicDocument = response.parse();
            System.out.println(standardId.toPlainString());

            List<Element> elements = bitsonicDocument.select("[class=notice-list-link]");
            List<NoticeEntity> noticeEntities = elements.stream().filter(element -> BigDecimal.valueOf(Long.parseLong(element.attr("href").replaceAll("[^0-9]", ""))).compareTo(standardId) > 0)
                    .map(element ->
                            NoticeEntity.builder()
                                    .noticeId(BigDecimal.valueOf(Long.parseLong(element.attr("href").replaceAll("[^0-9]", ""))))
                                    .exchangeCode(ExchangeEntity.builder().code(NoticeExchange.BITSONIC).build())
                                    .kind(NoticeKind.NOTICE)
                                    .title(element.children().select("[class=notice-list-link-title]").text())
                                    .url("https://bitsonic.co.kr" + element.attr("href"))
                                    .createdAt(LocalDateTime.parse((element.children().select("[class=notice-list-date]").text() + "T00:00:00")).atZone(ZoneId.of("Asia/Seoul")))
                                    .updatedAt(LocalDateTime.parse((element.children().select("[class=notice-list-date]").text() + "T00:00:00")).atZone(ZoneId.of("Asia/Seoul")))
                                    .build()
                    ).collect(Collectors.toList());

            noticeRepository.saveAll(noticeEntities);
        } catch (IOException e) {
            log.error("Bitsonic Jsoup Parser IOException : {}", e.getMessage());
        }
    }
}
