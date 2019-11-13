package com.hanaset.onepiececommon.entity;

import com.hanaset.onepiececommon.model.NoticeExchange;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

@Builder
@Data
@Entity(name = "TB_OP_EXCHANGE")
public class ExchangeEntity {

    @Id
    @Enumerated(EnumType.STRING)
    private NoticeExchange code;

    private String exchange;

    private Boolean oversea;

    private String url;
}
