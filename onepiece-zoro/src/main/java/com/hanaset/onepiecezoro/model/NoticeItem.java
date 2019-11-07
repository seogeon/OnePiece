package com.hanaset.onepiecezoro.model;

import com.hanaset.onepiececommon.model.NoticeExchange;
import com.hanaset.onepiececommon.model.NoticeKind;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Data
@Builder
public class NoticeItem {

    private BigDecimal noticeId;

    private NoticeExchange exchange;

    private NoticeKind kind;

    private String title;

    private String url;

    private ZonedDateTime createdAt;

    private ZonedDateTime updatedAt;
}
