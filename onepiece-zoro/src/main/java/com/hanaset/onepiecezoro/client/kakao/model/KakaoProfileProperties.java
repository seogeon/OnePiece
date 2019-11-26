package com.hanaset.onepiecezoro.client.kakao.model;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class KakaoProfileProperties {

    private String nickname;

    @SerializedName("profile_image")
    private String profileImage;

    @SerializedName("thumbnail_image")
    private String thumbnailImage;
}
