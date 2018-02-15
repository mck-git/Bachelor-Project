package Compile;

import DataTypes.CharVariable;
import DataTypes.IntegerVariable;
import DataTypes.StringVariable;
import DataTypes.Token;
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
                    System.out.print("[out]: ");



                Object[] val = Mapper.findVariable(t.getContent());
                if (val == null)
                    continue;

                switch (val[0].toString())
                {
                    case "int" :
                        printIntVariable(val[1]);
                        break;

                    case "char" :
                        printCharVariable(val[1]);
                        break;

                    case "string" :
                        printStringVariable(val[1]);
                        break;
                }
            }

            else
                System.out.println(t.getContent());
        }

    }

    private static void printIntVariable(Object o) {
        IntegerVariable v =  (IntegerVariable) o;

        System.out.println(
                v.getValue()
        );
    }

    private static void printCharVariable(Object o) {
        CharVariable v = (CharVariable) o;

        System.out.println(
                v.getValue()
        );
    }

    private static void printStringVariable(Object o) {
        StringVariable v = (StringVariable) o;

        System.out.println(
                v.getValue()
        );
    }
}
