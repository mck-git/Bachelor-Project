package Maps.Functions;

import DataTypes.Functions.VoidFunction;
import DataTypes.Token;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.IntFunction;

public class IntegerFunctionMap {

    private static HashMap intFunctionMap = new HashMap<String,ArrayList<ArrayList<Token>>>();


    public static void add(String name, VoidFunction vf)
    {
        intFunctionMap.put(name,vf);
    }

    public static VoidFunction find(String name)
    {
        return (IntFunction) voidFunctionMap.get(name);
    }

    public static int size()
    {
        return voidFunctionMap.size();
    }

    /**
     * FOR TESTING PURPOSES ONLY! DO NOT USE
     */
    public static void clear()
    {
        voidFunctionMap.clear();
    }

}
