
import javax.swing.JOptionPane;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author icarovts
 */
public class CommandAttr extends Command{
    private String value;
    private Symbol sym;
    
    public CommandAttr(Symbol sym){
        this.sym = sym;
    }
    
    public void setValue(String value){
        this.value = value;
    }
    
    public void run(){
        sym.setValue(Integer.parseInt(value));
        sym.setInitialized(true);
    }
    
}
