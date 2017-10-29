package com.aprigoldcorporation.allculator.helpers;

import com.aprigoldcorporation.allculator.util.Utils;

import java.util.LinkedList;

/**
 * Created by musta on 23.08.2017.
 */

public class ShuntingYard {

    public enum TOKEN_TYPE{
        NUMBER,
        OPERATOR,
        PARENTHESIS_OPEN,
        PARENTHESIS_CLOSE
    }

    private enum OPERATOR_TYPE{
        ONE,
        TWO
    }

    private enum ASSOCIATIVITY{
        RIGHT,
        LEFT
    }

    public enum ANGLE_TYPE{
        DEG,
        RAD
    }

    private LinkedList<String> inputStack;
    private LinkedList<String> outputStack;
    private LinkedList<String> operatorStack;
    private LinkedList<String> resultStack;

    private ANGLE_TYPE angle_type;

    public ShuntingYard(LinkedList<String> inputStack,ANGLE_TYPE angle_type) {
        this.inputStack = inputStack;
        this.angle_type = angle_type;

        outputStack = new LinkedList<>();
        operatorStack = new LinkedList<>();
        resultStack = new LinkedList<>();
    }

    private void evaluateExpression(){
        for (int i = 0; i < inputStack.size(); i++){
            String subToken = inputStack.get(i);
            CalculatorToken token = getToken(subToken);

            switch (token.getType()){
                case NUMBER:
                    outputStack.addLast(token.getName());
                    break;
                case OPERATOR:
                    Operator readOperator = ((Operator) token);
                    for (int y = 0; y < operatorStack.size(); y++){
                        Operator lastStackedOperator = getOperatorFromStack();
                        if (lastStackedOperator != null && lastStackedOperator.getPrecedence() >= readOperator.getPrecedence() &&
                                readOperator.associativity == ASSOCIATIVITY.LEFT){
                            outputStack.addLast(operatorStack.getFirst());
                            operatorStack.removeFirst();
                        }
                    }
                    operatorStack.addFirst(readOperator.getName());
                    break;
                case PARENTHESIS_OPEN:
                    operatorStack.addFirst("(");
                    break;
                case PARENTHESIS_CLOSE:
                    if (!operatorStack.contains("("))
                        return;
                    while (!operatorStack.getFirst().equals("(")){
                        outputStack.addLast(operatorStack.getFirst());
                        operatorStack.removeFirst();
                    }
                    operatorStack.removeFirst();
                    break;
            }

        }

        while (!operatorStack.isEmpty()){
            outputStack.addLast(operatorStack.getFirst());
            operatorStack.removeFirst();
        }
    }

    public double evaluate(){
        evaluateExpression();
        for (int i = 0; i < outputStack.size(); i++){
            CalculatorToken token = getToken(outputStack.get(i));

            switch (token.getType()){
                case OPERATOR:
                    Operator op = (Operator) token;
                    double result;
                    if (op.getOperator_type() == OPERATOR_TYPE.TWO){
                        Double operand_2 = Double.valueOf(resultStack.removeLast());
                        Double operand_1 = Double.valueOf(resultStack.removeLast());
                        result = calculate(operand_1,operand_2,token.getName());
                    }
                    else{
                        Double operand = Double.valueOf(resultStack.removeLast());
                        result = calculate(operand,token.getName());
                    }
                    resultStack.addLast(result + "");
                    break;
                case NUMBER:
                    resultStack.addLast(token.getName());
                    break;
            }
        }
        return Double.valueOf(resultStack.getLast());
    }

    private double calculate(Double operand_1, Double operand_2, String name) {
        switch (name){
            case "+":
                return operand_1 + operand_2;
            case "-":
                return operand_1 - operand_2;
            case "x":
                return operand_1 * operand_2;
            case "÷":
                return operand_1 / operand_2;
            case "≡":
                return operand_1 % operand_2;
            case "^":
                return Math.pow(operand_1,operand_2);
        }
        return Double.NaN;
    }

    private double calculate(Double operand, String name) {
        switch (name){
            case "%":
                return operand / 100.0;
            case "√":
                return Math.sqrt(operand);
            case "ln":
                return Math.log(operand);
            case "log":
                return Math.log10(operand);
            case "abs":
                return Math.abs(operand);
            case "!":
                return factorialOf(operand);
            case "³√":
                return Math.cbrt(operand);
            case "sin":
                if (angle_type == ANGLE_TYPE.DEG)
                    return Math.sin(Math.toRadians(operand));
                else
                    return Math.sin(operand);
            case "cos":
                if (angle_type == ANGLE_TYPE.DEG)
                    return Math.cos(Math.toRadians(operand));
                else
                    return Math.cos(operand);
            case "tan":
                if (angle_type == ANGLE_TYPE.DEG)
                    return Math.tan(Math.toRadians(operand));
                else
                    return Math.tan(operand);
            case "sinh":
                if (angle_type == ANGLE_TYPE.DEG)
                    return Math.sinh(Math.toRadians(operand));
                else
                    return Math.sinh(operand);
            case "cosh":
                if (angle_type == ANGLE_TYPE.DEG)
                    return Math.cosh(Math.toRadians(operand));
                else
                    return Math.cosh(operand);
            case "tanh":
                if (angle_type == ANGLE_TYPE.DEG)
                    return Math.tanh(Math.toRadians(operand));
                else
                    return Math.tanh(operand);
            case "arcsin":
                if (angle_type == ANGLE_TYPE.DEG)
                    return Math.toDegrees(Math.asin(operand));
                else
                    return Math.asin(operand);
            case "arccos":
                if (angle_type == ANGLE_TYPE.DEG)
                    return Math.toDegrees(Math.acos(operand));
                else
                    return Math.acos(operand);
            case "arctan":
                if (angle_type == ANGLE_TYPE.DEG)
                    return Math.toDegrees(Math.atan(operand));
                else
                    return Math.atan(operand);
            case "asinh":
                return asinh(operand);
            case "acosh":
                return acosh(operand);
            case "atanh":
                return atanh(operand);
        }
        return Double.NaN;
    }

    private Operator getOperatorFromStack() {
        if (operatorStack.isEmpty())
            return null;

        String operatorName = operatorStack.getFirst();
        Operator pickedOperator = pickOperator(operatorName);
        if (pickedOperator != null)
            return pickedOperator;

        return null;
    }

    private CalculatorToken getToken(String subToken) {
        if (Utils.isNumeric(subToken))
            return new CalculatorToken(subToken,TOKEN_TYPE.NUMBER);
        if (subToken.equals("e"))
            return new CalculatorToken(Math.E + "",TOKEN_TYPE.NUMBER);
        if (subToken.equals("π"))
            return new CalculatorToken(Math.PI + "",TOKEN_TYPE.NUMBER);

        Operator pickedOperator = pickOperator(subToken);
        if (pickedOperator != null)
            return pickedOperator;

        if (subToken.equals("("))
            return new CalculatorToken(subToken, TOKEN_TYPE.PARENTHESIS_OPEN);
        if (subToken.equals(")"))
            return new CalculatorToken(subToken, TOKEN_TYPE.PARENTHESIS_CLOSE);

        return null;
    }

    private Operator pickOperator(String operator){
        switch (operator){
            case "+":
                return new Operator(operator,OPERATOR_TYPE.TWO,2,ASSOCIATIVITY.LEFT);
            case "-":
                return new Operator(operator,OPERATOR_TYPE.TWO,2,ASSOCIATIVITY.LEFT);
            case "x":
                return new Operator(operator,OPERATOR_TYPE.TWO,3,ASSOCIATIVITY.LEFT);
            case "÷":
                return new Operator(operator,OPERATOR_TYPE.TWO,3,ASSOCIATIVITY.LEFT);
            case "%":
                return new Operator(operator,OPERATOR_TYPE.ONE,4,ASSOCIATIVITY.LEFT);
            case "≡":
                return new Operator(operator,OPERATOR_TYPE.TWO,3,ASSOCIATIVITY.LEFT);
            case "√":
                return new Operator(operator,OPERATOR_TYPE.ONE,4,ASSOCIATIVITY.RIGHT);
            case "ln":
                return new Operator(operator,OPERATOR_TYPE.ONE,4,ASSOCIATIVITY.RIGHT);
            case "log":
                return new Operator(operator,OPERATOR_TYPE.ONE,4,ASSOCIATIVITY.RIGHT);
            case "^":
                return new Operator(operator,OPERATOR_TYPE.TWO,4,ASSOCIATIVITY.RIGHT);
            case "abs":
                return new Operator(operator,OPERATOR_TYPE.ONE,4,ASSOCIATIVITY.RIGHT);
            case "!":
                return new Operator(operator,OPERATOR_TYPE.ONE,4,ASSOCIATIVITY.LEFT);
            case "³√":
                return new Operator(operator,OPERATOR_TYPE.ONE,4,ASSOCIATIVITY.RIGHT);
            case "sin":
                return new Operator(operator,OPERATOR_TYPE.ONE,4,ASSOCIATIVITY.RIGHT);
            case "cos":
                return new Operator(operator,OPERATOR_TYPE.ONE,4,ASSOCIATIVITY.RIGHT);
            case "tan":
                return new Operator(operator,OPERATOR_TYPE.ONE,4,ASSOCIATIVITY.RIGHT);
            case "sinh":
                return new Operator(operator,OPERATOR_TYPE.ONE,4,ASSOCIATIVITY.RIGHT);
            case "cosh":
                return new Operator(operator,OPERATOR_TYPE.ONE,4,ASSOCIATIVITY.RIGHT);
            case "tanh":
                return new Operator(operator,OPERATOR_TYPE.ONE,4,ASSOCIATIVITY.RIGHT);
            case "arcsin":
                return new Operator(operator,OPERATOR_TYPE.ONE,4,ASSOCIATIVITY.RIGHT);
            case "arccos":
                return new Operator(operator,OPERATOR_TYPE.ONE,4,ASSOCIATIVITY.RIGHT);
            case "arctan":
                return new Operator(operator,OPERATOR_TYPE.ONE,4,ASSOCIATIVITY.RIGHT);
            case "asinh":
                return new Operator(operator,OPERATOR_TYPE.ONE,4,ASSOCIATIVITY.RIGHT);
            case "acosh":
                return new Operator(operator,OPERATOR_TYPE.ONE,4,ASSOCIATIVITY.RIGHT);
            case "atanh":
                return new Operator(operator,OPERATOR_TYPE.ONE,4,ASSOCIATIVITY.RIGHT);
        }
        return null;
    }

    private class CalculatorToken{
        String name;
        TOKEN_TYPE type;

        public CalculatorToken(String name, TOKEN_TYPE type) {
            this.name = name;
            this.type = type;
        }

        public String getName() {
            return name;
        }

        public TOKEN_TYPE getType() {
            return type;
        }
    }

    private class Operator extends CalculatorToken{
        int precedence;
        ASSOCIATIVITY associativity;
        OPERATOR_TYPE operator_type;

        public Operator(String name, OPERATOR_TYPE operator_type, int precedence, ASSOCIATIVITY associativity) {
            super(name, TOKEN_TYPE.OPERATOR);

            this.precedence = precedence;
            this.associativity = associativity;
            this.operator_type = operator_type;

        }

        public int getPrecedence() {
            return precedence;
        }

        public ASSOCIATIVITY getAssociativity() {
            return associativity;
        }

        public OPERATOR_TYPE getOperator_type() {
            return operator_type;
        }
    }

    //////////////////////////////////////////////

    private double factorialOf(Double operand) {
        int c,n,fact = 1;
        n = operand.intValue();
        for ( c = 1 ; c <= n ; c++ )
            fact = fact*c;

        return fact;
    }

    private double asinh(double x) {
        return Math.log(x + Math.sqrt(x*x + 1.0));
    }

    private double acosh(double x) {
        return Math.log(x + Math.sqrt(x*x - 1.0));
    }

    private double atanh(double x) {
        return 0.5*Math.log( (x + 1.0) / (x - 1.0));
    }
}