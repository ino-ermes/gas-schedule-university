/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mio.DTO;

/**
 *
 * @author Mio
 */
public class EduProgram {

    public EduProgram(int id, String name, int departmentId, int semesterCount, int semesterCurrent) {
        this.id = id;
        this.name = name;
        this.departmentId = departmentId;
        this.semesterCount = semesterCount;
        this.semesterCurrent = semesterCurrent;
    }
    
    public EduProgram(EduProgram o) {
        this.id = o.id;
        this.name = o.name;
        this.departmentId = o.departmentId;
        this.semesterCount = o.semesterCount;
        this.semesterCurrent = o.semesterCurrent;
    }
    
    public void copy(EduProgram o) {
        this.id = o.id;
        this.name = o.name;
        this.departmentId = o.departmentId;
        this.semesterCount = o.semesterCount;
        this.semesterCurrent = o.semesterCurrent;
    }
    
    public EduProgram() {
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

    public int getSemesterCount() {
        return semesterCount;
    }

    public void setSemesterCount(int semesterCount) {
        this.semesterCount = semesterCount;
    }

    public int getSemesterCurrent() {
        return semesterCurrent;
    }
    
    public boolean isEnd() {
        return semesterCount == semesterCurrent;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof EduProgram) {
            return ((EduProgram)obj).getId() == id;
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + this.id;
        return hash;
    }

    @Override
    public String toString() {
        return name;
    }
    
    

    
    
    public void setSemesterCurrent(int semesterCurrent) {
        this.semesterCurrent = semesterCurrent;
    }
    private int id;
    private String name;
    private int departmentId;
    private int semesterCount;
    private int semesterCurrent;
}
