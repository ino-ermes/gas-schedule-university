

package com.mio.DTO;

public class User {

    public static enum UserRole {
        ADMIN, TEACHER, STUDENT
    }
    
    public User(int id, String username, UserRole role) {
        this.id = id;
        this.username = username;
        this.role = role;
    }

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }
    
    private int id;
    private String username;
    private UserRole role;
    
}
