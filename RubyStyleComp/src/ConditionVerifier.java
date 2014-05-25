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
    private String operator;
    
    public ConditionVerifier(int left){
        this.left = left;
    }
    
    public void setRight(int right){
        this.right = right;
    }
    
    public void setOperator(String operator){
        this.operator = operator;
    }
    
    public boolean verify(){
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
