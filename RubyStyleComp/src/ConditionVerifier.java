/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author icarovts
 */
public class ConditionVerifier {
    private float left;
    private float right;
    private Symbol symbolLeft;
    private Symbol symbolRight;
    private String operator;
    
    public ConditionVerifier(String left, Symbol symbolLeft){
        this.left = Float.parseFloat(left);
        this.symbolLeft = symbolLeft;
    }
    
    public void setRight(String right, Symbol symbolRight){
        this.right = Float.parseFloat(right);
        this.symbolRight = symbolRight;
    }
    
    public void setOperator(String operator){
        this.operator = operator;
    }
    
    public boolean verify(){
        if(symbolLeft != null)
            left = symbolLeft.getNumValue();
        if(symbolRight != null)
            right = symbolRight.getNumValue(); 
        
        if(operator.equals(">"))
            return left > right;
        if(operator.equals("<"))
            return left < right;
        if(operator.equals(">="))
            return left >= right;
        if(operator.equals("<="))
            return left <= right;
        if(operator.equals("!="))
            return left != right;
        if(operator.equals("=="))
            return left == right;
        
        return false;
    }
}
