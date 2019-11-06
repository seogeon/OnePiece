package com.hanaset.onepiececommon.model;

public enum  NoticeExchange {
    UPBIT("UPBIT"),
    BITHUMB("BITHUMB"),
    GDAC("GDAC"),
    OKEX("OKEX"),
    BITSONIC("BITSONIC");

    String exchange;

    NoticeExchange(String exchange) {
        this.exchange = exchange;
    }

    public String getExchange() {
        return exchange;
    }


}
