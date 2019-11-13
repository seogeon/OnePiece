package com.hanaset.onepiecesanji.client.coinone;

import com.hanaset.onepiecesanji.client.coinone.model.CoinoneResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface SanjiCoinoneRestApiService {

    @Headers({
            "origin: https://coinone.co.kr",
            "referer: https://coinone.co.kr/",
            "sec-fetch-mode: cors",
            "sec-fetch-site: same-site"
    })
    @GET("api/talk/notice/?page=1&searchWord=&searchType=&ordering=-created_at")
    Call<CoinoneResponse> getNotice();
}
