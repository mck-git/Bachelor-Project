package Compile;

import Compile.Operations.BooleanNumOperation;
import Compile.Operations.BooleanOperation;
import Compile.Operations.MathOperation;
import DataTypes.Functions.FunctionContainer;
import DataTypes.Functions.IntegerFunction;
import DataTypes.Token;
import DataTypes.Variables.VariableContainer;
import DataTypes.Variables.BooleanVariable;
import DataTypes.Variables.IntegerVariable;
import Errors.InvalidSyntaxException;
import Maps.Variables.TemporaryVariablesMap;
import Parser.Lexer;

import java.util.ArrayList;

public class Calculations {

    /**
     * Evaluate the returnvalue of a function and store it in the TemporaryVariablesMap
     * @param tokens Tokens describing the value to be evaluated and stored
     * @throws InvalidSyntaxException If the return statement contains illegal symbols or variables
     */
    public static void evaluateReturn(ArrayList<Token> tokens) throws InvalidSyntaxException
    {
        try
        {
            int returnValue = evaluateMath(tokens);


            TemporaryVariablesMap.add("returnValue", new IntegerVariable(
                    "returnValue", returnValue
            ));

            return;
        } catch (InvalidSyntaxException ignored) {}

        try
        {
            boolean returnValue = evaluateBoolean(tokens);

            TemporaryVariablesMap.add("returnValue", new BooleanVariable(
                    "returnValue", returnValue
            ));

            return;
        } catch (InvalidSyntaxException ignored) {}

        throw new InvalidSyntaxException(Lexer.getLineNumber());
    }


    /**
     * Takes an ArrayList of Tokens and calcualtes the math expression they describe
     *
     * @param tokens Tokens read from the code
     * @return Returns the calculated value of the tokens
     * @throws InvalidSyntaxException If the math expression contains illegal symbols or variables
     */
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

            else if (tokenContent.equals("=") || tokenContent.equals("(") || tokenContent.equals(")"))
                continue;


            int varValue;

            try
            {
                VariableContainer foundVariableContainer = Mapper.findVariable(tokenContent);
                if (foundVariableContainer == null || !foundVariableContainer.getType().equals("int"))
                    throw new InvalidSyntaxException(Lexer.getLineNumber());

                IntegerVariable foundVariable = (IntegerVariable) foundVariableContainer.getVariable();

                varValue = foundVariable.getValue();

                num = applyMathOp(num, op, "" + varValue);

                continue;
            } catch (InvalidSyntaxException ignored) {}


            try
            {
                FunctionContainer foundFunction = Mapper.findFunction(tokenContent);

                if (foundFunction == null || !(foundFunction.getFunction() instanceof IntegerFunction))
                    throw new InvalidSyntaxException(Lexer.getLineNumber());

                FunctionExecutor.execute(foundFunction.getFunction());

                VariableContainer returnValue = Mapper.findReturnValue();

                if (returnValue == null || !(returnValue.getVariable() instanceof IntegerVariable))
                    throw new InvalidSyntaxException(Lexer.getLineNumber());

                IntegerVariable returnValueVariable = (IntegerVariable) returnValue.getVariable();

                varValue = returnValueVariable.getValue();

                num = applyMathOp(num, op, "" + varValue);

                continue;

            } catch (InvalidSyntaxException ignored) {}


//            throw new InvalidSyntaxException(Lexer.getLineNumber());

        }

        return num;
    }

    /**
     * Parses a string to an integer, and applies the given MathOperation to that and the given number
     * @param num Integer to apply the String number to. Used as the result to be updated
     * @param op The operation to perform on the two numbers
     * @param s A number represented as a String. Used as the new number to apply to the current result
     * @return The result from applying the MathOperation to the two numbers
     */
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

    /**
     * Takes an ArrayList of Tokens and evaluates the boolean expression they describe
     * @param tokens Tokens read from the code
     * @return Returns the calculated boolean value of the tokens
     * @throws InvalidSyntaxException If the boolean expression contains illegal symbols or variables
     */
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


    /**
     * Parses a string to a boolean, and applies the given BooleanOperation to that and the given boolean
     * @param result The current result to apply the operation to
     * @param op The boolean operation
     * @param s The string to parse to a boolean and apply the boolean operation to
     * @return The result of applying the boolean operation on the two given booleans
     */
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

    /**
     * Compares two numbers according to the given BooleanNumOperation, and applies the given BooleanOperation to the result and the given boolean
     * @param result The current result boolean to apply the BooleanOperation to
     * @param op The BooleanOperation to apply to the boolean values
     * @param numOp The comparison between the two given numbers
     * @param num1 The first number in the comparison
     * @param num2 The second number in the comparison
     * @return The result of comparing the two boolean values
     */
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


    /**
     * Checks if the given string represents an integer
     * @param s String to check
     * @return Boolean value represeting whether the string represents an integer
     */
    public static boolean isNumeric(String s)
    {
        try {
            Integer.parseInt(s);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    /**
     * Checks if the given string represents a boolean
     * @param s String to check
     * @return Boolean value representing whether the string represents a boolean.
     */
    public static boolean isBoolean(String s)
    {
        return (s.equals("true") || s.equals("false"));
    }


}
