package DataTypes.Functions;

import Compile.Mapper;
import DataTypes.Token;
import DataTypes.VariableContainer;

import java.util.ArrayList;

public class IntegerFunction implements Function {

    String name;
    ArrayList< ArrayList<Token> > linesOfCodeInMethod;

    public IntegerFunction(String name, ArrayList< ArrayList<Token> > linesOfCodeInMethod)
    {
        this.name = name;
        this.linesOfCodeInMethod = linesOfCodeInMethod;
    }

    public void addLineOfCode(ArrayList<Token> tokens)
    {
        linesOfCodeInMethod.add(tokens);
    }

    public void store()
    {
        Mapper.storeIntFunction(this);
    }

    public VariableContainer returnValue()
    {

    }

    public String getName() {
        return name;
    }
}