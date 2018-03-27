package Maps;

import DataTypes.IntegerVariable;

import java.util.HashMap;

public class IntegerMap {

    private static HashMap intMap = new HashMap<String, IntegerVariable>();


    public static void add(String name, IntegerVariable iv)
    {
        intMap.put(name,iv);
    }

    public static void edit(String name, IntegerVariable iv)
    {
        intMap.replace(name, iv);
    }

    public static IntegerVariable find(String name)
    {
        return (IntegerVariable) intMap.get(name);
    }

    public static int size()
    {
        return intMap.size();
    }


    /**
     * FOR TESTING PURPOSES ONLY! DO NOT USE
     */
    public static void clear()
    {
        intMap.clear();
    }
}
