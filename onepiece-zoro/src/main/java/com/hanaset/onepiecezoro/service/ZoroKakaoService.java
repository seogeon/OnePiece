package com.hanaset.onepiecezoro.service;

import com.hanaset.onepiececommon.entity.UserEntity;
import com.hanaset.onepiececommon.repository.UserRepository;
import com.hanaset.onepiecezoro.cache.LoginUserCache;
import com.hanaset.onepiecezoro.client.kakao.ZoroKakaoApiClient;
import com.hanaset.onepiecezoro.client.kakao.model.KakaoLoginRequest;
import com.hanaset.onepiecezoro.client.kakao.model.KakaoLogoutRequest;
import com.hanaset.onepiecezoro.client.kakao.model.KakaoProfileResponse;
import com.hanaset.onepiecezoro.model.KakaoLogin;
import com.hanaset.onepiecezoro.web.exception.ZoroException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import retrofit2.Response;

import java.io.IOException;

@Slf4j
@Service
public class ZoroKakaoService {

    private final ZoroKakaoApiClient zoroKakaoApiClient;
    private final UserRepository userRepository;

    public ZoroKakaoService(ZoroKakaoApiClient zoroKakaoApiClient,
                            UserRepository userRepository) {
        this.zoroKakaoApiClient = zoroKakaoApiClient;
        this.userRepository = userRepository;
    }

    public KakaoLogin getProfile(Long userId) {

        if(!LoginUserCache.currentUser.containsKey(userId)) {
            throw new ZoroException("error_code" ,"KAKAO NOT LOGIN");
        }

        return LoginUserCache.currentUser.get(userId);

    }

    public KakaoLogin login(KakaoLoginRequest request) {

        try {
            Response<KakaoProfileResponse> response = zoroKakaoApiClient.getProfile(request).execute();

            if (!response.isSuccessful()) {
                log.error("KAKAO LOGIN ERROR : {}", response.errorBody().byteString());
                throw new ZoroException("error_code" ,"KAKAO LOGIN ERROR : " + response.errorBody().byteString());
            }

            KakaoLogin userData = KakaoLogin.builder()
                    .id(response.body().getId())
                    .nickname(response.body().getProperties().getNickname())
                    .email(response.body().getKakaoAccount().getEmail())
                    .token(request.getToken())
                    .build();

            LoginUserCache.currentUser.put(userData.getId(), userData);

            if(userRepository.findByUserId(userData.getId()) == null) {

                userRepository.save(UserEntity.builder()
                        .userId(userData.getId())
                        .userName(userData.getNickname())
                        .email(userData.getEmail())
                        .build());
            }

            return userData;

        } catch (IOException e) {
            log.error("KAKAO LOGIN IOException : {}", e.getMessage());
            throw new ZoroException("error_code", "KAKAO LOGIN IOException");
        }
    }

    public void logout(KakaoLogoutRequest request) {

        if(LoginUserCache.currentUser.containsKey(request.getUserId())) {
            LoginUserCache.currentUser.remove(request.getUserId());
        }
    }
}
