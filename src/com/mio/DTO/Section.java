
package com.mio.DTO;

public class Section {

    public static enum SectionStatus {
        NORMAL, NEW_SECTION, SCHEDULING
    }
    
    public Section() {
    }

    public Section(int id, int year, String decription) {
        this.id = id;
        this.year = year;
        this.decription = decription;
    }
    
    public void copy(Section s) {
        this.id = s.id;
        this.year = s.year;
        this.decription = s.decription;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getDecription() {
        return decription;
    }

    public void setDecription(String decription) {
        this.decription = decription;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Section) {
            return ((Section)obj).id == id;
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + this.id;
        return hash;
    }

    @Override
    public String toString() {
        return decription + " - " + year;
    }
    
    
    
    private int id;
    private int year;
    private String decription;
}
