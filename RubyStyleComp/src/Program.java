
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
    private ArrayList<Command> commands;
    private Command currentBlock;
    private String currentScope;
    
    public Program(){
        commands = new ArrayList<Command>();
    }
    
    public void add(Command cmd){
        if(currentBlock == null)
            commands.add(cmd);
        else if(currentScope.equals("if")){
            CommandIf cmdIf = (CommandIf) currentBlock;
            cmdIf.addToCommands(cmd);
        } else if(currentScope.equals("else")){
            CommandIf cmdIf = (CommandIf) currentBlock;
            cmdIf.addToElseCommands(cmd);
        } else if(currentScope.equals("while")){
            CommandWhile cmdWhile = (CommandWhile) currentBlock;
            cmdWhile.addToCommands(cmd);
        }
    }
    
    public void run(){
        for(Command cmd : commands){
            cmd.run();
        }
    }

    public void setCurrentBlock(Command currentBlock) {
        this.currentBlock = currentBlock;
    }

    public void setCurrentScope(String currentScope) {
        this.currentScope = currentScope;
    }
    
    
}
