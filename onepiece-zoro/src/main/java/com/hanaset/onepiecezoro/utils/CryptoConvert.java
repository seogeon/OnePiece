package com.hanaset.onepiecezoro.utils;

public class CryptoConvert {

    public static Double convert(String crypto, Long value) {

        Double convert= 1.d;

        switch (crypto) {
            case "BTC":
                convert = 100000000d;
                break;
            case "USTD":
                break;
        }

        return value / convert;
    }
}
