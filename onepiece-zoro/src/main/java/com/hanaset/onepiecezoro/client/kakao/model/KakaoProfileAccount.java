package com.hanaset.onepiecezoro.client.kakao.model;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class KakaoProfileAccount {

    @SerializedName("profile_needs_agreement")
    private Boolean profileNeedsAgreement;

    private KakaoProfile profile;

    @SerializedName("has_email")
    private Boolean hasEmail;

    @SerializedName("email_needs_agreement")
    private Boolean emailNeedsAgreement;

    @SerializedName("is_email_valid")
    private Boolean isEmailValid;

    @SerializedName("is_email_verified")
    private Boolean isEmailVerified;

    private String email;

    @SerializedName("has_age_range")
    private Boolean hasAgeRange;

    @SerializedName("age_range_needs_agreement")
    private Boolean ageRangeNeedsAgreement;

    @SerializedName("age_range")
    private String ageRange;

    @SerializedName("has_birthday")
    private Boolean basBirthday;

    @SerializedName("birthday_needs_agreement")
    private Boolean birthdayNeedsAgreement;

    private String birthday;

    @SerializedName("has_gender")
    private Boolean hasGender;

    @SerializedName("gender_needs_agreement")
    private Boolean genderNeedsAgreement;

    private String gender;

}
