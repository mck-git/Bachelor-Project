package Compile;

import DataTypes.*;
import Maps.*;
import SharedResources.InputType;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class DeclarationsTest {

    @Before
    void clearMaps() {
        IntegerMap.clear();
        BooleanMap.clear();
        StringMap.clear();
        CharMap.clear();
        VoidFunctionMap.clear();


    }

    @Test
    void declareInteger() {
        clearMaps();
        ArrayList<Token> line = new ArrayList<>();

        line.add(new Token("int", InputType.NORMAL));
        line.add(new Token("i", InputType.NORMAL));
        line.add(new Token("=", InputType.NORMAL));
        line.add(new Token("2", InputType.NORMAL));

        Declarations.declareInteger(line);

        assertEquals(1, IntegerMap.size());

        SearchedVariable foundA = Mapper.findVariable("i");

        assert foundA != null;
        assertEquals("int", foundA.getType());

        IntegerVariable var = (IntegerVariable) foundA.getVariable();

        assertEquals(2, var.getValue());


    }

    @Test
    void declareString() {
        clearMaps();
        ArrayList<Token> line = new ArrayList<>();

        line.add(new Token("string", InputType.NORMAL));
        line.add(new Token("s", InputType.NORMAL));
        line.add(new Token("=", InputType.NORMAL));
        line.add(new Token("Hello World!", InputType.STRING));

        Declarations.declareString(line);

        assertEquals(1, StringMap.size());


        SearchedVariable foundA = Mapper.findVariable("s");

        assert foundA != null;
        assertEquals("string", foundA.getType());

        StringVariable var = (StringVariable) foundA.getVariable();

        assertEquals("Hello World!", var.getValue());
    }

    @Test
    void declareChar() {
        clearMaps();
        ArrayList<Token> line = new ArrayList<>();

        line.add(new Token("char", InputType.NORMAL));
        line.add(new Token("c", InputType.NORMAL));
        line.add(new Token("=", InputType.NORMAL));
        line.add(new Token("d", InputType.NORMAL));

        Declarations.declareChar(line);

        assertEquals(1, CharMap.size());

        SearchedVariable foundA = Mapper.findVariable("c");

        assert foundA != null;
        assertEquals("char", foundA.getType());

        CharVariable var = (CharVariable) foundA.getVariable();

        assertEquals('d', var.getValue());
    }

    @Test
    void declareBoolean() {
        clearMaps();
        ArrayList<Token> line = new ArrayList<>();

        line.add(new Token("boolean", InputType.NORMAL));
        line.add(new Token("bool", InputType.NORMAL));
        line.add(new Token("=", InputType.NORMAL));
        line.add(new Token("true", InputType.NORMAL));

        Declarations.declareBoolean(line);

        assertEquals(1, BooleanMap.size());

        SearchedVariable foundA = Mapper.findVariable("bool");

        assert foundA != null;
        assertEquals("boolean", foundA.getType());

        BooleanVariable var = (BooleanVariable) foundA.getVariable();

        assertEquals(true, var.getValue());
    }

    //// REDEFINE ////

    @Test
    void redefineVariable_int() {
        clearMaps();
        // Define a
        ArrayList<Token> line = new ArrayList<>();

        line.add(new Token("int", InputType.NORMAL));
        line.add(new Token("i2", InputType.NORMAL));
        line.add(new Token("=", InputType.NORMAL));
        line.add(new Token("5", InputType.NORMAL));

        Declarations.declareInteger(line);

        assertEquals(1, IntegerMap.size());

        SearchedVariable foundA = Mapper.findVariable("i2");

        assert foundA != null;
        assertEquals("int", foundA.getType());

        IntegerVariable var = (IntegerVariable) foundA.getVariable();

        assertEquals(5, var.getValue());

        // Redefine a

        line.clear();

        line.add(new Token("i2", InputType.NORMAL));
        line.add(new Token("=", InputType.NORMAL));
        line.add(new Token("3", InputType.NORMAL));

        Declarations.redefineVariable(line);

        assertEquals(IntegerMap.size(),1);

        foundA = Mapper.findVariable("i2");

        assert foundA != null;
        assertEquals("int", foundA.getType());

        var = (IntegerVariable) foundA.getVariable();

        assertEquals(3, var.getValue());

    }


    //// EVALUATE ////

    @Test
    void evaluateMath_plus() {
        ArrayList<Token> line = new ArrayList<>();

        line.add(new Token("2", InputType.NORMAL));
        line.add(new Token("+", InputType.NORMAL));
        line.add(new Token("2", InputType.NORMAL));

        assertEquals(4, Declarations.evaluateMath(line));

    }

    @Test
    void evaluateMath_minus() {
        ArrayList<Token> line = new ArrayList<>();

        line.add(new Token("2", InputType.NORMAL));
        line.add(new Token("+", InputType.NORMAL));
        line.add(new Token("2", InputType.NORMAL));
        line.add(new Token("-", InputType.NORMAL));
        line.add(new Token("1", InputType.NORMAL));

        assertEquals(3, Declarations.evaluateMath(line));
        // quick maths
    }

    @Test
    void evaluateMath_mult() {
        ArrayList<Token> line = new ArrayList<>();

        line.add(new Token("2", InputType.NORMAL));
        line.add(new Token("*", InputType.NORMAL));
        line.add(new Token("2", InputType.NORMAL));

        assertEquals(4, Declarations.evaluateMath(line));
    }

    @Test
    void evaluateMath_div() {
        ArrayList<Token> line = new ArrayList<>();

        line.add(new Token("6", InputType.NORMAL));
        line.add(new Token("/", InputType.NORMAL));
        line.add(new Token("2", InputType.NORMAL));

        assertEquals(3, Declarations.evaluateMath(line));
    }



    @Test
    void evaluateBoolean() {
    }

    @Test
    void isNumeric() {
    }

    @Test
    void isBoolean() {
    }
}