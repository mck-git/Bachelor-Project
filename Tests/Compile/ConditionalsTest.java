package Compile;

import DataTypes.Token;
import DataTypes.Variables.CharVariable;
import DataTypes.Variables.VariableContainer;
import SharedResources.InputType;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

class ConditionalsTest {

    //// IF ////
    @Test
    void testIf_true() throws Exception
    {
        Mapper.clearMaps();

        ArrayList<Token> line1 = new ArrayList<>();
        ArrayList<Token> line2 = new ArrayList<>();
        ArrayList<Token> line3 = new ArrayList<>();
        ArrayList<Token> line4 = new ArrayList<>();


        line1.add(new Token("char",InputType.NORMAL));
        line1.add(new Token("c",InputType.NORMAL));
        line1.add(new Token("=",InputType.NORMAL));
        line1.add(new Token("q",InputType.NORMAL));


        line2.add(new Token("if",InputType.NORMAL));
        line2.add(new Token("true",InputType.NORMAL));
        line2.add(new Token("{",InputType.NORMAL));

        line3.add(new Token("c",InputType.NORMAL));
        line3.add(new Token("=",InputType.NORMAL));
        line3.add(new Token("r",InputType.NORMAL));

        line4.add(new Token("}",InputType.NORMAL));

        Translator.handleLine(line1);
        Translator.handleLine(line2);
        Translator.handleLine(line3);
        Translator.handleLine(line4);

        VariableContainer vc = Mapper.findVariable("c");

        CharVariable c = (CharVariable) vc.getVariable();

        assertEquals('r', c.getValue());
    }

    @Test
    void testIf_false() throws Exception
    {
        Mapper.clearMaps();

        ArrayList<Token> line1 = new ArrayList<>();
        ArrayList<Token> line2 = new ArrayList<>();
        ArrayList<Token> line3 = new ArrayList<>();
        ArrayList<Token> line4 = new ArrayList<>();


        line1.add(new Token("char",InputType.NORMAL));
        line1.add(new Token("c",InputType.NORMAL));
        line1.add(new Token("=",InputType.NORMAL));
        line1.add(new Token("q",InputType.NORMAL));


        line2.add(new Token("if",InputType.NORMAL));
        line2.add(new Token("false",InputType.NORMAL));
        line2.add(new Token("{",InputType.NORMAL));

        line3.add(new Token("c",InputType.NORMAL));
        line3.add(new Token("=",InputType.NORMAL));
        line3.add(new Token("r",InputType.NORMAL));

        line4.add(new Token("}",InputType.NORMAL));

        Translator.handleLine(line1);
        Translator.handleLine(line2);
        Translator.handleLine(line3);
        Translator.handleLine(line4);

        VariableContainer vc = Mapper.findVariable("c");

        CharVariable c = (CharVariable) vc.getVariable();

        assertEquals('q', c.getValue());
    }


    //// OR ////

    @org.junit.jupiter.api.Test
    void evaluateConditionalLine_or_t() throws Exception{

        ArrayList<Token> line = new ArrayList<>();

        line.add(new Token("if", InputType.NORMAL));
        line.add(new Token("true", InputType.NORMAL));
        line.add(new Token("|",InputType.NORMAL));
        line.add(new Token("false",InputType.NORMAL));
        line.add(new Token("{", InputType.NORMAL));

        boolean result = Compile.Conditionals.evaluateConditionalLine(line);

        assertTrue(result);
    }

    @org.junit.jupiter.api.Test
    void evaluateConditionalLine_or_f() throws Exception{

        ArrayList<Token> line = new ArrayList<>();

        line.add(new Token("if", InputType.NORMAL));
        line.add(new Token("false", InputType.NORMAL));
        line.add(new Token("|",InputType.NORMAL));
        line.add(new Token("false",InputType.NORMAL));
        line.add(new Token("{", InputType.NORMAL));

        boolean result = Compile.Conditionals.evaluateConditionalLine(line);

        assertFalse(result);
    }

    //// AND ////

    @org.junit.jupiter.api.Test
    void evaluateConditionalLine_and_t() throws Exception{

        ArrayList<Token> line = new ArrayList<>();

        line.add(new Token("if", InputType.NORMAL));
        line.add(new Token("true", InputType.NORMAL));
        line.add(new Token("&",InputType.NORMAL));
        line.add(new Token("true",InputType.NORMAL));
        line.add(new Token("{", InputType.NORMAL));

        boolean result = Compile.Conditionals.evaluateConditionalLine(line);

        assertTrue(result);
    }

    @org.junit.jupiter.api.Test
    void evaluateConditionalLine_and_f() throws Exception{

        ArrayList<Token> line = new ArrayList<>();

        line.add(new Token("if", InputType.NORMAL));
        line.add(new Token("true", InputType.NORMAL));
        line.add(new Token("&",InputType.NORMAL));
        line.add(new Token("false",InputType.NORMAL));
        line.add(new Token("{", InputType.NORMAL));

        boolean result = Compile.Conditionals.evaluateConditionalLine(line);

        assertFalse(result);
    }


    //// EQUALS ////

    @org.junit.jupiter.api.Test
    void evaluateConditionalLine_equals_t() throws Exception{

        ArrayList<Token> line = new ArrayList<>();

        line.add(new Token("if", InputType.NORMAL));
        line.add(new Token("3", InputType.NORMAL));
        line.add(new Token("==",InputType.NORMAL));
        line.add(new Token("3",InputType.NORMAL));
        line.add(new Token("{", InputType.NORMAL));

        boolean result = Compile.Conditionals.evaluateConditionalLine(line);

        assertTrue(result);
    }

    @org.junit.jupiter.api.Test
    void evaluateConditionalLine_equals_f() throws Exception{

        ArrayList<Token> line = new ArrayList<>();

        line.add(new Token("if", InputType.NORMAL));
        line.add(new Token("3", InputType.NORMAL));
        line.add(new Token("==",InputType.NORMAL));
        line.add(new Token("5",InputType.NORMAL));
        line.add(new Token("{", InputType.NORMAL));

        boolean result = Compile.Conditionals.evaluateConditionalLine(line);

        assertFalse(result);
    }

    //// GREATER ////

    @org.junit.jupiter.api.Test
    void evaluateConditionalLine_greater_t() throws Exception{

        ArrayList<Token> line = new ArrayList<>();

        line.add(new Token("if", InputType.NORMAL));
        line.add(new Token("3", InputType.NORMAL));
        line.add(new Token(">", InputType.NORMAL));
        line.add(new Token("1", InputType.NORMAL));
        line.add(new Token("{", InputType.NORMAL));



        boolean result = Compile.Conditionals.evaluateConditionalLine(line);

        assertTrue(result);
    }

    @org.junit.jupiter.api.Test
    void evaluateConditionalLine_greater_f() throws Exception{

        ArrayList<Token> line = new ArrayList<>();

        line.add(new Token("if", InputType.NORMAL));
        line.add(new Token("3", InputType.NORMAL));
        line.add(new Token(">", InputType.NORMAL));
        line.add(new Token("10", InputType.NORMAL));
        line.add(new Token("{", InputType.NORMAL));



        boolean result = Compile.Conditionals.evaluateConditionalLine(line);

        assertFalse(result);
    }

    //// LESSER ////

    @org.junit.jupiter.api.Test
    void evaluateConditionalLine_lesser_t() throws Exception{

        ArrayList<Token> line = new ArrayList<>();

        line.add(new Token("if", InputType.NORMAL));
        line.add(new Token("1", InputType.NORMAL));
        line.add(new Token("<", InputType.NORMAL));
        line.add(new Token("4", InputType.NORMAL));
        line.add(new Token("{", InputType.NORMAL));



        boolean result = Compile.Conditionals.evaluateConditionalLine(line);

        assertTrue(result);
    }

    @org.junit.jupiter.api.Test
    void evaluateConditionalLine_lesser_f() throws Exception{

        ArrayList<Token> line = new ArrayList<>();

        line.add(new Token("if", InputType.NORMAL));
        line.add(new Token("10", InputType.NORMAL));
        line.add(new Token("<", InputType.NORMAL));
        line.add(new Token("3", InputType.NORMAL));
        line.add(new Token("{", InputType.NORMAL));



        boolean result = Compile.Conditionals.evaluateConditionalLine(line);

        assertFalse(result);
    }


    //// COMBINATION ////

    @org.junit.jupiter.api.Test
    void evaluateConditionalLine_combi_eq_less() throws Exception{

        ArrayList<Token> line = new ArrayList<>();

        line.add(new Token("if", InputType.NORMAL));
        line.add(new Token("3", InputType.NORMAL));
        line.add(new Token("==",InputType.NORMAL));
        line.add(new Token("3",InputType.NORMAL));
        line.add(new Token("&",InputType.NORMAL));
        line.add(new Token("2",InputType.NORMAL));
        line.add(new Token("<",InputType.NORMAL));
        line.add(new Token("3",InputType.NORMAL));
        line.add(new Token("{", InputType.NORMAL));

        boolean result = Compile.Conditionals.evaluateConditionalLine(line);

        assertTrue(result);
    }

}