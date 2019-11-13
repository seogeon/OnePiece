package com.hanaset.onepiecezoro.service;

import com.hanaset.onepiececommon.entity.ExchangeEntity;
import com.hanaset.onepiececommon.repository.ExchangeRepository;
import com.hanaset.onepiecezoro.model.ExchangeInfo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ZoroExchangeService {

    private final ExchangeRepository exchangeRepository;

    public ZoroExchangeService(ExchangeRepository exchangeRepository) {
        this.exchangeRepository = exchangeRepository;
    }

    public List<ExchangeInfo> getExchangeList() {

        List<ExchangeEntity> exchangeEntities = exchangeRepository.findAll();

        return exchangeEntities.stream().map(exchangeEntity ->
            ExchangeInfo.builder()
                    .code(exchangeEntity.getCode())
                    .exchange(exchangeEntity.getExchange())
                    .oversea(exchangeEntity.getOversea())
                    .url(exchangeEntity.getUrl())
                    .build()
        ).collect(Collectors.toList());

    }

}
