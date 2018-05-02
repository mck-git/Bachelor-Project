package DataTypes.Functions;

import Compile.Mapper;
import DataTypes.Token;

import java.util.ArrayList;

public class VoidFunction extends Function {

    public VoidFunction (String name, ArrayList< ArrayList<Token> > linesOfCodeInMethod)
    {
        super(name, linesOfCodeInMethod);
    }

    public void store()
    {
        Mapper.storeVoidFunction(this);
    }

}
