package com.hanaset.onepiececommon.entity;

import com.hanaset.onepiececommon.converter.ZonedDateTimeConverter;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity
@Data
@Builder
@Table(name = "TB_OP_EXCHANGE_WALLET")
public class ExchangeWalletEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String exchange;

    private String blockchain;

    private String crypto;

    private String address;

    @Column(name = "reg_dtime", updatable = false)
    @Convert(converter = ZonedDateTimeConverter.class)
    private ZonedDateTime regDtime;

    @Column(name = "upd_dtime")
    @Convert(converter = ZonedDateTimeConverter.class)
    private ZonedDateTime updDtime;

}
