package Compile;

import DataTypes.Functions.FunctionContainer;
import DataTypes.Lists.*;
import DataTypes.Token;
import DataTypes.Variables.*;
import Errors.InvalidSyntaxException;
import Maps.Lists.BooleanListMap;
import Maps.Lists.CharListMap;
import Maps.Lists.IntegerListMap;
import Maps.Lists.StringListMap;
import Parser.Lexer;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ListHandler {

    public static Variable findGenericListValue (ArrayList<Token> tokens) throws InvalidSyntaxException
    {
        boolean foundList = false;

        int indexStart = 0;

        ListContainer lc = null;

        for (int i = 0; i < tokens.size(); i++)
        {
            String tokenContent = tokens.get(i).getContent();

            if (!foundList)
            {
                lc = Mapper.findList(tokenContent);

                if (lc == null) {
                    continue;
                }

                foundList = true;
                indexStart = i;
            }

            else if (tokenContent.equals("]"))
            {
                ArrayList<Token> subList = new ArrayList<>(tokens.subList(indexStart, i));

                if (lc.getType().equals("int"))
                    return new IntegerVariable("",
                        findIntegerListIndex(subList));

                if (lc.getType().equals("string"))
                    return new StringVariable("",
                        findStringListIndex(subList));

                if (lc.getType().equals("char"))
                    return new CharVariable("",
                        findCharListIndex(subList));

                if (lc.getType().equals("boolean"))
                    return new BooleanVariable("",
                        findBooleanListIndex(subList));
            }

        }

        return null;

//        throw new InvalidSyntaxException(Lexer.getLineNumber());

    }

    public static int findIntegerListIndex(ArrayList<Token> tokens) throws InvalidSyntaxException, NullPointerException
    {

        IntegerList foundList = null;

        for (Token t : tokens)
        {
            String tokenContent = t.getContent();

            if (foundList == null)
                foundList = IntegerListMap.find(tokenContent);

            else if (Calculations.isNumeric(tokenContent))
                return foundList.get(Integer.parseInt(tokenContent));

            try
            {
                IntegerVariable integerVariable = (IntegerVariable) Mapper.findVariable(tokenContent).getVariable();

                if (integerVariable != null)
                    return foundList.get(integerVariable.getValue());

            } catch (NullPointerException ignored) {}

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

            try
            {
                IntegerVariable integerVariable = (IntegerVariable) Mapper.findVariable(tokenContent).getVariable();

                if (integerVariable != null)
                    return foundList.get(integerVariable.getValue());

            } catch (NullPointerException ignored) {}
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

            try
            {
                IntegerVariable integerVariable = (IntegerVariable) Mapper.findVariable(tokenContent).getVariable();

                if (integerVariable != null)
                    return foundList.get(integerVariable.getValue());

            } catch (NullPointerException ignored) {}

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

            try
            {
                IntegerVariable integerVariable = (IntegerVariable) Mapper.findVariable(tokenContent).getVariable();

                if (integerVariable != null)
                    return foundList.get(integerVariable.getValue());

            } catch (NullPointerException ignored) {}

        }

        throw new InvalidSyntaxException(Lexer.getLineNumber());
    }

}
