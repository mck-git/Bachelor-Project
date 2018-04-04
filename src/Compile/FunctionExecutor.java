package Compile;

import DataTypes.Functions.Function;
import DataTypes.Functions.FunctionContainer;
import DataTypes.Token;
import Errors.InvalidSyntaxException;

import java.util.ArrayList;

public class FunctionExecutor {

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


    public static void execute(Function function) throws InvalidSyntaxException
    {
        ArrayList<ArrayList<Token>> lines = function.getLinesOfCodeInMethod();

        for (ArrayList<Token> line : lines)
        {
            Translator.handleLine(line);
        }


    }

}
