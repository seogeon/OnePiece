package com.hanaset.onepiecesanji.client.whaleAlert.model;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class WhaleTransaction {

    private String blockchain;

    private String symbol;

    @SerializedName("transaction_type")
    private String transcationType;

    private String hash;

    private WhaleAddress from;

    private WhaleAddress to;

    private Long timestamp;

    private Double amount;

    @SerializedName("amount_usd")
    private Double amountUsd;

    @SerializedName("transaction_count")
    private Long transactionCount;

}
