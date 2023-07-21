
package com.mio.DAL;

import com.mio.BLL.PasswordAuthentication;
import com.mio.DTO.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.IOException;

public class UserAccess {
    
    private static UserAccess instance;
    public static UserAccess getInstance() {
        if(instance == null)
            instance = new UserAccess();
        return instance;
    }    
    private UserAccess() {}
    
    public User checkLogin(String username, char[] password) throws SQLException, IOException {
        String query = "select id, token, role from user where username=?";
        PreparedStatement p = DatabaseConnection.getInstance().getConnection().prepareStatement(query);
        p.setString(1, username);
        ResultSet r = p.executeQuery();
        User user = null;
        if(r.next()) {
            if(new PasswordAuthentication().authenticate(password, r.getString(2))) {
                int role;
                user = new User(r.getInt(1), username, (role = r.getInt(3)) == 0 ? User.UserRole.ADMIN : role == 1 ? User.UserRole.TEACHER : User.UserRole.STUDENT);
            }
        }
        r.close();
        p.close();
        return user;
    }
    
    public boolean checkPassword(int userId, char[] password) throws SQLException, IOException {
        String query = "select token from user where id=?";
        PreparedStatement p = DatabaseConnection.getInstance().getConnection().prepareStatement(query);
        p.setInt(1, userId);
        ResultSet r = p.executeQuery();
        boolean rtn = false;
        if(r.next()) {
            String token = r.getString(1);
            rtn = new PasswordAuthentication().authenticate(password, token);
        }
        r.close();
        p.close();
        return rtn;
    }
    
    public boolean changePassword(int userId, char[] oldPassword, char[] newPassword) throws SQLException, IOException {
        boolean success = false;
        if(checkPassword(userId, oldPassword)) {
            String query = "update user set token=? where id=?";
            PreparedStatement p = DatabaseConnection.getInstance().getConnection().prepareStatement(query);
            p.setString(1, new PasswordAuthentication().hash(newPassword));
            p.setInt(2, userId);
            success = p.executeUpdate() == 1;
            p.close();
        }
        return success;
    }
    public void addUser(User user) throws SQLException, IOException {
        String query = "insert into user(username, token, role) values(?,?,?)";
        PreparedStatement p = DatabaseConnection.getInstance().getConnection().prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
        p.setString(1, user.getUsername());
        p.setString(2, new PasswordAuthentication().hash(user.getUsername().toCharArray()));
        p.setInt(3, user.getRole() == User.UserRole.ADMIN ? 0 : user.getRole() == User.UserRole.TEACHER ? 1 : 2);
        p.execute();
        ResultSet r = p.getGeneratedKeys();
        if(r.next()) {
            user.setId(r.getInt(1));
        }
        r.close();
        p.close();
    }
    public String getUsername(int id) throws SQLException, IOException {
        String query = "select username from user where id=?";
        PreparedStatement p = DatabaseConnection.getInstance().getConnection().prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
        p.setInt(1, id);
        ResultSet r = p.executeQuery();
        String rtn;
        rtn = "";
        if(r.next()) {
            rtn = r.getString(1);
        }
        return rtn;
    }
    public boolean updateUsername(int id, String username) throws SQLException, IOException {
        String query = "update user set username=? where id=?";
        PreparedStatement p = DatabaseConnection.getInstance().getConnection().prepareStatement(query);
        p.setString(1, username);
        p.setInt(2, id);
        boolean success = p.executeUpdate() == 1;
        p.close();
        return success;
    }
    
    public boolean resetPassword(int id, char[] password) throws SQLException, IOException {
        String query = "update user set token=? where id=?";
        PreparedStatement p = DatabaseConnection.getInstance().getConnection().prepareStatement(query);
        p.setString(1, new PasswordAuthentication().hash(password));
        p.setInt(2, id);
        boolean success = p.executeUpdate() == 1;
        p.close();
        return success;
    }
    
    public boolean deleteUser(int id) throws SQLException, IOException {
        String query = "delete from user where id=?";
        PreparedStatement p = DatabaseConnection.getInstance().getConnection().prepareStatement(query);
        p.setInt(1, id);
        boolean success = p.executeUpdate() == 1;
        p.close();
        return success;
    }
}
