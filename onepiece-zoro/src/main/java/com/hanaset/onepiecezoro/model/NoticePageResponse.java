package com.hanaset.onepiecezoro.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NoticePageResponse {

    @JsonProperty("total_size")
    private Long totalSize;

    @JsonProperty("total_page")
    private Long totalPage;

    @JsonProperty("current_size")
    private Long currentSize;

    @JsonProperty("current_page")
    private Long currentPage;

    @JsonProperty("notice_item_list")
    private List<NoticeItem> noticeItemList;

}
