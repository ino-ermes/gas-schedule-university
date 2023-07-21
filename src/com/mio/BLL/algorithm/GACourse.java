/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mio.BLL.algorithm;

/**
 *
 * @author Mio
 */
public class GACourse {

    public GACourse(int id, String name, boolean lab) {
        this.id = id;
        this.name = name;
        this.lab = lab;
    }

    public GACourse() {
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

    public boolean isLab() {
        return lab;
    }

    public void setLab(boolean lab) {
        this.lab = lab;
    }

    private int id;
    private String name;
    private boolean lab;
}
