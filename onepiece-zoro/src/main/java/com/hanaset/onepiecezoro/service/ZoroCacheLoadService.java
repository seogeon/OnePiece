package com.hanaset.onepiecezoro.service;

import com.hanaset.onepiececommon.entity.NoticeEntity;
import com.hanaset.onepiececommon.repository.NoticeRepository;
import com.hanaset.onepiecezoro.cache.NoticeCache;
import com.hanaset.onepiecezoro.model.NoticeItem;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ZoroCacheLoadService {

    private final NoticeRepository noticeRepository;

    public ZoroCacheLoadService(NoticeRepository noticeRepository) {
        this.noticeRepository = noticeRepository;
    }

    public void loadNotice() {

        List<NoticeEntity> entityList = noticeRepository.findAll();

        NoticeCache.noticeList = entityList.stream().map(noticeEntity ->
                NoticeItem.builder()
                        .noticeId(noticeEntity.getNoticeId())
                        .exchange(noticeEntity.getExchange())
                        .kind(noticeEntity.getKind())
                        .createdAt(noticeEntity.getCreatedAt())
                        .updatedAt(noticeEntity.getUpdatedAt())
                        .title(noticeEntity.getTitle())
                        .url(noticeEntity.getUrl())
                        .build()
        ).collect(Collectors.toList());

        log.info("cache Load :{}", entityList);

    }
}
