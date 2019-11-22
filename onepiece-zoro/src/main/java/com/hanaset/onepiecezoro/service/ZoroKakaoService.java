package com.hanaset.onepiecezoro.service;

import com.hanaset.onepiecezoro.client.kakao.KakaoContants;
import com.hanaset.onepiecezoro.client.kakao.ZoroKakaoApiClient;
import com.hanaset.onepiecezoro.client.kakao.model.KakaoTokenRequest;
import com.hanaset.onepiecezoro.client.kakao.model.KakaoTokenResponse;
import com.hanaset.onepiecezoro.web.exception.ZoroException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import retrofit2.Response;

import java.io.IOException;

@Slf4j
@Service
public class ZoroKakaoService {

    private final ZoroKakaoApiClient zoroKakaoApiClient;

    public ZoroKakaoService(ZoroKakaoApiClient zoroKakaoApiClient) {
        this.zoroKakaoApiClient = zoroKakaoApiClient;
    }

    public KakaoTokenResponse createToken(String token) {

        KakaoTokenRequest request = KakaoTokenRequest.builder()
                .clientId(KakaoContants.REST_API_KEY)
                .grantType("authorization_code")
                .redirectUri(KakaoContants.LOCAL_REDIERCT_URI)
                .code(token)
                .build();

        log.info(token);
        try {
            Response<KakaoTokenResponse> response = zoroKakaoApiClient.createToken(request).execute();

            if (response.isSuccessful()) {
                log.info("{}",response.body());
            } else {
                log.error("KAKAO CREATE TOKEN ERROR : {}", response.errorBody().byteString());
                throw new ZoroException("error_code" ,"KAKAO POST TOKEN ERROR : " + response.errorBody().byteString());
            }

            return response.body();

        } catch (IOException e) {
            log.error("KAKAO CREATE TOKEN IOException : {}", e.getMessage());
            throw new ZoroException("error_code", "KAKAO POST TOKEN IOException");
        }
    }
}
