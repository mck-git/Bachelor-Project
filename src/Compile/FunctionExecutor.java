package Compile;

import DataTypes.Functions.Function;
import DataTypes.Functions.FunctionContainer;
import DataTypes.Token;
import Errors.InvalidSyntaxException;

import java.util.ArrayList;

public class FunctionExecutor {

    /**
     * Takes tokens and executes any function found within
     * @param tokens Tokens to read through
     * @throws InvalidSyntaxException Thrown if the function contains invalid syntax
     */
    public static void findAndRunFunction(ArrayList<Token> tokens) throws InvalidSyntaxException
    {
        for (Token token : tokens)
        {
            String tokenContent = token.getContent();

            FunctionContainer foundFunctionContainer = Mapper.findFunction(tokenContent);

            if (foundFunctionContainer == null)
                continue;

            Function function = foundFunctionContainer.getFunction();

            execute(function);
        }
    }

    /**
     * Executes the given function by calling the Translator
     * @param function Function to execute
     * @throws InvalidSyntaxException Thrown if the function contains invalid syntax
     */
    public static void execute(Function function) throws InvalidSyntaxException
    {
        ArrayList<ArrayList<Token>> lines = function.getLinesOfCodeInMethod();

        for (ArrayList<Token> line : lines)
        {
            Translator.handleLine(line);
        }
    }

}
