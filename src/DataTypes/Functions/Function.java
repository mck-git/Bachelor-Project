package DataTypes.Functions;

import DataTypes.Token;
import DataTypes.VariableContainer;

import java.util.ArrayList;

public interface Function {

    void addLineOfCode(ArrayList<Token> tokens);

    void store();

    VariableContainer returnValue();
}
