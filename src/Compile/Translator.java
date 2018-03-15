package Compile;

import DataTypes.Token;
import Errors.InvalidSyntaxException;
import Parser.Lexer;
import SharedResources.ExecutionType;

import java.util.ArrayList;


public class Translator {

    private static ExecutionType executionType = ExecutionType.NORMAL;

    public static void main(String[] args) throws Exception
    {
//        Lexer.read("test_program");
//        Lexer.read("test_loops");
//        Lexer.read("test_methods");
        Lexer.read("quick_test");
    }

    public static void handleLine(ArrayList<Token> tokens) throws InvalidSyntaxException
    {
        if (tokens.isEmpty())
            return;

        if (executionType == ExecutionType.IF_FALSE)
            return;

        String firstTokenContent = tokens.get(0).getContent();

        if (executionType == ExecutionType.WHILE && !firstTokenContent.equals("}"))
        {
            Loops.saveLine(tokens);
            return;
        }

        if (executionType == ExecutionType.WHILE && !firstTokenContent.equals("}"))
        {
            Declarations.saveLine(tokens);
            return;
        }


        switch (firstTokenContent)
        {
            case "int":
                Declarations.declareInteger(tokens);
                break;

            case "string":
                Declarations.declareString(tokens);
                break;

            case "char":
                Declarations.declareChar(tokens);
                break;

            case "boolean":
                Declarations.declareBoolean(tokens);
                break;

            case "if":
                Conditionals.handleIf(tokens);
                break;

            case "while":
                Loops.initiateWhile(tokens);
                break;

            case "func":
                Declarations.initiateFunctionDeclaration(tokens);
                break;

            case "}":
                if (executionType == ExecutionType.WHILE) {
                    Loops.executeLoop();
                }
                else if (executionType == ExecutionType.METHOD) {
                    Declarations.storeMethod();
                }
                setExecutionType(ExecutionType.NORMAL);
                break;

            case "print":
                Printer.print(tokens);
                break;

            default :
                Declarations.redefineVariable(tokens);
        }

    }


    public static void setExecutionType(ExecutionType et)
    {
        executionType = et;
    }

}
