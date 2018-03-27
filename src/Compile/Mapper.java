package Compile;

import DataTypes.*;
import DataTypes.Functions.IntegerFunction;
import DataTypes.Functions.VoidFunction;
import DataTypes.Variables.BooleanVariable;
import DataTypes.Variables.CharVariable;
import DataTypes.Variables.IntegerVariable;
import DataTypes.Variables.StringVariable;
import Maps.Functions.IntegerFunctionMap;
import Maps.Functions.VoidFunctionMap;
import Maps.Variables.BooleanMap;
import Maps.Variables.CharMap;
import Maps.Variables.IntegerMap;
import Maps.Variables.StringMap;

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


    // VOID FUNCTIONS
    public static void storeVoidFunction(VoidFunction voidFunction)
    {
        VoidFunctionMap.add(voidFunction.getName(),voidFunction);
    }

    // INT FUNCTIONS
    public static void storeIntFunction(IntegerFunction integerFunction)
    {
        IntegerFunctionMap.add(integerFunction.getName(), integerFunction);
    }

    // AUX FUNCTIONS
    public static VariableContainer findVariable(String varname)
    {
        Object result;

        result = IntegerMap.find(varname);

        if (result != null)
            return new VariableContainer("int", result);

        result = CharMap.find(varname);

        if (result != null)
            return new VariableContainer("char",result);

        result = StringMap.find(varname);

        if (result != null)
            return new VariableContainer("string",result);

        result = BooleanMap.find(varname);

        if (result != null)
            return new VariableContainer("boolean",result);

        return null;
    }


    /**
     * FOR TESTING PURPOSES ONLY! DO NOT USE
     */
    public static void clearMaps() {
        IntegerMap.clear();
        BooleanMap.clear();
        StringMap.clear();
        CharMap.clear();
        VoidFunctionMap.clear();


    }

}
