package Compile;

import DataTypes.Token;
import Errors.InvalidSyntaxException;
import Parser.Lexer;
import SharedResources.ExecutionType;

import java.util.ArrayList;


public class MainCompiler {

    private static ExecutionType executionType = ExecutionType.NORMAL;

    public static void main(String[] args) throws Exception
    {
//        Lexer.read("test_program");
        Lexer.read("test_loops");
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
            System.out.println("saving token");
            Loops.saveLine(tokens);
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

            case "}":
                if (executionType == ExecutionType.WHILE)
                    Loops.executeLoop();
                executionType = ExecutionType.NORMAL;
                break;

            case "while":
                Loops.initiateWhile(tokens);
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
