package Compile;

import DataTypes.*;
import SharedResources.InputType;

import java.util.ArrayList;

public class Printer {

    public static void print(ArrayList<Token> tokens) throws NullPointerException
    {
        for (Token t : tokens)
        {
            if (t.getInputType() != InputType.STRING)
            {
                if (t.getContent().equals("print"))
                {
                    System.out.print("[out]: ");
                    continue;
                }


                SearchedVariable foundVariable = Mapper.findVariable(t.getContent());
                if (foundVariable == null)
                    continue;

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

            else
                System.out.println(t.getContent());
        }

    }

    private static void printIntVariable(Object o)
    {
        IntegerVariable v =  (IntegerVariable) o;

        System.out.println(
                v.getValue()
        );
    }

    private static void printCharVariable(Object o)
    {
        CharVariable v = (CharVariable) o;

        System.out.println(
                v.getValue()
        );
    }

    private static void printStringVariable(Object o)
    {
        StringVariable v = (StringVariable) o;

        System.out.println(
                v.getValue()
        );
    }

    private static void printBooleanVariable(Object o)
    {
        BooleanVariable v = (BooleanVariable) o;

        System.out.println(
                v.getValue()
        );
    }
}
