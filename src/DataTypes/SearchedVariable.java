package DataTypes;

public class SearchedVariable {

    String type;
    Object value;

    public SearchedVariable(String type, Object value)
    {
        this.type = type;
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public Object getValue() {
        return value;
    }
}
