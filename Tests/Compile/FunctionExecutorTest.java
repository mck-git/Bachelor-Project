package Compile;

import DataTypes.Functions.Function;
import DataTypes.Functions.FunctionContainer;
import DataTypes.Functions.IntegerFunction;
import DataTypes.Token;
import DataTypes.Variables.IntegerVariable;
import DataTypes.Variables.Variable;
import DataTypes.Variables.VariableContainer;
import Errors.InvalidSyntaxException;
import Maps.Functions.IntegerFunctionMap;
import Maps.Functions.VoidFunctionMap;
import Maps.Variables.IntegerMap;
import Maps.Variables.TemporaryVariablesMap;
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


    @Test
    void findAndRunVoidFunctionWithArguments() throws Exception
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
        line1.add(new Token("void", InputType.NORMAL));
        line1.add(new Token("test", InputType.NORMAL));
        line1.add(new Token("(", InputType.NORMAL));
        line1.add(new Token("int", InputType.NORMAL));
        line1.add(new Token("b", InputType.NORMAL));
        line1.add(new Token(")", InputType.NORMAL));
        line1.add(new Token("{", InputType.NORMAL));

        line2.add(new Token("a", InputType.NORMAL));
        line2.add(new Token("=", InputType.NORMAL));
        line2.add(new Token("b", InputType.NORMAL));

        line3.add(new Token("}", InputType.NORMAL));

        Compile.Translator.handleLine(line0);
        Compile.Translator.handleLine(line1);
        Compile.Translator.handleLine(line2);
        Compile.Translator.handleLine(line3);

        assertEquals(1, Maps.Functions.VoidFunctionMap.size());

        FunctionContainer fcTest = Mapper.findFunction("test");

        Function test = fcTest.getFunction();

        ArrayList<Variable> args = test.getArgumentVariables();

        assertEquals(1, args.size());

        line4.add(new Token("test", InputType.NORMAL));
        line4.add(new Token("(", InputType.NORMAL));
        line4.add(new Token("2", InputType.NORMAL));
        line4.add(new Token(")", InputType.NORMAL));

        Translator.handleLine(line4);
        assertEquals(0, TemporaryVariablesMap.size());

        assertEquals(1, IntegerMap.size());

        VariableContainer vc = Mapper.findVariable("a");

        IntegerVariable var = (IntegerVariable) vc.getVariable();

        assertEquals(2, var.getValue());

    }

    @Test
    void findAndRunIntFunctionWithArguments() throws Exception
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

        Translator.handleLine(line4);
        assertEquals(1, TemporaryVariablesMap.size());

        assertEquals(1, IntegerMap.size());

        VariableContainer vc = Mapper.findVariable("a");

        IntegerVariable var = (IntegerVariable) vc.getVariable();

        assertEquals(3, var.getValue());

    }


}