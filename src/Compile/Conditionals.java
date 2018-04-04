package Compile;

import DataTypes.Token;
import Errors.InvalidSyntaxException;
import Parser.Lexer;
import SharedResources.ExecutionType;

import java.util.ArrayList;

public class Conditionals {

    /**
     * Recieves tokens corresponding to a conditional statement. If the statement evaluates to
     * true, the executionType is set to IF_TRUE, otherwise it is set to IF_FALSE.
     *
     * @param tokens Tokens recieved from the Translator
     * @throws InvalidSyntaxException Thrown if the conditional statement is syntactically incorrect
     */
    public static void handleIf(ArrayList<Token> tokens) throws InvalidSyntaxException
    {
        if (evaluateConditionalLine(tokens))
            Translator.setExecutionType(ExecutionType.IF_TRUE);
        else
            Translator.setExecutionType(ExecutionType.IF_FALSE);

    }

    /**
     * Isolates the tokens that are actually part of the conditional statement, and sends them to the boolean evaluator
     * @param tokens A line of code containing a conditional statement
     * @return Returns the result of the evaluation
     * @throws InvalidSyntaxException Thrown if the line is syntactically incorrect.
     */
    public static boolean evaluateConditionalLine(ArrayList<Token> tokens) throws InvalidSyntaxException {
        int conditionalStart = 0;
        String tokenContent;

        for (int i = 0; i < tokens.size(); i++)
        {
            tokenContent = tokens.get(i).getContent();

            if ( Calculations.isNumeric(tokenContent) || Calculations.isBoolean(tokenContent)
                    || (Mapper.findVariable(tokenContent) != null) )
                conditionalStart = (conditionalStart == 0? i : conditionalStart);

            else if (tokenContent.equals("{"))
                return Calculations.evaluateBoolean(new ArrayList<>(tokens.subList(conditionalStart,i)));

        }
        throw new InvalidSyntaxException(Lexer.getLineNumber());
    }

}
