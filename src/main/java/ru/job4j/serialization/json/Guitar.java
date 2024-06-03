package ru.job4j.serialization.json;

import java.util.List;

public class Guitar {
    
    private String modelName;
    private String wood;
    private List<ElectronicComponent> electronicComponents;
    
    public Guitar(String modelName, String wood) {
        this.modelName = modelName;
        this.wood = wood;
    }
    
    public Guitar(String modelName, String wood, List<ElectronicComponent> electronicComponents) {
        this.modelName = modelName;
        this.wood = wood;
        this.electronicComponents = electronicComponents;
    }
    
    public String getModelName() {
        return modelName;
    }
    
    public void setModelName(String modelName) {
        this.modelName = modelName;
    }
    
    public String getWood() {
        return wood;
    }
    
    public void setWood(String wood) {
        this.wood = wood;
    }
    
    public boolean isElectric() {
        return electronicComponents != null && !electronicComponents.isEmpty();
    }
    
    public List<ElectronicComponent> getElectronicComponents() {
        return electronicComponents;
    }
    
    public void setElectronicComponents(List<ElectronicComponent> electronicComponents) {
        this.electronicComponents = electronicComponents;
    }
    
    @Override
    public String toString() {
        return "Guitar{" +
            "wood='" + wood + '\'' +
            ", isElectric=" + isElectric() +
            ", electronicComponent=" + electronicComponents +
            '}';
    }
}
