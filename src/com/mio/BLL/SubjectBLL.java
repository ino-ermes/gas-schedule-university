
package com.mio.BLL;

import com.mio.DAL.SubjectAccess;
import com.mio.DTO.SO;
import com.mio.DTO.Subject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SubjectBLL {
    private static SubjectBLL instance;
    public static SubjectBLL getInstance() {
        if(instance == null)
            instance = new SubjectBLL();
        return instance;
    }    
    private SubjectBLL() {}
    
    public List<Subject> getSubjectListByDepId(int department_id) {
        try {
            return SubjectAccess.getInstance().getSubjectListByDepId(department_id);
        } catch(Exception e) {
            return new ArrayList<>();
        }
    }
    
    public HashMap<Integer, Subject> getSubjectHashMap() {
        try {
            return SubjectAccess.getInstance().getSubjectHashMap();
        } catch(Exception e) {
            return null;
        }
    }
    
    public void addSubject(Subject s) {
        try {
            SubjectAccess.getInstance().addSubject(s);
        } catch(Exception e) {
            System.err.println(e);
        }
    }
    public Subject getSubject(int id) {
        try {
            return SubjectAccess.getInstance().getSubject(id);
        } catch(Exception e) {
            return null;
        }
    }
    public boolean updateSubject(Subject s) {
        try {
            return SubjectAccess.getInstance().updateSubject(s);
        } catch(Exception e) {
            return false;
        }
    }
    public boolean delSubject(int id) {
        try {
            return SubjectAccess.getInstance().delSubject(id);
        } catch(Exception e) {
            return false;
        }
    }
    public List<Subject> getSubjectList() {
        try {
            return SubjectAccess.getInstance().getSubjectList();
        } catch(Exception e) {
            return new ArrayList<>();
        }
    }
    public List<SO> getSSubjectList() {
        try {
            return SubjectAccess.getInstance().getSSubjectList();
        } catch(Exception e) {
            return new ArrayList<>();
        }
    }
    
    public SO getSSubject(int subjectId) {
        try {
            return SubjectAccess.getInstance().getSSubject(subjectId);
        } catch(Exception e) {
            return null;
        }
    }
}
