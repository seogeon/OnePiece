package com.hanaset.onepiecezoro.client.kakao;

import com.hanaset.onepiecezoro.client.kakao.model.KakaoTokenRequest;
import com.hanaset.onepiecezoro.client.kakao.model.KakaoTokenResponse;
import org.springframework.stereotype.Component;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Component
public class ZoroKakaoApiClient {

    private final ZoroKakaoApiService zoroKakaoApiService;

    public ZoroKakaoApiClient() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(KakaoContants.API)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        this.zoroKakaoApiService = retrofit.create(ZoroKakaoApiService.class);
    }

    public Call<KakaoTokenResponse> createToken(KakaoTokenRequest request) {
        return zoroKakaoApiService.createToken(request.getGrantType(), request.getClientId(), request.getRedirectUri(), request.getCode());
    }
}
