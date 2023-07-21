/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mio.DTO;

/**
 *
 * @author Mio
 */
public class Department {

    public Department(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Department() {
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
    private int id;
    private String name;

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Department) {
            return id == ((Department)obj).id;
        }
        return false;
    }

    @Override
    public String toString() {
        return name;
    }
    
}
