package Compile;

import DataTypes.*;
import Maps.BooleanMap;
import Maps.CharMap;
import Maps.IntegerMap;
import Maps.StringMap;

public class Mapper {

    // INTEGERS
    public static void addToIntMap(IntegerVariable iv)
    {
        IntegerMap.add(iv.getName(),iv);
    }

    public static void redefineInt(IntegerVariable iv)
    {
        IntegerMap.edit(iv.getName(),iv);
    }


    // STRINGS
    public static void addToStringMap(StringVariable sv)
    {
        StringMap.add(sv.getName(), sv);
    }

    public static void redefineString(StringVariable sv)
    {
        StringMap.edit(sv.getName(),sv);
    }


    // CHARS
    public static void addToCharMap(CharVariable cv)
    {
        CharMap.add(cv.getName(), cv);
    }

    public static void redefineChar(CharVariable cv)
    {
        CharMap.edit(cv.getName(),cv);
    }


    // BOOLEANS
    public static void addToBooleanMap(BooleanVariable bv)
    {
        BooleanMap.add(bv.getName(),bv);
    }

    public static void redefineBoolean(BooleanVariable bv)
    {
        BooleanMap.edit(bv.getName(),bv);
    }

    // AUX FUNCTIONS
    public static SearchedVariable findVariable(String varname)
    {
        Object result;

        result = IntegerMap.find(varname);

        if (result != null)
            return new SearchedVariable("int", result);

        result = CharMap.find(varname);

        if (result != null)
            return new SearchedVariable("char",result);

        result = StringMap.find(varname);

        if (result != null)
            return new SearchedVariable("string",result);

        result = BooleanMap.find(varname);

        if (result != null)
            return new SearchedVariable("boolean",result);

        return null;
    }


}
