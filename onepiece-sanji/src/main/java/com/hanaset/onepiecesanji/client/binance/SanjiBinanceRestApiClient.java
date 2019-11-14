package com.hanaset.onepiecesanji.client.binance;

import com.hanaset.onepiecesanji.client.binance.model.BinanceResponse;
import com.hanaset.onepiecesanji.properties.SanjiUrlProperties;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class SanjiBinanceRestApiClient {

    private final SanjiBinanceRestApiService sanjiBinanceRestApiService;
    private final SanjiUrlProperties sanjiUrlProperties;

    public SanjiBinanceRestApiClient(SanjiUrlProperties sanjiUrlProperties) {
        this.sanjiUrlProperties = sanjiUrlProperties;

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(sanjiUrlProperties.getBinanceEventUrl())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        this.sanjiBinanceRestApiService = retrofit.create(SanjiBinanceRestApiService.class);
    }

    public Call<BinanceResponse> getNotice(Integer page) {
        return sanjiBinanceRestApiService.getNotice(page, 5, "en-us");
    }
}
