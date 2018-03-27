package DataTypes;

public class VariableContainer {

    String type;
    Object variable;

    public VariableContainer(String type, Object variable)
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
