package Compile;

import DataTypes.Token;
import Parser.Lexer;
import SharedResources.ExecutionType;

import java.util.ArrayList;


public class MainCompiler {

    private static ExecutionType executionType = ExecutionType.NORMAL;

    public static void main(String[] args) throws Exception
    {
        Lexer.read("test_program");
    }

    public static void handleLine(ArrayList<Token> tokens)
    {
        if (tokens.isEmpty())
            return;

        if (executionType == ExecutionType.IF_FALSE)
            return;

        switch (tokens.get(0).getContent())
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
                executionType = ExecutionType.NORMAL;
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
