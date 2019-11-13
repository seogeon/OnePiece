package com.hanaset.onepiecesanji.client.coinone.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CoinoneResponse {

    private Long count;

    private String next;

    private String previous;

    private List<CoinoneNoticeInfo> results;
}
