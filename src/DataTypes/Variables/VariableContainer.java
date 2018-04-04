package DataTypes.Variables;

public class VariableContainer {

    String type;
    Variable variable;

    public VariableContainer(String type, Variable variable)
    {
        this.type = type;
        this.variable = variable;
    }

    public String getType() {
        return type;
    }

    public Variable getVariable() {
        return variable;
    }
}
