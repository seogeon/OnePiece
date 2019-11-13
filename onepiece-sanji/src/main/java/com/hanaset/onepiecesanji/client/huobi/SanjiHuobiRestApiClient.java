package com.hanaset.onepiecesanji.client.huobi;

import com.hanaset.onepiecesanji.client.gdac.SanjiGdacRestApiService;
import com.hanaset.onepiecesanji.client.huobi.model.HuobitResponse;
import com.hanaset.onepiecesanji.properties.SanjiUrlProperties;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class SanjiHuobiRestApiClient {

    private final SanjiUrlProperties sanjiUrlProperties;
    private final SanjiHuobiRestApiService sanjiHuobiRestApiService;

    public SanjiHuobiRestApiClient(SanjiUrlProperties sanjiUrlProperties) {
        this.sanjiUrlProperties = sanjiUrlProperties;

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(sanjiUrlProperties.getHuobiEventUrl())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        this.sanjiHuobiRestApiService = retrofit.create(SanjiHuobiRestApiService.class);
    }

    public Call<HuobitResponse> getNotice() {
        return sanjiHuobiRestApiService.getNotice();
    }
}
