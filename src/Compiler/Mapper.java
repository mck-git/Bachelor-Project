package Compiler;

import DataTypes.IntegerVariable;
import Maps.IntegerMap;

public class Mapper {

    public static void addToIntMap(IntegerVariable iv)
    {
        IntegerMap.add(iv.getName(),iv);
    }

    public static Object[] findVariable(String varname)
    {
        Object result;

        result = IntegerMap.findVal(varname);

        if (result != null)
        {
            return new Object[]{"int",result};
        }

        return null;
    }


}
