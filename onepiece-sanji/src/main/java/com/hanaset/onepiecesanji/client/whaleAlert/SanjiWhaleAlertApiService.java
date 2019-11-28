package com.hanaset.onepiecesanji.client.whaleAlert;

import com.hanaset.onepiecesanji.client.whaleAlert.model.WhaleTransactionResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SanjiWhaleAlertApiService {

    @GET("/v1/transactions")
    Call<WhaleTransactionResponse> getTransactions(@Query("start") Long start, @Query("min_value") Long minValue, @Query("api_key") String apiKey);
}
