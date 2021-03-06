package Maps.Functions;

import DataTypes.Token;
import DataTypes.Functions.VoidFunction;

import java.util.ArrayList;
import java.util.HashMap;

public class VoidFunctionMap {

    private static HashMap voidFunctionMap = new HashMap<String,ArrayList<ArrayList<Token>>>();


    public static void add(String name, VoidFunction vf)
    {
        voidFunctionMap.put(name,vf);
    }

    public static VoidFunction find(String name)
    {
        return (VoidFunction) voidFunctionMap.get(name);
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
