
package com.mio.DTO;

public class Admin {

    public Admin(int id, String name, String phoneNumber, boolean male, String birthday, String imagePath) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.male = male;
        this.birthday = birthday;
        this.imagePath = imagePath;
    }

    public Admin() {
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean isMale() {
        return male;
    }

    public void setMale(boolean male) {
        this.male = male;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }


    private int id;
    private String name;
    private String phoneNumber;
    private boolean male;
    private String birthday;
    private String imagePath;
}
