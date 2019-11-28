package com.hanaset.onepiecesanji.client.whaleAlert;

import com.hanaset.onepiecesanji.client.whaleAlert.model.WhaleTransactionResponse;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class SanjiWhaleAlertApiClient {

    private final SanjiWhaleAlertApiService sanjiWhaleAlertApiService;

    public SanjiWhaleAlertApiClient() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(SanjiWhaleAlertConstants.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        this.sanjiWhaleAlertApiService = retrofit.create(SanjiWhaleAlertApiService.class);
    }


    public Call<WhaleTransactionResponse> getTransactions(Long start, Long minValue) {
        return sanjiWhaleAlertApiService.getTransactions(start, minValue, SanjiWhaleAlertConstants.API_KEY);
    }
}
