
package com.mio.DTO;

public class Room {

    public Room(int id, String name, int size, boolean lab) {
        this.id = id;
        this.name = name;
        this.size = size;
        this.lab = lab;
    }
    
    public void copy(Room r) {
        this.id = r.id;
        this.name = r.name;
        this.size = r.size;
        this.lab = r.lab;
    }

    public Room() {
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
    private boolean lab;

    public boolean isLab() {
        return lab;
    }

    public void setLab(boolean lab) {
        this.lab = lab;
    }
}
