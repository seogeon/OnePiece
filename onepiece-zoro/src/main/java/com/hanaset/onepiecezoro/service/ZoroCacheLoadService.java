package com.hanaset.onepiecezoro.service;

import com.hanaset.onepiececommon.repository.NoticeRepository;
import org.springframework.stereotype.Service;

@Service
public class ZoroCacheLoadService {

    private final NoticeRepository noticeRepository;

    public ZoroCacheLoadService(NoticeRepository noticeRepository) {
        this.noticeRepository = noticeRepository;
    }


}
