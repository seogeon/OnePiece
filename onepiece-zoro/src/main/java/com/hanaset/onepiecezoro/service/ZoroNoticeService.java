package com.hanaset.onepiecezoro.service;

import com.google.common.collect.Lists;
import com.hanaset.onepiececommon.model.NoticeExchange;
import com.hanaset.onepiececommon.model.NoticeKind;
import com.hanaset.onepiecezoro.cache.NoticeCache;
import com.hanaset.onepiecezoro.model.NoticeItem;
import com.hanaset.onepiecezoro.model.NoticePageResponse;
import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ZoroNoticeService {

    private final ZoroCacheLoadService zoroCacheLoadService;

    public ZoroNoticeService(ZoroCacheLoadService zoroCacheLoadService) {
        this.zoroCacheLoadService = zoroCacheLoadService;
    }

    public NoticePageResponse findNotices(NoticeExchange exchange, NoticeKind kind, Pageable pageable) {

        if(NoticeCache.noticeList == null) {
            zoroCacheLoadService.loadNotice();
        }

        List<NoticeItem> noticeItems;

        if(exchange == NoticeExchange.ALL && kind == NoticeKind.ALL) {
            noticeItems = NoticeCache.noticeList;
        } else if(exchange != NoticeExchange.ALL && kind == NoticeKind.ALL) {
            noticeItems = NoticeCache.noticeList.stream().filter(noticeItem -> noticeItem.getExchange() == exchange).collect(Collectors.toList());
        } else if(exchange == NoticeExchange.ALL && kind != NoticeKind.ALL) {
            noticeItems = NoticeCache.noticeList.stream().filter(noticeItem -> noticeItem.getKind() == kind).collect(Collectors.toList());
        } else {
            noticeItems = NoticeCache.noticeList.stream().filter(noticeItem -> noticeItem.getExchange() == exchange && noticeItem.getKind() == kind).collect(Collectors.toList());
        }

        PagedListHolder<NoticeItem> pagedListHolder = new PagedListHolder(noticeItems);
        pagedListHolder.setPage(pageable.getPageSize());
        pagedListHolder.setPageSize(pageable.getPageSize());

        List<MutableSortDefinition> sortDefinitions = Lists.newArrayList();

        final Sort pageableSort = pageable.getSort();
        if (pageableSort != null) {
            Iterator<Sort.Order> iterator = pageableSort.iterator();
            while (iterator.hasNext()) {
                final Sort.Order order = iterator.next();
                MutableSortDefinition sortDefinition = new MutableSortDefinition();
                sortDefinition.setProperty(order.getProperty());
                sortDefinition.setAscending(order.isAscending());
                sortDefinitions.add(sortDefinition);
            }
        }

        sort(pagedListHolder, sortDefinitions);


        NoticePageResponse response = NoticePageResponse.builder()
                .currentPage(Long.valueOf(pageable.getPageNumber()))
                .currentSize(Long.valueOf(pageable.getPageSize()))
                .totalPage(Long.valueOf(pagedListHolder.getPageCount()))
                .totalSize(Long.valueOf(pagedListHolder.getNrOfElements()))
                .noticeItemList(pagedListHolder.getPageList())
                .build();

        return response;
    }

    private static void sort(final PagedListHolder pagedListHolder,
                             final List<MutableSortDefinition> sortDefinitions) {
        for (MutableSortDefinition definition : sortDefinitions) {
            pagedListHolder.setSort(definition);
            pagedListHolder.resort();
        }

    }
}
