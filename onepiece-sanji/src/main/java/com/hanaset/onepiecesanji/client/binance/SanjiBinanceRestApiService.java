package com.hanaset.onepiecesanji.client.binance;

import com.hanaset.onepiecesanji.client.binance.model.BinanceResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SanjiBinanceRestApiService {

    @GET("en/support/api/recent-activities")
    Call<BinanceResponse> getNotice(@Query("page") Integer page,
                                    @Query("per_page") Integer per_page,
                                    @Query("locale") String locale);
}
