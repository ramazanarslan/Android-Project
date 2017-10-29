package com.aprigoldcorporation.allculator.ui.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.aprigoldcorporation.allculator.R;
import com.aprigoldcorporation.allculator.helpers.BaseCalculator;
import com.aprigoldcorporation.allculator.helpers.ShuntingYard;
import com.aprigoldcorporation.allculator.listeners.CalculationListener;

public class Other_AdvancedCalculator extends AppCompatActivity implements TextWatcher {

    BaseCalculator abc;
    View layout_types1, layout_types2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advanced_calculator);

        layout_types1 = findViewById(R.id.advanced_calc_layout_types1);
        layout_types2 = findViewById(R.id.advanced_calc_layout_types2);

        final TextView advanced_input = (TextView) findViewById(R.id.other_advanced_calc_txt_input);
        advanced_input.addTextChangedListener(this);
        final TextView advanced_result = (TextView) findViewById(R.id.other_advanced_calc_txt_result);
        advanced_result.addTextChangedListener(this);

        abc = new BaseCalculator(this, new CalculationListener() {
            @Override
            public void onInputUpdated(String updated_input) {
                advanced_input.setText(updated_input);
            }

            @Override
            public void onResultUpdated(String updated_result) {
                advanced_result.setText(updated_result);
            }
        });

        abc.angle_type = ShuntingYard.ANGLE_TYPE.DEG;
        abc.reset();
        abc.clearUi();
    }

    public void simpleClicked(View v){
        switch (v.getTag().toString()){
            case "clear":
                abc.calculatorClearClicked();
                break;
            case "parenthesis":
                abc.calculatorParenthesisClicked();
                break;
            case "percent":
                abc.calculatorPercentClicked();
                break;
            case "operator":
                abc.calculatorOperatorClicked(v);
                break;
            case "number":
                abc.calculatorNumberClicked(v);
                break;
            case "sign":
                abc.calculatorSignClicked();
                break;
            case "dot":
                abc.calculatorDotClicked();
                break;
            case "equal":
                abc.calculatorEqualClicked();
                break;
            case "backwards":
                abc.calculatorBackwardsClicked();
                break;
        }
    }

    public void changeCalculationGroup(View v){
        if (layout_types1.getVisibility() == View.VISIBLE){
            layout_types1.setVisibility(View.GONE);
            layout_types2.setVisibility(View.VISIBLE);
        }
        else{
            layout_types2.setVisibility(View.GONE);
            layout_types1.setVisibility(View.VISIBLE);
        }
    }

    public void modClicked(View v){
        if (abc.last_entered != BaseCalculator.INPUT_TYPE.NUMBER)
            return;

        abc.input += "≡";
        abc.expression.addLast("≡");
        abc.updateInput();
        abc.last_entered = BaseCalculator.INPUT_TYPE.OPERATOR;
    }

    public void special1RightClicked(View v){
        String operator = ((Button) v).getText().toString();
        if (abc.last_entered == BaseCalculator.INPUT_TYPE.NUMBER || abc.last_entered == BaseCalculator.INPUT_TYPE.PARENTHESIS_CLOSE
                || abc.last_entered == BaseCalculator.INPUT_TYPE.SPECIAL_1_LEFT){
            abc.input += "x" + operator + "(";
            abc.expression.addLast("x");
            abc.expression.addLast(operator);
            abc.expression.add("(");
            abc.openedParenthesis++;
        }
        else {
            abc.input += operator + "(";
            abc.expression.addLast(operator);
            abc.expression.addLast("(");
            abc.openedParenthesis++;
        }

        abc.updateInput();
        abc.last_entered = BaseCalculator.INPUT_TYPE.PARENTHESIS_OPEN;
    }

    public void dividedBy1Clicked(View v){
        if (abc.last_entered == BaseCalculator.INPUT_TYPE.NUMBER || abc.last_entered == BaseCalculator.INPUT_TYPE.PARENTHESIS_CLOSE
                || abc.last_entered == BaseCalculator.INPUT_TYPE.SPECIAL_1_LEFT){
            abc.input += "x1÷";
            abc.expression.addLast("x");
            abc.expression.addLast("1");
            abc.expression.addLast("÷");
        }
        else {
            abc.input += "1÷";
            abc.expression.addLast("1");
            abc.expression.addLast("÷");
        }

        abc.updateInput();
        abc.last_entered = BaseCalculator.INPUT_TYPE.OPERATOR;
    }

    public void exClicked(View v){
        if (abc.last_entered == BaseCalculator.INPUT_TYPE.NUMBER || abc.last_entered == BaseCalculator.INPUT_TYPE.PARENTHESIS_CLOSE
                || abc.last_entered == BaseCalculator.INPUT_TYPE.SPECIAL_1_LEFT){
            abc.input += "xe^(";
            abc.expression.addLast("x");
            abc.expression.addLast("e");
            abc.expression.addLast("^");
            abc.expression.addLast("(");
            abc.openedParenthesis++;
        }
        else {
            abc.input += "e^(";
            abc.expression.addLast("e");
            abc.expression.addLast("^");
            abc.expression.addLast("(");
            abc.openedParenthesis++;
        }

        abc.updateInput();
        abc.last_entered = BaseCalculator.INPUT_TYPE.PARENTHESIS_OPEN;
    }

    public void x2Clicked(View v){
        if(abc.last_entered != BaseCalculator.INPUT_TYPE.NUMBER)
            return;

        abc.input += "^2";
        abc.expression.addLast("^");
        abc.expression.addLast("2");
        abc.updateInput();
        abc.last_entered = BaseCalculator.INPUT_TYPE.NUMBER;
    }

    public void powClicked(View v){
        if (abc.last_entered != BaseCalculator.INPUT_TYPE.NUMBER)
            return;

        abc.input += "^";
        abc.expression.addLast("^");
        abc.updateInput();
        abc.last_entered = BaseCalculator.INPUT_TYPE.OPERATOR;
    }

    public void pow2Clicked(View v){
        if (abc.last_entered == BaseCalculator.INPUT_TYPE.NUMBER || abc.last_entered == BaseCalculator.INPUT_TYPE.PARENTHESIS_CLOSE
                || abc.last_entered == BaseCalculator.INPUT_TYPE.SPECIAL_1_LEFT){
            abc.input += "x2^";
            abc.expression.addLast("x");
            abc.expression.addLast("2");
            abc.expression.addLast("^");
        }
        else {
            abc.input += "2^";
            abc.expression.addLast("2");
            abc.expression.addLast("^");
        }
        abc.updateInput();
        abc.last_entered = BaseCalculator.INPUT_TYPE.OPERATOR;
    }

    public void x3Clicked(View v){
        if(abc.last_entered != BaseCalculator.INPUT_TYPE.NUMBER)
            return;

        abc.input += "^3";
        abc.expression.addLast("^");
        abc.expression.addLast("3");
        abc.updateInput();
        abc.last_entered = BaseCalculator.INPUT_TYPE.NUMBER;
    }

    public void absClicked(View v) {
        if (abc.last_entered == BaseCalculator.INPUT_TYPE.NUMBER || abc.last_entered == BaseCalculator.INPUT_TYPE.PARENTHESIS_CLOSE
                || abc.last_entered == BaseCalculator.INPUT_TYPE.SPECIAL_1_LEFT){
            abc.input += "xabs(";
            abc.expression.addLast("x");
            abc.expression.addLast("abs");
            abc.expression.addLast("(");
            abc.openedParenthesis++;
        }
        else {
            abc.input += "abs(";
            abc.expression.addLast("abs");
            abc.expression.addLast("(");
            abc.openedParenthesis++;
        }

        abc.updateInput();
        abc.last_entered = BaseCalculator.INPUT_TYPE.PARENTHESIS_OPEN;
    }

    public void factorialClicked(View v){
        if (abc.last_entered != BaseCalculator.INPUT_TYPE.NUMBER)
            return;

        abc.input += "!";
        abc.expression.addLast("!");
        abc.updateInput();
        abc.last_entered = BaseCalculator.INPUT_TYPE.SPECIAL_1_LEFT;
    }

    public void specialNumberClicked(View v){
        String number = ((Button) v).getText().toString();

        if (abc.last_entered == BaseCalculator.INPUT_TYPE.NUMBER || abc.last_entered == BaseCalculator.INPUT_TYPE.PARENTHESIS_CLOSE
                || abc.last_entered == BaseCalculator.INPUT_TYPE.SPECIAL_1_LEFT || abc.last_entered == BaseCalculator.INPUT_TYPE.SPECIAL_NUMBER){
            abc.input += "x" + number;
            abc.expression.addLast("x");
            if (abc.isNextNumberNegative)
                abc.expression.addLast("-" + number);
            else
                abc.expression.addLast(number);
        }
        else {
            abc.input += number;
            if (abc.isNextNumberNegative)
                abc.expression.addLast("-" + number);
            else
                abc.expression.addLast(number);
        }

        abc.isNextNumberNegative = false;
        abc.last_entered = BaseCalculator.INPUT_TYPE.SPECIAL_NUMBER;
        abc.updateInput();
    }

    public void DEGRADClicked(View v){
        Button btn_text = ((Button) v);
        if (btn_text.getText().equals("Deg")){
            btn_text.setText("Rad");
            abc.angle_type = ShuntingYard.ANGLE_TYPE.RAD;
        }
        else{
            btn_text.setText("Deg");
            abc.angle_type = ShuntingYard.ANGLE_TYPE.DEG;
        }
    }

    public void trigonoClicked(View v) {
        String operator = ((Button) v).getText().toString();
        if (abc.last_entered == BaseCalculator.INPUT_TYPE.NUMBER || abc.last_entered == BaseCalculator.INPUT_TYPE.PARENTHESIS_CLOSE
                || abc.last_entered == BaseCalculator.INPUT_TYPE.SPECIAL_1_LEFT){
            abc.input += "x" + operator + "(";
            abc.expression.addLast("x");
            abc.expression.addLast(operator);
            abc.expression.addLast("(");
            abc.openedParenthesis++;
        }
        else {
            abc.input += operator + "(";
            abc.expression.addLast(operator);
            abc.expression.addLast("(");
            abc.openedParenthesis++;
        }

        abc.updateInput();
        abc.last_entered = BaseCalculator.INPUT_TYPE.PARENTHESIS_OPEN;
    }

    public void arcTrigonoClicked(View v){
        String operator = ((Button) v).getText().toString();
        operator = determineArcOperator(operator);
        if (abc.last_entered == BaseCalculator.INPUT_TYPE.NUMBER || abc.last_entered == BaseCalculator.INPUT_TYPE.PARENTHESIS_CLOSE
                || abc.last_entered == BaseCalculator.INPUT_TYPE.SPECIAL_1_LEFT){
            abc.input += "x" + operator + "(";
            abc.expression.addLast("x");
            abc.expression.addLast(operator);
            abc.expression.addLast("(");
            abc.openedParenthesis++;
        }
        else {
            abc.input += operator + "(";
            abc.expression.addLast(operator);
            abc.expression.addLast("(");
            abc.openedParenthesis++;
        }

        abc.updateInput();
        abc.last_entered = BaseCalculator.INPUT_TYPE.PARENTHESIS_OPEN;
    }

    private String determineArcOperator(String operator) {
        switch (operator){
            case "sinˉ¹":
                operator = "arcsin";
                break;
            case "cosˉ¹":
                operator = "arccos";
                break;
            case "tanˉ¹":
                operator = "arctan";
                break;
            case "sinhˉ¹":
                operator = "asinh";
                break;
            case "coshˉ¹":
                operator = "acosh";
                break;
            case "tanhˉ¹":
                operator = "atanh";
                break;
        }
        return operator;
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
