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
    public final static String INT = "int";
    private String  id;
    private int     intValue;
    private float   floatValue;
    private String  stringValue;
    private boolean initialized;
    private String  type;

    public Symbol(String id, int value, boolean initialized, String type) {
        this.id = id;
        this.intValue = value;
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

    public int getIntValue() {
        return intValue;
    }
    
    public String getStringValue() {
        return stringValue;
    }

    public void setIntValue(int intValue) {
        this.intValue = intValue;
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
    
    public boolean isInt(){
        return type.equals(INT);
    }
     
}
