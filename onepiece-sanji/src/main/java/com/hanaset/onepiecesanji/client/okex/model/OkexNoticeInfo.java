package com.hanaset.onepiecesanji.client.okex.model;

import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;

import java.time.ZonedDateTime;

@Data
@Builder
public class OkexNoticeInfo {

    private Integer exposureNo;

    private String title;

    @SerializedName("link")
    private String url;

    private String starTime;

    private String endTime;

    private String urgency;

    @SerializedName("create_time")
    private String createTime;

    @SerializedName("update_time")
    private String updateTime;
}
