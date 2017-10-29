package com.aprigoldcorporation.allculator.ui.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.aprigoldcorporation.allculator.App;
import com.aprigoldcorporation.allculator.R;
import com.aprigoldcorporation.allculator.helpers.BaseCalculator;
import com.aprigoldcorporation.allculator.helpers.ShuntingYard;
import com.aprigoldcorporation.allculator.listeners.CalculationListener;

public class Other_SimpleCalculator extends AppCompatActivity implements TextWatcher {

    BaseCalculator sbc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_calculator);

        final TextView simple_input = (TextView) findViewById(R.id.other_simple_calc_txt_input);
        simple_input.addTextChangedListener(this);
        final TextView simple_result = (TextView) findViewById(R.id.other_simple_calc_txt_result);
        simple_result.addTextChangedListener(this);

        sbc = new BaseCalculator(this, new CalculationListener() {
            @Override
            public void onInputUpdated(String updated_input) {
                simple_input.setText(updated_input);
            }

            @Override
            public void onResultUpdated(String updated_result) {
                simple_result.setText(updated_result);
            }
        });

        sbc.reset();
        sbc.clearUi();
    }

    public void simpleClicked(View v){
        switch (v.getTag().toString()){
            case "clear":
                sbc.calculatorClearClicked();
                break;
            case "parenthesis":
                sbc.calculatorParenthesisClicked();
                break;
            case "percent":
                sbc.calculatorPercentClicked();
                break;
            case "operator":
                sbc.calculatorOperatorClicked(v);
                break;
            case "number":
                sbc.calculatorNumberClicked(v);
                break;
            case "sign":
                sbc.calculatorSignClicked();
                break;
            case "dot":
                sbc.calculatorDotClicked();
                break;
            case "equal":
                sbc.calculatorEqualClicked();
                break;
            case "backwards":
                sbc.calculatorBackwardsClicked();
                break;
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}