package DataTypes.Variables;

public class CharVariable extends Variable{
    char value;

    public CharVariable(String name, char value)
    {
        super(name);
        this.value = value;
    }

    public CharVariable(String name)
    {
        super(name);
    }

    public char getValue() {
        return this.value;
    }




}
