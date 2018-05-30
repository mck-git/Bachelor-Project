package Maps.Lists;

import DataTypes.Lists.StringList;

import java.util.HashMap;

public class StringListMap {

    private static HashMap stringListMap = new HashMap<String,StringList>();


    public static void add(String name, StringList stringList)
    {
        stringListMap.put(name,stringList);
    }

    public static StringList find(String name)
    {
        return (StringList) stringListMap.get(name);
    }

    public static int size()
    {
        return stringListMap.size();
    }

    /**
     * FOR TESTING PURPOSES ONLY! DO NOT USE
     */
    public static void clear()
    {
        stringListMap.clear();
    }


}
