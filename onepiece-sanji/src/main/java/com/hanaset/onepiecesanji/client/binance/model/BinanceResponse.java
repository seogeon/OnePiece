package com.hanaset.onepiecesanji.client.binance.model;

import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class BinanceResponse {

    private List<BinanceNoticeInfo> activities;

    private Integer count;

    @SerializedName("next_page")
    private String nextPage;

    private Integer page;

    @SerializedName("page_count")
    private Integer pageCount;

    @SerializedName("per_page")
    private Integer perPage;

    @SerializedName("previous_page")
    private String previousPage;
}
