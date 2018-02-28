package Compile;

import DataTypes.SearchedVariable;
import DataTypes.Token;
import Errors.InvalidSyntaxException;
import Parser.Lexer;
import SharedResources.ExecutionType;

import java.util.ArrayList;

public class Conditionals {


    public static void handleIf(ArrayList<Token> tokens) throws InvalidSyntaxException
    {
        if (evaluateConditionalLine(tokens))
            MainCompiler.setExecutionType(ExecutionType.IF_TRUE);
        else
            MainCompiler.setExecutionType(ExecutionType.IF_FALSE);

    }

    public static boolean evaluateConditionalLine(ArrayList<Token> tokens) throws InvalidSyntaxException {
        int conditionalStart = 0;
        String tokenContent;

        for (int i = 0; i < tokens.size(); i++)
        {
            tokenContent = tokens.get(i).getContent();

            if ( Declarations.isNumeric(tokenContent) || Declarations.isBoolean(tokenContent)
                    || (Mapper.findVariable(tokenContent) != null) )
                conditionalStart = (conditionalStart == 0? i : conditionalStart);

            else if (tokenContent.equals("{"))
                return Declarations.evaluateBoolean(new ArrayList<>(tokens.subList(conditionalStart,i)));

        }
        throw new InvalidSyntaxException(Lexer.getLineNumber());
    }

}
