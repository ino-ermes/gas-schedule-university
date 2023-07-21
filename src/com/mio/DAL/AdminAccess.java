
package com.mio.DAL;

import com.mio.DTO.Admin;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminAccess {
    private static AdminAccess instance;
    public static AdminAccess getInstance() {
        if(instance == null)
            instance = new AdminAccess();
        return instance;
    }    
    private AdminAccess() {}
    
    public Admin getAdmin(int id) throws SQLException, IOException {
        String query = "select name, phonenumber, is_male, birthday, image_path from admin where id=?";
        PreparedStatement p = DatabaseConnection.getInstance().getConnection().prepareStatement(query);
        p.setInt(1, id);
        ResultSet r = p.executeQuery();
        Admin admin;
        admin = null;
        if(r.next()) {
            admin = new Admin(id, r.getString(1), r.getString(2), r.getBoolean(3), r.getDate(4).toString(), r.getString(5));
        }
        r.close();
        p.close();
        return admin;
    }
    public boolean updateAdmin(Admin admin) throws SQLException, IOException {
        String query = "update admin set name=?,phoneNumber=?,is_male=?,birthday=?,image_path=? where id=?";
        PreparedStatement p = DatabaseConnection.getInstance().getConnection().prepareStatement(query);
        p.setString(1, admin.getName());
        p.setString(2, admin.getPhoneNumber());
        p.setBoolean(3, admin.isMale());
        p.setString(4, admin.getBirthday());
        p.setString(5, admin.getImagePath());
        p.setInt(6, admin.getId());
        boolean success = p.executeUpdate() == 1;
        p.close();
        return success;
    }
}
