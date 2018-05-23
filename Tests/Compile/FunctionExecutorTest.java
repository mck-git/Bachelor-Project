package Compile;

import DataTypes.Functions.IntegerFunction;
import DataTypes.Token;
import DataTypes.Variables.IntegerVariable;
import Errors.InvalidSyntaxException;
import Maps.Functions.IntegerFunctionMap;
import Maps.Functions.VoidFunctionMap;
import Maps.Variables.IntegerMap;
import SharedResources.InputType;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class FunctionExecutorTest {

    @Test
    void findAndRunVoidFunction() throws Exception
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


        line2.add(new Token("int", InputType.NORMAL));
        line2.add(new Token("a", InputType.NORMAL));
        line2.add(new Token("=", InputType.NORMAL));
        line2.add(new Token("5", InputType.NORMAL));


        line3.add(new Token("}", InputType.NORMAL));

        Translator.handleLine(line1);
        Translator.handleLine(line2);
        Translator.handleLine(line3);


        assertEquals(1, VoidFunctionMap.size());

        assertEquals(0, IntegerMap.size());

        ArrayList<Token> line4 = new ArrayList<>();

        line4.add(new Token("test", InputType.NORMAL));
        line4.add(new Token("(", InputType.NORMAL));
        line4.add(new Token(")", InputType.NORMAL));

        Translator.handleLine(line4);

        assertEquals(1, IntegerMap.size());

        IntegerVariable a = IntegerMap.find("a");


        assertEquals(5,a.getValue());
    }

    @Test
    void findAndRunIntegerFunction() throws Exception
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
        line1.add(new Token(")", InputType.NORMAL));
        line1.add(new Token("{", InputType.NORMAL));


        line2.add(new Token("int", InputType.NORMAL));
        line2.add(new Token("a", InputType.NORMAL));
        line2.add(new Token("=", InputType.NORMAL));
        line2.add(new Token("5", InputType.NORMAL));

        line3.add(new Token("return", InputType.NORMAL));
        line3.add(new Token("2", InputType.NORMAL));

        line4.add(new Token("}", InputType.NORMAL));

        Translator.handleLine(line1);
        Translator.handleLine(line2);
        Translator.handleLine(line3);
        Translator.handleLine(line4);

        assertEquals(1, IntegerFunctionMap.size());

        assertEquals(0, IntegerMap.size());

        ArrayList<Token> line5 = new ArrayList<>();

        line5.add(new Token("test", InputType.NORMAL));
        line5.add(new Token("(", InputType.NORMAL));
        line5.add(new Token(")", InputType.NORMAL));

        Translator.handleLine(line5);

        assertEquals(1, IntegerMap.size());

        IntegerVariable a = IntegerMap.find("a");


        assertEquals(5,a.getValue());
    }




    @Test
    void findAndRunNonexistingFunction() throws Exception
    {
        Mapper.clearMaps();

        try {
            ArrayList<Token> line4 = new ArrayList<>();

            line4.add(new Token("test", InputType.NORMAL));
            line4.add(new Token("(", InputType.NORMAL));
            line4.add(new Token(")", InputType.NORMAL));

            Translator.handleLine(line4);
            fail("Handle line is supposed to not parse as function is not declared");
        } catch (InvalidSyntaxException ignored) {
        }
    }
}