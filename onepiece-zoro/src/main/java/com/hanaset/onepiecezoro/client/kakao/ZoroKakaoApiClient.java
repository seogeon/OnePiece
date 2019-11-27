package com.hanaset.onepiecezoro.client.kakao;

import com.hanaset.onepiecezoro.client.kakao.model.KakaoLoginRequest;
import com.hanaset.onepiecezoro.client.kakao.model.KakaoProfileResponse;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import org.springframework.stereotype.Component;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Component
public class ZoroKakaoApiClient {

    private final ZoroKakaoApiService zoroKakaoApiService;

    public ZoroKakaoApiClient() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(KakaoContants.API)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        this.zoroKakaoApiService = retrofit.create(ZoroKakaoApiService.class);
    }

//    public Call<KakaoTokenResponse> createToken(KakaoTokenRequest request) {
//        return zoroKakaoApiService.createToken(request.getGrantType(), request.getClientId(), request.getRedirectUri(), request.getCode());
//    }

    public Call<KakaoProfileResponse> getProfile(KakaoLoginRequest request) {

        String authToken = "Bearer " + request.getToken();
        return zoroKakaoApiService.getProfile(authToken);
    }
}
