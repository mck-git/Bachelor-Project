package Compile;

import DataTypes.Lists.BooleanList;
import DataTypes.Lists.CharList;
import DataTypes.Lists.IntegerList;
import DataTypes.Lists.StringList;
import DataTypes.Token;
import Errors.InvalidSyntaxException;
import Maps.Lists.BooleanListMap;
import Maps.Lists.CharListMap;
import Maps.Lists.IntegerListMap;
import Maps.Lists.StringListMap;
import Parser.Lexer;

import java.util.ArrayList;

public class ListHandler {

    public static int findIntegerListIndex(ArrayList<Token> tokens) throws InvalidSyntaxException
    {

        IntegerList foundList = null;

        for (Token t : tokens)
        {
            String tokenContent = t.getContent();

            if (foundList == null)
                foundList = IntegerListMap.find(tokenContent);

            else if (Calculations.isNumeric(tokenContent))
                return foundList.get(Integer.parseInt(tokenContent));

        }


        throw new InvalidSyntaxException(Lexer.getLineNumber());
    }

    public static boolean findBooleanListIndex(ArrayList<Token> tokens) throws InvalidSyntaxException
    {

        BooleanList foundList = null;

        for (Token t : tokens)
        {
            String tokenContent = t.getContent();

            if (foundList == null)
                foundList = BooleanListMap.find(tokenContent);

            else if (Calculations.isNumeric(tokenContent))
                return foundList.get(Integer.parseInt(tokenContent));
        }

        throw new InvalidSyntaxException(Lexer.getLineNumber());
    }

    public static String findStringListIndex(ArrayList<Token> tokens) throws InvalidSyntaxException
    {

        StringList foundList = null;

        for (Token t : tokens)
        {
            String tokenContent = t.getContent();

            if (foundList == null)
                foundList = StringListMap.find(tokenContent);

            else if (Calculations.isNumeric(tokenContent))
                return foundList.get(Integer.parseInt(tokenContent));

        }

        throw new InvalidSyntaxException(Lexer.getLineNumber());
    }

    public static char findCharListIndex(ArrayList<Token> tokens) throws InvalidSyntaxException
    {

        CharList foundList = null;

        for (Token t : tokens)
        {
            String tokenContent = t.getContent();

            if (foundList == null)
                foundList = CharListMap.find(tokenContent);

            else if (Calculations.isNumeric(tokenContent))
                return foundList.get(Integer.parseInt(tokenContent));

        }

        throw new InvalidSyntaxException(Lexer.getLineNumber());
    }

}
