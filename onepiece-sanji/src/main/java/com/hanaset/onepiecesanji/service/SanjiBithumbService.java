package com.hanaset.onepiecesanji.service;

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
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class SanjiBithumbService {

    private final SanjiUrlProperties sanjiUrlProperties;
    private final NoticeRepository noticeRepository;

    public SanjiBithumbService(SanjiUrlProperties sanjiUrlProperties,
                               NoticeRepository noticeRepository) {
        this.sanjiUrlProperties = sanjiUrlProperties;
        this.noticeRepository = noticeRepository;
    }


    public void searchBithumbEvent() {
        try {
            Connection.Response response = Jsoup.connect(sanjiUrlProperties.getBithumbEventUrl() + "/40").method(Connection.Method.GET).execute();
            Document bithumbDocument = response.parse();

            List<Element> elements = bithumbDocument.select("[class=inner-container]");
            Integer standardId = noticeRepository.getMaxNoticeId(NoticeExchange.BITHUMB.getExchange()).orElse(0);

            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss");

            List<NoticeEntity> noticeEntities = elements.stream().filter(element ->
                    Integer.parseInt(element.attributes().get("onclick").replaceAll("[^0-9]", "")) > standardId
            ).map(element ->
                    NoticeEntity.builder()
                            .exchange(NoticeExchange.BITHUMB)
                            .noticeId(Integer.parseInt(element.attributes().get("onclick").replaceAll("[^0-9]", "")))
                            .kind(NoticeKind.EVENT)
                            .title(element.select("[class=block-with-text]").text())
                            .url("https://cafe.bithumb.com/view/board-contents/" + element.attributes().get("onclick").replaceAll("[^0-9]", ""))
                            .createdAt(LocalDateTime.parse(element.text().replace(element.select("[class=block-with-text]").text() + " ", ""), dateTimeFormatter).atZone(ZoneId.of("Asia/Seoul")))
                            .build()
            ).collect(Collectors.toList());

            noticeRepository.saveAll(noticeEntities);
        } catch (IOException e) {
            log.error("Bithumb Jsoup Parser IOException : {}", e.getMessage());
        }
    }

    public void searchBithumbNotice() {
        try {
            Connection.Response response = Jsoup.connect(sanjiUrlProperties.getBithumbEventUrl() + "/43").method(Connection.Method.GET).execute();
            Document bithumbDocument = response.parse();

            List<Element> elements = bithumbDocument.select("[class=col-20 col-md-3]");
            Integer standardId = noticeRepository.getMaxNoticeId(NoticeExchange.BITHUMB.getExchange()).orElse(0);

            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss");

            List<NoticeEntity> noticeEntities = elements.stream().filter(element ->
                    Integer.parseInt(element.attributes().get("onclick").replaceAll("[^0-9]", "")) > standardId
            ).map(element ->
                    NoticeEntity.builder()
                            .exchange(NoticeExchange.BITHUMB)
                            .noticeId(Integer.parseInt(element.attributes().get("onclick").replaceAll("[^0-9]", "")))
                            .kind(element.select("[class=one-line]").text().contains("이벤트") ? NoticeKind.EVENT : NoticeKind.NOTICE)
                            .title(element.select("[class=one-line]").text())
                            .url("https://cafe.bithumb.com/view/board-contents/" + element.attributes().get("onclick").replaceAll("[^0-9]", ""))
                            .createdAt(LocalDateTime.parse(element.select("[class=small-size]").text().length() > 6 ?
                                            element.select("[class=small-size]").text() + " 00:00:00" : ZonedDateTime.now().format(dateTimeFormatter)
                                    , dateTimeFormatter).atZone(ZoneId.of("Asia/Seoul")))
                            .build()
            ).collect(Collectors.toList());

            //System.out.println(noticeEntities);
            noticeRepository.saveAll(noticeEntities);

        } catch (IOException e) {
            log.error("Bithumb Jsoup Parser IOException : {}", e.getMessage());
        }
    }
}