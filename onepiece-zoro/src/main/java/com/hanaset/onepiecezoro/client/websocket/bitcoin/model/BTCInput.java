package com.hanaset.onepiecezoro.client.websocket.bitcoin.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class BTCInput {

    private Long sequence;

    @JsonProperty("prev_out")
    private BTCOut prevOut;

    private String script;
}
