
package com.mio.DAL;

import com.mio.DTO.SO;
import com.mio.DTO.StudentGroup;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentGroupAccess {
    private static StudentGroupAccess instance;
    public static StudentGroupAccess getInstance() {
        if(instance == null)
            instance = new StudentGroupAccess();
        return instance;
    }    
    private StudentGroupAccess() {}
    
    public List<StudentGroup> getStudentGroupListByDepId(int depId) throws SQLException, IOException {
        String query = "select id, name, edu_program_id, size.c from student_group left join (select student_group_id, count(*) as c from student group by student_group_id) as size on student_group.id = size.student_group_id where department_id = ?";
        PreparedStatement p = DatabaseConnection.getInstance().getConnection().prepareStatement(query);
        p.setInt(1, depId);
        ResultSet r = p.executeQuery();
        List<StudentGroup> list = new ArrayList<>();
        while(r.next()) {
            list.add(new StudentGroup(r.getInt(1), r.getString(2), depId, r.getInt(3), r.getInt(4)));
        }
        p.close();
        r.close();
        return list;
    }
    
    public List<SO> getSStudentGroupListByDepId(int depId) throws SQLException, IOException {
        String query = "select id, name from student_group where department_id = ?";
        PreparedStatement p = DatabaseConnection.getInstance().getConnection().prepareStatement(query);
        p.setInt(1, depId);
        ResultSet r = p.executeQuery();
        List<SO> list = new ArrayList<>();
        while(r.next()) {
            list.add(new SO(r.getInt(1), r.getString(2)));
        }
        p.close();
        r.close();
        return list;
    }
    
    public List<StudentGroup> getStudentGroupListByEdu(int eduProgId) throws SQLException, IOException {
        String query = "select id, name, department_id, size.c from student_group left join (select student_group_id, count(*) as c from student group by student_group_id) as size on student_group.id = size.student_group_id where edu_program_id = ?";
        PreparedStatement p = DatabaseConnection.getInstance().getConnection().prepareStatement(query);
        p.setInt(1, eduProgId);
        ResultSet r = p.executeQuery();
        List<StudentGroup> list = new ArrayList<>();
        while(r.next()) {
            list.add(new StudentGroup(r.getInt(1), r.getString(2), r.getInt(3), eduProgId, r.getInt(4)));
        }
        p.close();
        r.close();
        return list;
    }
    
    public List<SO> getSStudentGroupListByEdu(int eduProgId) throws SQLException, IOException {
        String query = "select id, name from student_group where edu_program_id = ?";
        PreparedStatement p = DatabaseConnection.getInstance().getConnection().prepareStatement(query);
        p.setInt(1, eduProgId);
        ResultSet r = p.executeQuery();
        List<SO> list = new ArrayList<>();
        while(r.next()) {
            list.add(new SO(r.getInt(1), r.getString(2)));
        }
        p.close();
        r.close();
        return list;
    }
    
    public List<StudentGroup> getStudentGroupList() throws SQLException, IOException {
        String query = "select id, name, department_id, edu_program_id, size.c from student_group left join (select student_group_id, count(*) as c from student group by student_group_id) as size on student_group.id = size.student_group_id";
        PreparedStatement p = DatabaseConnection.getInstance().getConnection().prepareStatement(query);
        ResultSet r = p.executeQuery();
        List<StudentGroup> list = new ArrayList<>();
        while(r.next()) {
            list.add(new StudentGroup(r.getInt(1), r.getString(2), r.getInt(3), r.getInt(4), r.getInt(5)));
        }
        p.close();
        r.close();
        return list;
    }
    
    public List<SO> getSStudentGroupList() throws SQLException, IOException {
        String query = "select id, name from student_group";
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
    
    public void addStudentGroup(StudentGroup s) throws SQLException, IOException {
        String query = "insert into student_group(name, department_id, edu_program_id) values(?,?,?)";
        PreparedStatement p = DatabaseConnection.getInstance().getConnection().prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
        p.setString(1, s.getName());
        p.setInt(2, s.getDepartmentId());
        p.setInt(3, s.getEduProgramId());
        p.execute();
        ResultSet r = p.getGeneratedKeys();
        if(r.next()) {
            s.setId(r.getInt(1));
        }
        r.close();
        p.close();
    }
    
    public boolean updateStudentGroup(StudentGroup s) throws SQLException, IOException {
        String query = "update student_group set name=?,edu_program_id=? where id=?";
        PreparedStatement p = DatabaseConnection.getInstance().getConnection().prepareStatement(query);
        p.setString(1, s.getName());
        p.setInt(2, s.getEduProgramId());
        p.setInt(3, s.getId());
        int rtn = p.executeUpdate();
        p.close();
        return rtn == 1;
    }
    public boolean delStudentGroup(int id) throws SQLException, IOException {
        String query = "delete from student_group where id=?";
        PreparedStatement p = DatabaseConnection.getInstance().getConnection().prepareStatement(query);
        p.setInt(1, id);
        int rtn = p.executeUpdate();
        p.close();
        return rtn == 1;
    }
    public StudentGroup getStudentGroup(int id) throws SQLException, IOException {
        String query = "select gr.name, gr.department_id, gr.edu_program_id, size.c from (select id, name, department_id, edu_program_id from student_group where id=?) as gr left join (select student_group_id, count(*) as c from student group by student_group_id) as size on gr.id = size.student_group_id;";
        PreparedStatement p = DatabaseConnection.getInstance().getConnection().prepareStatement(query);
        p.setInt(1, id);
        ResultSet r = p.executeQuery();
        StudentGroup subject;
        subject = null;
        if(r.next()) {
            subject = new StudentGroup(id, r.getString(1), r.getInt(2), r.getInt(3), r.getInt(4));
        }
        r.close();
        p.close();
        return subject;
    }
    
    public SO getSStudentGroup(int id) throws SQLException, IOException {
        String query = "select name from student_group where id=?";
        PreparedStatement p = DatabaseConnection.getInstance().getConnection().prepareStatement(query);
        p.setInt(1, id);
        ResultSet r = p.executeQuery();
        SO group = null;
        if(r.next()) {
            group = new SO(id, r.getString(1));
        }
        r.close();
        p.close();
        return group;
    }
    
    public String getNameById(int id) throws SQLException, IOException {
        String query = "select name from student_group where id=?";
        PreparedStatement p = DatabaseConnection.getInstance().getConnection().prepareStatement(query);
        p.setInt(1, id);
        ResultSet r = p.executeQuery();
        String rtn = "";
        if(r.next()) {
            rtn = r.getString(1);
        }
        r.close();
        p.close();
        return rtn;
    }
}
