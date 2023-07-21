
package com.mio.DAL;

import com.mio.DTO.SO;
import com.mio.DTO.Teacher;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TeacherAccess {
    private static TeacherAccess instance;
    public static TeacherAccess getInstance() {
        if(instance == null)
            instance = new TeacherAccess();
        return instance;
    }    
    private TeacherAccess() {}
    public List<Teacher> getTeacherListByDep(int departmentId) throws SQLException, IOException {
        String query = "select id, name, phonenumber, is_male, birthday, image_path from teacher where department_id=?";
        PreparedStatement p = DatabaseConnection.getInstance().getConnection().prepareStatement(query);
        p.setInt(1, departmentId);
        ResultSet r = p.executeQuery();
        List<Teacher> list = new ArrayList<>();
        while(r.next()) {
            list.add(new Teacher(r.getInt(1), r.getString(2), r.getString(3), r.getBoolean(4), r.getDate(5).toString(), r.getString(6), departmentId));
        }
        p.close();
        r.close();
        return list;
    }
    
    public List<SO> getSTeacherListByDep(int departmentId) throws SQLException, IOException {
        String query = "select id, name from teacher where department_id=?";
        PreparedStatement p = DatabaseConnection.getInstance().getConnection().prepareStatement(query);
        p.setInt(1, departmentId);
        ResultSet r = p.executeQuery();
        List<SO> list = new ArrayList<>();
        while(r.next()) {
            list.add(new SO(r.getInt(1), r.getString(2)));
        }
        p.close();
        r.close();
        return list;
    }
    
    public List<SO> getSTeacherList() throws SQLException, IOException {
        String query = "select id, name from teacher";
        PreparedStatement p = DatabaseConnection.getInstance().getConnection().prepareStatement(query);
        ResultSet r = p.executeQuery();
        List<SO> list = new ArrayList<>();
        while(r.next()) {
            list.add(new SO(r.getInt(1), r.getString(2)));
        }
        p.close();
        r.close();
        return list;
    }
    
    public boolean addTeacher(Teacher teacher) throws SQLException, IOException {
        String query = "insert into teacher(id, name, phonenumber, is_male, birthday, image_path, department_id) values(?,?,?,?,?,?,?)";
        PreparedStatement p = DatabaseConnection.getInstance().getConnection().prepareStatement(query);
        p.setInt(1, teacher.getId());
        p.setString(2, teacher.getName());
        p.setString(3, teacher.getPhoneNumber());
        p.setBoolean(4, teacher.isMale());
        p.setString(5, teacher.getBirthday());
        p.setString(6, teacher.getImagePath());
        p.setInt(7, teacher.getDepartmentId());
        int rtn = p.executeUpdate();
        p.close();
        return rtn == 1;
    }
    public Teacher getTeacher(int id) throws SQLException, IOException {
        String query = "select name, phonenumber, is_male, birthday, image_path, department_id from teacher where id=?";
        PreparedStatement p = DatabaseConnection.getInstance().getConnection().prepareStatement(query);
        p.setInt(1, id);
        ResultSet r = p.executeQuery();
        Teacher teacher;
        teacher = null;
        if(r.next()) {
            teacher = new Teacher(id, r.getString(1), r.getString(2), r.getBoolean(3), r.getDate(4).toString(), r.getString(5), r.getInt(6));
        }
        r.close();
        p.close();
        return teacher;
    }
    public SO getSTeacher(int id) throws SQLException, IOException {
        String query = "select name from teacher where id=?";
        PreparedStatement p = DatabaseConnection.getInstance().getConnection().prepareStatement(query);
        p.setInt(1, id);
        ResultSet r = p.executeQuery();
        SO teacher;
        teacher = null;
        if(r.next()) {
            teacher = new SO(id, r.getString(1));
        }
        r.close();
        p.close();
        return teacher;
    }
    public boolean updateTeacher(Teacher teacher) throws SQLException, IOException {
        String query = "update teacher set name=?,phoneNumber=?,is_male=?,birthday=?,image_path=? where id=?";
        PreparedStatement p = DatabaseConnection.getInstance().getConnection().prepareStatement(query);
        p.setString(1, teacher.getName());
        p.setString(2, teacher.getPhoneNumber());
        p.setBoolean(3, teacher.isMale());
        p.setString(4, teacher.getBirthday());
        p.setString(5, teacher.getImagePath());
        p.setInt(6, teacher.getId());
        int rtn = p.executeUpdate();
        p.close();
        return rtn == 1;
    }
    public boolean delTeacher(int id) throws SQLException, IOException {
        String query = "delete from teacher where id=?";
        PreparedStatement p = DatabaseConnection.getInstance().getConnection().prepareStatement(query);
        p.setInt(1, id);
        int rtn = p.executeUpdate();
        p.close();
        return rtn == 1;
    }
    public String getTeacherName(int id) throws SQLException, IOException {
        String query = "select name from teacher where id=?";
        PreparedStatement p = DatabaseConnection.getInstance().getConnection().prepareStatement(query);
        p.setInt(1, id);
        ResultSet r = p.executeQuery();
        String rtn = "";
        if(r.next()) {
            rtn = r.getString(1);
        }
        p.close();
        r.close();
        return rtn;
    }
    public String getBusy(int id) throws SQLException, IOException {
        String query = "select busy from teacher where id=?";
        PreparedStatement p = DatabaseConnection.getInstance().getConnection().prepareStatement(query);
        p.setInt(1, id);
        ResultSet r = p.executeQuery();
        String busy = null;
        if(r.next()) {
            busy = r.getString(1);
        }
        r.close();
        p.close();
        return busy;
    }
    public boolean updateBusy(int id, String busy) throws SQLException, IOException {
        String query = "update teacher set busy=? where id=?";
        PreparedStatement p = DatabaseConnection.getInstance().getConnection().prepareStatement(query);
        p.setString(1, busy);
        p.setInt(2, id);
        int rtn = p.executeUpdate();
        p.close();
        return rtn == 1;
    }
}
