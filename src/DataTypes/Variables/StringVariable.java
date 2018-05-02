package DataTypes.Variables;

public class StringVariable extends Variable{

    private String value;

    public StringVariable(String name, String value)
    {
        super(name);
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }


}
