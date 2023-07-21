
package com.mio.BLL;

import com.mio.DAL.ClassAccess;
import com.mio.DTO.CourseClass;
import java.util.ArrayList;
import java.util.List;

public class ClassBLL {
    private static ClassBLL instance;
    public static ClassBLL getInstance() {
        if(instance == null)
            instance = new ClassBLL();
        return instance;
    }    
    private ClassBLL() {}
    
    public List<CourseClass> getClassByRoom(int roomId, int sectionId) {
        try {
            return ClassAccess.getInstance().getClassByRoom(roomId, sectionId);
        } catch(Exception e) {
            return new ArrayList<>();
        }
    }
    public List<CourseClass> getClassByTeacher(int teacherId, int sectionId) {
        try {
            return ClassAccess.getInstance().getClassByTeacher(teacherId, sectionId);
        } catch(Exception e) {
            return new ArrayList<>();
        }
    }
    
    public List<CourseClass> getClassByStudentGroup(int groupId, int sectionId) {
        try {
            return ClassAccess.getInstance().getClassByStudentGroup(groupId, sectionId);
        } catch(Exception e) {
            return new ArrayList<>();
        }
    }
    
    public List<CourseClass> getClassBySection(int sectionId) {
        try {
            return ClassAccess.getInstance().getClassBySection(sectionId);
        } catch(Exception e) {
            return new ArrayList<>();
        }
    }
    public void addClassWithoutSchedule(CourseClass cc) {
        try {
            ClassAccess.getInstance().addClassWithoutSchedule(cc);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    public void addClass(CourseClass cc) {
        try {
            ClassAccess.getInstance().addClass(cc);
        } catch(Exception e) {

        }
    }
    public void updateClass(CourseClass cc) {
        try {
            ClassAccess.getInstance().updateClass(cc);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    public void updateClassWithoutSchedule(CourseClass cc) {
        try {
            ClassAccess.getInstance().updateClassWithoutSchedule(cc);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    public boolean delClass(int id) {
        try {
            return ClassAccess.getInstance().delClass(id);
        } catch(Exception e) {
            return false;
        }
    }
    public String getBusy(int id) {
        try {
            return ClassAccess.getInstance().getBusy(id);
        } catch(Exception e) {
            return null;
        }
    }
    public boolean updateBusy(int id, String busy) {
        try {
            return ClassAccess.getInstance().updateBusy(id, busy);
        } catch(Exception e) {
            return false;
        }
    }
}
