package DataTypes.Functions;

import Compile.Mapper;
import DataTypes.Functions.Function;
import DataTypes.Token;

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

    public String getName() {
        return name;
    }
}
