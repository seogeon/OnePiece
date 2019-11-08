package com.hanaset.onepiececommon.entity;

import com.hanaset.onepiececommon.converter.ZonedDateTimeConverter;
import com.hanaset.onepiececommon.model.NoticeExchange;
import com.hanaset.onepiececommon.model.NoticeKind;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Data
@Builder
@Entity
@Table(name = "TB_OP_NOTICE")
public class NoticeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "notice_id")
    private BigDecimal noticeId;

    @Enumerated(EnumType.STRING)
    private NoticeExchange exchange;

    private Boolean oversea;

    @Enumerated(EnumType.STRING)
    private NoticeKind kind;

    private String title;

    private String url;

    @Column(name = "created_at")
    private ZonedDateTime createdAt;

    @Column(name = "updated_at")
    private ZonedDateTime updatedAt;

    @Column(name = "reg_dtime", updatable = false)
    @Convert(converter = ZonedDateTimeConverter.class)
    private ZonedDateTime regDtime;

    @Column(name = "upd_dtime")
    @Convert(converter = ZonedDateTimeConverter.class)
    private ZonedDateTime updDtime;
}
