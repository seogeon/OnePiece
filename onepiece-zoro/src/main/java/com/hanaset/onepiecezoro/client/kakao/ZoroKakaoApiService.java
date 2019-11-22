package com.hanaset.onepiecezoro.client.kakao;

import com.hanaset.onepiecezoro.client.kakao.model.KakaoTokenResponse;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ZoroKakaoApiService {

    @FormUrlEncoded
    @POST("/oauth/token")
    Call<KakaoTokenResponse> createToken(@Field("grant_type") String grantType,
                                         @Field("client_id") String clientId,
                                         @Field("redirect_uri") String uri,
                                         @Field("code") String code);
}
