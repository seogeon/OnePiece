package com.hanaset.onepiecezoro.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class KakaoLogin {

    private Long id;

    private String token;

    private String nickname;

    private String email;
}
