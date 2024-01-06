package PojoClasses;

import java.util.List;

public class AddPlace {

    private String name;
    private int accuracy;
    private String phone_number;
    private String address;
    private String website;
    private String language;
private Location location;
private List<String> types;


public void setName(String name){
    this.name =name;
}

public String getName(){
    return name;
    }

    public void setPhone_number(String phone_number){
        this.phone_number =phone_number;
    }

    public String getPhone_number(){
        return phone_number;
    }

    public void setAddress(String address){
        this.address =address;
    }

    public String getAddress(){
        return address;
    }

    public void setWebsite(String website){
        this.website =website;
    }

    public String getWebsite(){
        return website;
    }

    public void setLanguage(String language){
        this.language =language;
    }

    public String getLanguage(){
        return language;
    }

    public void setAccuracy(int accuracy){
        this.accuracy =accuracy;
    }

    public int getAccuracy(){
        return accuracy;
    }

    public void setLocation(Location location){
    this.location =location;
    }

    public Location getLocation(){
    return  location;
    }


    public void setTypes(List<String> types){
    this.types=types;

    }
    public List<String> getTypes(){
    return types;
    }

}
