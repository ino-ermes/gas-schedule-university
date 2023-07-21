
package com.mio.DTO;

public class Teacher {    
    
    public Teacher(int id, String name, String phoneNumber, boolean male, String birthday, String imagePath, int departmentId) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.male = male;
        this.birthday = birthday;
        this.imagePath = imagePath;
        this.departmentId = departmentId;
    }

    public Teacher(Teacher t) {
        id = t.id;
        name = t.name;
        phoneNumber = t.phoneNumber;
        male = t.male;
        birthday = t.birthday;
        imagePath = t.imagePath;
        departmentId = t.departmentId;
    }
    
    public Teacher() {
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

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
    
    public void copy(Teacher t) {
        id = t.id;
        name = t.name;
        phoneNumber = t.phoneNumber;
        male = t.male;
        birthday = t.birthday;
        imagePath = t.imagePath;
        departmentId = t.departmentId;
    }

    private int id;
    private String name;
    private String phoneNumber;
    private boolean male;
    private String birthday;
    private String imagePath;
    private int departmentId;
}
