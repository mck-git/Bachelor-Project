package Compile;

import Compile.Operations.BooleanNumOperation;
import Compile.Operations.BooleanOperation;
import Compile.Operations.MathOperation;
import DataTypes.*;
import DataTypes.Functions.Function;
import DataTypes.Functions.VoidFunction;
import DataTypes.Variables.BooleanVariable;
import DataTypes.Variables.CharVariable;
import DataTypes.Variables.IntegerVariable;
import DataTypes.Variables.StringVariable;
import Errors.InvalidSyntaxException;
import Parser.Lexer;
import SharedResources.ExecutionType;

import java.util.ArrayList;

public class Declarations {
    final static int STATEMENT_BODY_START_INDEX = 2; // SHOULD BE 3?

    private static Function functionDeclared;

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
        VariableContainer val = Mapper.findVariable(tokens.get(0).getContent());

        if (val == null)
            return;

        ArrayList<Token> body = new ArrayList<>(tokens.subList(STATEMENT_BODY_START_INDEX, tokens.size()));

        switch (val.getType())
        {
            case "int":

                Mapper.redefineInt(
                        new IntegerVariable(
                                tokens.get(0).getContent(),
                                evaluateMath(body)
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
                                evaluateBoolean(body)
                        )
                );
        }



    }


    // FUNCTIONS

    public static void initiateFunctionDeclaration(ArrayList<Token> tokens) throws InvalidSyntaxException
    {
        String methodType = tokens.get(1).getContent();

        switch (methodType)
        {
            case "void":
                initiateVoidFunctionDeclaration(tokens);
                break;

            case "int":
                //declare
                break;

            case "string":
                //declare
                break;

            case "char":
                //declare
                break;

            case "boolean":
                //declare
                break;

            default:
                throw new InvalidSyntaxException(Lexer.getLineNumber());


        }


    }


    private static void initiateVoidFunctionDeclaration(ArrayList<Token> tokens)
    {
        String name = tokens.get(2).getContent();
        functionDeclared = new VoidFunction(name, new ArrayList<ArrayList<Token>>());
        Translator.setExecutionType(ExecutionType.METHOD);
    }

    public static void saveLine(ArrayList<Token> tokens)
    {
        functionDeclared.addLineOfCode(tokens);
    }

    public static void storeMethod()
    {
        functionDeclared.store();
    }

    // MOVE
    public static int evaluateMath(ArrayList<Token> tokens)
    {
        int num = 0;
        MathOperation op = MathOperation.PLUS;


        for (int i = 0; i < tokens.size(); i++)
        {
            String tokenContent = tokens.get(i).getContent();

            if ( tokenContent.equals("+") )
                op = MathOperation.PLUS;

            else if ( tokenContent.equals("-") )
                op = MathOperation.MINUS;

            else if ( tokenContent.equals("*"))
                op = MathOperation.MULT;

            else if ( tokenContent.equals("/"))
                op = MathOperation.DIVISION;

            else if (isNumeric(tokenContent))
                num = applyMathOp(num, op, tokenContent);

            else
            {
                VariableContainer foundVariable = Mapper.findVariable(tokenContent);

                if (foundVariable == null || !foundVariable.getType().equals("int") )
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

    // MOVE
    public static boolean evaluateBoolean(ArrayList<Token> tokens)
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

    // MOVE
    public static boolean isNumeric(String s)
    {
        try {
            Integer.parseInt(s);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // MOVE
    public static boolean isBoolean(String s)
    {
        return (s.equals("true") || s.equals("false"));
    }
}
