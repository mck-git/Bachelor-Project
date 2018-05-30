package Maps.Lists;

import DataTypes.Lists.BooleanList;

import java.util.HashMap;

public class BooleanListMap {

    private static HashMap booleanListMap = new HashMap<String,BooleanList>();


    public static void add(String name, BooleanList integerList)
    {
        booleanListMap.put(name,integerList);
    }

    public static BooleanList find(String name)
    {
        return (BooleanList) booleanListMap.get(name);
    }

    public static int size()
    {
        return booleanListMap.size();
    }

    /**
     * FOR TESTING PURPOSES ONLY! DO NOT USE
     */
    public static void clear()
    {
        booleanListMap.clear();
    }

}
