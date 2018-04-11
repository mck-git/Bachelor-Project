package DataTypes.Functions;

import Compile.Mapper;
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
        linesOfCodeInMethod.add( (ArrayList<Token>) tokens.clone() );
    }

    public void store()
    {
        Mapper.storeVoidFunction(this);
    }

    public ArrayList<ArrayList<Token>> getLinesOfCodeInMethod() {
        return linesOfCodeInMethod;
    }

    public String getName() {
        return name;
    }

}
