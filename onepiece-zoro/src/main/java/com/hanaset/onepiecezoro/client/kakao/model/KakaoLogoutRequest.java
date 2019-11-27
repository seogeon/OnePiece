package com.hanaset.onepiecezoro.client.kakao.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class KakaoLogoutRequest {

    private Long userId;
}
