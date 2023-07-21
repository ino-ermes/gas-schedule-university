/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mio.DTO;

/**
 *
 * @author Mio
 */
public class Student {

    public Student(Student s) {
        this.id = s.id;
        this.name = s.name;
        this.phoneNumber = s.phoneNumber;
        this.male = s.male;
        this.birthday = s.birthday;
        this.imagePath = s.imagePath;
        this.studentGroupId = s.studentGroupId;
    }
    
    public void copy(Student s) {
        this.id = s.id;
        this.name = s.name;
        this.phoneNumber = s.phoneNumber;
        this.male = s.male;
        this.birthday = s.birthday;
        this.imagePath = s.imagePath;
        this.studentGroupId = s.studentGroupId;
    }
    
    public Student(int id, String name, String phoneNumber, boolean male, String birthday, String imagePath, int studentGroupId) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.male = male;
        this.birthday = birthday;
        this.imagePath = imagePath;
        this.studentGroupId = studentGroupId;
    }

    public Student() {
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

    public void setImagePath(String imgagePath) {
        this.imagePath = imgagePath;
    }

    public int getStudentGroupId() {
        return studentGroupId;
    }

    public void setStudentGroupId(int studentGroupId) {
        this.studentGroupId = studentGroupId;
    }
    private int id;
    private String name;
    private String phoneNumber;
    private boolean male;
    private String birthday;
    private String imagePath;
    private int studentGroupId;
}
