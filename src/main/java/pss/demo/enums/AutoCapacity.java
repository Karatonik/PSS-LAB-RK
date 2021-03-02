package pss.demo.enums;

public enum AutoCapacity {
   Up (">= 900m3"),Down ("< 900m3");

    private final String name;

    AutoCapacity(String name){
        this.name= name;
    }

    public String getAutoCapacityName(){
        return name;
    }
}
