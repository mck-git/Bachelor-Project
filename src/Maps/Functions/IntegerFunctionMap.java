package Maps.Functions;

import DataTypes.Functions.IntegerFunction;
import DataTypes.Functions.VoidFunction;
import DataTypes.Token;

import java.util.ArrayList;
import java.util.HashMap;

public class IntegerFunctionMap {

    private static HashMap integerFunctionMap = new HashMap<String,ArrayList<ArrayList<Token>>>();


    public static void add(String name, IntegerFunction integerFunction)
    {
        integerFunctionMap.put(name,integerFunction);
    }

    public static IntegerFunction find(String name)
    {
        return (IntegerFunction) integerFunctionMap.get(name);
    }

    public static int size()
    {
        return integerFunctionMap.size();
    }

    /**
     * FOR TESTING PURPOSES ONLY! DO NOT USE
     */
    public static void clear()
    {
        integerFunctionMap.clear();
    }

}
