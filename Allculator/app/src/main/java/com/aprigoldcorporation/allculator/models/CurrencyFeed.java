package com.aprigoldcorporation.allculator.models;

/**
 * Created by musta on 2.09.2017.
 */

public class CurrencyFeed {
    String currencyCode;
    String currencyName;
    String currencyRate;

    public String getCurrencyCode() {
        return currencyCode;
    }
    public String getCurrencyName() {
        return currencyName;
    }
    public String getCurrencyRate() {
        return currencyRate;
    }

    public CurrencyFeed(String currencyCode, String currencyName, String currencyRate) {
        this.currencyCode = currencyCode;
        this.currencyName = currencyName;
        this.currencyRate = currencyRate;
    }
}
