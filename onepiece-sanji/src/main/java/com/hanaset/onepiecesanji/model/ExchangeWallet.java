package com.hanaset.onepiecesanji.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ExchangeWallet {

    private String address;

    private String exchange;

    private String blockchain;

    private String crypto;
}
