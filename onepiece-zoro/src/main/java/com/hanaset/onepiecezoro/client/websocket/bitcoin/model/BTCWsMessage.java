package com.hanaset.onepiecezoro.client.websocket.bitcoin.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BTCWsMessage {

    private String op;

    private String addr;
}
