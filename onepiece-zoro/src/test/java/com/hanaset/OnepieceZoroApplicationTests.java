package com.hanaset;

import com.hanaset.onepiececommon.model.NoticeExchange;
import com.hanaset.onepiececommon.model.NoticeKind;
import com.hanaset.onepiececommon.repository.NoticeRepository;
import com.hanaset.onepiecezoro.service.ZoroCacheLoadService;
import com.hanaset.onepiecezoro.service.ZoroNoticeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("local")
@SpringBootConfiguration
@SpringBootApplication
@ContextConfiguration(classes = {
        NoticeRepository.class
})
public class OnepieceZoroApplicationTests {

    @Autowired
    private ZoroNoticeService zoroNoticeService;

    @Autowired
    ZoroCacheLoadService zoroCacheLoadService;

    @Test
    public void pageTest() {
        zoroCacheLoadService.loadNotice();
        Sort sort = new Sort(Sort.Direction.DESC, "createdAt");
        Pageable pageable = PageRequest.of(2, 10, sort);
        zoroNoticeService.findNotices(NoticeExchange.BITHUMB, NoticeKind.ALL, pageable);
    }

}
