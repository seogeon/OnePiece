package com.hanaset.onepiecezoro.service;

import com.hanaset.onepiecezoro.client.kakao.ZoroKakaoApiClient;
import com.hanaset.onepiecezoro.client.kakao.model.KakaoProfileRequest;
import com.hanaset.onepiecezoro.client.kakao.model.KakaoProfileResponse;
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

    public KakaoProfileResponse getProfile(KakaoProfileRequest request) {

        try {

            Response<KakaoProfileResponse> response = zoroKakaoApiClient.getProfile(request).execute();

            if (response.isSuccessful()) {
                log.info("{}",response.body());
            } else {
                log.error("KAKAO GET PROFILE ERROR : {}", response.errorBody().byteString());
                throw new ZoroException("error_code" ,"KAKAO GET PROFILE ERROR : " + response.errorBody().byteString());
            }

            return response.body();

        } catch (IOException e) {
            log.error("KAKAO GET PROFILE IOException : {}", e.getMessage());
            throw new ZoroException("error_code", "KAKAO GET PROFILE IOException");
        }
    }
}
