package com.aprigoldcorporation.allculator.helpers;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.aprigoldcorporation.allculator.listeners.CalculationListener;
import com.aprigoldcorporation.allculator.util.Utils;

import java.util.LinkedList;

/**
 * Created by musta on 23.08.2017.
 */

public class BaseCalculator {

    private Context mContext;
    CalculationListener listener;

    public enum INPUT_TYPE{
        NULL,
        NUMBER,
        SPECIAL_NUMBER,
        SIGN,
        OPERATOR,
        PARENTHESIS_OPEN,
        PARENTHESIS_CLOSE,
        SPECIAL_1_LEFT,
        SPECIAL_1_RIGHT
    }

    public String input;
    public INPUT_TYPE last_entered;

    public int openedParenthesis;
    public boolean isNextNumberNegative;

    public LinkedList<String> expression;
    public ShuntingYard.ANGLE_TYPE angle_type;

    //////////////////////////////////////

    public BaseCalculator(Context context, CalculationListener listener) {
        this.listener = listener;

        mContext = context;
    }

    /////////////////////////////////////

    public void calculatorNumberClicked(View v){
        String number = ((Button) v).getText().toString();

        if (last_entered == INPUT_TYPE.NUMBER){
            input += number;
            String lastNumber = expression.removeLast();
            if (isNextNumberNegative)
                expression.addLast("-" + lastNumber + number);
            else
                expression.addLast(lastNumber + number);
        }
        else if (last_entered == INPUT_TYPE.PARENTHESIS_CLOSE || last_entered == INPUT_TYPE.SPECIAL_1_LEFT || last_entered == INPUT_TYPE.SPECIAL_NUMBER){
            input += "x" + number;
            expression.addLast("x");
            if (isNextNumberNegative)
                expression.addLast("-" + number);
            else
                expression.addLast(number);
        }
        else {
            input += number;
            if (isNextNumberNegative)
                expression.addLast("-" + number);
            else
                expression.addLast(number);
        }

        isNextNumberNegative = false;
        last_entered = INPUT_TYPE.NUMBER;
        updateInput();
    }

    public void calculatorOperatorClicked(View v){
        if (last_entered == INPUT_TYPE.NULL || last_entered == INPUT_TYPE.SIGN
                || last_entered == INPUT_TYPE.PARENTHESIS_OPEN || last_entered == INPUT_TYPE.SPECIAL_1_RIGHT)
            return;

        String operator = ((Button) v).getText().toString();

        if (last_entered == INPUT_TYPE.OPERATOR){
            input = input.substring(0,input.length()-1) + operator;
            expression.removeLast();
            expression.addLast(operator);
        }
        else{
            input += operator;
            expression.addLast(operator);
        }
        updateInput();
        last_entered = INPUT_TYPE.OPERATOR;
    }

    public void calculatorClearClicked(){
        reset();
        clearUi();
    }

    public void calculatorBackwardsClicked() {
        if (last_entered == INPUT_TYPE.NULL)
            return;
        else if (last_entered == INPUT_TYPE.SIGN){
            input = input.substring(0,input.length()-2);
            expression.removeLast();
            openedParenthesis--;
            isNextNumberNegative = false;
        }
        else if (last_entered == INPUT_TYPE.NUMBER){
            input = input.substring(0,input.length()-1);
            String lastNumber = expression.removeLast();
            if (lastNumber.length() != 1)
                expression.addLast(lastNumber.substring(0,lastNumber.length()-1));
        }
        else if (last_entered == INPUT_TYPE.SPECIAL_1_RIGHT){
            input = input.substring(0,input.length()-expression.removeLast().length());
        }
        else {
            input = input.substring(0,input.length()-1);
            expression.removeLast();
            if (last_entered == INPUT_TYPE.PARENTHESIS_CLOSE)
                openedParenthesis++;
            else if (last_entered == INPUT_TYPE.PARENTHESIS_OPEN)
                openedParenthesis--;
        }
        changeLastEntered();
        updateInput();
    }

    public void calculatorEqualClicked(){
        if (last_entered == INPUT_TYPE.NUMBER || last_entered == INPUT_TYPE.PARENTHESIS_CLOSE || last_entered == INPUT_TYPE.SPECIAL_1_LEFT){
            if (openedParenthesis > 0){
                Toast.makeText(mContext,"Lütfen açık parentez bırakmayın",Toast.LENGTH_SHORT).show();
                return;
            }

            ShuntingYard calculation = new ShuntingYard(expression,angle_type);
            updateResult(calculation.evaluate() + "");

            reset();
            updateInput();
        }
    }

    public void calculatorParenthesisClicked(){
        if (last_entered == INPUT_TYPE.NUMBER || last_entered == INPUT_TYPE.PARENTHESIS_CLOSE
                || last_entered == INPUT_TYPE.SPECIAL_1_LEFT || last_entered == INPUT_TYPE.SPECIAL_NUMBER){
            if (openedParenthesis > 0){
                input += ")";
                expression.addLast(")");
                openedParenthesis--;
                last_entered = INPUT_TYPE.PARENTHESIS_CLOSE;
            }
            else {
                input += "x(";
                expression.addLast("x");
                expression.addLast("(");
                openedParenthesis++;
                last_entered = INPUT_TYPE.PARENTHESIS_OPEN;
            }
        }
        else {
            input += "(";
            expression.addLast("(");
            openedParenthesis++;
            last_entered = INPUT_TYPE.PARENTHESIS_OPEN;
        }
        updateInput();
    }

    public void calculatorSignClicked() {
        if (last_entered == INPUT_TYPE.NULL || last_entered == INPUT_TYPE.OPERATOR || last_entered == INPUT_TYPE.SPECIAL_1_RIGHT){
            input += "(-";
            expression.addLast("(");
            openedParenthesis++;
            isNextNumberNegative = true;
            last_entered = INPUT_TYPE.SIGN;
        }
        else if (last_entered == INPUT_TYPE.NUMBER ||last_entered == INPUT_TYPE.SPECIAL_NUMBER){
            String lastNumber = expression.removeLast();
            if (lastNumber.contains("-")){
                input = input.substring(0,input.length()-(lastNumber.length()+1)) + lastNumber.substring(1);
                expression.removeLast();
                expression.addLast(lastNumber.substring(1));
                openedParenthesis--;
            }
            else {
                input = input.substring(0,input.length()-lastNumber.length()) + "(-" + lastNumber;
                expression.addLast("(");
                expression.addLast("-" + lastNumber);
                openedParenthesis++;
            }
        }
        else if (last_entered == INPUT_TYPE.PARENTHESIS_OPEN){
            input += "-";
            isNextNumberNegative = true;
            last_entered = INPUT_TYPE.SIGN;
        }
        else if (last_entered == INPUT_TYPE.PARENTHESIS_CLOSE || last_entered == INPUT_TYPE.SPECIAL_1_LEFT){
            input += "x(-";
            expression.addLast("x");
            expression.addLast("(");
            openedParenthesis++;
            isNextNumberNegative = true;
            last_entered = INPUT_TYPE.SIGN;
        }
        else if (last_entered == INPUT_TYPE.SIGN){
            input = input.substring(0,input.length()-2);
            expression.removeLast();
            openedParenthesis--;
            isNextNumberNegative = false;
            changeLastEntered();
        }

        updateInput();
    }

    public void calculatorDotClicked(){
        if (last_entered == INPUT_TYPE.NUMBER){
            String lastNumber = expression.getLast();
            if (!lastNumber.contains(".")){
                expression.removeLast();
                input += ".";
                expression.addLast(lastNumber + ".");
            }
        }
        else if (last_entered == INPUT_TYPE.PARENTHESIS_CLOSE || last_entered == INPUT_TYPE.SPECIAL_1_LEFT
                || last_entered == INPUT_TYPE.SPECIAL_NUMBER){
            input += "x0.";
            expression.addLast("x");
            expression.addLast("0.");
            last_entered = INPUT_TYPE.NUMBER;
        }
        else {
            input += "0.";
            expression.add("0.");
            last_entered = INPUT_TYPE.NUMBER;
        }
        updateInput();
    }

    public void calculatorPercentClicked() {
        if (last_entered != INPUT_TYPE.NUMBER)
            return;

        input += "%";
        expression.addLast("%");
        updateInput();
        last_entered = INPUT_TYPE.SPECIAL_1_LEFT;
    }

    //////////////////////////////////////

    public void reset() {
        input = "";
        last_entered = INPUT_TYPE.NULL;
        openedParenthesis = 0;
        isNextNumberNegative = false;

        expression = new LinkedList<>();
    }

    public void clearUi() {
        updateInput();
        updateResult("");
    }

    public void updateInput(){
        listener.onInputUpdated(input);
    }

    public void updateResult(String val){
        listener.onResultUpdated(val);
    }

    public void changeLastEntered() {
        if (expression.size() == 0){
            last_entered = INPUT_TYPE.NULL;
            return;
        }

        String lastToken = expression.getLast();

        if (Utils.isNumeric(lastToken)){
            last_entered = INPUT_TYPE.NUMBER;
            return;
        }

        switch (lastToken){
            case "+":
                last_entered = INPUT_TYPE.OPERATOR;
                break;
            case "x":
                last_entered = INPUT_TYPE.OPERATOR;
                break;
            case "÷":
                last_entered = INPUT_TYPE.OPERATOR;
                break;
            case "-":
                if (input.charAt(input.length()-2) == '(')
                    last_entered = INPUT_TYPE.SIGN;
                else
                    last_entered = INPUT_TYPE.OPERATOR;
                break;
            case "(":
                last_entered = INPUT_TYPE.PARENTHESIS_OPEN;
                break;
            case ")":
                last_entered = INPUT_TYPE.PARENTHESIS_CLOSE;
                break;
            case "%":
                last_entered = INPUT_TYPE.SPECIAL_1_LEFT;
                break;
            case "≡":
                last_entered = INPUT_TYPE.OPERATOR;
                break;
            case "√":
                last_entered = INPUT_TYPE.SPECIAL_1_RIGHT;
                break;
            case "ln":
                last_entered = INPUT_TYPE.SPECIAL_1_RIGHT;
                break;
            case "log":
                last_entered = INPUT_TYPE.SPECIAL_1_RIGHT;
                break;
            case "e":
                last_entered = INPUT_TYPE.SPECIAL_NUMBER;
                break;
            case "π":
                last_entered = INPUT_TYPE.SPECIAL_NUMBER;
                break;
            case "^":
                last_entered = INPUT_TYPE.OPERATOR;
                break;
            case "abs":
                last_entered = INPUT_TYPE.SPECIAL_1_RIGHT;
                break;
            case "!":
                last_entered = INPUT_TYPE.SPECIAL_1_LEFT;
                break;
            case "³√":
                last_entered = INPUT_TYPE.SPECIAL_1_RIGHT;
                break;
            case "sin":
                last_entered = INPUT_TYPE.SPECIAL_1_RIGHT;
                break;
            case "cos":
                last_entered = INPUT_TYPE.SPECIAL_1_RIGHT;
                break;
            case "tan":
                last_entered = INPUT_TYPE.SPECIAL_1_RIGHT;
                break;
            case "sinh":
                last_entered = INPUT_TYPE.SPECIAL_1_RIGHT;
                break;
            case "cosh":
                last_entered = INPUT_TYPE.SPECIAL_1_RIGHT;
                break;
            case "tanh":
                last_entered = INPUT_TYPE.SPECIAL_1_RIGHT;
                break;
            case "arcsin":
                last_entered = INPUT_TYPE.SPECIAL_1_RIGHT;
                break;
            case "arccos":
                last_entered = INPUT_TYPE.SPECIAL_1_RIGHT;
                break;
            case "arctan":
                last_entered = INPUT_TYPE.SPECIAL_1_RIGHT;
                break;
            case "asinh":
                last_entered = INPUT_TYPE.SPECIAL_1_RIGHT;
                break;
            case "acosh":
                last_entered = INPUT_TYPE.SPECIAL_1_RIGHT;
                break;
            case "atanh":
                last_entered = INPUT_TYPE.SPECIAL_1_RIGHT;
                break;
        }
    }
}