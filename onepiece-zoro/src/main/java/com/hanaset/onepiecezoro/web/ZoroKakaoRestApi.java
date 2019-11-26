package com.hanaset.onepiecezoro.web;

import com.hanaset.onepiecezoro.client.kakao.model.KakaoProfileRequest;
import com.hanaset.onepiecezoro.service.ZoroKakaoService;
import com.hanaset.onepiecezoro.web.support.ZoroApiRestSupport;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class ZoroKakaoRestApi extends ZoroApiRestSupport {

    private final ZoroKakaoService zoroKakaoService;

    public ZoroKakaoRestApi(ZoroKakaoService zoroKakaoService) {
        this.zoroKakaoService = zoroKakaoService;
    }

    @PostMapping
    public ResponseEntity loginProcess(@RequestBody KakaoProfileRequest request) {

        HttpHeaders headers = new HttpHeaders();
        headers.add("token", request.getToken());
        headers.add("Access-Control-Expose-Headers", "*");

        return responseHeaer(zoroKakaoService.getProfile(request), headers);
    }
}
