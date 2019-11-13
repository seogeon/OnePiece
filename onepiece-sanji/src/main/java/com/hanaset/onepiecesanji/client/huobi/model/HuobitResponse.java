package com.hanaset.onepiecesanji.client.huobi.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class HuobitResponse {

    private String code;

    private List<HuobiNoticeInfo> data;

    private String message;

    private Boolean success;
}
