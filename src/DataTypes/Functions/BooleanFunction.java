package DataTypes.Functions;

import Compile.Mapper;
import DataTypes.Token;

import java.util.ArrayList;

public class BooleanFunction extends Function {

    public BooleanFunction(String name, ArrayList<ArrayList<Token> > linesOfCodeInMethod)
    {
        super(name, linesOfCodeInMethod);
    }

    public void store()
    {
        Mapper.storeBooleanFunction(this);
    }


}
