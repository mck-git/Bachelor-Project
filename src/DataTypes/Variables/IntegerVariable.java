package DataTypes.Variables;

public class IntegerVariable {

    String name;
    int value;

    public IntegerVariable(String name, int value)
    {
        this.name = name;
        this.value = value;
    }


    public String getName()
    {
        return this.name;
    }

    public int getValue() {
        return this.value;
    }
}
