package com.hanaset.onepiecesanji.client.coinone.model;

import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CoinoneCreateAt {

    private String uuid;

    @SerializedName("trading_level")
    private Integer tradingLevel;

    private String nickname;

    @SerializedName("comment_count")
    private Integer commentCount;

    @SerializedName("thread_count")
    private Integer threadCount;

    @SerializedName("vote_count")
    private Integer voteCount;

    private Integer level;

    private String signature;

    @SerializedName("user_type")
    private String userType;

    @SerializedName("is_blocked")
    private Boolean isBlocked;
}
