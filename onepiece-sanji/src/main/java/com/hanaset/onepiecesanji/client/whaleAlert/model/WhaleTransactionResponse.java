package com.hanaset.onepiecesanji.client.whaleAlert.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class WhaleTransactionResponse {

    private String result;

    private Long count;

    private String cursor;

    private List<WhaleTransaction> transactions;
}
