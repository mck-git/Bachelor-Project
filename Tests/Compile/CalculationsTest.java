package Compile;

import DataTypes.Token;
import Errors.InvalidSyntaxException;
import SharedResources.InputType;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class CalculationsTest {


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
    void evaluateBoolean() throws Exception
    {
        ArrayList<Token> line = new ArrayList<>();

        line.add(new Token("true", InputType.NORMAL));
        line.add(new Token("&", InputType.NORMAL));
        line.add(new Token("false", InputType.NORMAL));

        assertEquals(false, Calculations.evaluateBoolean(line));
    }

    @Test
    void evaluateBooleanFail() throws Exception
    {

        Mapper.clearMaps();
        ArrayList<Token> line1 = new ArrayList<>();

        line1.add(new Token("hello",InputType.NORMAL));
        try
        {
            Compile.Calculations.evaluateBoolean(line1);

            //fail("Should not be able to parse list containing no booleans");
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