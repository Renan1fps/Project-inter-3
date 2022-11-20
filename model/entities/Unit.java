package model.entities;

public class Unit {

    private int id;
    private String name;
    private String unitPhone;
    private String address;
    private String city;
    private int number;
    private String state;

    public Unit(int id, String name, String unitPhone, String address, String city, int number, String state) {
        this.id = id;
        this.name = name;
        this.unitPhone = unitPhone;
        this.address = address;
        this.city = city;
        this.number = number;
        this.state = state;
    }

    public Unit() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnitPhone() {
        return unitPhone;
    }

    public void setUnitPhone(String unitPhone) {
        this.unitPhone = unitPhone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String adress) {
        this.address = adress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
