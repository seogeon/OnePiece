package com.hanaset.onepiecesanji.client.gdac;

import com.hanaset.onepiecesanji.client.gdac.model.GdacNoticeInfo;
import com.hanaset.onepiecesanji.client.gdac.model.GdacNoticeResponse;
import com.hanaset.onepiecesanji.properties.SanjiUrlProperties;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class SanjiGdacRestApiClient {

    private final SanjiUrlProperties sanjiUrlProperties;
    private final SanjiGdacRestApiService sanjiGdacRestApiService;

    public SanjiGdacRestApiClient(SanjiUrlProperties sanjiUrlProperties) {

        this.sanjiUrlProperties = sanjiUrlProperties;

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(sanjiUrlProperties.getGdacEventUrl())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        this.sanjiGdacRestApiService = retrofit.create(SanjiGdacRestApiService.class);
    }

    public Call<GdacNoticeResponse<GdacNoticeInfo>> getNotices() {
        return sanjiGdacRestApiService.getNotices();
    }
}
