package DataTypes.Functions;

import Compile.Mapper;
import DataTypes.Token;
import DataTypes.VariableContainer;

import java.util.ArrayList;

public class VoidFunction implements Function {

    String name;
    ArrayList< ArrayList<Token> > linesOfCodeInMethod;

    public VoidFunction (String name, ArrayList< ArrayList<Token> > linesOfCodeInMethod)
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
        Mapper.storeVoidFunction(this);
    }

    public VariableContainer returnValue()
    {
        return null;
    }

    public String getName() {
        return name;
    }
}
