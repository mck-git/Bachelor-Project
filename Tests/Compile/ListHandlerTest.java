package Compile;

import DataTypes.Token;
import DataTypes.Variables.BooleanVariable;
import DataTypes.Variables.CharVariable;
import DataTypes.Variables.IntegerVariable;
import DataTypes.Variables.StringVariable;
import SharedResources.InputType;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ListHandlerTest {

    @Before
    void clearMaps()
    {
        Mapper.clearMaps();
    }

    @Test
    void findIntegerListIndex() throws Exception
    {
        ArrayList<Token> line1 = new ArrayList<>();
        ArrayList<Token> line2 = new ArrayList<>();

        line1.add(new Token("list", InputType.NORMAL));
        line1.add(new Token("int", InputType.NORMAL));
        line1.add(new Token("a", InputType.NORMAL));
        line1.add(new Token("=", InputType.NORMAL));
        line1.add(new Token("[", InputType.NORMAL));
        line1.add(new Token("2", InputType.NORMAL));
        line1.add(new Token(",", InputType.NORMAL));
        line1.add(new Token("3", InputType.NORMAL));
        line1.add(new Token("}", InputType.NORMAL));

        line2.add(new Token("a",InputType.NORMAL));
        line2.add(new Token("[",InputType.NORMAL));
        line2.add(new Token("1",InputType.NORMAL));
        line2.add(new Token("]",InputType.NORMAL));

        Translator.handleLine(line1);

        assertEquals(3,ListHandler.findIntegerListIndex(line2));
    }

    @Test
    void findIntegerListIndexByVariable() throws Exception
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
        line1.add(new Token("}", InputType.NORMAL));

        line2.add(new Token("a",InputType.NORMAL));
        line2.add(new Token("[",InputType.NORMAL));
        line2.add(new Token("b",InputType.NORMAL));
        line2.add(new Token("]",InputType.NORMAL));

        line3.add(new Token("int",InputType.NORMAL));
        line3.add(new Token("b",InputType.NORMAL));
        line3.add(new Token("=",InputType.NORMAL));
        line3.add(new Token("1",InputType.NORMAL));

        Translator.handleLine(line1);
        Translator.handleLine(line3);

        assertEquals(3,ListHandler.findIntegerListIndex(line2));
    }

    @Test
    void findGenericListValue_int() throws Exception
    {
        ArrayList<Token> line1 = new ArrayList<>();
        ArrayList<Token> line2 = new ArrayList<>();

        line1.add(new Token("list", InputType.NORMAL));
        line1.add(new Token("int", InputType.NORMAL));
        line1.add(new Token("a", InputType.NORMAL));
        line1.add(new Token("=", InputType.NORMAL));
        line1.add(new Token("[", InputType.NORMAL));
        line1.add(new Token("2", InputType.NORMAL));
        line1.add(new Token(",", InputType.NORMAL));
        line1.add(new Token("3", InputType.NORMAL));
        line1.add(new Token("}", InputType.NORMAL));

        line2.add(new Token("a",InputType.NORMAL));
        line2.add(new Token("[",InputType.NORMAL));
        line2.add(new Token("1",InputType.NORMAL));
        line2.add(new Token("]",InputType.NORMAL));

        Translator.handleLine(line1);

        IntegerVariable var = (IntegerVariable) ListHandler.findGenericListValue(line2);

        assertEquals(3, var.getValue());
    }

    @Test
    void findBooleanListIndex() throws Exception
    {
        ArrayList<Token> line1 = new ArrayList<>();
        ArrayList<Token> line2 = new ArrayList<>();

        line1.add(new Token("list", InputType.NORMAL));
        line1.add(new Token("boolean", InputType.NORMAL));
        line1.add(new Token("a", InputType.NORMAL));
        line1.add(new Token("=", InputType.NORMAL));
        line1.add(new Token("[", InputType.NORMAL));
        line1.add(new Token("true", InputType.NORMAL));
        line1.add(new Token(",", InputType.NORMAL));
        line1.add(new Token("false", InputType.NORMAL));
        line1.add(new Token("}", InputType.NORMAL));

        line2.add(new Token("a",InputType.NORMAL));
        line2.add(new Token("[",InputType.NORMAL));
        line2.add(new Token("1",InputType.NORMAL));
        line2.add(new Token("]",InputType.NORMAL));

        Translator.handleLine(line1);

        assertFalse(ListHandler.findBooleanListIndex(line2));
    }

    @Test
    void findBooleanListIndexByVariable() throws Exception
    {
        ArrayList<Token> line1 = new ArrayList<>();
        ArrayList<Token> line2 = new ArrayList<>();
        ArrayList<Token> line3 = new ArrayList<>();

        line1.add(new Token("list", InputType.NORMAL));
        line1.add(new Token("boolean", InputType.NORMAL));
        line1.add(new Token("a", InputType.NORMAL));
        line1.add(new Token("=", InputType.NORMAL));
        line1.add(new Token("[", InputType.NORMAL));
        line1.add(new Token("true", InputType.NORMAL));
        line1.add(new Token(",", InputType.NORMAL));
        line1.add(new Token("false", InputType.NORMAL));
        line1.add(new Token("}", InputType.NORMAL));

        line2.add(new Token("a",InputType.NORMAL));
        line2.add(new Token("[",InputType.NORMAL));
        line2.add(new Token("b",InputType.NORMAL));
        line2.add(new Token("]",InputType.NORMAL));

        line3.add(new Token("int",InputType.NORMAL));
        line3.add(new Token("b",InputType.NORMAL));
        line3.add(new Token("=",InputType.NORMAL));
        line3.add(new Token("1",InputType.NORMAL));

        Translator.handleLine(line1);
        Translator.handleLine(line3);

        assertFalse(ListHandler.findBooleanListIndex(line2));
    }

    @Test
    void findGenericListValue_bool() throws Exception
    {
        ArrayList<Token> line1 = new ArrayList<>();
        ArrayList<Token> line2 = new ArrayList<>();

        line1.add(new Token("list", InputType.NORMAL));
        line1.add(new Token("boolean", InputType.NORMAL));
        line1.add(new Token("b", InputType.NORMAL));
        line1.add(new Token("=", InputType.NORMAL));
        line1.add(new Token("[", InputType.NORMAL));
        line1.add(new Token("true", InputType.NORMAL));
        line1.add(new Token(",", InputType.NORMAL));
        line1.add(new Token("false", InputType.NORMAL));
        line1.add(new Token("}", InputType.NORMAL));

        line2.add(new Token("b",InputType.NORMAL));
        line2.add(new Token("[",InputType.NORMAL));
        line2.add(new Token("1",InputType.NORMAL));
        line2.add(new Token("]",InputType.NORMAL));

        Translator.handleLine(line1);

        BooleanVariable var = (BooleanVariable) ListHandler.findGenericListValue(line2);

        assertEquals(false, var.getValue());
    }

    @Test
    void findStringListIndex() throws Exception
    {
        Mapper.clearMaps();

        ArrayList<Token> line1 = new ArrayList<>();
        ArrayList<Token> line2 = new ArrayList<>();

        line1.add(new Token("list", InputType.NORMAL));
        line1.add(new Token("string", InputType.NORMAL));
        line1.add(new Token("a", InputType.NORMAL));
        line1.add(new Token("=", InputType.NORMAL));
        line1.add(new Token("[", InputType.NORMAL));
        line1.add(new Token("hello", InputType.STRING));
        line1.add(new Token(",", InputType.NORMAL));
        line1.add(new Token("world", InputType.STRING));
        line1.add(new Token("}", InputType.NORMAL));

        line2.add(new Token("a",InputType.NORMAL));
        line2.add(new Token("[",InputType.NORMAL));
        line2.add(new Token("1",InputType.NORMAL));
        line2.add(new Token("]",InputType.NORMAL));

        Translator.handleLine(line1);

        assertEquals("world",ListHandler.findStringListIndex(line2));
    }

    @Test
    void findStringListIndexByVariable() throws Exception
    {
        Mapper.clearMaps();

        ArrayList<Token> line1 = new ArrayList<>();
        ArrayList<Token> line2 = new ArrayList<>();
        ArrayList<Token> line3 = new ArrayList<>();

        line1.add(new Token("list", InputType.NORMAL));
        line1.add(new Token("string", InputType.NORMAL));
        line1.add(new Token("a", InputType.NORMAL));
        line1.add(new Token("=", InputType.NORMAL));
        line1.add(new Token("[", InputType.NORMAL));
        line1.add(new Token("hello", InputType.STRING));
        line1.add(new Token(",", InputType.NORMAL));
        line1.add(new Token("world", InputType.STRING));
        line1.add(new Token("}", InputType.NORMAL));

        line2.add(new Token("a",InputType.NORMAL));
        line2.add(new Token("[",InputType.NORMAL));
        line2.add(new Token("b",InputType.NORMAL));
        line2.add(new Token("]",InputType.NORMAL));

        line3.add(new Token("int",InputType.NORMAL));
        line3.add(new Token("b",InputType.NORMAL));
        line3.add(new Token("=",InputType.NORMAL));
        line3.add(new Token("1",InputType.NORMAL));

        Translator.handleLine(line1);
        Translator.handleLine(line3);

        assertEquals("world",ListHandler.findStringListIndex(line2));
    }

    @Test
    void findGenericListValue_string() throws Exception
    {
        Mapper.clearMaps();

        ArrayList<Token> line1 = new ArrayList<>();
        ArrayList<Token> line2 = new ArrayList<>();

        line1.add(new Token("list", InputType.NORMAL));
        line1.add(new Token("string", InputType.NORMAL));
        line1.add(new Token("a", InputType.NORMAL));
        line1.add(new Token("=", InputType.NORMAL));
        line1.add(new Token("[", InputType.NORMAL));
        line1.add(new Token("hello", InputType.STRING));
        line1.add(new Token(",", InputType.NORMAL));
        line1.add(new Token("world", InputType.STRING));
        line1.add(new Token("}", InputType.NORMAL));

        line2.add(new Token("a",InputType.NORMAL));
        line2.add(new Token("[",InputType.NORMAL));
        line2.add(new Token("1",InputType.NORMAL));
        line2.add(new Token("]",InputType.NORMAL));

        Translator.handleLine(line1);

        StringVariable var = (StringVariable) ListHandler.findGenericListValue(line2);

        assertEquals("world", var.getValue());
    }

    @Test
    void findCharListIndex() throws Exception
    {
        ArrayList<Token> line1 = new ArrayList<>();
        ArrayList<Token> line2 = new ArrayList<>();

        line1.add(new Token("list", InputType.NORMAL));
        line1.add(new Token("char", InputType.NORMAL));
        line1.add(new Token("a", InputType.NORMAL));
        line1.add(new Token("=", InputType.NORMAL));
        line1.add(new Token("[", InputType.NORMAL));
        line1.add(new Token("g", InputType.STRING));
        line1.add(new Token(",", InputType.NORMAL));
        line1.add(new Token("q", InputType.STRING));
        line1.add(new Token("}", InputType.NORMAL));

        line2.add(new Token("a",InputType.NORMAL));
        line2.add(new Token("[",InputType.NORMAL));
        line2.add(new Token("1",InputType.NORMAL));
        line2.add(new Token("]",InputType.NORMAL));

        Translator.handleLine(line1);

        assertEquals('q',ListHandler.findCharListIndex(line2));
    }

    @Test
    void findCharListIndexByVariable() throws Exception
    {
        ArrayList<Token> line1 = new ArrayList<>();
        ArrayList<Token> line2 = new ArrayList<>();
        ArrayList<Token> line3 = new ArrayList<>();

        line1.add(new Token("list", InputType.NORMAL));
        line1.add(new Token("char", InputType.NORMAL));
        line1.add(new Token("a", InputType.NORMAL));
        line1.add(new Token("=", InputType.NORMAL));
        line1.add(new Token("[", InputType.NORMAL));
        line1.add(new Token("g", InputType.STRING));
        line1.add(new Token(",", InputType.NORMAL));
        line1.add(new Token("q", InputType.STRING));
        line1.add(new Token("}", InputType.NORMAL));

        line2.add(new Token("a",InputType.NORMAL));
        line2.add(new Token("[",InputType.NORMAL));
        line2.add(new Token("b",InputType.NORMAL));
        line2.add(new Token("]",InputType.NORMAL));

        line3.add(new Token("int",InputType.NORMAL));
        line3.add(new Token("b",InputType.NORMAL));
        line3.add(new Token("=",InputType.NORMAL));
        line3.add(new Token("1",InputType.NORMAL));

        Translator.handleLine(line1);
        Translator.handleLine(line3);

        assertEquals('q',ListHandler.findCharListIndex(line2));
    }

    @Test
    void findGenericListValue_char() throws Exception
    {
        Mapper.clearMaps();

        ArrayList<Token> line1 = new ArrayList<>();
        ArrayList<Token> line2 = new ArrayList<>();

        line1.add(new Token("list", InputType.NORMAL));
        line1.add(new Token("char", InputType.NORMAL));
        line1.add(new Token("a", InputType.NORMAL));
        line1.add(new Token("=", InputType.NORMAL));
        line1.add(new Token("[", InputType.NORMAL));
        line1.add(new Token("g", InputType.STRING));
        line1.add(new Token(",", InputType.NORMAL));
        line1.add(new Token("q", InputType.STRING));
        line1.add(new Token("}", InputType.NORMAL));

        line2.add(new Token("a",InputType.NORMAL));
        line2.add(new Token("[",InputType.NORMAL));
        line2.add(new Token("1",InputType.NORMAL));
        line2.add(new Token("]",InputType.NORMAL));

        Translator.handleLine(line1);

        CharVariable var = (CharVariable) ListHandler.findGenericListValue(line2);

        assertEquals('q', var.getValue());
    }
}