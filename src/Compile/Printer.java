package Compile;

import DataTypes.*;
import DataTypes.Functions.Function;
import DataTypes.Functions.FunctionContainer;
import DataTypes.Variables.*;
import Errors.InvalidSyntaxException;
import SharedResources.InputType;

import java.util.ArrayList;

public class Printer {

    public static void print(ArrayList<Token> tokens) throws NullPointerException, InvalidSyntaxException
    {
        for (Token t : tokens)
        {
            if (t.getInputType() == InputType.STRING)
            {
                System.out.println(t.getContent());
                continue;
            }

            String tokenContent = t.getContent();

            if (tokenContent.equals("print"))
            {
                System.out.print("[out]: ");
                continue;
            }


            VariableContainer foundVariable = Mapper.findVariable(tokenContent);
            if (foundVariable != null) {
                printVariable(foundVariable);
                continue;
            }

            FunctionContainer foundFunction = Mapper.findFunction(tokenContent);
            if (foundFunction != null) {
                printFunction(foundFunction);
                continue;
            }

        }

    }

    private static void printVariable(VariableContainer foundVariable) {
        switch (foundVariable.getType())
        {
            case "int" :
                printIntVariable(foundVariable.getVariable());
                break;

            case "char" :
                printCharVariable(foundVariable.getVariable());
                break;

            case "string" :
                printStringVariable(foundVariable.getVariable());
                break;

            case "boolean" :
                printBooleanVariable(foundVariable.getVariable());
                break;
        }
    }

    private static void printIntVariable(Variable variable)
    {
        IntegerVariable v =  (IntegerVariable) variable;

        System.out.println(
                v.getValue()
        );
    }

    private static void printCharVariable(Variable variable)
    {
        CharVariable v = (CharVariable) variable;

        System.out.println(
                v.getValue()
        );
    }

    private static void printStringVariable(Variable variable)
    {
        StringVariable v = (StringVariable) variable;

        System.out.println(
                v.getValue()
        );
    }

    private static void printBooleanVariable(Variable variable)
    {
        BooleanVariable v = (BooleanVariable) variable;

        System.out.println(
                v.getValue()
        );
    }

    private static void printFunction(FunctionContainer functionContainer) throws InvalidSyntaxException
    {
        switch (functionContainer.getType())
        {
            case "int" :
                printIntFunction(functionContainer.getFunction());
                break;

//            case "char" :
//                printCharVariable(functionContainer.getFunction());
//                break;
//
//            case "string" :
//                printStringVariable(functionContainer.getFunction());
//                break;
//
//            case "boolean" :
//                printBooleanVariable(functionContainer.getFunction());
//                break;
        }
    }

    private static void printIntFunction(Function function) throws InvalidSyntaxException
    {
        FunctionExecutor.execute(function);

        VariableContainer returnValue = Mapper.findReturnValue();

        if (returnValue == null)
            return;

        Variable variable = returnValue.getVariable();

        if (variable instanceof IntegerVariable)
            System.out.println(((IntegerVariable) variable).getValue());


    }


}
