package DataTypes;

public class SearchedVariable {

    String type;
    Object variable;

    public SearchedVariable(String type, Object variable)
    {
        this.type = type;
        this.variable = variable;
    }

    public String getType() {
        return type;
    }

    public Object getVariable() {
        return variable;
    }
}
