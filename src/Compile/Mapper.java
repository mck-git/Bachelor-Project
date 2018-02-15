package Compile;

import DataTypes.CharVariable;
import DataTypes.IntegerVariable;
import DataTypes.StringVariable;
import Maps.CharMap;
import Maps.IntegerMap;
import Maps.StringMap;

public class Mapper {

    public static void addToIntMap(IntegerVariable iv)
    {
        IntegerMap.add(iv.getName(),iv);
    }

    public static void addToStringMap(StringVariable sv)
    {
        StringMap.add(sv.getName(), sv);
    }

    public static void addToCharMap(CharVariable cv)
    {
        CharMap.add(cv.getName(), cv);
    }

    public static Object[] findVariable(String varname)
    {
        Object result;

        result = IntegerMap.findVal(varname);

        if (result != null)
            return new Object[]{"int",result};

        result = CharMap.findVal(varname);

        if (result != null)
            return new Object[]{"char",result};


        result = StringMap.findVal(varname);

        if (result != null)
            return new Object[]{"string",result};

        return null;
    }


}
