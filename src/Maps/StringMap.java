package Maps;

import DataTypes.StringVariable;

import java.util.HashMap;

public class StringMap {

    static HashMap stringMap = new HashMap<String, StringVariable>();


    public static void add(String name, StringVariable iv)
    {
        stringMap.put(name,iv);
    }

    public static StringVariable findVal(String name)
    {
        return (StringVariable) stringMap.get(name);
    }


}
