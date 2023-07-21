/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mio.DTO;

/**
 *
 * @author Mio
 */
public class StudentGroup {

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public StudentGroup(int id, String name, int departmentId, int eduProgramId, int size) {
        this.id = id;
        this.name = name;
        this.departmentId = departmentId;
        this.eduProgramId = eduProgramId;
        this.size = size;
    }
    
    public StudentGroup(StudentGroup s) {
        this.id = s.id;
        this.name = s.name;
        this.departmentId = s.departmentId;
        this.eduProgramId = s.eduProgramId;
        this.size = s.size;
    }
    
    public void copy(StudentGroup s) {
        this.id = s.id;
        this.name = s.name;
        this.departmentId = s.departmentId;
        this.eduProgramId = s.eduProgramId;
        this.size = s.size;
    }

    public StudentGroup() {
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

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public int getEduProgramId() {
        return eduProgramId;
    }

    public void setEduProgramId(int eduProgramId) {
        this.eduProgramId = eduProgramId;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        return ((StudentGroup)obj).id == id;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 31 * hash + this.id;
        return hash;
    }

    

    private int id;
    private String name;
    private int departmentId;
    private int eduProgramId;
    private int size;
}
