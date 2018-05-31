package Compile;

import DataTypes.Token;
import DataTypes.Variables.IntegerVariable;
import DataTypes.Variables.VariableContainer;
import Errors.InvalidSyntaxException;
import SharedResources.InputType;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class LoopsTest {

    @Test
    void loopFalse() throws InvalidSyntaxException
    {
        Mapper.clearMaps();

        ArrayList<Token> line1 = new ArrayList<>();
        ArrayList<Token> line2 = new ArrayList<>();
        ArrayList<Token> line3 = new ArrayList<>();
        ArrayList<Token> line4 = new ArrayList<>();

        line1.add(new Token("int", InputType.NORMAL));
        line1.add(new Token("a", InputType.NORMAL));
        line1.add(new Token("=", InputType.NORMAL));
        line1.add(new Token("1", InputType.NORMAL));

        line2.add(new Token("while", InputType.NORMAL));
        line2.add(new Token("false", InputType.NORMAL));
        line2.add(new Token("{", InputType.NORMAL));

        line3.add(new Token("a", InputType.NORMAL));
        line3.add(new Token("=", InputType.NORMAL));
        line3.add(new Token("a", InputType.NORMAL));
        line3.add(new Token("+", InputType.NORMAL));
        line3.add(new Token("1", InputType.NORMAL));

        line4.add(new Token("}", InputType.NORMAL));

        Translator.handleLine(line1);
        Translator.handleLine(line2);
        Translator.handleLine(line3);
        Translator.handleLine(line4);

        VariableContainer vc = Mapper.findVariable("a");

        IntegerVariable var = (IntegerVariable) vc.getVariable();

        assertEquals(1,var.getValue());
    }

    @Test
    void loopTrue() throws InvalidSyntaxException
    {
        Mapper.clearMaps();

        ArrayList<Token> line1 = new ArrayList<>();
        ArrayList<Token> line2 = new ArrayList<>();
        ArrayList<Token> line3 = new ArrayList<>();
        ArrayList<Token> line4 = new ArrayList<>();

        line1.add(new Token("int", InputType.NORMAL));
        line1.add(new Token("a", InputType.NORMAL));
        line1.add(new Token("=", InputType.NORMAL));
        line1.add(new Token("1", InputType.NORMAL));

        line2.add(new Token("while", InputType.NORMAL));
        line2.add(new Token("a", InputType.NORMAL));
        line2.add(new Token("<", InputType.NORMAL));
        line2.add(new Token("5", InputType.NORMAL));
        line2.add(new Token("{", InputType.NORMAL));

        line3.add(new Token("a", InputType.NORMAL));
        line3.add(new Token("=", InputType.NORMAL));
        line3.add(new Token("a", InputType.NORMAL));
        line3.add(new Token("+", InputType.NORMAL));
        line3.add(new Token("1", InputType.NORMAL));

        line4.add(new Token("}", InputType.NORMAL));

        Translator.handleLine(line1);
        Translator.handleLine(line2);
        Translator.handleLine(line3);
        Translator.handleLine(line4);

        VariableContainer vc = Mapper.findVariable("a");

        IntegerVariable var = (IntegerVariable) vc.getVariable();

        assertEquals(5,var.getValue());

    }


}