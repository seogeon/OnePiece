package com.hanaset.onepiecesanji.client.binance.model;

import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class BinanceNoticeInfo {

    private String action;

    private List<BinanceCategory> breadcrumbs;

    @SerializedName("comment_count")
    private Integer commentCount;

    private Long id;

    private String timestamp;

    private String title;

    private String url;
}
