package Compile;

import DataTypes.*;
import Maps.BooleanMap;
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
                        printIntVariable(foundVariable.getValue());
                        break;

                    case "char" :
                        printCharVariable(foundVariable.getValue());
                        break;

                    case "string" :
                        printStringVariable(foundVariable.getValue());
                        break;

                    case "boolean" :
                        printBooleanVariable(foundVariable.getValue());
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
