
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
    private Symbol sym;
    private ExpressionCalculator ec;
    
    public CommandAttr(Symbol sym){
        this.sym = sym;
    }

    public void setExpressionCalculator(ExpressionCalculator ec) {
        this.ec = ec;
    }
    
    public void run(){
        sym.setValue(ec.calculate());
        sym.setInitialized(true);
    }
    
}
