package com.hanaset.onepiecesanji.client.gdac.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class GdacNoticeResponse<T> {

    private String code;

    private List<T> data;
}
