package com.hanaset.onepiecezoro.web;

import com.hanaset.onepiecezoro.service.ZoroKakaoService;
import com.hanaset.onepiecezoro.web.support.ZoroApiRestSupport;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/oauth")
public class ZoroKakaoRestApi extends ZoroApiRestSupport {

    private final ZoroKakaoService zoroKakaoService;

    public ZoroKakaoRestApi(ZoroKakaoService zoroKakaoService) {
        this.zoroKakaoService = zoroKakaoService;
    }

    @GetMapping
    public ResponseEntity loginProcess(@RequestParam("code") String code) {

        return response(zoroKakaoService.createToken(code));
    }
}
