package Compile;

import DataTypes.IntegerVariable;
import DataTypes.StringVariable;
import DataTypes.Token;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Declarations {


    public static void declareInteger(ArrayList<Token> tokens)
    {
        int num = 0;
        MathOperation op = MathOperation.PLUS;


        for (int i = 3; i < tokens.size(); i++)
        {
            String s = tokens.get(i).getContent();

            if ( s.equals("+") )
                op = MathOperation.PLUS;

            else if ( s.equals("-") )
                op = MathOperation.MINUS;

            else if ( s.equals("*"))
                op = MathOperation.MULT;

            else if ( s.equals("/"))
                op = MathOperation.DIVISION;

            else if (isNumeric(s))
            {
                switch (op) {
                    case PLUS:
                        num = num + Integer.parseInt(s);
                        break;
                    case MINUS:
                        num = num - Integer.parseInt(s);
                        break;
                    case MULT:
                        num = num * Integer.parseInt(s);
                        break;
                    case DIVISION:
                        num = num / Integer.parseInt(s);
                        break;
                }
            }

        }


        Mapper.addToIntMap(
                new IntegerVariable(
                        tokens.get(1).getContent(),
                        num
                )
        );
    }

    public static void declareString(ArrayList<Token> tokens)
    {
        Mapper.addToStringMap(
                new StringVariable(
                        tokens.get(1).getContent(),
                        tokens.get(3).getContent()
                )
        );
    }





    private static boolean isNumeric(String s)
    {
        try {
            Integer.parseInt(s);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


}
