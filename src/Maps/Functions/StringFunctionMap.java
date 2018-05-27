package Maps.Functions;

import DataTypes.Functions.StringFunction;
import DataTypes.Token;

import java.util.ArrayList;
import java.util.HashMap;

public class StringFunctionMap {

    private static HashMap stringFunctionMap = new HashMap<String,ArrayList<ArrayList<Token>>>();


    public static void add(String name, StringFunction stringFunction)
    {
        stringFunctionMap.put(name,stringFunction);
    }

    public static StringFunction find(String name)
    {
        return (StringFunction) stringFunctionMap.get(name);
    }

    public static int size()
    {
        return stringFunctionMap.size();
    }

    /**
     * FOR TESTING PURPOSES ONLY! DO NOT USE
     */
    public static void clear()
    {
        stringFunctionMap.clear();
    }

}
