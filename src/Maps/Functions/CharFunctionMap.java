package Maps.Functions;

import DataTypes.Functions.CharFunction;
import DataTypes.Token;

import java.util.ArrayList;
import java.util.HashMap;

public class CharFunctionMap {

    private static HashMap charFunctionMap = new HashMap<String,ArrayList<ArrayList<Token>>>();


    public static void add(String name, CharFunction charFunction)
    {
        charFunctionMap.put(name,charFunction);
    }

    public static CharFunction find(String name)
    {
        return (CharFunction) charFunctionMap.get(name);
    }

    public static int size()
    {
        return charFunctionMap.size();
    }

    /**
     * FOR TESTING PURPOSES ONLY! DO NOT USE
     */
    public static void clear()
    {
        charFunctionMap.clear();
    }

}
