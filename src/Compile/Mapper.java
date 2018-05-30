package Compile;

import DataTypes.Functions.*;
import DataTypes.Lists.BooleanList;
import DataTypes.Lists.CharList;
import DataTypes.Lists.IntegerList;
import DataTypes.Lists.StringList;
import DataTypes.Variables.*;
import Maps.Functions.*;
import Maps.Lists.BooleanListMap;
import Maps.Lists.CharListMap;
import Maps.Lists.IntegerListMap;
import Maps.Lists.StringListMap;
import Maps.Variables.*;

import java.util.ArrayList;
import java.util.NoSuchElementException;

import static Compile.FunctionExecutor.functionsExecuting;

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

    // CHAR FUNCTIONS
    public static void storeCharFunction(CharFunction charFunction)
    {
        CharFunctionMap.add(charFunction.getName(), charFunction);
    }

    // STRING FUNCTIONS
    public static void storeStringFunction(StringFunction stringFunction)
    {
        StringFunctionMap.add(stringFunction.getName(), stringFunction);
    }

    // BOOLEAN FUNCTIONS
    public static void storeBooleanFunction(BooleanFunction booleanFunction)
    {
        BooleanFunctionMap.add(booleanFunction.getName(), booleanFunction);
    }

    // LISTS
    public static void storeIntegerList(IntegerList integerList)
    {
        IntegerListMap.add(integerList.getName(), integerList);
    }

    public static void storeCharList(CharList charList)
    {
        CharListMap.add(charList.getName(), charList);
    }

    public static void storeStringList(StringList stringList)
    {
        StringListMap.add(stringList.getName(), stringList);
    }

    public static void storeBooleanList(BooleanList booleanList)
    {
        BooleanListMap.add(booleanList.getName(), booleanList);
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

        try
        {

            Function fc = functionsExecuting.getFirst();

            ArrayList<Variable> arguments = fc.getArgumentVariables();

            for (Variable v : arguments)
            {
                if (v.getName().equals(varname)) {
                    result = TemporaryVariablesMap.find(varname);
                }
            }


            if (result != null)
            {
                if (result instanceof IntegerVariable)
                    return new VariableContainer("int",result);

                if (result instanceof CharVariable)
                    return new VariableContainer("char",result);

                if (result instanceof BooleanVariable)
                    return new VariableContainer("boolean",result);

                if (result instanceof StringVariable)
                    return new VariableContainer("string",result);
            }


        } catch (NoSuchElementException ignored) {}

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

        result = BooleanFunctionMap.find(name);

        if (result != null)
            return new FunctionContainer("boolean",result);

        result = StringFunctionMap.find(name);

        if (result != null)
            return new FunctionContainer("string",result);

        result = CharFunctionMap.find(name);

        if (result != null)
            return new FunctionContainer("char",result);


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
        TemporaryVariablesMap.clear();

        VoidFunctionMap.clear();
        IntegerFunctionMap.clear();
        CharFunctionMap.clear();
        BooleanFunctionMap.clear();
        StringFunctionMap.clear();

        IntegerListMap.clear();
        CharListMap.clear();
        StringListMap.clear();
        BooleanListMap.clear();
    }

}
