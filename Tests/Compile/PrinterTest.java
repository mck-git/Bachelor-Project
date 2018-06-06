package Compile;

import DataTypes.Token;
import SharedResources.InputType;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import javax.print.DocFlavor;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class PrinterTest {
//
//    private final ByteArrayOutputStream sysout = new ByteArrayOutputStream();
//    private final ByteArrayOutputStream syserr = new ByteArrayOutputStream();
//
//    @Before
//    public void preparePrintTest() {
//        System.setOut(new PrintStream(sysout));
//        System.setErr(new PrintStream(syserr));
//    }
//
//    @After
//    public void restoreOutputs() {
//        System.setOut(System.out);
//        System.setErr(System.err);
//    }
//
//    @Test
//    void testPrintString() throws Exception
//    {
//        ByteArrayOutputStream sysout = new ByteArrayOutputStream();
//        ByteArrayOutputStream syserr = new ByteArrayOutputStream();
//
//        System.setOut(new PrintStream(sysout));
//        System.setErr(new PrintStream(syserr));
//
//        ArrayList<Token> line1 = new ArrayList<>();
//
//        line1.add(new Token("print", InputType.NORMAL));
//        line1.add(new Token("Hello World", InputType.STRING));
//
//        Translator.handleLine(line1);
//
//        assertEquals("[out]: Hello World", sysout.toString());
//
//
//    }



}