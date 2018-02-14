package Compile;

import DataTypes.IntegerVariable;
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
                    continue;



                Object[] val = Mapper.findVariable(t.getContent());
                if ( val != null && val[0] == ("int") )
                {

                    IntegerVariable v =  (IntegerVariable) val[1];

                    System.out.println(
                            v.getValue()
                    );
                }
            }

            else
                System.out.println(t.getContent());


        }

    }
}
