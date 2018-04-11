package DataTypes.Functions;

import DataTypes.Token;

import java.util.ArrayList;

public interface Function {

    void addLineOfCode(ArrayList<Token> tokens);

    void store();

    ArrayList<ArrayList<Token>> getLinesOfCodeInMethod();

    String getName();

}
