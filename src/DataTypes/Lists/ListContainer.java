package DataTypes.Lists;

public class ListContainer {

    String type;
    List list;

    public ListContainer(String type, List list)
    {
        this.type = type;
        this.list = list;
    }

    public String getType()
    {
        return type;
    }

    public List getList()
    {
        return list;
    }


}
