package DataTypes.Lists;

import java.util.ArrayList;

public class IntegerList extends List {

    ArrayList<Integer> listValues;

    public IntegerList(String name)
    {
        super(name);
        listValues = new ArrayList<>();
    }

    public void add(int num)
    {
        listValues.add(num);
    }

    public int get(int index)
    {
        return listValues.get(index);
    }

    public int length()
    {
        return listValues.size();
    }

}
