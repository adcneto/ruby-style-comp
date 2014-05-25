/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author icarovts
 */
public class ConditionVerifier {
    private Symbol left;
    private Symbol right;
    private String operator;
    
    public ConditionVerifier(Symbol left){
        this.left = left;
    }
    
    public void setRight(Symbol right){
        this.right = right;
    }
    
    public void setOperator(String operator){
        this.operator = operator;
    }
    
    public boolean verify(){
        if(operator.equals(">"))
            return left.getValue() > right.getValue();
        if(operator.equals("<"))
            return left.getValue() < right.getValue();
        if(operator.equals(">="))
            return left.getValue() >= right.getValue();
        if(operator.equals("<="))
            return left.getValue() <= right.getValue();
        if(operator.equals("!="))
            return left.getValue() != right.getValue();
        if(operator.equals("=="))
            return left.getValue() == right.getValue();
        
        return false;
    }
}
