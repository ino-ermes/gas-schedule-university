
package com.mio.BLL;

import com.mio.DAL.AdminAccess;
import com.mio.DTO.Admin;

public class AdminBLL {
    private static AdminBLL instance;
    public static AdminBLL getInstance() {
        if(instance == null)
            instance = new AdminBLL();
        return instance;
    }    
    private AdminBLL() {}
    public Admin getAdmin(int id) {
        try {
            return AdminAccess.getInstance().getAdmin(id);
        } catch(Exception e) {
            return null;
        }
    }
    
    public boolean updateAdmin(Admin admin) {
        try {
            return AdminAccess.getInstance().updateAdmin(admin);
        } catch(Exception e) {
            return false;
        }
    }
}
