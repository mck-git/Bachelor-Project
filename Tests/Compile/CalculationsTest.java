package Compile;

import DataTypes.Functions.BooleanFunction;
import DataTypes.Functions.Function;
import DataTypes.Functions.FunctionContainer;
import DataTypes.Lists.BooleanList;
import DataTypes.Lists.IntegerList;
import DataTypes.Lists.List;
import DataTypes.Lists.ListContainer;
import DataTypes.Token;
import DataTypes.Variables.BooleanVariable;
import DataTypes.Variables.IntegerVariable;
import DataTypes.Variables.Variable;
import DataTypes.Variables.VariableContainer;
import Errors.InvalidSyntaxException;
import Maps.Functions.BooleanFunctionMap;
import Maps.Functions.IntegerFunctionMap;
import Maps.Lists.BooleanListMap;
import Maps.Lists.IntegerListMap;
import Maps.Variables.IntegerMap;
import Maps.Variables.TemporaryVariablesMap;
import SharedResources.InputType;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class CalculationsTest {


    //// EVALUATE ////

    @Test
    void evaluateMath_plus() throws Exception
    {
        ArrayList<Token> line = new ArrayList<>();

        line.add(new Token("2", InputType.NORMAL));
        line.add(new Token("+", InputType.NORMAL));
        line.add(new Token("2", InputType.NORMAL));

        assertEquals(4, Calculations.evaluateMath(line));

    }

    @Test
    void evaluateMath_minus() throws Exception
    {
        ArrayList<Token> line = new ArrayList<>();

        line.add(new Token("2", InputType.NORMAL));
        line.add(new Token("+", InputType.NORMAL));
        line.add(new Token("2", InputType.NORMAL));
        line.add(new Token("-", InputType.NORMAL));
        line.add(new Token("1", InputType.NORMAL));

        assertEquals(3, Calculations.evaluateMath(line));
        // quick maths
    }

    @Test
    void evaluateMath_mult() throws Exception
    {
        ArrayList<Token> line = new ArrayList<>();

        line.add(new Token("2", InputType.NORMAL));
        line.add(new Token("*", InputType.NORMAL));
        line.add(new Token("2", InputType.NORMAL));

        assertEquals(4, Calculations.evaluateMath(line));
    }

    @Test
    void evaluateMath_div() throws Exception
    {
        ArrayList<Token> line = new ArrayList<>();

        line.add(new Token("6", InputType.NORMAL));
        line.add(new Token("/", InputType.NORMAL));
        line.add(new Token("2", InputType.NORMAL));

        assertEquals(3, Calculations.evaluateMath(line));
    }


    @Test
    void evaluateMath_variable() throws Exception
    {
        Mapper.clearMaps();
        ArrayList<Token> line = new ArrayList<>();

        line.add(new Token("int", InputType.NORMAL));
        line.add(new Token("a", InputType.NORMAL));
        line.add(new Token("=", InputType.NORMAL));
        line.add(new Token("2", InputType.NORMAL));

        Declarations.declareInteger(line);

        line.clear();

        line.add(new Token("a", InputType.NORMAL));
        line.add(new Token("+", InputType.NORMAL));
        line.add(new Token("4", InputType.NORMAL));

        assertEquals(6, Calculations.evaluateMath(line));
    }

    @Test
    void evaluateMath_function() throws Exception
    {
        Mapper.clearMaps();
        ArrayList<Token> line0 = new ArrayList<>();
        ArrayList<Token> line1 = new ArrayList<>();
        ArrayList<Token> line2 = new ArrayList<>();
        ArrayList<Token> line3 = new ArrayList<>();
        ArrayList<Token> line4 = new ArrayList<>();



        line0.add(new Token("int",InputType.NORMAL));
        line0.add(new Token("a",InputType.NORMAL));
        line0.add(new Token("=",InputType.NORMAL));
        line0.add(new Token("0",InputType.NORMAL));

        line1.add(new Token("func", InputType.NORMAL));
        line1.add(new Token("int", InputType.NORMAL));
        line1.add(new Token("test", InputType.NORMAL));
        line1.add(new Token("(", InputType.NORMAL));
        line1.add(new Token("int", InputType.NORMAL));
        line1.add(new Token("b", InputType.NORMAL));
        line1.add(new Token(")", InputType.NORMAL));
        line1.add(new Token("{", InputType.NORMAL));

        line2.add(new Token("return", InputType.NORMAL));
        line2.add(new Token("b", InputType.NORMAL));
        line2.add(new Token("+", InputType.NORMAL));
        line2.add(new Token("1", InputType.NORMAL));


        line3.add(new Token("}", InputType.NORMAL));

        Compile.Translator.handleLine(line0);
        Compile.Translator.handleLine(line1);
        Compile.Translator.handleLine(line2);
        Compile.Translator.handleLine(line3);

        assertEquals(1, Maps.Functions.IntegerFunctionMap.size());

        FunctionContainer fcTest = Mapper.findFunction("test");

        Function test = fcTest.getFunction();

        ArrayList<Variable> args = test.getArgumentVariables();

        assertEquals(1, args.size());

        line4.add(new Token("a", InputType.NORMAL));
        line4.add(new Token("=", InputType.NORMAL));
        line4.add(new Token("test", InputType.NORMAL));
        line4.add(new Token("(", InputType.NORMAL));
        line4.add(new Token("2", InputType.NORMAL));
        line4.add(new Token(")", InputType.NORMAL));
        line4.add(new Token("+", InputType.NORMAL));
        line4.add(new Token("1", InputType.NORMAL));

        Translator.handleLine(line4);
        assertEquals(1, TemporaryVariablesMap.size());

        assertEquals(1, IntegerMap.size());

        VariableContainer vc = Mapper.findVariable("a");

        IntegerVariable var = (IntegerVariable) vc.getVariable();

        assertEquals(4, var.getValue());
    }

    @Test
    void evaluateMath_list() throws Exception
    {
        Mapper.clearMaps();

        ArrayList<Token> line1 = new ArrayList<>();
        ArrayList<Token> line2 = new ArrayList<>();
        ArrayList<Token> line3 = new ArrayList<>();


        line1.add(new Token("list", InputType.NORMAL));
        line1.add(new Token("int", InputType.NORMAL));
        line1.add(new Token("a", InputType.NORMAL));
        line1.add(new Token("=", InputType.NORMAL));
        line1.add(new Token("[", InputType.NORMAL));
        line1.add(new Token("2", InputType.NORMAL));
        line1.add(new Token(",", InputType.NORMAL));
        line1.add(new Token("3", InputType.NORMAL));
        line1.add(new Token("]", InputType.NORMAL));

        line2.add(new Token("int", InputType.NORMAL));
        line2.add(new Token("b", InputType.NORMAL));
        line2.add(new Token("=", InputType.NORMAL));
        line2.add(new Token("1", InputType.NORMAL));

        line3.add(new Token("int", InputType.NORMAL));
        line3.add(new Token("c", InputType.NORMAL));
        line3.add(new Token("=", InputType.NORMAL));
        line3.add(new Token("b", InputType.NORMAL));
        line3.add(new Token("+", InputType.NORMAL));
        line3.add(new Token("a", InputType.NORMAL));
        line3.add(new Token("[", InputType.NORMAL));
        line3.add(new Token("0", InputType.NORMAL));
        line3.add(new Token("]", InputType.NORMAL));


        Translator.handleLine(line1);
        Translator.handleLine(line2);
        Translator.handleLine(line3);

        assertEquals(1, IntegerListMap.size());

        ListContainer foundListContainer = Mapper.findList("a");

        List foundList = foundListContainer.getList();


        if (!(foundList instanceof IntegerList)) {
            fail("Should be an IntegerList");
        }

        IntegerList intList = (IntegerList) foundList;

        assertEquals(2, intList.get(0));
        assertEquals(3, intList.get(1));

        VariableContainer vc = Mapper.findVariable("c");

        IntegerVariable c = (IntegerVariable) vc.getVariable();

        assertEquals(3, c.getValue());
    }

    @Test
    void evaluateBoolean() throws Exception
    {
        ArrayList<Token> line = new ArrayList<>();

        line.add(new Token("true", InputType.NORMAL));
        line.add(new Token("&", InputType.NORMAL));
        line.add(new Token("false", InputType.NORMAL));

        assertEquals(false, Calculations.evaluateBoolean(line));
    }

    @Test
    void evaluateBoolean_boolVariable() throws Exception
    {
        ArrayList<Token> line1 = new ArrayList<>();
        ArrayList<Token> line2 = new ArrayList<>();

        line1.add(new Token("boolean", InputType.NORMAL));
        line1.add(new Token("b", InputType.NORMAL));
        line1.add(new Token("=", InputType.NORMAL));
        line1.add(new Token("true", InputType.NORMAL));


        line2.add(new Token("false", InputType.NORMAL));
        line2.add(new Token("&", InputType.NORMAL));
        line2.add(new Token("a", InputType.NORMAL));


        Translator.handleLine(line1);

        assertEquals(false, Calculations.evaluateBoolean(line2));
    }

    @Test
    void evaluateBoolean_intVariable() throws Exception
    {
        ArrayList<Token> line1 = new ArrayList<>();
        ArrayList<Token> line2 = new ArrayList<>();

        line1.add(new Token("int", InputType.NORMAL));
        line1.add(new Token("b", InputType.NORMAL));
        line1.add(new Token("=", InputType.NORMAL));
        line1.add(new Token("5", InputType.NORMAL));


        line2.add(new Token("5", InputType.NORMAL));
        line2.add(new Token("==", InputType.NORMAL));
        line2.add(new Token("b", InputType.NORMAL));


        Translator.handleLine(line1);

        assertEquals(true, Calculations.evaluateBoolean(line2));
    }

    @Test
    void evaluateBoolean_boolFunction() throws Exception
    {
        ArrayList<Token> line1 = new ArrayList<>();
        ArrayList<Token> line2 = new ArrayList<>();
        ArrayList<Token> line3 = new ArrayList<>();
        ArrayList<Token> line4 = new ArrayList<>();

        line1.add(new Token("func", InputType.NORMAL));
        line1.add(new Token("boolean", InputType.NORMAL));
        line1.add(new Token("b", InputType.NORMAL));
        line1.add(new Token("(", InputType.NORMAL));
        line1.add(new Token(")", InputType.NORMAL));
        line1.add(new Token("{", InputType.NORMAL));

        line2.add(new Token("return", InputType.NORMAL));
        line2.add(new Token("false", InputType.NORMAL));

        line3.add(new Token("}", InputType.NORMAL));




        line4.add(new Token("true", InputType.NORMAL));
        line4.add(new Token("&", InputType.NORMAL));
        line4.add(new Token("b", InputType.NORMAL));
        line4.add(new Token("(", InputType.NORMAL));
        line4.add(new Token(")", InputType.NORMAL));


        Translator.handleLine(line1);
        Translator.handleLine(line2);
        Translator.handleLine(line3);

        assertEquals(1, BooleanFunctionMap.size());

        assertEquals(false, Calculations.evaluateBoolean(line4));
    }

    @Test
    void evaluateBoolean_intFunction() throws Exception
    {
        ArrayList<Token> line1 = new ArrayList<>();
        ArrayList<Token> line2 = new ArrayList<>();
        ArrayList<Token> line3 = new ArrayList<>();
        ArrayList<Token> line4 = new ArrayList<>();

        line1.add(new Token("func", InputType.NORMAL));
        line1.add(new Token("int", InputType.NORMAL));
        line1.add(new Token("a", InputType.NORMAL));
        line1.add(new Token("(", InputType.NORMAL));
        line1.add(new Token(")", InputType.NORMAL));
        line1.add(new Token("{", InputType.NORMAL));

        line2.add(new Token("return", InputType.NORMAL));
        line2.add(new Token("2", InputType.NORMAL));

        line3.add(new Token("}", InputType.NORMAL));




        line4.add(new Token("3", InputType.NORMAL));
        line4.add(new Token("==", InputType.NORMAL));
        line4.add(new Token("a", InputType.NORMAL));
        line4.add(new Token("(", InputType.NORMAL));
        line4.add(new Token(")", InputType.NORMAL));


        Translator.handleLine(line1);
        Translator.handleLine(line2);
        Translator.handleLine(line3);

        assertEquals(1, IntegerFunctionMap.size());

        assertEquals(false, Calculations.evaluateBoolean(line4));
    }

    @Test
    void evaluateBoolean_boolFunction_arguments() throws Exception
    {
        ArrayList<Token> line1 = new ArrayList<>();
        ArrayList<Token> line2 = new ArrayList<>();
        ArrayList<Token> line3 = new ArrayList<>();
        ArrayList<Token> line4 = new ArrayList<>();

        line1.add(new Token("func", InputType.NORMAL));
        line1.add(new Token("boolean", InputType.NORMAL));
        line1.add(new Token("b", InputType.NORMAL));
        line1.add(new Token("(", InputType.NORMAL));
        line1.add(new Token("boolean", InputType.NORMAL));
        line1.add(new Token("a", InputType.NORMAL));
        line1.add(new Token(")", InputType.NORMAL));
        line1.add(new Token("{", InputType.NORMAL));

        line2.add(new Token("return", InputType.NORMAL));
        line2.add(new Token("a", InputType.NORMAL));

        line3.add(new Token("}", InputType.NORMAL));




        line4.add(new Token("true", InputType.NORMAL));
        line4.add(new Token("&", InputType.NORMAL));
        line4.add(new Token("b", InputType.NORMAL));
        line4.add(new Token("(", InputType.NORMAL));
        line4.add(new Token("false", InputType.NORMAL));
        line4.add(new Token(")", InputType.NORMAL));


        Translator.handleLine(line1);
        Translator.handleLine(line2);
        Translator.handleLine(line3);

        assertEquals(1, BooleanFunctionMap.size());

        assertEquals(false, Calculations.evaluateBoolean(line4));
    }

    @Test
    void evaluateBoolean_intFunction_arguments() throws Exception
    {
        ArrayList<Token> line1 = new ArrayList<>();
        ArrayList<Token> line2 = new ArrayList<>();
        ArrayList<Token> line3 = new ArrayList<>();
        ArrayList<Token> line4 = new ArrayList<>();

        line1.add(new Token("func", InputType.NORMAL));
        line1.add(new Token("int", InputType.NORMAL));
        line1.add(new Token("a", InputType.NORMAL));
        line1.add(new Token("(", InputType.NORMAL));
        line1.add(new Token("int", InputType.NORMAL));
        line1.add(new Token("q", InputType.NORMAL));
        line1.add(new Token(")", InputType.NORMAL));
        line1.add(new Token("{", InputType.NORMAL));

        line2.add(new Token("return", InputType.NORMAL));
        line2.add(new Token("q", InputType.NORMAL));

        line3.add(new Token("}", InputType.NORMAL));




        line4.add(new Token("3", InputType.NORMAL));
        line4.add(new Token("==", InputType.NORMAL));
        line4.add(new Token("a", InputType.NORMAL));
        line4.add(new Token("(", InputType.NORMAL));
        line4.add(new Token("5", InputType.NORMAL));
        line4.add(new Token(")", InputType.NORMAL));


        Translator.handleLine(line1);
        Translator.handleLine(line2);
        Translator.handleLine(line3);

        assertEquals(1, IntegerFunctionMap.size());

        assertEquals(false, Calculations.evaluateBoolean(line4));
    }

    @Test
    void evaluateBoolean_booleanList() throws Exception
    {
        Mapper.clearMaps();

        ArrayList<Token> line1 = new ArrayList<>();
        ArrayList<Token> line2 = new ArrayList<>();
        ArrayList<Token> line3 = new ArrayList<>();


        line1.add(new Token("list", InputType.NORMAL));
        line1.add(new Token("boolean", InputType.NORMAL));
        line1.add(new Token("a", InputType.NORMAL));
        line1.add(new Token("=", InputType.NORMAL));
        line1.add(new Token("[", InputType.NORMAL));
        line1.add(new Token("false", InputType.STRING));
        line1.add(new Token(",", InputType.NORMAL));
        line1.add(new Token("false", InputType.STRING));
        line1.add(new Token("]", InputType.NORMAL));

        line2.add(new Token("boolean", InputType.NORMAL));
        line2.add(new Token("b", InputType.NORMAL));
        line2.add(new Token("=", InputType.NORMAL));
        line2.add(new Token("false", InputType.NORMAL));

        line3.add(new Token("b", InputType.NORMAL));
        line3.add(new Token("=", InputType.NORMAL));
        line3.add(new Token("b", InputType.NORMAL));
        line3.add(new Token("|", InputType.NORMAL));
        line3.add(new Token("a", InputType.NORMAL));
        line3.add(new Token("[", InputType.NORMAL));
        line3.add(new Token("0", InputType.NORMAL));
        line3.add(new Token("]", InputType.NORMAL));


        Translator.handleLine(line1);
        Translator.handleLine(line2);
        Translator.handleLine(line3);

        assertEquals(1, BooleanListMap.size());

        ListContainer foundListContainer = Mapper.findList("a");

        List foundList = foundListContainer.getList();


        if (!(foundList instanceof BooleanList)) {
            fail("Should be an IntegerList");
        }

        BooleanList booleanList = (BooleanList) foundList;

        assertEquals(false, booleanList.get(0));
        assertEquals(false, booleanList.get(1));

        VariableContainer vc = Mapper.findVariable("b");

        BooleanVariable b = (BooleanVariable) vc.getVariable();

        assertEquals(false, b.getValue());
    }

    @Test
    void evaluateBoolean_integerList() throws Exception
    {
        Mapper.clearMaps();

        ArrayList<Token> line1 = new ArrayList<>();
        ArrayList<Token> line2 = new ArrayList<>();
        ArrayList<Token> line3 = new ArrayList<>();


        line1.add(new Token("list", InputType.NORMAL));
        line1.add(new Token("int", InputType.NORMAL));
        line1.add(new Token("a", InputType.NORMAL));
        line1.add(new Token("=", InputType.NORMAL));
        line1.add(new Token("[", InputType.NORMAL));
        line1.add(new Token("2", InputType.STRING));
        line1.add(new Token(",", InputType.NORMAL));
        line1.add(new Token("3", InputType.STRING));
        line1.add(new Token("]", InputType.NORMAL));

        line2.add(new Token("int", InputType.NORMAL));
        line2.add(new Token("b", InputType.NORMAL));
        line2.add(new Token("=", InputType.NORMAL));
        line2.add(new Token("2", InputType.NORMAL));

        line3.add(new Token("boolean", InputType.NORMAL));
        line3.add(new Token("c", InputType.NORMAL));
        line3.add(new Token("=", InputType.NORMAL));
        line3.add(new Token("a", InputType.NORMAL));
        line3.add(new Token("[", InputType.NORMAL));
        line3.add(new Token("0", InputType.NORMAL));
        line3.add(new Token("]", InputType.NORMAL));
        line3.add(new Token("==", InputType.NORMAL));
        line3.add(new Token("b", InputType.NORMAL));

        Translator.handleLine(line1);
        Translator.handleLine(line2);
        Translator.handleLine(line3);

        assertEquals(1, IntegerListMap.size());

        ListContainer foundListContainer = Mapper.findList("a");

        List foundList = foundListContainer.getList();


        if (!(foundList instanceof IntegerList)) {
            fail("Should be an IntegerList");
        }

        IntegerList integerList = (IntegerList) foundList;

        assertEquals(2, integerList.get(0));
        assertEquals(3, integerList.get(1));

        VariableContainer vc = Mapper.findVariable("c");

        BooleanVariable c = (BooleanVariable) vc.getVariable();

        assertEquals(true, c.getValue());
    }

    @Test
    void evaluateBooleanFail() throws Exception
    {

        Mapper.clearMaps();
        ArrayList<Token> line1 = new ArrayList<>();

        line1.add(new Token("hello",InputType.STRING));
        try
        {
            Compile.Calculations.evaluateBoolean(line1);

//            fail("Should not be able to parse list containing no booleans");
        } catch (InvalidSyntaxException ignored)
        {

        }


    }

    @Test
    void isNumeric_t() {
        assertTrue(Calculations.isNumeric("5"));
    }

    @Test
    void isNumeric_f() {
        assertFalse(Calculations.isNumeric("five"));
    }

    @Test
    void isBoolean_t() {
        assertTrue(Calculations.isBoolean("true"));
    }

    @Test
    void isBoolean_f() {
        assertFalse(Calculations.isBoolean("This is a boolean"));
    }

}