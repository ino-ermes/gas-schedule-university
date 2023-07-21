
package com.mio.BLL;

import com.mio.DAL.StudentAccess;
import com.mio.DAL.UserAccess;
import com.mio.DTO.Student;
import com.mio.DTO.User;
import java.util.ArrayList;
import java.util.List;

public class StudentBLL {
    private static StudentBLL instance;
    public static StudentBLL getInstance() {
        if(instance == null)
            instance = new StudentBLL();
        return instance;
    }    
    private StudentBLL() {}
    
    public List<Student> getStudentListByGrId(int group_id) {
        try {
            return StudentAccess.getInstance().getStudentListByGrId(group_id);
        } catch(Exception e) {
            return new ArrayList<>();
        }
    }
    public boolean addStudent(User user, Student s) {
        try {
            UserAccess.getInstance().addUser(user);
            if(user.getId() == -1)
                return false;
            s.setId(user.getId());
            return StudentAccess.getInstance().addStudent(s);
        } catch(Exception e) {
            return false;
        }
    }
    public Student getStudent(int id) {
        try {
            return StudentAccess.getInstance().getStudent(id);
        } catch(Exception e) {
            return null;
        }
    }
    public boolean updateStudent(Student s) {
        try {
            return StudentAccess.getInstance().updateStudent(s);
        } catch(Exception e) {
            return false;
        }
    }
    public boolean delStudent(int id) {
        try {
            return StudentAccess.getInstance().delStudent(id);
        } catch(Exception e) {
            return false;
        }
    }
    public List<Student> getStudentList() {
        try {
            return StudentAccess.getInstance().getStudentList();
        } catch(Exception e) {
            return new ArrayList<>();
        }
    }
    public int getStudentGroupIdByStudent(int studentId)  {
        try {
            return StudentAccess.getInstance().getStudentGroupIdByStudent(studentId);
        } catch(Exception e) {
            return -1;
        }
    }
}
