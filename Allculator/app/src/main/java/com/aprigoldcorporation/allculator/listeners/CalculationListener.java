package com.aprigoldcorporation.allculator.listeners;

/**
 * Created by musta on 4.09.2017.
 */

public interface CalculationListener {
    void onInputUpdated(String updated_input);
    void onResultUpdated(String updated_result);
}
