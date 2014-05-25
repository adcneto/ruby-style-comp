
import javax.swing.JOptionPane;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author icarovts
 */
public class CommandWrite extends Command{
    private Object var;
    
    public CommandWrite(Object var){
        this.var = var;
    }
    public void run(){
        if (var instanceof Symbol){
            Symbol sym = (Symbol)var;
            JOptionPane.showMessageDialog(null, sym.getId() + "=" + sym.getValue());
        }
        else if (var instanceof java.lang.String) {
            JOptionPane.showMessageDialog(null, (String)var);
        }
}
    
}
