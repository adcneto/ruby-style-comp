
import javax.swing.JOptionPane;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author icarovts
 */
public class CommandRead extends Command{
    private Symbol sym;
    
    public CommandRead(Symbol sym){
        this.sym = sym;
    }
    public void run(){
        String text;
        text = JOptionPane.showInputDialog("Digite o valor de " + sym.getId());
        sym.setValue(Integer.parseInt(text));      
    }
    
}
