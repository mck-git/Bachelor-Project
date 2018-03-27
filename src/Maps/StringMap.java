package Maps;

import DataTypes.StringVariable;

import java.util.HashMap;

public class StringMap {

    private static HashMap stringMap = new HashMap<String, StringVariable>();


    public static void add(String name, StringVariable iv)
    {
        stringMap.put(name,iv);
    }

    public static void edit(String name, StringVariable sv)
    {
        stringMap.replace(name, sv);
    }

    public static StringVariable find(String name)
    {
        return (StringVariable) stringMap.get(name);
    }

    public static int size()
    {
        return stringMap.size();
    }

    /**
     * FOR TESTING PURPOSES ONLY! DO NOT USE
     */
    public static void clear()
    {
        stringMap.clear();
    }

}
