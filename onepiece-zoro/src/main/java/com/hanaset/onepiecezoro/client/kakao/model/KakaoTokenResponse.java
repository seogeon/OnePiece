package com.hanaset.onepiecezoro.client.kakao.model;

import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class KakaoTokenResponse {

    @SerializedName("access_token")
    private String accessToken;

    @SerializedName("token_type")
    private String tokenType;

    @SerializedName("refresh_token")
    private String refreshToken;

    @SerializedName("expires_in")
    private Integer expiresIn;

    private String scope;
}
