
import java.util.ArrayList;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author icarovts
 */
public class CommandIf extends Command{
    private ArrayList<Command> commands;
    private ArrayList<Command> elseCommands;
    private ConditionVerifier verifier;

    public CommandIf(ConditionVerifier verifier) {
        this.verifier = verifier;
        commands = new ArrayList<Command>();
        elseCommands = new ArrayList<Command>();
    }
    
    public void run(){
        if(verifier.verify()){
            for(Command cmd : commands){
                cmd.run();
            }
        } else {
            for(Command cmd : elseCommands){
                cmd.run();
            }
        }
            
    }
    
    public void addToCommands(Command cmd){
        commands.add(cmd);
    }
    
    public void addToElseCommands(Command cmd){
        elseCommands.add(cmd);
    }
    
}
