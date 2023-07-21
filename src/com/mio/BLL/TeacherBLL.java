
package com.mio.BLL;

import com.mio.DAL.TeacherAccess;
import com.mio.DAL.UserAccess;
import com.mio.DTO.SO;
import com.mio.DTO.Teacher;
import com.mio.DTO.User;
import java.util.ArrayList;
import java.util.List;

public class TeacherBLL {
    private static TeacherBLL instance;
    public static TeacherBLL getInstance() {
        if(instance == null)
            instance = new TeacherBLL();
        return instance;
    }    
    private TeacherBLL() {}
    
    public List<Teacher> getTeacherList(int department_id) {
        try {
            return TeacherAccess.getInstance().getTeacherListByDep(department_id);
        } catch(Exception e) {
            return new ArrayList<Teacher>();
        }
    }
    
    public List<SO> getSTeacherList(int department_id) {
        try {
            return TeacherAccess.getInstance().getSTeacherListByDep(department_id);
        } catch(Exception e) {
            return new ArrayList<SO>();
        }
    }
    
    public List<SO> getSTeacherALL() {
        try {
            return TeacherAccess.getInstance().getSTeacherList();
        } catch(Exception e) {
            return new ArrayList<SO>();
        }
    }
    
    public boolean addTeacher(User user, Teacher teacher) {
        try {
            UserAccess.getInstance().addUser(user);
            if(user.getId() == -1)
                return false;
            teacher.setId(user.getId());
            return TeacherAccess.getInstance().addTeacher(teacher);
        } catch(Exception e) {
            return false;
        }
    }
    public Teacher getTeacher(int id) {
        try {
            return TeacherAccess.getInstance().getTeacher(id);
        } catch(Exception e) {
            return null;
        }
    }
    
    public SO getSTeacher(int id) {
        try {
            return TeacherAccess.getInstance().getSTeacher(id);
        } catch(Exception e) {
            return null;
        }
    }
    
    public boolean updateTeacher(Teacher teacher) {
        try {
            return TeacherAccess.getInstance().updateTeacher(teacher);
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean delTeacher(int id) {
        try {
            return TeacherAccess.getInstance().delTeacher(id);
        } catch(Exception e) {
            return false;
        }
    }
    public String getTeacherName(int id) {
        try {
            return TeacherAccess.getInstance().getTeacherName(id);
        } catch(Exception e) {
            return "";
        }
    }
    public String getBusy(int id) {
        try {
            return TeacherAccess.getInstance().getBusy(id);
        } catch(Exception e) {
            return null;
        }
    }
    public boolean updateBusy(int id, String busy) {
        try {
            return TeacherAccess.getInstance().updateBusy(id, busy);
        } catch(Exception e) {
            return false;
        }
    }
}
