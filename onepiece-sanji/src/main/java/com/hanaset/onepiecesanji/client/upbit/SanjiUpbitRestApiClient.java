package com.hanaset.onepiecesanji.client.upbit;

import com.hanaset.onepiecesanji.client.upbit.model.UpbitNoticeData;
import com.hanaset.onepiecesanji.client.upbit.model.UpbitNoticeResponse;
import com.hanaset.onepiecesanji.properties.SanjiUrlProperties;
import lombok.extern.slf4j.Slf4j;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Slf4j
public class SanjiUpbitRestApiClient {

    private final SanjiUrlProperties sanjiUrlProperties;
    private final SanjiUpbitRestApiService sanjiUpbitRestApiService;

    public SanjiUpbitRestApiClient(SanjiUrlProperties sanjiUrlProperties) {

        this.sanjiUrlProperties = sanjiUrlProperties;

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(sanjiUrlProperties.getUpbitEventUrl())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        this.sanjiUpbitRestApiService = retrofit.create(SanjiUpbitRestApiService.class);
    }

    public Call<UpbitNoticeResponse<UpbitNoticeData>> getNotices(String threadName) {
        return sanjiUpbitRestApiService.getNotices(threadName);
    }
}
