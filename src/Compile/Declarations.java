package Compile;

import DataTypes.*;
import DataTypes.Functions.*;
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
                initiateStringFunctionDeclaration(tokens);
                break;

            case "char":
                initiateCharFunctionDeclaration(tokens);
                break;

            case "boolean":
                initiateBooleanFunctionDeclaration(tokens);
                break;

            default:
                throw new InvalidSyntaxException(Lexer.getLineNumber());


        }


    }


    private static void initiateVoidFunctionDeclaration(ArrayList<Token> tokens) throws InvalidSyntaxException
    {
        String name = tokens.get(2).getContent();
        functionDeclared = new VoidFunction(name, new ArrayList<ArrayList<Token>>());

        extractAndAddArguments(tokens);

        Translator.setExecutionType(ExecutionType.FUNCTION);
    }

    private static void initiateIntegerFunctionDeclaration(ArrayList<Token> tokens) throws InvalidSyntaxException
    {
        String name = tokens.get(2).getContent();
        functionDeclared = new IntegerFunction(name, new ArrayList<ArrayList<Token>>());

        extractAndAddArguments(tokens);

        Translator.setExecutionType(ExecutionType.FUNCTION);
    }

    private static void initiateCharFunctionDeclaration(ArrayList<Token> tokens) throws InvalidSyntaxException
    {
        String name = tokens.get(2).getContent();
        functionDeclared = new CharFunction(name, new ArrayList<ArrayList<Token>>());

        extractAndAddArguments(tokens);

        Translator.setExecutionType(ExecutionType.FUNCTION);
    }

    private static void initiateBooleanFunctionDeclaration(ArrayList<Token> tokens) throws InvalidSyntaxException
    {
        String name = tokens.get(2).getContent();
        functionDeclared = new BooleanFunction(name, new ArrayList<ArrayList<Token>>());

        extractAndAddArguments(tokens);

        Translator.setExecutionType(ExecutionType.FUNCTION);
    }

    private static void initiateStringFunctionDeclaration(ArrayList<Token> tokens) throws InvalidSyntaxException
    {
        String name = tokens.get(2).getContent();
        functionDeclared = new StringFunction(name, new ArrayList<ArrayList<Token>>());

        extractAndAddArguments(tokens);

        Translator.setExecutionType(ExecutionType.FUNCTION);
    }


    private static void extractAndAddArguments(ArrayList<Token> tokens) throws InvalidSyntaxException
    {
        boolean argumentsStarted = false;
        boolean expectingArgumentName = false;
        String argumentType = "";

        for (Token t: tokens)
        {
            String tokenContent = t.getContent();

            if (tokenContent.equals("("))
                argumentsStarted = true;

            else if (tokenContent.equals(")"))
                break;

            if (!argumentsStarted)
                continue;

            switch (tokenContent)
            {
                case ",":
                    continue;

                case "int":
                    if (expectingArgumentName)
                        throw new InvalidSyntaxException("Cannot name a variable '" + tokenContent + "'! At " + Lexer.getLineNumber());
                    expectingArgumentName = true;
                    argumentType = tokenContent;
                    break;

                case "boolean":
                    if (expectingArgumentName)
                        throw new InvalidSyntaxException("Cannot name a variable '" + tokenContent + "'! At " + Lexer.getLineNumber());
                    expectingArgumentName = true;
                    argumentType = tokenContent;
                    break;

                case "char":
                    if (expectingArgumentName)
                        throw new InvalidSyntaxException("Cannot name a variable '" + tokenContent + "'! At " + Lexer.getLineNumber());
                    expectingArgumentName = true;
                    argumentType = tokenContent;
                    break;

                case "string":
                    if (expectingArgumentName)
                        throw new InvalidSyntaxException("Cannot name a variable '" + tokenContent + "'! At " + Lexer.getLineNumber());
                    expectingArgumentName = true;
                    argumentType = tokenContent;
                    break;

                default:
                    if (Calculations.isNumeric(tokenContent) || Calculations.isBoolean(tokenContent))
                        throw new InvalidSyntaxException("Cannot name a variable '" + tokenContent + "'! At " + Lexer.getLineNumber());

                    else if (tokenContent.equals(")"))
                        continue;

                    switch (argumentType) {
                        case "int":
                            functionDeclared.addArgument(new IntegerVariable(tokenContent));
                            break;
                        case "boolean":
                            functionDeclared.addArgument(new BooleanVariable(tokenContent));
                            break;
                        case "char":
                            functionDeclared.addArgument(new CharVariable(tokenContent));
                            break;
                        case "string":
                            functionDeclared.addArgument(new StringVariable(tokenContent));
                            break;
                    }
            }
        }
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
