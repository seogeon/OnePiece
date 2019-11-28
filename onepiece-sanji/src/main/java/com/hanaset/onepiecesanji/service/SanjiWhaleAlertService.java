package com.hanaset.onepiecesanji.service;

import com.google.common.collect.Lists;
import com.hanaset.onepiececommon.entity.ExchangeWalletEntity;
import com.hanaset.onepiececommon.repository.ExchangeWalletRepository;
import com.hanaset.onepiecesanji.client.whaleAlert.SanjiWhaleAlertApiClient;
import com.hanaset.onepiecesanji.client.whaleAlert.model.WhaleTransaction;
import com.hanaset.onepiecesanji.client.whaleAlert.model.WhaleTransactionResponse;
import com.hanaset.onepiecesanji.model.ExchangeWallet;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import retrofit2.Response;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class SanjiWhaleAlertService {

    private final SanjiWhaleAlertApiClient sanjiWhaleAlertApiClient;
    private final ExchangeWalletRepository exchangeWalletRepository;
    private Map<String, ExchangeWallet> walletMap;

    public SanjiWhaleAlertService(SanjiWhaleAlertApiClient sanjiWhaleAlertApiClient,
                                  ExchangeWalletRepository exchangeWalletRepository) {
        this.sanjiWhaleAlertApiClient = sanjiWhaleAlertApiClient;
        this.exchangeWalletRepository = exchangeWalletRepository;
    }

    @PostConstruct
    public void init() {
        walletMap = exchangeWalletRepository.findAll().stream().map(exchangeWalletEntity ->
                ExchangeWallet.builder()
                        .address(exchangeWalletEntity.getAddress())
                        .exchange(exchangeWalletEntity.getExchange())
                        .blockchain(exchangeWalletEntity.getBlockchain())
                        .crypto(exchangeWalletEntity.getCrypto())
                        .build()
        ).collect(Collectors.toMap(ExchangeWallet::getAddress, exchangeWallet -> exchangeWallet));

        log.info("{}", walletMap);
    }

    public void searchTransactions() {

        Long timestamp = (System.currentTimeMillis() / 1000) - 3000; // 프리티어는 최대 3600초까지 요청 가능

        Long minValue = 500000L; // 프리티어는 최소 금액 500000부터 가능

        try {
            Response<WhaleTransactionResponse> response = sanjiWhaleAlertApiClient.getTransactions(timestamp, minValue).execute();

            if (response.isSuccessful()) {

                List<WhaleTransaction> transactions = response.body().getTransactions().stream().filter(whaleTransaction ->
                        whaleTransaction.getTo().getOwnerType().toUpperCase().equals("EXCHANGE") || whaleTransaction.getFrom().getOwnerType().toUpperCase().equals("EXCHANGE"))
                        .collect(Collectors.toList());

                List<ExchangeWalletEntity> exchangeWalletEntities = Lists.newArrayList();

                transactions.forEach(whaleTransaction -> {


                    if (whaleTransaction.getTo().getOwnerType().toUpperCase().equals("EXCHANGE")) {
                        if (!walletMap.containsKey(whaleTransaction.getTo().getAddress())) {
                            exchangeWalletEntities.add(
                                    ExchangeWalletEntity.builder()
                                    .address(whaleTransaction.getTo().getAddress())
                                    .exchange(whaleTransaction.getTo().getOwner())
                                    .blockchain(whaleTransaction.getBlockchain())
                                    .crypto(whaleTransaction.getSymbol())
                                    .build()
                            );
                            walletMap.put(whaleTransaction.getTo().getAddress(), ExchangeWallet.builder()
                                    .address(whaleTransaction.getTo().getAddress())
                                    .exchange(whaleTransaction.getTo().getOwner())
                                    .blockchain(whaleTransaction.getBlockchain())
                                    .crypto(whaleTransaction.getSymbol())
                                    .build());
                        }
                    }

                    if (whaleTransaction.getFrom().getOwnerType().toUpperCase().equals("EXCHANGE")) {
                        if (!walletMap.containsKey(whaleTransaction.getFrom().getAddress())) {
                            exchangeWalletEntities.add(
                                    ExchangeWalletEntity.builder()
                                            .address(whaleTransaction.getFrom().getAddress())
                                            .exchange(whaleTransaction.getFrom().getOwner())
                                            .blockchain(whaleTransaction.getBlockchain())
                                            .crypto(whaleTransaction.getSymbol())
                                            .build()
                            );
                            walletMap.put(whaleTransaction.getFrom().getAddress(), ExchangeWallet.builder()
                                    .address(whaleTransaction.getFrom().getAddress())
                                    .exchange(whaleTransaction.getFrom().getOwner())
                                    .blockchain(whaleTransaction.getBlockchain())
                                    .crypto(whaleTransaction.getSymbol())
                                    .build());
                        }
                    }

                });

                exchangeWalletRepository.saveAll(exchangeWalletEntities);

            } else {
                log.error("Whale Alert Search Error : {}", response.errorBody().byteString());
            }
        } catch (IOException e) {
            log.error("Whale Alert Search IOException : {}", e.getMessage());
        }
    }
}
