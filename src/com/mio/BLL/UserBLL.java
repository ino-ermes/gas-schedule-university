
package com.mio.BLL;
import com.mio.DAL.UserAccess;
import com.mio.DTO.User;

public class UserBLL {
    private static UserBLL instance;
    public static UserBLL getInstance() {
        if(instance == null)
            instance = new UserBLL();
        return instance;
    }    
    private UserBLL() {}
    public User checkLogin(String username, char[] password) {
        try {
            return UserAccess.getInstance().checkLogin(username, password);
        } catch(Exception e) {
            return null;
        }
    }

    public boolean changePassword(int userId, char[] oldPassword, char[] newPassword) {
        try  {
            return UserAccess.getInstance().changePassword(userId, oldPassword, newPassword);
        } catch(Exception e) {
            return false;
        }  
    }
    public String getUsername(int id) {
        try {
            return UserAccess.getInstance().getUsername(id);
        } catch(Exception e) {
            return "";
        }
    }
    public boolean resetPassword(int id, char[] newPassword) {
        try {
            return UserAccess.getInstance().resetPassword(id, newPassword);
        } catch(Exception e) {
            return false;
        }
    }
    public boolean deleteUser(int id) {
        try {
            return UserAccess.getInstance().deleteUser(id);
        } catch(Exception e) {
            return false;
        }
    }
}
