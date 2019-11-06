package com.hanaset.onepiecesanji.client.gdac.model;

import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GdacNoticeInfo {

    private Long id;

    @SerializedName(value = "event_name")
    private String eventName;

    @SerializedName(value = "event_start_time")
    private String eventStartTime;

    @SerializedName(value = "event_end_time")
    private String eventEndTime;

    @SerializedName(value = "img_url")
    private String imgUrl;

    @SerializedName(value = "img_link_url")
    private String imgLinkUrl;

    @SerializedName(value = "is_main")
    private String isMain;

    private Integer sort;
}
