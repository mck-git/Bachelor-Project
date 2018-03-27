package Compile;

import DataTypes.Token;
import Errors.InvalidSyntaxException;
import SharedResources.ExecutionType;

import java.util.ArrayList;

public class Loops {
    private static ArrayList<Token> conditionalTokens = new ArrayList<>();

    private static ArrayList< ArrayList<Token> > linesOfCodeInLoop = new ArrayList<>();

    public static void initiateWhile(ArrayList<Token> tokens) throws InvalidSyntaxException
    {
        if ( ! Conditionals.evaluateConditionalLine(tokens) )
        {
            Translator.setExecutionType(ExecutionType.IF_FALSE);
            return;
        }

        conditionalTokens = (ArrayList<Token>) tokens.clone();

        Translator.setExecutionType(ExecutionType.WHILE);
        linesOfCodeInLoop.clear();
    }


    public static void saveLine(ArrayList<Token> tokens)
    {
        linesOfCodeInLoop.add((ArrayList<Token>) tokens.clone());
    }

    public static void executeLoop() throws InvalidSyntaxException
    {
        Translator.setExecutionType(ExecutionType.NORMAL);
        while (Conditionals.evaluateConditionalLine(conditionalTokens) ) // && count < 2
        {
            for (ArrayList<Token> t : linesOfCodeInLoop) {
                Translator.handleLine(t);
            }
        }
    }

}
