package com.aprigoldcorporation.allculator.models;

import android.widget.TextView;

/**
 * Created by musta on 2.09.2017.
 */

public class CurrencyAdapterModel {
    String txt_first, txt_second, txt_num1, txt_num2;

    public CurrencyAdapterModel() {
    }

    public CurrencyAdapterModel(String txt_first, String txt_second, String txt_num1, String txt_num2) {
        this.txt_first = txt_first;
        this.txt_second = txt_second;
        this.txt_num1 = txt_num1;
        this.txt_num2 = txt_num2;
    }

    public String getTxt_first() {
        return txt_first;
    }

    public String getTxt_second() {
        return txt_second;
    }

    public String getTxt_num1() {
        return txt_num1;
    }

    public String getTxt_num2() {
        return txt_num2;
    }

    public void setTxt_first(String txt_first) {
        this.txt_first = txt_first;
    }

    public void setTxt_second(String txt_second) {
        this.txt_second = txt_second;
    }

    public void setTxt_num1(String txt_num1) {
        this.txt_num1 = txt_num1;
    }

    public void setTxt_num2(String txt_num2) {
        this.txt_num2 = txt_num2;
    }
}
