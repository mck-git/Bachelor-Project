package Maps.Functions;

import DataTypes.Functions.BooleanFunction;
import DataTypes.Token;

import java.util.ArrayList;
import java.util.HashMap;

public class BooleanFunctionMap {

    private static HashMap booleanFunctionMap = new HashMap<String,ArrayList<ArrayList<Token>>>();


    public static void add(String name, BooleanFunction booleanFunction)
    {
        booleanFunctionMap.put(name,booleanFunction);
    }

    public static BooleanFunction find(String name)
    {
        return (BooleanFunction) booleanFunctionMap.get(name);
    }

    public static int size()
    {
        return booleanFunctionMap.size();
    }

    /**
     * FOR TESTING PURPOSES ONLY! DO NOT USE
     */
    public static void clear()
    {
        booleanFunctionMap.clear();
    }

}
