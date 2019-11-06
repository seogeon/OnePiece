package com.hanaset.onepiecesanji.client.upbit.model;

import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpbitNoticeInfo {


    @SerializedName("created_at")
    private String createdAt;

    @SerializedName("updated_at")
    private String updatedAt;

    private Integer id;

    private String title;

    @SerializedName("view_count")
    private Long viewCount;
}
