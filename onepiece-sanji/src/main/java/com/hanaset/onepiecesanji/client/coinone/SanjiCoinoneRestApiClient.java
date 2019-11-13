package com.hanaset.onepiecesanji.client.coinone;

import com.hanaset.onepiecesanji.client.coinone.model.CoinoneResponse;
import com.hanaset.onepiecesanji.properties.SanjiUrlProperties;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class SanjiCoinoneRestApiClient {

    private final SanjiUrlProperties sanjiUrlProperties;

    private final SanjiCoinoneRestApiService sanjiCoinoneRestApiService;

    public SanjiCoinoneRestApiClient(SanjiUrlProperties sanjiUrlProperties) {
        this.sanjiUrlProperties = sanjiUrlProperties;

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(sanjiUrlProperties.getCoinoneEventUrl())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        this.sanjiCoinoneRestApiService = retrofit.create(SanjiCoinoneRestApiService.class);
    }

    public Call<CoinoneResponse> getNotice() {
        return sanjiCoinoneRestApiService.getNotice();
    }
}
