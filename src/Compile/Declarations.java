package Compile;

import DataTypes.IntegerVariable;
import DataTypes.Token;

import java.util.ArrayList;

public class Declarations {


    public static void declareInteger(ArrayList<Token> tokens)
    {
        for (int i = 3; i < tokens.size(); i++)
        {

        }


        Mapper.addToIntMap(
                new IntegerVariable(
                        tokens.get(1).getContent(),
                        Integer.parseInt(tokens.get(3).getContent())
                )
        );
    }


}
