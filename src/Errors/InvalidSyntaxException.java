package Errors;

public class InvalidSyntaxException extends Exception {

    int lineNumber;

    public InvalidSyntaxException(int lineNumber)
    {
        this.lineNumber = lineNumber;
    }

    public InvalidSyntaxException(String message)
    {
        super(message);
    }

    public String getMessage() {
        return "Syntax error at line: " + lineNumber;
    }
}
