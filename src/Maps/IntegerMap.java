package Maps;

import DataTypes.IntegerVariable;

import java.util.HashMap;

public class IntegerMap {

    static HashMap intMap = new HashMap<String, IntegerVariable>();


    public static void add(String name, IntegerVariable iv)
    {
        intMap.put(name,iv);
    }

    public static IntegerVariable findVal(String name)
    {
        return (IntegerVariable) intMap.get(name);
    }

}
