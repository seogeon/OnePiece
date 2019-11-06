package com.hanaset.onepiecesanji.client.upbit.model;

import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class UpbitNoticeData {

    @SerializedName("total_count")
    private Long totalCount;

    @SerializedName("total_pages")
    private Long totalPages;

    private List<UpbitNoticeInfo> list;

    @SerializedName("fixed_notices")
    private List<UpbitNoticeInfo> fixedNotices;
}
