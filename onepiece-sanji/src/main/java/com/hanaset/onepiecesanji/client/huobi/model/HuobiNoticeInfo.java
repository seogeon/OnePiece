package com.hanaset.onepiecesanji.client.huobi.model;

import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Builder
public class HuobiNoticeInfo {

    @SerializedName("create_time")
    private Long createTime;

    @SerializedName("is_top")
    private Integer isTop;

    private String title;

    private String url;
}
