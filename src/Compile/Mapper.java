package Compile;

import DataTypes.IntegerVariable;
import DataTypes.StringVariable;
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

    public static Object[] findVariable(String varname)
    {
        Object result;

        result = IntegerMap.findVal(varname);

        if (result != null)
        {
            return new Object[]{"int",result};
        }

        result = StringMap.findVal(varname);

        if (result != null)
        {
            return new Object[]{"string",result};
        }

        return null;
    }


}
