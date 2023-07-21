
package com.mio.DAL;

import com.mio.DTO.Student;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentAccess {
    private static StudentAccess instance;
    public static StudentAccess getInstance() {
        if(instance == null)
            instance = new StudentAccess();
        return instance;
    }    
    private StudentAccess() {}
    
    public List<Student> getStudentListByGrId(int grId) throws SQLException, IOException {
        String query = "select id, name, phonenumber, is_male, birthday, image_path from student where student_group_id=?";
        PreparedStatement p = DatabaseConnection.getInstance().getConnection().prepareStatement(query);
        p.setInt(1, grId);
        ResultSet r = p.executeQuery();
        List<Student> list = new ArrayList<>();
        while(r.next()) {
            list.add(new Student(r.getInt(1), r.getString(2), r.getString(3), r.getBoolean(4), r.getDate(5).toString(), r.getString(6), grId));
        }
        p.close();
        r.close();
        return list;
    }
    
 
    
    public List<Student> getStudentList() throws SQLException, IOException {
        String query = "select * from student";
        PreparedStatement p = DatabaseConnection.getInstance().getConnection().prepareStatement(query);
        ResultSet r = p.executeQuery();
        List<Student> list = new ArrayList<>();
        while(r.next()) {
            list.add(new Student(r.getInt(1), r.getString(2), r.getString(3), r.getBoolean(4), r.getDate(5).toString(), r.getString(6), r.getInt(7)));
        }
        p.close();
        r.close();
        return list;
    }
    
    public boolean addStudent(Student s) throws SQLException, IOException {
        String query = "insert into student(id, name, phonenumber, is_male, birthday, image_path, student_group_id) values(?,?,?,?,?,?,?)";
        PreparedStatement p = DatabaseConnection.getInstance().getConnection().prepareStatement(query);
        p.setInt(1, s.getId());
        p.setString(2, s.getName());
        p.setString(3, s.getPhoneNumber());
        p.setBoolean(4, s.isMale());
        p.setString(5, s.getBirthday());
        p.setString(6, s.getImagePath());
        p.setInt(7, s.getStudentGroupId());
        int rtn = p.executeUpdate();
        p.close();
        return rtn == 1;
    }
    
    public boolean updateStudent(Student s) throws SQLException, IOException {
        String query = "update student set name=?, phonenumber=?, is_male=?, birthday=?, image_path=?, student_group_id=? where id=?";
        PreparedStatement p = DatabaseConnection.getInstance().getConnection().prepareStatement(query);
        p.setString(1, s.getName());
        p.setString(2, s.getPhoneNumber());
        p.setBoolean(3, s.isMale());
        p.setString(4, s.getBirthday());
        p.setString(5, s.getImagePath());
        p.setInt(6, s.getStudentGroupId());
        p.setInt(7, s.getId());
        int rtn = p.executeUpdate();
        p.close();
        return rtn == 1;
    }
    public boolean delStudent(int id) throws SQLException, IOException {
        String query = "delete from student where id=?";
        PreparedStatement p = DatabaseConnection.getInstance().getConnection().prepareStatement(query);
        p.setInt(1, id);
        int rtn = p.executeUpdate();
        p.close();
        return rtn == 1;
    }
    public Student getStudent(int id) throws SQLException, IOException {
        String query = "select * from student where id=?";
        PreparedStatement p = DatabaseConnection.getInstance().getConnection().prepareStatement(query);
        p.setInt(1, id);
        ResultSet r = p.executeQuery();
        Student Student;
        Student = null;
        if(r.next()) {
            Student = new Student(r.getInt(1), r.getString(2), r.getString(3), r.getBoolean(4), r.getDate(5).toString(), r.getString(6), r.getInt(7));
        }
        r.close();
        p.close();
        return Student;
    }
    public int getStudentGroupIdByStudent(int studentId) throws SQLException, IOException {
        String query = "select student_group_id from student where id=?";
        PreparedStatement p = DatabaseConnection.getInstance().getConnection().prepareStatement(query);
        p.setInt(1, studentId);
        ResultSet r = p.executeQuery();
        int rtn = -1;
        if(r.next()) {
            rtn = r.getInt(1);
        }
        r.close();
        p.close();
        return rtn;
    }
}
