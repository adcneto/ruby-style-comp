
import java.io.BufferedReader;
import java.io.FileReader;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author icarovts
 */
public class Main {
    public static void main(String[] args) throws Exception {
        try{
            RubyStyleLexer lexer = new RubyStyleLexer(new BufferedReader(new FileReader("input.txt")));
            RubyStyleParser parser = new RubyStyleParser(lexer);
            parser.program();
        } catch(Exception e){
            System.err.print(e.getMessage());
        }
    }
    
}
