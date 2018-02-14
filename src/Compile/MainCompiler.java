package Compile;

import DataTypes.Token;
import Parser.Lexer;

import java.util.ArrayList;


public class MainCompiler {

    public static void main(String[] args) throws Exception
    {
        Lexer.read("test_program");
    }

    public static void handleLine(ArrayList<Token> tokens)
    {
        if (tokens.isEmpty())
            return;

        switch (tokens.get(0).getContent())
        {
            case "int":
                Declarations.declareInteger(tokens);
                break;


            case "print":
                Printer.print(tokens);
                break;
        }

//        switch (words[0])
//        {
//            case "int":
//                System.out.println("int declaration!");
//                Mapper.addToIntMap(
//                        new IntegerVariable(words[1],Integer.parseInt(words[3]))
//                );
//                break;
//
//            case "print":
//                System.out.println("print statement!");
//                Object[] val = Mapper.findVariable(words[1]);
//                if ( val[0] == ("int") )
//                {
//
//                    IntegerVariable v =  (IntegerVariable) val[1];
//
//                    System.out.println(
//                            v.getValue()
//                    );
//                }
//                break;
//
//        }


    }


}
