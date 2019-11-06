package com.hanaset.onepiecesanji.client.upbit.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpbitNoticeResponse<T> {

    private String success;

    private T data;

}
