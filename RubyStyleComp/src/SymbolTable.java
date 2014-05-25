
import java.util.ArrayList;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author icarovts
 */
public class SymbolTable {
    
    public ArrayList<Symbol> table;
    
    public SymbolTable(){
        table = new ArrayList<Symbol>();
    }
    
    public void add(Symbol sym){
        table.add(sym);
        System.out.println(sym.getId());
    }
    
    public Symbol getById(String id){
        for(Symbol sym: table){
            if (sym.getId().equals(id))
                return sym;
        }
        return null;
    }

    
}
