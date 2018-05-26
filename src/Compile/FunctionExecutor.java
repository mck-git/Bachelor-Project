package Compile;

import DataTypes.Functions.Function;
import DataTypes.Functions.FunctionContainer;
import DataTypes.Token;
import DataTypes.Variables.*;
import Errors.InvalidSyntaxException;
import Maps.Variables.TemporaryVariablesMap;
import Parser.Lexer;

import java.lang.reflect.Array;
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
        FunctionContainer foundFunctionContainer;
        Function function = null;

        ArrayList<Variable> argumentNames  = null;
        int argumentIndex = 0;


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
                argumentNames = function.getArgumentVariables();

                functionFound = true;
            }

            else
            {
                if (tokenContent.equals("(") ||
                        token.equals(","))
                    continue;

                else if (tokenContent.equals(")"))
                    break;


                Variable arg = argumentNames.get(argumentIndex);

                if ( (arg instanceof IntegerVariable) && Calculations.isNumeric(tokenContent) )
                    TemporaryVariablesMap.add(arg.getName(), new IntegerVariable(arg.getName(), Integer.parseInt(tokenContent)) );

                else if ( (arg instanceof BooleanVariable) && Calculations.isBoolean(tokenContent) )
                    TemporaryVariablesMap.add(arg.getName(), new BooleanVariable(arg.getName(), Boolean.valueOf(tokenContent)) );

                else if ( (arg instanceof StringVariable) && tokenContent.length() > 1 && Character.isLetter(tokenContent.charAt(0)) )
                    TemporaryVariablesMap.add(arg.getName(), new StringVariable(arg.getName(), tokenContent));

                else if ( (arg instanceof CharVariable) && tokenContent.length() == 1 && Character.isLetter(tokenContent.charAt(0)))
                    TemporaryVariablesMap.add(arg.getName(), new CharVariable(arg.getName(), tokenContent.charAt(0)));



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
