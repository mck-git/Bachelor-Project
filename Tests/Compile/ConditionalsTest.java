package Compile;

import DataTypes.Token;
import SharedResources.InputType;
import javafx.scene.effect.SepiaTone;

import java.text.Normalizer;
import java.util.ArrayList;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

class ConditionalsTest {

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