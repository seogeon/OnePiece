package com.hanaset.onepiecesanji.client.huobi;

import com.hanaset.onepiecesanji.client.huobi.model.HuobitResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface SanjiHuobiRestApiService {

    @GET("-/g/open/v1/notice/list?r=534410596")
    Call<HuobitResponse> getNotice(@Header("Accept-language") String lang);
}
