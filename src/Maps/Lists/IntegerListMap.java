package Maps.Lists;

import DataTypes.Lists.IntegerList;

import java.util.HashMap;

public class IntegerListMap {

    private static HashMap integerListMap = new HashMap<String,IntegerList>();


    public static void add(String name, IntegerList integerList)
    {
        integerListMap.put(name,integerList);
    }

    public static IntegerList find(String name)
    {
        return (IntegerList) integerListMap.get(name);
    }

    public static int size()
    {
        return integerListMap.size();
    }

    /**
     * FOR TESTING PURPOSES ONLY! DO NOT USE
     */
    public static void clear()
    {
        integerListMap.clear();
    }

}
