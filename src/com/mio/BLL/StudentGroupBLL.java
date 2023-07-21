
package com.mio.BLL;

import com.mio.DAL.StudentGroupAccess;
import com.mio.DTO.SO;
import com.mio.DTO.StudentGroup;
import java.util.ArrayList;
import java.util.List;

public class StudentGroupBLL {
    private static StudentGroupBLL instance;
    public static StudentGroupBLL getInstance() {
        if(instance == null)
            instance = new StudentGroupBLL();
        return instance;
    }    
    private StudentGroupBLL() {}
    
    public List<StudentGroup> getStudentGroupListByDepId(int department_id) {
        try {
            return StudentGroupAccess.getInstance().getStudentGroupListByDepId(department_id);
        } catch(Exception e) {
            return new ArrayList<>();
        }
    }
    
    public List<SO> getSStudentGroupListByDepId(int department_id) {
        try {
            return StudentGroupAccess.getInstance().getSStudentGroupListByDepId(department_id);
        } catch(Exception e) {
            return new ArrayList<>();
        }
    }
    
    public List<StudentGroup> getStudentGroupListByEdu(int eduProgId) {
        try {
            return StudentGroupAccess.getInstance().getStudentGroupListByEdu(eduProgId);
        } catch(Exception e) {
            return new ArrayList<>();
        }
    }
    
    public List<SO> getSStudentGroupListByEdu(int eduProgId) {
        try {
            return StudentGroupAccess.getInstance().getSStudentGroupListByEdu(eduProgId);
        } catch(Exception e) {
            return new ArrayList<>();
        }
    }
    
    public void addStudentGroup(StudentGroup s) {
        try {
            StudentGroupAccess.getInstance().addStudentGroup(s);
        } catch(Exception e) {
            System.err.println(e);
        }
    }
    public StudentGroup getStudentGroup(int id) {
        try {
            return StudentGroupAccess.getInstance().getStudentGroup(id);
        } catch(Exception e) {
            return null;
        }
    }
    public boolean updateStudentGroup(StudentGroup s) {
        try {
            return StudentGroupAccess.getInstance().updateStudentGroup(s);
        } catch(Exception e) {
            return false;
        }
    }
    public boolean delStudentGroup(int id) {
        try {
            return StudentGroupAccess.getInstance().delStudentGroup(id);
        } catch(Exception e) {
            return false;
        }
    }
    public List<StudentGroup> getStudentGroupList() {
        try {
            return StudentGroupAccess.getInstance().getStudentGroupList();
        } catch(Exception e) {
            return new ArrayList<>();
        }
    }
    
    public List<SO> getSStudentGroupList() {
        try {
            return StudentGroupAccess.getInstance().getSStudentGroupList();
        } catch(Exception e) {
            return new ArrayList<>();
        }
    }
    public String getNameById(int id) {
        try {
            return StudentGroupAccess.getInstance().getNameById(id);
        } catch(Exception e) {
            return "";
        }
    }
}
