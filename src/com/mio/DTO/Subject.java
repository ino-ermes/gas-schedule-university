
package com.mio.DTO;

public class Subject {

    public Subject(int id, String name, int departmentId, boolean requireLab, int credit) {
        this.id = id;
        this.name = name;
        this.departmentId = departmentId;
        this.requireLab = requireLab;
        this.credit = credit;
    }

    public Subject(Subject s) {
        this.id = s.id;
        this.name = s.name;
        this.departmentId = s.departmentId;
        this.requireLab = s.requireLab;
        this.credit = s.credit; 
    }
    
    public void copy(Subject s) {
        this.id = s.id;
        this.name = s.name;
        this.departmentId = s.departmentId;
        this.requireLab = s.requireLab;
        this.credit = s.credit; 
    }
    
    public Subject() {
    }
    public Subject(int id) {
        this.id = id;
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

    public boolean isRequireLab() {
        return requireLab;
    }

    public void setRequireLab(boolean requireLab) {
        this.requireLab = requireLab;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Subject) {
            return ((Subject)obj).getId() == id;
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + this.id;
        return hash;
    }
    
    
    private int id;
    private String name;
    private int departmentId;
    private boolean requireLab;
    private int credit;
}
