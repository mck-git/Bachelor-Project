package Compile;

import DataTypes.*;
import DataTypes.Functions.Function;
import DataTypes.Functions.IntegerFunction;
import DataTypes.Functions.VoidFunction;
import DataTypes.Variables.*;
import Errors.InvalidSyntaxException;
import Parser.Lexer;
import SharedResources.ExecutionType;

import java.util.ArrayList;

public class Declarations {
    final static int DECLARATION_STATEMENT_BODY_START_INDEX = 3; // SHOULD BE 3?
    final static int STATEMENT_BODY_START_INDEX = 2;

    private static Function functionDeclared;

    public static void declareInteger(ArrayList<Token> tokens) throws InvalidSyntaxException
    {
        ArrayList<Token> body = new ArrayList<>(tokens.subList(DECLARATION_STATEMENT_BODY_START_INDEX,tokens.size()));

        Mapper.addToIntMap(
                new IntegerVariable(
                        tokens.get(1).getContent(),
                        Calculations.evaluateMath(body)
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

    public static void declareBoolean(ArrayList<Token> tokens) throws InvalidSyntaxException
    {
        Mapper.addToBooleanMap(
                new BooleanVariable(
                        tokens.get(1).getContent(),
                        Calculations.evaluateBoolean(tokens)
                )
        );
    }

    public static void redefineVariable(ArrayList<Token> tokens) throws InvalidSyntaxException
    {
        VariableContainer val = Mapper.findVariable(tokens.get(0).getContent());

        if (val == null)
            throw new InvalidSyntaxException(Lexer.getLineNumber());

        ArrayList<Token> body = new ArrayList<>(tokens.subList(STATEMENT_BODY_START_INDEX, tokens.size()));

        switch (val.getType())
        {
            case "int":

                Mapper.redefineInt(
                        new IntegerVariable(
                                tokens.get(0).getContent(),
                                Calculations.evaluateMath(body)
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
                                Calculations.evaluateBoolean(body)
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
                initiateIntegerFunctionDeclaration(tokens);
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

        boolean argumentsStarted = false;



        for (Token t: tokens)
        {
            if (t.getContent().equals("("))
                argumentsStarted = true;

            if (!argumentsStarted)
                continue;



        }

        Translator.setExecutionType(ExecutionType.FUNCTION);
    }

    private static void initiateIntegerFunctionDeclaration(ArrayList<Token> tokens)
    {
        String name = tokens.get(2).getContent();
        functionDeclared = new IntegerFunction(name, new ArrayList<ArrayList<Token>>());
        Translator.setExecutionType(ExecutionType.FUNCTION);
    }


    public static void saveLine(ArrayList<Token> tokens)
    {
        functionDeclared.addLineOfCode(tokens);
    }

    public static void storeMethod()
    {
        functionDeclared.store();
    }


}
