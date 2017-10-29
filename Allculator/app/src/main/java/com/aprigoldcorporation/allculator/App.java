package com.aprigoldcorporation.allculator;

import android.app.Application;
import android.content.Context;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * Created by musta on 15.08.2017.
 */

public class App extends Application
{

    public static enum GENDER
    {
        MAN,
        WOMAN
    }

    public static String doubleFormat(int decimalNum, double num)
    {
        String form = "#.";
        for (int i = 0; i < decimalNum; i++)
            form += "#";
        DecimalFormat df = new DecimalFormat(form);
        df.setRoundingMode(RoundingMode.CEILING);
        return df.format(num);
    }



}
