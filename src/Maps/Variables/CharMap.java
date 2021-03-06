package Maps.Variables;

import DataTypes.Variables.CharVariable;

import java.util.HashMap;

public class CharMap {

    private static HashMap charMap = new HashMap<String, CharVariable>();


    public static void add(String name, CharVariable cv)
    {
        charMap.put(name,cv);
    }

    public static void edit(String name, CharVariable cv)
    {
        charMap.replace(name,cv);
    }

    public static CharVariable find(String name)
    {
        return (CharVariable) charMap.get(name);
    }

    public static int size()
    {
        return charMap.size();
    }


    /**
     * FOR TESTING PURPOSES ONLY! DO NOT USE
     */
    public static void clear()
    {
        charMap.clear();
    }
}
