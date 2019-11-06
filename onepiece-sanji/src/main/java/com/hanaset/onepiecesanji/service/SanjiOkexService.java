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
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class SanjiOkexService {

    private final SanjiUrlProperties sanjiUrlProperties;
    private final NoticeRepository noticeRepository;

    public SanjiOkexService(SanjiUrlProperties sanjiUrlProperties,
                            NoticeRepository noticeRepository) {
        this.sanjiUrlProperties = sanjiUrlProperties;
        this.noticeRepository = noticeRepository;
    }

    public void searchOkexEvent() {
        try {
            Connection.Response response = Jsoup.connect(sanjiUrlProperties.getOkexEventUrl()).method(Connection.Method.GET).execute();
            Document bithumbDocument = response.parse();

            List<Element> elements = bithumbDocument.select("[class=article-list-link]");

            BigDecimal standardId = noticeRepository.getMaxNoticeId(NoticeExchange.OKEX.getExchange()).orElse(BigDecimal.ZERO);
            System.out.println(standardId.toPlainString());
            List<NoticeEntity> noticeEntities = elements.stream().filter(element -> BigDecimal.valueOf(Long.parseLong(element.attr("href").split("--")[0].replaceAll("[^0-9]", ""))).compareTo(standardId) > 0)
                    .map(element ->
                        NoticeEntity.builder()
                                .noticeId(BigDecimal.valueOf(Long.parseLong(element.attr("href").split("--")[0].replaceAll("[^0-9]", ""))))
                                .exchange(NoticeExchange.OKEX)
                                .title(element.text())
                                .kind(element.text().contains("이벤트") ? NoticeKind.EVENT : NoticeKind.NOTICE)
                                .url("https://support.okex.co.kr/hc/ko/articles/" + element.attr("href").split("--")[0].replaceAll("[^0-9]", ""))
                                .build()
                    ).collect(Collectors.toList());

            //System.out.println(noticeEntities);
            noticeRepository.saveAll(noticeEntities);
        }catch (IOException e) {
            log.error("Okex Jsoup Parser IOException : {}", e.getMessage());
        }
    }
}
