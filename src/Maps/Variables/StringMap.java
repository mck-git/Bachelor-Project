package Maps.Variables;

import DataTypes.Variables.StringVariable;

import java.util.HashMap;

public class StringMap {

    private static HashMap stringMap = new HashMap<String, StringVariable>();


    public static void add(String name, StringVariable stringVariable)
    {
        stringMap.put(name,stringVariable);
    }

    public static void edit(String name, StringVariable stringVariable)
    {
        stringMap.replace(name, stringVariable);
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
