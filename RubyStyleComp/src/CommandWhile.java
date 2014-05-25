
import java.util.ArrayList;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author icarovts
 */
public class CommandWhile extends Command{
    private ArrayList<Command> commands;
    private ConditionVerifier verifier;

    public CommandWhile(ConditionVerifier verifier) {
        this.verifier = verifier;
        commands = new ArrayList<Command>();
    }
    
    public void run(){
        while(verifier.verify()){
            for(Command cmd : commands){
                cmd.run();
            }
        }            
    }
    
    public void addToCommands(Command cmd){
        commands.add(cmd);
    }
    
}

