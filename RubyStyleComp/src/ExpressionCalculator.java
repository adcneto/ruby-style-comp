
import java.util.ArrayList;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author icarovts
 */
public class ExpressionCalculator {
    public ArrayList<Object> values;
    public ArrayList<String> operators;
    
    public ExpressionCalculator(){
        values = new ArrayList<Object>();
        operators = new ArrayList<String>();
    }
    
    public int calculate(){
        return calculateStep(0, 0, false);
    }
    // 0, 1, 2, 3
    // 1, 2, 3, 4
    // +, *, +, null
    private int calculateStep(int step, int cachedValue, boolean cached){
        int val;
        if(cached)
            val = cachedValue;
        else{
            Object obj = values.get(step);
            if (obj instanceof Symbol){
                Symbol sym = (Symbol)obj;
                val = sym.getIntValue();
            } else {
               val = (Integer) obj;
            }
        }
        
        int nextVal;
              
        String op = operators.get(step);
               
        if(op == null){
            return val;
        } else {
            Object nextObj = values.get(step + 1);
            if (nextObj instanceof Symbol){
                Symbol sym = (Symbol)nextObj;
                nextVal = sym.getIntValue();
            } else {
               nextVal = (Integer) nextObj;
            }
            
            if(op.equals("*")){
                val = val * nextVal;
                return calculateStep(step + 1, val, true);
            }
            else if(op.equals("/")){
                val = val / nextVal;
                return calculateStep(step + 1, val, true);
            }
            else if(op.equals("+")){
                nextVal = calculateStep(step + 1, 0, false);
                return val + nextVal;
            }
            else if(op.equals("-")){
                nextVal = calculateStep(step + 1, 0, false);
                return val - nextVal;
            }
                
        } 
        
        return 0;
    }
}



// 1, 2
// +, null

// 1, 2, 3, 4
// *, *, +, null
