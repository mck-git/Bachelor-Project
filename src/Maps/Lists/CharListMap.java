package Maps.Lists;

import DataTypes.Lists.CharList;

import java.util.HashMap;

public class CharListMap {

    private static HashMap charListMap = new HashMap<String,CharList>();


    public static void add(String name, CharList charList)
    {
        charListMap.put(name,charList);
    }

    public static CharList find(String name)
    {
        return (CharList) charListMap.get(name);
    }

    public static int size()
    {
        return charListMap.size();
    }

    /**
     * FOR TESTING PURPOSES ONLY! DO NOT USE
     */
    public static void clear()
    {
        charListMap.clear();
    }

}
