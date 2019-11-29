package com.hanaset.onepiecezoro.client.websocket.bitcoin.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class BTCOut {

    private Boolean spent;

    @JsonProperty("tx_index")
    private Long txIndex;

    private Long type;

    private String addr;

    private Long value;

    private Long n;

    private String script;
}
