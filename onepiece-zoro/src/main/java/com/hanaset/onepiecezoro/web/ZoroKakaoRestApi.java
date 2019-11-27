package com.hanaset.onepiecezoro.web;

import com.hanaset.onepiecezoro.client.kakao.model.KakaoLoginRequest;
import com.hanaset.onepiecezoro.client.kakao.model.KakaoLogoutRequest;
import com.hanaset.onepiecezoro.model.KakaoLogin;
import com.hanaset.onepiecezoro.service.ZoroKakaoService;
import com.hanaset.onepiecezoro.web.support.ZoroApiRestSupport;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class ZoroKakaoRestApi extends ZoroApiRestSupport {

    private final ZoroKakaoService zoroKakaoService;

    public ZoroKakaoRestApi(ZoroKakaoService zoroKakaoService) {
        this.zoroKakaoService = zoroKakaoService;
    }

    @GetMapping("login_check")
    public ResponseEntity loginCheck(@RequestParam Long userId) {

        return response(zoroKakaoService.getProfile(userId));
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody KakaoLoginRequest request) {

        KakaoLogin login = zoroKakaoService.login(request);

        HttpHeaders headers = new HttpHeaders();
        headers.add("userId", String.valueOf(login.getId()));
        headers.add("Access-Control-Expose-Headers", "*");

        return responseHeaer(login, headers);
    }


    @PostMapping("/logout")
    public ResponseEntity logout(@RequestBody KakaoLogoutRequest request) {
        zoroKakaoService.logout(request);

        return response("success");
    }
}
