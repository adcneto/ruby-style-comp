
import java.util.ArrayList;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author icarovts
 */
public class Program {
    ArrayList<Command> commands;
    
    public Program(){
        commands = new ArrayList<Command>();
    }
    
    public void add(Command cmd){
        commands.add(cmd);
    }
    
    public void run(){
        for(Command cmd : commands){
            cmd.run();
        }
    }
    
}
