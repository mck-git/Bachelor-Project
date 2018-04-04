package Compile;

import Compile.Operations.BooleanNumOperation;
import Compile.Operations.BooleanOperation;
import Compile.Operations.MathOperation;
import DataTypes.Token;
import DataTypes.VariableContainer;
import DataTypes.Variables.IntegerVariable;
import Errors.InvalidSyntaxException;
import Parser.Lexer;

import java.util.ArrayList;

public class Calculations {

    public static void evaluateReturn(ArrayList<Token> tokens) throws InvalidSyntaxException
    {
        try
        {
            evaluateMath(tokens);

            

            return;
        } catch (InvalidSyntaxException e)
        {}







    }


    public static int evaluateMath(ArrayList<Token> tokens) throws InvalidSyntaxException
    {
        int num = 0;
        MathOperation op = MathOperation.PLUS;


        for (Token token : tokens) {
            String tokenContent = token.getContent();

            if (tokenContent.equals("+"))
                op = MathOperation.PLUS;

            else if (tokenContent.equals("-"))
                op = MathOperation.MINUS;

            else if (tokenContent.equals("*"))
                op = MathOperation.MULT;

            else if (tokenContent.equals("/"))
                op = MathOperation.DIVISION;

            else if (isNumeric(tokenContent))
                num = applyMathOp(num, op, tokenContent);

            else if (tokenContent.equals("&") || tokenContent.equals("|") || tokenContent.equals("=="))
                throw new InvalidSyntaxException(Lexer.getLineNumber());

            else {
                VariableContainer foundVariable = Mapper.findVariable(tokenContent);

                if (foundVariable == null || !foundVariable.getType().equals("int"))
                    continue;

                IntegerVariable varValue = (IntegerVariable) foundVariable.getVariable();

                num = applyMathOp(num, op, "" + varValue.getValue());
            }
        }
        return num;
    }

    private static int applyMathOp(int num, MathOperation op, String s) {
        switch (op)
        {
            case PLUS:
                num = num + Integer.parseInt(s);
                break;
            case MINUS:
                num = num - Integer.parseInt(s);
                break;
            case MULT:
                num = num * Integer.parseInt(s);
                break;
            case DIVISION:
                num = num / Integer.parseInt(s);
                break;
        }
        return num;
    }

    public static boolean evaluateBoolean(ArrayList<Token> tokens) throws InvalidSyntaxException
    {
        boolean result = true;
        BooleanOperation op = BooleanOperation.AND;
        BooleanNumOperation numOp = BooleanNumOperation.GREATER;


        boolean numberSequence = false;
        int numIndexStart = 0;

        int numBuffer = 0;


        for (int i = 0; i < tokens.size(); i++)
        {
            String tokenContent = tokens.get(i).getContent();

            if (tokenContent.equals("&"))
            {
                if (numberSequence)
                {
                    result = applyBooleanNumOp(
                            result, op, numOp,
                            numBuffer, evaluateMath(new ArrayList<>(tokens.subList(numIndexStart,i)))
                    );
                    numberSequence = false;
                }

                op = BooleanOperation.AND;

                continue;
            }



            if (tokenContent.equals("|"))
            {
                if (numberSequence)
                {
                    result = applyBooleanNumOp(
                            result, op, numOp,
                            numBuffer, evaluateMath(new ArrayList<>(tokens.subList(numIndexStart,i)))
                    );
                    numberSequence = false;
                }

                op = BooleanOperation.OR;

                continue;
            }

            if (tokenContent.equals("<"))
            {
                numOp = BooleanNumOperation.LESSER;

                numBuffer = evaluateMath(
                        new ArrayList<>(tokens.subList(numIndexStart,i))
                );

                numberSequence = false;

                continue;
            }

            if (tokenContent.equals(">"))
            {
                numOp = BooleanNumOperation.GREATER;

                numBuffer = evaluateMath(
                        new ArrayList<>(tokens.subList(numIndexStart,i))
                );

                numberSequence = false;

                continue;
            }

            if (tokenContent.equals("=="))
            {
                numOp = BooleanNumOperation.EQUALS;

                numBuffer = evaluateMath(
                        new ArrayList<>(tokens.subList(numIndexStart,i))
                );

                numberSequence = false;

                continue;
            }

            if (isBoolean(tokenContent))
            {
                result = applyBooleanOp(
                        result, op, tokenContent
                );

                continue;
            }

            if (isNumeric(tokenContent))
            {
                if (numberSequence)
                    continue;
                numIndexStart = i;
                numberSequence = true;
            }

        }

        if (numberSequence)
        {
            result = applyBooleanNumOp(
                    result, op, numOp,
                    numBuffer, evaluateMath(new ArrayList<>(tokens.subList(numIndexStart,tokens.size())))
            );
        }

        return result;
    }

    private static boolean applyBooleanOp(boolean result, BooleanOperation op, String s) {
        switch (op)
        {
            case AND :
                return result && Boolean.parseBoolean(s);

            case OR :
                return result || Boolean.parseBoolean(s);

            default :
                return result;
        }

    }

    private static boolean applyBooleanNumOp(boolean result, BooleanOperation op, BooleanNumOperation numOp, int num1, int num2) {
        switch (numOp)
        {
            case LESSER:
                return applyBooleanOp (
                        result, op, "" + (num1 < num2)
                );

            case GREATER:
                return applyBooleanOp(
                        result, op, "" + (num1 > num2)
                );

            case EQUALS:
                return applyBooleanOp(
                        result, op, "" + (num1 == num2)
                );

            default:
                return result;
        }
    }


    public static boolean isNumeric(String s)
    {
        try {
            Integer.parseInt(s);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    public static boolean isBoolean(String s)
    {
        return (s.equals("true") || s.equals("false"));
    }


}
