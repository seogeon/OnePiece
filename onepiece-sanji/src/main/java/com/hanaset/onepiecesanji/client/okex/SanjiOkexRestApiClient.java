package com.hanaset.onepiecesanji.client.okex;

import com.hanaset.onepiecesanji.client.okex.model.OkexNoticeInfo;
import com.hanaset.onepiecesanji.properties.SanjiUrlProperties;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.List;

public class SanjiOkexRestApiClient {

    private final SanjiOkexRestApiService sanjiOkexRestApiService;
    private final SanjiUrlProperties sanjiUrlProperties;

    public SanjiOkexRestApiClient(SanjiUrlProperties sanjiUrlProperties) {
        this.sanjiUrlProperties = sanjiUrlProperties;

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(sanjiUrlProperties.getOkexEventUrl())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        this.sanjiOkexRestApiService = retrofit.create(SanjiOkexRestApiService.class);
    }

    public Call<List<OkexNoticeInfo>> getNotice() {
        return sanjiOkexRestApiService.getNotice();
    }
}
