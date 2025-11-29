package model;

import java.io.Serializable;

public class Customer implements Serializable {
    private String name;
    private String phoneNumber;

    public Customer(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public String getName() { return name; }
    public String getPhoneNumber() { return phoneNumber; }

    @Override
    public String toString() {
        return name + " (" + phoneNumber + ")";
    }
}
