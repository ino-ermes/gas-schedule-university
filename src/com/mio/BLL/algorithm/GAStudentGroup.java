/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mio.BLL.algorithm;

/**
 *
 * @author Mio
 */
public class GAStudentGroup {

    public GAStudentGroup(int id, String name, int size) {
        this.id = id;
        this.name = name;
        this.size = size;
    }

    public GAStudentGroup() {
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

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
    private int id;
    private String name;
    private int size;
}
