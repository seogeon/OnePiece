package com.hanaset.onepiececommon.model;

public enum  NoticeExchange {
    ALL("ALL"),
    UPBIT("UPBIT"),
    BITHUMB("BITHUMB"),
    GDAC("GDAC"),
    OKEX("OKEX"),
    BITSONIC("BITSONIC"),
    HUOBI("HUOBI"),
    COINONE("COINONE"),
    BINANCE("BINANCE"),
    BITFINEX("BITFINEX");

    String exchange;

    NoticeExchange(String exchange) {
        this.exchange = exchange;
    }

    public String getExchange() {
        return exchange;
    }


}
