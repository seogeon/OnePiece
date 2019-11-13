package com.hanaset.onepiecezoro.model;

import com.hanaset.onepiececommon.model.NoticeExchange;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ExchangeInfo {

    private NoticeExchange code;

    private String exchange;

    private Boolean oversea;

    private String url;
}
