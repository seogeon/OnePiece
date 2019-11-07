package com.hanaset.onepiecezoro.cache;

import com.hanaset.onepiecezoro.model.NoticeItem;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class NoticeCache {

    public static List<NoticeItem> noticeList;
}
