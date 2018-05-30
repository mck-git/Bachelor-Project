package Compile;

import DataTypes.Functions.BooleanFunction;
import DataTypes.Token;
import DataTypes.Variables.IntegerVariable;
import Errors.InvalidSyntaxException;
import Maps.Functions.BooleanFunctionMap;
import Maps.Functions.IntegerFunctionMap;
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