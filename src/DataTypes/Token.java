package DataTypes;

import SharedResources.InputType;

public class Token {

    String content;
    InputType inputType;

    public Token (String content, InputType inputType)
    {
        this.content = content;
        this.inputType = inputType;
    }


    public String getContent() {
        return content;
    }
}
