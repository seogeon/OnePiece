package com.hanaset.onepiecezoro.cache;

import com.google.common.collect.Maps;
import com.hanaset.onepiecezoro.model.KakaoLogin;
import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
@Builder
public class LoginUserCache {

    public static Map<Long, KakaoLogin> currentUser = Maps.newHashMap();
}
