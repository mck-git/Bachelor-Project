package Compile;

import DataTypes.*;
import DataTypes.Functions.Function;
import DataTypes.Functions.FunctionContainer;
import DataTypes.Functions.VoidFunction;
import DataTypes.Lists.IntegerList;
import DataTypes.Lists.List;
import DataTypes.Variables.*;
import Errors.InvalidSyntaxException;
import Maps.Lists.BooleanListMap;
import Maps.Lists.CharListMap;
import Maps.Lists.IntegerListMap;
import Maps.Lists.StringListMap;
import Maps.Variables.BooleanMap;
import Maps.Variables.CharMap;
import Maps.Variables.IntegerMap;
import Maps.Variables.StringMap;
import SharedResources.InputType;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class DeclarationsTest {

    // VARIABLES

    @Test
    void declareInteger() throws Exception
    {
        Mapper.clearMaps();
        ArrayList<Token> line = new ArrayList<>();

        line.add(new Token("int", InputType.NORMAL));
        line.add(new Token("i", InputType.NORMAL));
        line.add(new Token("=", InputType.NORMAL));
        line.add(new Token("2", InputType.NORMAL));

        Translator.handleLine(line);
//        Declarations.declareInteger(line);

        assertEquals(1, IntegerMap.size());

        VariableContainer foundA = Mapper.findVariable("i");

        assert foundA != null;
        assertEquals("int", foundA.getType());

        IntegerVariable var = (IntegerVariable) foundA.getVariable();

        assertEquals(2, var.getValue());


    }

    @Test
    void declareString() throws Exception
    {
        Mapper.clearMaps();
        ArrayList<Token> line = new ArrayList<>();

        line.add(new Token("string", InputType.NORMAL));
        line.add(new Token("s", InputType.NORMAL));
        line.add(new Token("=", InputType.NORMAL));
        line.add(new Token("Hello World!", InputType.STRING));

        Translator.handleLine(line);
//        Declarations.declareString(line);

        assertEquals(1, StringMap.size());


        VariableContainer foundA = Mapper.findVariable("s");

        assert foundA != null;
        assertEquals("string", foundA.getType());

        StringVariable var = (StringVariable) foundA.getVariable();

        assertEquals("Hello World!", var.getValue());
    }

    @Test
    void declareChar() throws Exception
    {
        Mapper.clearMaps();
        ArrayList<Token> line = new ArrayList<>();

        line.add(new Token("char", InputType.NORMAL));
        line.add(new Token("c", InputType.NORMAL));
        line.add(new Token("=", InputType.NORMAL));
        line.add(new Token("d", InputType.NORMAL));

        Translator.handleLine(line);
//        Declarations.declareChar(line);

        assertEquals(1, CharMap.size());

        VariableContainer foundA = Mapper.findVariable("c");

        assert foundA != null;
        assertEquals("char", foundA.getType());

        CharVariable var = (CharVariable) foundA.getVariable();

        assertEquals('d', var.getValue());
    }

    @Test
    void declareBoolean() throws Exception
    {
        Mapper.clearMaps();
        ArrayList<Token> line = new ArrayList<>();

        line.add(new Token("boolean", InputType.NORMAL));
        line.add(new Token("bool", InputType.NORMAL));
        line.add(new Token("=", InputType.NORMAL));
        line.add(new Token("true", InputType.NORMAL));

        Translator.handleLine(line);
//        Declarations.declareBoolean(line);

        assertEquals(1, BooleanMap.size());

        VariableContainer foundA = Mapper.findVariable("bool");

        assert foundA != null;
        assertEquals("boolean", foundA.getType());

        BooleanVariable var = (BooleanVariable) foundA.getVariable();

        assertEquals(true, var.getValue());
    }


    // FUNCTIONS

    @Test
    void declareVoidFunction() throws InvalidSyntaxException
    {
        Mapper.clearMaps();
        ArrayList<Token> line1 = new ArrayList<>();
        ArrayList<Token> line2 = new ArrayList<>();
        ArrayList<Token> line3 = new ArrayList<>();

        line1.add(new Token("func", InputType.NORMAL));
        line1.add(new Token("void", InputType.NORMAL));
        line1.add(new Token("test", InputType.NORMAL));
        line1.add(new Token("(", InputType.NORMAL));
        line1.add(new Token(")", InputType.NORMAL));
        line1.add(new Token("{", InputType.NORMAL));

        line2.add(new Token("print", InputType.NORMAL));
        line2.add(new Token("Hello World", InputType.STRING));

        line3.add(new Token("}", InputType.NORMAL));


        Compile.Translator.handleLine(line1);
        Compile.Translator.handleLine(line2);
        Compile.Translator.handleLine(line3);

        assertEquals(1, Maps.Functions.VoidFunctionMap.size());
    }

    @Test
    void declareVoidFunctionWithArguments() throws InvalidSyntaxException
    {
        Mapper.clearMaps();
        ArrayList<Token> line1 = new ArrayList<>();
        ArrayList<Token> line2 = new ArrayList<>();
        ArrayList<Token> line3 = new ArrayList<>();

        line1.add(new Token("func", InputType.NORMAL));
        line1.add(new Token("void", InputType.NORMAL));
        line1.add(new Token("test", InputType.NORMAL));
        line1.add(new Token("(", InputType.NORMAL));
        line1.add(new Token("int", InputType.NORMAL));
        line1.add(new Token("a", InputType.NORMAL));
        line1.add(new Token(")", InputType.NORMAL));
        line1.add(new Token("{", InputType.NORMAL));

        line2.add(new Token("print", InputType.NORMAL));
        line2.add(new Token("Hello World", InputType.STRING));

        line3.add(new Token("}", InputType.NORMAL));


        Compile.Translator.handleLine(line1);
        Compile.Translator.handleLine(line2);
        Compile.Translator.handleLine(line3);

        assertEquals(1, Maps.Functions.VoidFunctionMap.size());

        FunctionContainer fcTest = Mapper.findFunction("test");

        Function test = fcTest.getFunction();

        ArrayList<Variable> args = test.getArgumentVariables();

        assertEquals(1, args.size());
    }

    @Test
    void declareIntegerFunction() throws InvalidSyntaxException
    {
        Mapper.clearMaps();
        ArrayList<Token> line1 = new ArrayList<>();
        ArrayList<Token> line2 = new ArrayList<>();
        ArrayList<Token> line3 = new ArrayList<>();

        line1.add(new Token("func", InputType.NORMAL));
        line1.add(new Token("int", InputType.NORMAL));
        line1.add(new Token("test", InputType.NORMAL));
        line1.add(new Token("(", InputType.NORMAL));
        line1.add(new Token(")", InputType.NORMAL));
        line1.add(new Token("{", InputType.NORMAL));

        line2.add(new Token("return", InputType.NORMAL));
        line2.add(new Token("2", InputType.NORMAL));

        line3.add(new Token("}", InputType.NORMAL));


        Compile.Translator.handleLine(line1);
        Compile.Translator.handleLine(line2);
        Compile.Translator.handleLine(line3);

        assertEquals(1, Maps.Functions.IntegerFunctionMap.size());
    }

    @Test
    void declareIntegerFunctionWithArguments() throws InvalidSyntaxException
    {
        Mapper.clearMaps();
        ArrayList<Token> line1 = new ArrayList<>();
        ArrayList<Token> line2 = new ArrayList<>();
        ArrayList<Token> line3 = new ArrayList<>();
        ArrayList<Token> line4 = new ArrayList<>();

        line1.add(new Token("func", InputType.NORMAL));
        line1.add(new Token("int", InputType.NORMAL));
        line1.add(new Token("test", InputType.NORMAL));
        line1.add(new Token("(", InputType.NORMAL));
        line1.add(new Token("int", InputType.NORMAL));
        line1.add(new Token("a", InputType.NORMAL));
        line1.add(new Token(")", InputType.NORMAL));
        line1.add(new Token("{", InputType.NORMAL));

        line2.add(new Token("print", InputType.NORMAL));
        line2.add(new Token("Hello World", InputType.STRING));

        line3.add(new Token("return", InputType.NORMAL));
        line3.add(new Token("0", InputType.NORMAL));


        line4.add(new Token("}", InputType.NORMAL));


        Compile.Translator.handleLine(line1);
        Compile.Translator.handleLine(line2);
        Compile.Translator.handleLine(line3);
        Compile.Translator.handleLine(line4);

        assertEquals(1, Maps.Functions.IntegerFunctionMap.size());

        FunctionContainer fcTest = Mapper.findFunction("test");

        Function test = fcTest.getFunction();

        ArrayList<Variable> args = test.getArgumentVariables();

        assertEquals(1, args.size());
    }

    @Test
    void declareBooleanFunction() throws InvalidSyntaxException
    {
        Mapper.clearMaps();
        ArrayList<Token> line1 = new ArrayList<>();
        ArrayList<Token> line2 = new ArrayList<>();
        ArrayList<Token> line3 = new ArrayList<>();

        line1.add(new Token("func", InputType.NORMAL));
        line1.add(new Token("boolean", InputType.NORMAL));
        line1.add(new Token("test", InputType.NORMAL));
        line1.add(new Token("(", InputType.NORMAL));
        line1.add(new Token(")", InputType.NORMAL));
        line1.add(new Token("{", InputType.NORMAL));

        line2.add(new Token("return", InputType.NORMAL));
        line2.add(new Token("true", InputType.NORMAL));

        line3.add(new Token("}", InputType.NORMAL));


        Compile.Translator.handleLine(line1);
        Compile.Translator.handleLine(line2);
        Compile.Translator.handleLine(line3);

        assertEquals(1, Maps.Functions.BooleanFunctionMap.size());
    }

    @Test
    void declareBooleanFunctionWithArguments() throws InvalidSyntaxException
    {
        Mapper.clearMaps();
        ArrayList<Token> line1 = new ArrayList<>();
        ArrayList<Token> line2 = new ArrayList<>();
        ArrayList<Token> line3 = new ArrayList<>();

        line1.add(new Token("func", InputType.NORMAL));
        line1.add(new Token("boolean", InputType.NORMAL));
        line1.add(new Token("test", InputType.NORMAL));
        line1.add(new Token("(", InputType.NORMAL));
        line1.add(new Token("int", InputType.NORMAL));
        line1.add(new Token("b", InputType.NORMAL));
        line1.add(new Token(")", InputType.NORMAL));
        line1.add(new Token("{", InputType.NORMAL));

        line2.add(new Token("return", InputType.NORMAL));
        line2.add(new Token("true", InputType.NORMAL));

        line3.add(new Token("}", InputType.NORMAL));


        Compile.Translator.handleLine(line1);
        Compile.Translator.handleLine(line2);
        Compile.Translator.handleLine(line3);

        assertEquals(1, Maps.Functions.BooleanFunctionMap.size());


        FunctionContainer fcTest = Mapper.findFunction("test");

        Function test = fcTest.getFunction();

        ArrayList<Variable> args = test.getArgumentVariables();

        assertEquals(1, args.size());
    }


    @Test
    void declareStringFunction() throws InvalidSyntaxException
    {
        Mapper.clearMaps();
        ArrayList<Token> line1 = new ArrayList<>();
        ArrayList<Token> line2 = new ArrayList<>();
        ArrayList<Token> line3 = new ArrayList<>();

        line1.add(new Token("func", InputType.NORMAL));
        line1.add(new Token("string", InputType.NORMAL));
        line1.add(new Token("test", InputType.NORMAL));
        line1.add(new Token("(", InputType.NORMAL));
        line1.add(new Token(")", InputType.NORMAL));
        line1.add(new Token("{", InputType.NORMAL));

        line2.add(new Token("return", InputType.NORMAL));
        line2.add(new Token("hello", InputType.STRING));

        line3.add(new Token("}", InputType.NORMAL));


        Compile.Translator.handleLine(line1);
        Compile.Translator.handleLine(line2);
        Compile.Translator.handleLine(line3);

        assertEquals(1, Maps.Functions.StringFunctionMap.size());
    }

    @Test
    void declareStringFunctionWithArguments() throws InvalidSyntaxException
    {
        Mapper.clearMaps();
        ArrayList<Token> line1 = new ArrayList<>();
        ArrayList<Token> line2 = new ArrayList<>();
        ArrayList<Token> line3 = new ArrayList<>();

        line1.add(new Token("func", InputType.NORMAL));
        line1.add(new Token("string", InputType.NORMAL));
        line1.add(new Token("test", InputType.NORMAL));
        line1.add(new Token("(", InputType.NORMAL));
        line1.add(new Token("string", InputType.NORMAL));
        line1.add(new Token("a", InputType.NORMAL));
        line1.add(new Token(")", InputType.NORMAL));
        line1.add(new Token("{", InputType.NORMAL));

        line2.add(new Token("return", InputType.NORMAL));
        line2.add(new Token("a", InputType.NORMAL));

        line3.add(new Token("}", InputType.NORMAL));


        Compile.Translator.handleLine(line1);
        Compile.Translator.handleLine(line2);
        Compile.Translator.handleLine(line3);

        assertEquals(1, Maps.Functions.StringFunctionMap.size());


        FunctionContainer fcTest = Mapper.findFunction("test");

        Function test = fcTest.getFunction();

        ArrayList<Variable> args = test.getArgumentVariables();

        assertEquals(1, args.size());
    }

    @Test
    void declareCharFunction() throws InvalidSyntaxException
    {
        Mapper.clearMaps();
        ArrayList<Token> line1 = new ArrayList<>();
        ArrayList<Token> line2 = new ArrayList<>();
        ArrayList<Token> line3 = new ArrayList<>();

        line1.add(new Token("func", InputType.NORMAL));
        line1.add(new Token("char", InputType.NORMAL));
        line1.add(new Token("test", InputType.NORMAL));
        line1.add(new Token("(", InputType.NORMAL));
        line1.add(new Token(")", InputType.NORMAL));
        line1.add(new Token("{", InputType.NORMAL));

        line2.add(new Token("return", InputType.NORMAL));
        line2.add(new Token("c", InputType.NORMAL));

        line3.add(new Token("}", InputType.NORMAL));


        Compile.Translator.handleLine(line1);
        Compile.Translator.handleLine(line2);
        Compile.Translator.handleLine(line3);

        assertEquals(1, Maps.Functions.CharFunctionMap.size());
    }

    @Test
    void declareCharFunctionWithArguments() throws InvalidSyntaxException
    {
        Mapper.clearMaps();
        ArrayList<Token> line1 = new ArrayList<>();
        ArrayList<Token> line2 = new ArrayList<>();
        ArrayList<Token> line3 = new ArrayList<>();

        line1.add(new Token("func", InputType.NORMAL));
        line1.add(new Token("char", InputType.NORMAL));
        line1.add(new Token("test", InputType.NORMAL));
        line1.add(new Token("(", InputType.NORMAL));
        line1.add(new Token("char", InputType.NORMAL));
        line1.add(new Token("a", InputType.NORMAL));
        line1.add(new Token(")", InputType.NORMAL));
        line1.add(new Token("{", InputType.NORMAL));

        line2.add(new Token("return", InputType.NORMAL));
        line2.add(new Token("a", InputType.NORMAL));

        line3.add(new Token("}", InputType.NORMAL));


        Compile.Translator.handleLine(line1);
        Compile.Translator.handleLine(line2);
        Compile.Translator.handleLine(line3);

        assertEquals(1, Maps.Functions.CharFunctionMap.size());


        FunctionContainer fcTest = Mapper.findFunction("test");

        Function test = fcTest.getFunction();

        ArrayList<Variable> args = test.getArgumentVariables();

        assertEquals(1, args.size());
    }

    @Test
    void declareInvalidFunction() throws InvalidSyntaxException
    {
        Mapper.clearMaps();
        ArrayList<Token> line1 = new ArrayList<>();
        ArrayList<Token> line2 = new ArrayList<>();
        ArrayList<Token> line3 = new ArrayList<>();

        line1.add(new Token("func", InputType.NORMAL));
        line1.add(new Token("asdasdasdasd", InputType.NORMAL));
        line1.add(new Token("test", InputType.NORMAL));
        line1.add(new Token("(", InputType.NORMAL));
        line1.add(new Token(")", InputType.NORMAL));
        line1.add(new Token("{", InputType.NORMAL));

        line2.add(new Token("return", InputType.NORMAL));
        line2.add(new Token("c", InputType.NORMAL));

        line3.add(new Token("}", InputType.NORMAL));

        try
        {
            Compile.Translator.handleLine(line1);
            Compile.Translator.handleLine(line2);
            Compile.Translator.handleLine(line3);

            fail("Function requires an actual type!");
        } catch (InvalidSyntaxException ignored) {}


        assertEquals(0, Maps.Functions.CharFunctionMap.size());

    }

    @Test
    void declareFunctionInvalidArgumentType() throws InvalidSyntaxException
    {
        Mapper.clearMaps();
        ArrayList<Token> line1 = new ArrayList<>();
        ArrayList<Token> line2 = new ArrayList<>();
        ArrayList<Token> line3 = new ArrayList<>();

        line1.add(new Token("func", InputType.NORMAL));
        line1.add(new Token("void", InputType.NORMAL));
        line1.add(new Token("test", InputType.NORMAL));
        line1.add(new Token("(", InputType.NORMAL));
        line1.add(new Token("int", InputType.NORMAL));
        line1.add(new Token("b", InputType.NORMAL));
        line1.add(new Token(")", InputType.NORMAL));
        line1.add(new Token("{", InputType.NORMAL));

        line2.add(new Token("return", InputType.NORMAL));
        line2.add(new Token("c", InputType.NORMAL));

        line3.add(new Token("}", InputType.NORMAL));

        try
        {
            Compile.Translator.handleLine(line1);
            Compile.Translator.handleLine(line2);
            Compile.Translator.handleLine(line3);

//            fail("Function requires an actual type!");
        } catch (InvalidSyntaxException ignored) {}


        assertEquals(0, Maps.Functions.CharFunctionMap.size());

    }

    // LISTS

    @Test
    void declareIntegerList() throws InvalidSyntaxException
    {
        Mapper.clearMaps();

        ArrayList<Token> line1 = new ArrayList<>();

        line1.add(new Token("list", InputType.NORMAL));
        line1.add(new Token("int", InputType.NORMAL));
        line1.add(new Token("a", InputType.NORMAL));
        line1.add(new Token("=", InputType.NORMAL));
        line1.add(new Token("[", InputType.NORMAL));
        line1.add(new Token("2", InputType.NORMAL));
        line1.add(new Token(",", InputType.NORMAL));
        line1.add(new Token("3", InputType.NORMAL));
        line1.add(new Token("}", InputType.NORMAL));

        Translator.handleLine(line1);

        assertEquals(1,IntegerListMap.size());

//        ListContainer foundListContainer = Mapper.findList("a");
//
//        List foundList = foundListContainer.getList();
//
//
//        if (!(foundList instanceof IntegerList))
//            fail("Should be an IntegerList");
//
//        foundList = (IntegerList) foundList
//
//        assertEquals(2, foundList.get(0));
//        assertEquals(3, foundList.get(1));
    }

    @Test
    void declareStringList() throws InvalidSyntaxException
    {
        Mapper.clearMaps();

        ArrayList<Token> line1 = new ArrayList<>();

        line1.add(new Token("list", InputType.NORMAL));
        line1.add(new Token("string", InputType.NORMAL));
        line1.add(new Token("a", InputType.NORMAL));
        line1.add(new Token("=", InputType.NORMAL));
        line1.add(new Token("[", InputType.NORMAL));
        line1.add(new Token("hello", InputType.STRING));
        line1.add(new Token(",", InputType.NORMAL));
        line1.add(new Token("world", InputType.STRING));
        line1.add(new Token("]", InputType.NORMAL));

        Translator.handleLine(line1);

        assertEquals(1, StringListMap.size());

//        ListContainer foundListContainer = Mapper.findList("a");
//
//        List foundList = foundListContainer.getList();
//
//
//        if (!(foundList instanceof StringList))
//            fail("Should be an StringList");
//
//        foundList = (StringList) foundList
//
//        assertTrue(foundList.get(0).equals("hello");
//        assertTrue(foundList.get(1).equals("world");
    }

    @Test
    void declareCharList() throws InvalidSyntaxException
    {
        Mapper.clearMaps();

        ArrayList<Token> line1 = new ArrayList<>();

        line1.add(new Token("list", InputType.NORMAL));
        line1.add(new Token("char", InputType.NORMAL));
        line1.add(new Token("a", InputType.NORMAL));
        line1.add(new Token("=", InputType.NORMAL));
        line1.add(new Token("[", InputType.NORMAL));
        line1.add(new Token("b", InputType.STRING));
        line1.add(new Token(",", InputType.NORMAL));
        line1.add(new Token("r", InputType.STRING));
        line1.add(new Token("]", InputType.NORMAL));

        Translator.handleLine(line1);

        assertEquals(1, CharListMap.size());

//        ListContainer foundListContainer = Mapper.findList("a");
//
//        List foundList = foundListContainer.getList();
//
//
//        if (!(foundList instanceof CharList))
//            fail("Should be an StringList");
//
//        foundList = (CharList) foundList
//
//        assertEquals(2, foundList.get(0));
//        assertEquals(3, foundList.get(1));
    }

    @Test
    void declareBooleanList() throws InvalidSyntaxException
    {
        Mapper.clearMaps();

        ArrayList<Token> line1 = new ArrayList<>();

        line1.add(new Token("list", InputType.NORMAL));
        line1.add(new Token("boolean", InputType.NORMAL));
        line1.add(new Token("a", InputType.NORMAL));
        line1.add(new Token("=", InputType.NORMAL));
        line1.add(new Token("[", InputType.NORMAL));
        line1.add(new Token("true", InputType.STRING));
        line1.add(new Token(",", InputType.NORMAL));
        line1.add(new Token("false", InputType.STRING));
        line1.add(new Token("]", InputType.NORMAL));

        Translator.handleLine(line1);

        assertEquals(1, BooleanListMap.size());

//        ListContainer foundListContainer = Mapper.findList("a");
//
//        List foundList = foundListContainer.getList();
//
//
//        if (!(foundList instanceof BooleanList))
//            fail("Should be an BooleanList");
//
//        foundList = (BooleanList) foundList
//
//        assertTrue(foundList.get(0));
//        assertFalse(foundList.get(1));
    }

    //// REDEFINE ////

    @Test
    void redefineVariable_int() throws Exception
    {
        Mapper.clearMaps();
        // Define a
        ArrayList<Token> line = new ArrayList<>();

        line.add(new Token("int", InputType.NORMAL));
        line.add(new Token("i", InputType.NORMAL));
        line.add(new Token("=", InputType.NORMAL));
        line.add(new Token("3", InputType.NORMAL));

        Translator.handleLine(line);

        assertEquals(1, IntegerMap.size());

        VariableContainer foundA = Mapper.findVariable("i");

        assert foundA != null;
        assertEquals("int", foundA.getType());

        IntegerVariable var = (IntegerVariable) foundA.getVariable();

        assertEquals(3, var.getValue());

        // Redefine a

        line.clear();

        line.add(new Token("i", InputType.NORMAL));
        line.add(new Token("=", InputType.NORMAL));
        line.add(new Token("3", InputType.NORMAL));


        Translator.handleLine(line);

        assertEquals(IntegerMap.size(),1);

        foundA = Mapper.findVariable("i");

        assert foundA != null;
        assertEquals("int", foundA.getType());


        var = (IntegerVariable) foundA.getVariable();


        assertEquals(3, var.getValue());

    }




}