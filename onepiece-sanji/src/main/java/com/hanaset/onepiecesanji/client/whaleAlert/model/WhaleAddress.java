package com.hanaset.onepiecesanji.client.whaleAlert.model;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class WhaleAddress {

    private String address;

    @SerializedName("owner_type")
    private String ownerType;

    private String owner;

}
