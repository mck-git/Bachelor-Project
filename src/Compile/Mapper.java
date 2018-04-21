package Compile;

import DataTypes.Functions.Function;
import DataTypes.Functions.FunctionContainer;
import DataTypes.Functions.IntegerFunction;
import DataTypes.Functions.VoidFunction;
import DataTypes.Variables.*;
import Maps.Functions.IntegerFunctionMap;
import Maps.Functions.VoidFunctionMap;
import Maps.Variables.*;

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
        Variable result;

        result = IntegerMap.find(varname);

        if (result != null)
            return new VariableContainer("int",result);

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


    public static VariableContainer findReturnValue()
    {
        Variable result = TemporaryVariablesMap.find("returnValue");

        if (result instanceof IntegerVariable)
            return new VariableContainer("int", result);

        return null;
    }

    public static FunctionContainer findFunction(String name)
    {
        Function result;

        result = VoidFunctionMap.find(name);

        if (result != null)
            return new FunctionContainer("void",result);

        result = IntegerFunctionMap.find(name);

        if (result != null)
            return new FunctionContainer("int",result);

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
        IntegerFunctionMap.clear();


    }

}
