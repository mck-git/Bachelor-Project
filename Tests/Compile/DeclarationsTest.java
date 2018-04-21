package Compile;

import DataTypes.*;
import DataTypes.Variables.*;
import Maps.Variables.BooleanMap;
import Maps.Variables.CharMap;
import Maps.Variables.IntegerMap;
import Maps.Variables.StringMap;
import SharedResources.InputType;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class DeclarationsTest {

    @Test
    void declareInteger() throws Exception {
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
    void declareString() throws Exception {
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
    void declareChar() throws Exception {
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
    void declareBoolean() throws Exception {
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

    //// REDEFINE ////

    @Test
    void redefineVariable_int() throws Exception {
        Mapper.clearMaps();
        // Define a
        ArrayList<Token> line = new ArrayList<>();

        line.add(new Token("int", InputType.NORMAL));
        line.add(new Token("i", InputType.NORMAL));
        line.add(new Token("=", InputType.NORMAL));
        line.add(new Token("3", InputType.NORMAL));

        Translator.handleLine(line);
//        Declarations.declareInteger(line);

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
//        Declarations.redefineVariable(line);

        assertEquals(IntegerMap.size(),1);

        foundA = Mapper.findVariable("i");

        assert foundA != null;
        assertEquals("int", foundA.getType());


        var = (IntegerVariable) foundA.getVariable();


        assertEquals(3, var.getValue());

    }


    //// EVALUATE ////

    @Test
    void evaluateMath_plus() throws Exception {
        ArrayList<Token> line = new ArrayList<>();

        line.add(new Token("2", InputType.NORMAL));
        line.add(new Token("+", InputType.NORMAL));
        line.add(new Token("2", InputType.NORMAL));

        assertEquals(4, Calculations.evaluateMath(line));

    }

    @Test
    void evaluateMath_minus() throws Exception {
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
    void evaluateMath_mult() throws Exception {
        ArrayList<Token> line = new ArrayList<>();

        line.add(new Token("2", InputType.NORMAL));
        line.add(new Token("*", InputType.NORMAL));
        line.add(new Token("2", InputType.NORMAL));

        assertEquals(4, Calculations.evaluateMath(line));
    }

    @Test
    void evaluateMath_div() throws Exception {
        ArrayList<Token> line = new ArrayList<>();

        line.add(new Token("6", InputType.NORMAL));
        line.add(new Token("/", InputType.NORMAL));
        line.add(new Token("2", InputType.NORMAL));

        assertEquals(3, Calculations.evaluateMath(line));
    }


    @Test
    void evaluateMath_variable() throws Exception {
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
    void evaluateBoolean() throws Exception {
        ArrayList<Token> line = new ArrayList<>();

        line.add(new Token("true", InputType.NORMAL));
        line.add(new Token("&", InputType.NORMAL));
        line.add(new Token("false", InputType.NORMAL));

        assertEquals(false, Calculations.evaluateBoolean(line));
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