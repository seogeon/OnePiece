package com.hanaset.onepiecesanji.service;

import com.hanaset.onepiececommon.entity.NoticeEntity;
import com.hanaset.onepiececommon.model.NoticeExchange;
import com.hanaset.onepiececommon.model.NoticeKind;
import com.hanaset.onepiececommon.repository.NoticeRepository;
import com.hanaset.onepiecesanji.client.gdac.SanjiGdacRestApiClient;
import com.hanaset.onepiecesanji.client.gdac.model.GdacNoticeInfo;
import com.hanaset.onepiecesanji.client.gdac.model.GdacNoticeResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import retrofit2.Response;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class SanjiGdacService {

    private final SanjiGdacRestApiClient sanjiGdacRestApiClient;
    private final NoticeRepository noticeRepository;

    public SanjiGdacService(SanjiGdacRestApiClient sanjiGdacRestApiClient,
                            NoticeRepository noticeRepository) {
        this.sanjiGdacRestApiClient = sanjiGdacRestApiClient;
        this.noticeRepository = noticeRepository;
    }

    public void searchGdacEvent() {

        Integer standardId = noticeRepository.getMaxNoticeId(NoticeExchange.GDAC.getExchange()).orElse(0);

        try {
            Response<GdacNoticeResponse<GdacNoticeInfo>> response = sanjiGdacRestApiClient.getNotices().execute();

            if(response.isSuccessful()) {
                List<NoticeEntity> entityList = response.body().getData().stream().filter(gdacNoticeInfo -> Integer.parseInt(gdacNoticeInfo.getId()) > standardId)
                        .map(gdacNoticeInfo ->
                                NoticeEntity.builder()
                                .noticeId(Integer.parseInt(gdacNoticeInfo.getId()))
                                .exchange(NoticeExchange.GDAC)
                                .kind(NoticeKind.EVENT)
                                .createdAt(ZonedDateTime.parse(gdacNoticeInfo.getEventStartTime()))
                                .updatedAt(ZonedDateTime.parse(gdacNoticeInfo.getEventEndTime()))
                                .title(gdacNoticeInfo.getEventName())
                                .url(gdacNoticeInfo.getImgLinkUrl())
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
