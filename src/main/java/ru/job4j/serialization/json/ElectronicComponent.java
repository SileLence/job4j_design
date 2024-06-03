package ru.job4j.serialization.json;

public class ElectronicComponent {
    
    private String type;
    
    public ElectronicComponent(String type) {
        this.type = type;
    }
    
    public String getType() {
        return type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    
    @Override
    public String toString() {
        return "ElectronicComponent{"
            + "type='" + type + '\''
            + '}';
    }
}
