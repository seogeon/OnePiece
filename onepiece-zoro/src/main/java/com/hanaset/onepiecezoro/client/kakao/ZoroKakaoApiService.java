package com.hanaset.onepiecezoro.client.kakao;

import com.hanaset.onepiecezoro.client.kakao.model.KakaoProfileResponse;
import com.hanaset.onepiecezoro.client.kakao.model.KakaoTokenResponse;
import retrofit2.Call;
import retrofit2.http.*;

public interface ZoroKakaoApiService {

//    @FormUrlEncoded
//    @POST("/oauth/token")
//    Call<KakaoTokenResponse> createToken(@Field("grant_type") String grantType,
//                                         @Field("client_id") String clientId,
//                                         @Field("redirect_uri") String uri,
//                                         @Field("code") String code);

    @POST("/v2/user/me")
    Call<KakaoProfileResponse> getProfile(@Header("Authorization") String auth);
}
