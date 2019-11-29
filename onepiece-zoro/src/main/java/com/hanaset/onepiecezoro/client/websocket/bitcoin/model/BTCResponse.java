package com.hanaset.onepiecezoro.client.websocket.bitcoin.model;

import lombok.Data;

@Data
public class BTCResponse {

    private String op;

    private BTCData x;
}
