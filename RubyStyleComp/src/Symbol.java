/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author icarovts
 */
public class Symbol {
    public final static String STRING = "string";
    public final static String NUM = "num";
    private String  id;
    private float     numValue;
    private String  stringValue;
    private boolean initialized;
    private String  type;

    public Symbol(String id, float value, boolean initialized, String type) {
        this.id = id;
        this.numValue = value;
        this.initialized = initialized;
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public float getNumValue() {
        return numValue;
    }
    
    public String getStringValue() {
        return stringValue;
    }

    public void setNumValue(float numValue) {
        this.numValue = numValue;
    }
    
    public void setStringValue(String stringValue) {
        this.stringValue = stringValue.replaceAll("\"", "");
    }

    public boolean isInitialized() {
        return initialized;
    }

    public void setInitialized(boolean initialized) {
        this.initialized = initialized;
    }
    
    public boolean isString(){
        return type.equals(STRING);
    }
    
    public boolean isNum(){
        return type.equals(NUM);
    }
     
}
