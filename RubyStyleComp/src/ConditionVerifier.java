/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author icarovts
 */
public class ConditionVerifier {
    private int left;
    private int right;
    private Symbol symbolLeft;
    private Symbol symbolRight;
    private String operator;
    
    public ConditionVerifier(String left, Symbol symbolLeft){
        this.left = Integer.parseInt(left);
        this.symbolLeft = symbolLeft;
    }
    
    public void setRight(String right, Symbol symbolRight){
        this.right = Integer.parseInt(right);
        this.symbolRight = symbolRight;
    }
    
    public void setOperator(String operator){
        this.operator = operator;
    }
    
    public boolean verify(){
        if(symbolLeft != null)
            left = symbolLeft.getValue();
        if(symbolRight != null)
            right = symbolRight.getValue(); 
        
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
