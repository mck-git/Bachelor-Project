package Compile;

import DataTypes.CharVariable;
import DataTypes.IntegerVariable;
import DataTypes.StringVariable;
import DataTypes.Token;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Declarations {


    public static void declareInteger(ArrayList<Token> tokens)
    {
        int num = evaluateMath(tokens);


        Mapper.addToIntMap(
                new IntegerVariable(
                        tokens.get(1).getContent(),
                        num
                )
        );
    }

    private static int evaluateMath(ArrayList<Token> tokens) {
        int num = 0;
        MathOperation op = MathOperation.PLUS;


        for (int i = 0; i < tokens.size(); i++)
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
        return num;
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

    public static void declareChar(ArrayList<Token> tokens)
    {
        Mapper.addToCharMap(
                new CharVariable(
                        tokens.get(1).getContent(),
                        tokens.get(3).getContent().charAt(0)
                )
        );
    }


    public static void redefineVariable(ArrayList<Token> tokens)
    {
        Object[] val = Mapper.findVariable(tokens.get(0).getContent());

        if (val == null)
            return;

        switch ((String) val[0])
        {
            case "int":
                Mapper.redefineInt(
                        new IntegerVariable(
                                tokens.get(0).getContent(),
                                evaluateMath(tokens)
                        )
                );
                break;

            case "string":
                Mapper.redefineString(
                        new StringVariable(
                                tokens.get(0).getContent(),
                                tokens.get(2).getContent()
                        )
                );
                break;

            case "char":
                Mapper.redefineChar(
                        new CharVariable(
                                tokens.get(0).getContent(),
                                tokens.get(2).getContent().charAt(0)
                        )
                );
                break;
        }



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
