package Maps;

import DataTypes.BooleanVariable;

import java.util.HashMap;

public class BooleanMap {

    private static HashMap booleanMap = new HashMap<String, BooleanVariable>();


    public static void add(String name, BooleanVariable bv)
    {
        booleanMap.put(name,bv);
    }

    public static void edit(String name, BooleanVariable bv)
    {
        booleanMap.replace(name,bv);
    }

    public static BooleanVariable find(String name)
    {
        return (BooleanVariable) booleanMap.get(name);
    }

    public static int size()
    {
        return booleanMap.size();
    }


    /**
     * FOR TESTING PURPOSES ONLY! DO NOT USE
     */
    public static void clear()
    {
        booleanMap.clear();
    }
}
