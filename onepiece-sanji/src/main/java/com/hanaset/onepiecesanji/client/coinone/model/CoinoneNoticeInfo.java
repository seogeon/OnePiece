package com.hanaset.onepiecesanji.client.coinone.model;

import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CoinoneNoticeInfo {

    private Integer id;

    @SerializedName("card_category")
    private String cardCategory;

    @SerializedName("is_edited")
    private Boolean isEdited;

    @SerializedName("get_absolute_url")
    private String getAbsoluteUrl;

    @SerializedName("vote_count")
    private Long voteCount;

    @SerializedName("comment_count")
    private Long commonetCount;

    @SerializedName("view_count")
    private Long viewCount;

    @SerializedName("created_by")
    private CoinoneCreateAt createdBy;

    @SerializedName("flagged_cotent")
    private CoinoneFlag flaggedContent;

    @SerializedName("vote_type_by_current_user")
    private String voteTypeByCurrentUser;

    @SerializedName("content_type")
    private Integer contentType;

    private String board;

    @SerializedName("sanitized_content")
    private String santitizedContent;

    private String title;

    private String summary;

    @SerializedName("created_at")
    private String createdAt;

    @SerializedName("updated_at")
    private String updatedAt;

    private String thumbnail;

    @SerializedName("is_visible")
    private Boolean isVisible;

    @SerializedName("visible_type")
    private Integer visibleType;
}
