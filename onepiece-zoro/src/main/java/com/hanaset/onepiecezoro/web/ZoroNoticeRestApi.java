package com.hanaset.onepiecezoro.web;

import com.hanaset.onepiececommon.model.NoticeExchange;
import com.hanaset.onepiececommon.model.NoticeKind;
import com.hanaset.onepiecezoro.service.ZoroExchangeService;
import com.hanaset.onepiecezoro.service.ZoroNoticeService;
import com.hanaset.onepiecezoro.web.support.ZoroApiRestSupport;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notice")
public class ZoroNoticeRestApi extends ZoroApiRestSupport {

    private final ZoroNoticeService zoroNoticeService;
    private final ZoroExchangeService zoroExchangeService;

    public ZoroNoticeRestApi(ZoroNoticeService zoroNoticeService,
                             ZoroExchangeService zoroExchangeService) {
        this.zoroNoticeService = zoroNoticeService;
        this.zoroExchangeService = zoroExchangeService;
    }

    @GetMapping("/{exchange}")
    public ResponseEntity findNotice(@PathVariable NoticeExchange exchange, @RequestParam NoticeKind kind, @RequestParam String keyword,
                                     @PageableDefault(size = 10)
                                     @SortDefault.SortDefaults({@SortDefault(sort = "updatedAt", direction = Sort.Direction.DESC)}) Pageable pageable) {
        return response(zoroNoticeService.findNotices(exchange, kind, keyword, pageable));
    }

    @GetMapping
    public ResponseEntity findNoticeToOverea(@RequestParam Integer oversea,
                                             @PageableDefault(size = 5)
                                             @SortDefault.SortDefaults({@SortDefault(sort = "updatedAt", direction = Sort.Direction.DESC)}) Pageable pageable) {
        return response(zoroNoticeService.findNoticesToOversea(oversea, pageable));
    }

    @GetMapping("/exchange_list")
    public ResponseEntity findExchangeList() {
        return response(zoroExchangeService.getExchangeList());
    }
}

