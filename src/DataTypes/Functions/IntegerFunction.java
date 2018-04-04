package DataTypes.Functions;

import Compile.Mapper;
import DataTypes.Token;

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

    public ArrayList<ArrayList<Token>> getLinesOfCodeInMethod() {
        return linesOfCodeInMethod;
    }

    public String getName() {
        return name;
    }
}
