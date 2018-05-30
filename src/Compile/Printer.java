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
        ArrayList<Token> functionToPrint = null;

        for (Token t : tokens)
        {

            if ( t.getContent().equals(")") && (functionToPrint != null))
            {
                printFunction(functionToPrint);
                functionToPrint = null;
                continue;
            }

            if (functionToPrint != null)
            {
                functionToPrint.add(t);
                continue;
            }


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
                functionToPrint = new ArrayList<>();
                functionToPrint.add(t);
                continue;
            }

        }

    }

    private static void printVariable(VariableContainer foundVariable)
    {
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

    private static void printFunction(ArrayList<Token> functionToPrint) throws InvalidSyntaxException
    {
        FunctionExecutor.findAndRunFunction(functionToPrint);

        VariableContainer returnValue = Mapper.findReturnValue();

        if (returnValue == null)
            return;

        Variable variable = returnValue.getVariable();

        if (variable instanceof IntegerVariable)
            System.out.println(((IntegerVariable) variable).getValue());

        else if (variable instanceof BooleanVariable)
            System.out.println(((BooleanVariable) variable).getValue());

        else if (variable instanceof StringVariable)
            System.out.println(((StringVariable) variable).getValue());

        else if (variable instanceof CharVariable)
            System.out.println(((CharVariable) variable).getValue());
    }

}
