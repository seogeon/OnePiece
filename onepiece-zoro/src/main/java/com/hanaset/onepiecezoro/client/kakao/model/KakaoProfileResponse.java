package com.hanaset.onepiecezoro.client.kakao.model;

import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class KakaoProfileResponse {

    private Long id;

    private KakaoProfileProperties properties;

    @SerializedName("kakao_account")
    private KakaoProfileAccount kakaoAccount;
}
