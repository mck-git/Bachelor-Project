package Maps;

import DataTypes.CharVariable;

import java.util.HashMap;

public class CharMap {

    static HashMap charMap = new HashMap<String, CharVariable>();


    public static void add(String name, CharVariable iv)
    {
        charMap.put(name,iv);
    }

    public static CharVariable findVal(String name)
    {
        return (CharVariable) charMap.get(name);
    }


}
