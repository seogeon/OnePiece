package com.hanaset.onepiecesanji.client.okex;

import com.hanaset.onepiecesanji.client.okex.model.OkexNoticeInfo;
import retrofit2.Call;
import retrofit2.http.GET;

import java.util.List;

public interface SanjiOkexRestApiService {

    @GET("kr/site/notice-all")
    Call<List<OkexNoticeInfo>> getNotice();
}
