package Compile;

import DataTypes.Functions.Function;
import DataTypes.Functions.FunctionContainer;
import DataTypes.Token;
import Errors.InvalidSyntaxException;
import Parser.Lexer;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class FunctionExecutor {

    public static LinkedList<Function> functionsExecuting = new LinkedList<>();


    /**
     * Takes tokens and executes any function found within
     * @param tokens Tokens to read through
     * @throws InvalidSyntaxException Thrown if the function contains invalid syntax
     */
    public static void findAndRunFunction(ArrayList<Token> tokens) throws InvalidSyntaxException
    {
        boolean functionFound = false;
        boolean arguments = false;
        FunctionContainer foundFunctionContainer = null;
        Function function = null;

        for (Token token : tokens)
        {
            String tokenContent = token.getContent();


            if (!functionFound)
            {
                foundFunctionContainer = Mapper.findFunction(tokenContent);

                if (foundFunctionContainer == null) {
                    continue;
                }


                function = foundFunctionContainer.getFunction();
                functionFound = true;
            }

            else
            {
                if (tokenContent.equals("("))
                    continue;

                else if (tokenContent.equals(")"))
                    break;





            }


            // Retrieve argument names and save the given values in TemporaryVariables map


        }


        if (functionFound)
        {

            execute(function);
            return;
        }

        throw new InvalidSyntaxException(Lexer.getLineNumber());
    }

    /**
     * Executes the given function by calling the Translator
     * @param function Function to execute
     * @throws InvalidSyntaxException Thrown if the function contains invalid syntax
     */
    public static void execute(Function function) throws InvalidSyntaxException
    {
        functionsExecuting.push(function);
        ArrayList<ArrayList<Token>> lines = function.getLinesOfCodeInMethod();

        for (ArrayList<Token> line : lines)
            Translator.handleLine(line);

        functionsExecuting.pop();
    }

}
