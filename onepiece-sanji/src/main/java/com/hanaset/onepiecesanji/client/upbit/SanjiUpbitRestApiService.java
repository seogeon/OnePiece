package com.hanaset.onepiecesanji.client.upbit;

import com.hanaset.onepiecesanji.client.upbit.model.UpbitNoticeData;
import com.hanaset.onepiecesanji.client.upbit.model.UpbitNoticeResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SanjiUpbitRestApiService {

    @GET("/api/v1/notices")
    Call<UpbitNoticeResponse<UpbitNoticeData>> getNotices(@Query("thread_name") String threadName);
}
