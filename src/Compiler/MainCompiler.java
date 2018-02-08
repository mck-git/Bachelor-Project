package Compiler;

import DataTypes.IntegerVariable;

public class MainCompiler {

    public static void main(String[] args) throws Exception
    {
        Parser.read("test_program");
    }

    public static void handleLine(String line)
    {
        String[] words = line.split("\\s+");

        switch (words[0])
        {
            case "int":
                System.out.println("int declaration!");
                Mapper.addToIntMap(
                        new IntegerVariable(words[1],Integer.parseInt(words[3]))
                );
                break;

            case "print":
                System.out.println("print statement!");
                Object[] val = Mapper.findVariable(words[1]);
                if ( val[0] == ("int") )
                {

                    IntegerVariable v =  (IntegerVariable) val[1];

                    System.out.println(
                            v.getValue()
                    );
                }
                break;

        }


    }


}
