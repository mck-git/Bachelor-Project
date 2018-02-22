package Compile;

import Compile.Operations.BooleanNumOperation;
import Compile.Operations.BooleanOperation;
import Compile.Operations.MathOperation;
import DataTypes.*;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Declarations {


    public static void declareInteger(ArrayList<Token> tokens)
    {
        Mapper.addToIntMap(
                new IntegerVariable(
                        tokens.get(1).getContent(),
                        evaluateMath(tokens)
                )
        );
    }

    public static void declareString(ArrayList<Token> tokens)
    {
        Mapper.addToStringMap(
                new StringVariable(
                        tokens.get(1).getContent(),
                        tokens.get(3).getContent()
                )
        );
    }

    public static void declareChar(ArrayList<Token> tokens)
    {
        Mapper.addToCharMap(
                new CharVariable(
                        tokens.get(1).getContent(),
                        tokens.get(3).getContent().charAt(0)
                )
        );
    }

    public static void declareBoolean(ArrayList<Token> tokens)
    {

        Mapper.addToBooleanMap(
                new BooleanVariable(
                        tokens.get(1).getContent(),
                        evaluateBoolean(tokens)
                )
        );
    }


    public static void redefineVariable(ArrayList<Token> tokens)
    {
        Object[] val = Mapper.findVariable(tokens.get(0).getContent());

        if (val == null)
            return;

        switch ((String) val[0])
        {
            case "int":
                Mapper.redefineInt(
                        new IntegerVariable(
                                tokens.get(0).getContent(),
                                evaluateMath(tokens)
                        )
                );
                break;

            case "string":
                Mapper.redefineString(
                        new StringVariable(
                                tokens.get(0).getContent(),
                                tokens.get(2).getContent()
                        )
                );
                break;

            case "char":
                Mapper.redefineChar(
                        new CharVariable(
                                tokens.get(0).getContent(),
                                tokens.get(2).getContent().charAt(0)
                        )
                );
                break;

            case "boolean":
                Mapper.redefineBoolean(
                        new BooleanVariable(
                                tokens.get(0).getContent(),
                                evaluateBoolean(tokens)
                        )
                );
        }



    }



    private static int evaluateMath(ArrayList<Token> tokens)
    {
        int num = 0;
        MathOperation op = MathOperation.PLUS;


        for (int i = 0; i < tokens.size(); i++)
        {
            String s = tokens.get(i).getContent();

            if ( s.equals("+") )
                op = MathOperation.PLUS;

            else if ( s.equals("-") )
                op = MathOperation.MINUS;

            else if ( s.equals("*"))
                op = MathOperation.MULT;

            else if ( s.equals("/"))
                op = MathOperation.DIVISION;

            else if (isNumeric(s))
            {
                num = applyMathOp(num, op, s);
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

    private static boolean evaluateBoolean(ArrayList<Token> tokens)
    {
        boolean result = true;
        BooleanOperation op = BooleanOperation.AND;
        BooleanNumOperation numOp = BooleanNumOperation.GREATER;


        boolean numberSequence = false;
        int numIndexStart = 0;

        int numBuffer = 0;


        for (int i = 0; i < tokens.size(); i++)
        {
            String s = tokens.get(i).getContent();

            if (s.equals("&"))
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
            }

            else if (s.equals("|"))
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
            }

            else if (s.equals("<"))
            {
                numOp = BooleanNumOperation.LESSER;

                numBuffer = evaluateMath(
                        new ArrayList<>(tokens.subList(numIndexStart,i))
                );

                numberSequence = false;

            }

            else if (s.equals(">"))
            {
                numOp = BooleanNumOperation.GREATER;

                numBuffer = evaluateMath(
                        new ArrayList<>(tokens.subList(numIndexStart,i))
                );

                numberSequence = false;
            }

            else if (s.equals("=="))
            {
                numOp = BooleanNumOperation.EQUALS;

                numBuffer = evaluateMath(
                        new ArrayList<>(tokens.subList(numIndexStart,i))
                );

                numberSequence = false;
            }

            else if (isBoolean(s))
            {
                result = applyBooleanOp(
                        result, op, s
                        );
            }

            else if (isNumeric(s))
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


    private static boolean isNumeric(String s)
    {
        try {
            Integer.parseInt(s);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private static boolean isBoolean(String s)
    {
        return (s.equals("true") || s.equals("false"));
    }
}
