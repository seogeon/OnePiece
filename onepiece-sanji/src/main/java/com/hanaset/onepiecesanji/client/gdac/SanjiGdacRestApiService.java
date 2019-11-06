package com.hanaset.onepiecesanji.client.gdac;

import com.hanaset.onepiecesanji.client.gdac.model.GdacNoticeInfo;
import com.hanaset.onepiecesanji.client.gdac.model.GdacNoticeResponse;
import retrofit2.Call;
import retrofit2.http.GET;

public interface SanjiGdacRestApiService {

    @GET("/v1/event/marketing/current")
    Call<GdacNoticeResponse<GdacNoticeInfo>> getNotices();
}
