package com.hanaset.onepiecezoro.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TransactionInfo {

    private String blockchain;

    private String input;

    private String output;

    private String value;

    private String crypto;
}
