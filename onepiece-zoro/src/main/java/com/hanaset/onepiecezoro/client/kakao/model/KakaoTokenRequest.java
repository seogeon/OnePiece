package com.hanaset.onepiecezoro.client.kakao.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class KakaoTokenRequest {

    private String grantType;

    private String clientId;

    private String redirectUri;

    private String code;
}
