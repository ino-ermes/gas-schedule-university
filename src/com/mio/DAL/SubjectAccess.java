
package com.mio.DAL;

import com.mio.DTO.SO;
import com.mio.DTO.Subject;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SubjectAccess {
    private static SubjectAccess instance;
    public static SubjectAccess getInstance() {
        if(instance == null)
            instance = new SubjectAccess();
        return instance;
    }    
    private SubjectAccess() {}
    
    public List<Subject> getSubjectListByDepId(int depId) throws SQLException, IOException {
        String query = "select id, name, require_lab, credit from subject where department_id=?";
        PreparedStatement p = DatabaseConnection.getInstance().getConnection().prepareStatement(query);
        p.setInt(1, depId);
        ResultSet r = p.executeQuery();
        List<Subject> list = new ArrayList<>();
        while(r.next()) {
            list.add(new Subject(r.getInt(1), r.getString(2), depId, r.getBoolean(3), r.getInt(4)));
        }
        p.close();
        r.close();
        return list;
    }

    public List<Subject> getSubjectList() throws SQLException, IOException {
        String query = "select id, name, department_id, require_lab, credit from subject";
        PreparedStatement p = DatabaseConnection.getInstance().getConnection().prepareStatement(query);
        ResultSet r = p.executeQuery();
        List<Subject> list = new ArrayList<>();
        while(r.next()) {
            list.add(new Subject(r.getInt(1), r.getString(2), r.getInt(3), r.getBoolean(4), r.getInt(5)));
        }
        p.close();
        r.close();
        return list;
    }
    
    public List<SO> getSSubjectList() throws SQLException, IOException {
        String query = "select id, name from subject";
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
    
    public void addSubject(Subject s) throws SQLException, IOException {
        String query = "insert into subject(name, department_id, require_lab, credit) values(?,?,?,?)";
        PreparedStatement p = DatabaseConnection.getInstance().getConnection().prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
        p.setString(1, s.getName());
        p.setInt(2, s.getDepartmentId());
        p.setBoolean(3, s.isRequireLab());
        p.setInt(4, s.getCredit());
        p.execute();
        ResultSet r = p.getGeneratedKeys();
        if(r.next()) {
            s.setId(r.getInt(1));
        }
        r.close();
        p.close();
    }
    
    public HashMap<Integer, Subject> getSubjectHashMap() throws SQLException, IOException {
        String query = "select id, name, department_id, require_lab, credit from subject";
        PreparedStatement p = DatabaseConnection.getInstance().getConnection().prepareStatement(query);
        ResultSet r = p.executeQuery();
        HashMap<Integer, Subject> list = new HashMap<>();
        while(r.next()) {
            Subject s = new Subject(r.getInt(1), r.getString(2), r.getInt(3), r.getBoolean(4), r.getInt(5));
            list.put(s.getId(), s);
        }
        p.close();
        r.close();
        return list;
    }
    
    public boolean updateSubject(Subject s) throws SQLException, IOException {
        String query = "update subject set name=?,require_lab=?,credit=? where id=?";
        PreparedStatement p = DatabaseConnection.getInstance().getConnection().prepareStatement(query);
        p.setString(1, s.getName());
        p.setBoolean(2, s.isRequireLab());
        p.setInt(3, s.getCredit());
        p.setInt(4, s.getId());
        int rtn = p.executeUpdate();
        p.close();
        return rtn == 1;
    }
    public boolean delSubject(int id) throws SQLException, IOException {
        String query = "delete from subject where id=?";
        PreparedStatement p = DatabaseConnection.getInstance().getConnection().prepareStatement(query);
        p.setInt(1, id);
        int rtn = p.executeUpdate();
        p.close();
        return rtn == 1;
    }
    public Subject getSubject(int id) throws SQLException, IOException {
        String query = "select name, department_id, require_lab, credit from subject where id=?";
        PreparedStatement p = DatabaseConnection.getInstance().getConnection().prepareStatement(query);
        p.setInt(1, id);
        ResultSet r = p.executeQuery();
        Subject subject;
        subject = null;
        if(r.next()) {
            subject = new Subject(id, r.getString(1), r.getInt(2), r.getBoolean(3), r.getInt(4));
        }
        r.close();
        p.close();
        return subject;
    }
    public SO getSSubject(int id) throws SQLException, IOException {
        String query = "select name from subject where id=?";
        PreparedStatement p = DatabaseConnection.getInstance().getConnection().prepareStatement(query);
        p.setInt(1, id);
        ResultSet r = p.executeQuery();
        SO subject;
        subject = null;
        if(r.next()) {
            subject = new SO(id, r.getString(1));
        }
        r.close();
        p.close();
        return subject;
    }
}
