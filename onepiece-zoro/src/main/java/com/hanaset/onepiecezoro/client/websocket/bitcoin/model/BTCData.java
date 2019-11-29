package com.hanaset.onepiecezoro.client.websocket.bitcoin.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class BTCData {

    @JsonProperty("lock_time")
    private Long lockTime;

    private Long ver;

    private Long size;

    private List<BTCInput> inputs;

    private Long time;

    @JsonProperty("tx_index")
    private Long txIndex;

    @JsonProperty("vin_sz")
    private Long vinSz;

    private String hash;

    @JsonProperty("vout_sz")
    private Long voutSz;

    @JsonProperty("relayed_by")
    private String relayedBy;

    private List<BTCOut> out;
}
