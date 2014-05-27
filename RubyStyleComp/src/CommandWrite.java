
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
            String message = sym.getId() + " = ";
            if(sym.isInt())
                message += sym.getIntValue();
            else if(sym.isString())
                message += sym.getStringValue();
            
            JOptionPane.showMessageDialog(null, message);
        }
        else if (var instanceof java.lang.String) {
            JOptionPane.showMessageDialog(null, (String)var);
        }
}
    
}
