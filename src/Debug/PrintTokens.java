package Debug;

import DataTypes.Token;

import java.util.ArrayList;

public class PrintTokens {


    public static void printTokens(ArrayList<Token> tokens)
    {
        System.out.println("Tokens: ");
        for (Token t : tokens)
            System.out.println(t.getContent());
    }
}
