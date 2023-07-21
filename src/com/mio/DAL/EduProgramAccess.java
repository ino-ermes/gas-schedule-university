/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mio.DAL;

import com.mio.DTO.EduProgram;
import com.mio.DTO.EduProgramDetail;
import com.mio.DTO.SO;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Mio
 */
public class EduProgramAccess {
    private static EduProgramAccess instance;
    public static EduProgramAccess getInstance() {
        if(instance == null)
            instance = new EduProgramAccess();
        return instance;
    }    
    private EduProgramAccess() {}
    
    public List<EduProgram> getEduProgramByDepId(int depId) throws SQLException, IOException {
        String query = "select id, name, semester_count, semester_current from edu_program where department_id=?";
        PreparedStatement p = DatabaseConnection.getInstance().getConnection().prepareStatement(query);
        p.setInt(1, depId);
        ResultSet r = p.executeQuery();
        List<EduProgram> list = new ArrayList<>();
        while(r.next()) {
            list.add(new EduProgram(r.getInt(1), r.getString(2), depId, r.getInt(3), r.getInt(4)));
        }
        p.close();
        r.close();
        return list;
    }
    
    public boolean nextSemester(int eduId) throws SQLException, IOException {
        String query = "update edu_program set semester_current=semester_current+1 where id=? and semester_current<semester_count";
        PreparedStatement p = DatabaseConnection.getInstance().getConnection().prepareStatement(query);
        p.setInt(1, eduId);
        int rtn = p.executeUpdate();
        p.close();
        return rtn == 1;
    }
    
    public List<SO> getSEduProgramByDepId(int depId) throws SQLException, IOException {
        String query = "select id, name from edu_program where department_id=?";
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
    
    public List<EduProgram> getEduProgramActive() throws SQLException, IOException {
        String query = "select id, name, department_id, semester_count, semester_current from edu_program where semester_current < semester_count";
        PreparedStatement p = DatabaseConnection.getInstance().getConnection().prepareStatement(query);
        ResultSet r = p.executeQuery();
        List<EduProgram> list = new ArrayList<>();
        while(r.next()) {
            list.add(new EduProgram(r.getInt(1), r.getString(2), r.getInt(3), r.getInt(4), r.getInt(5)));
        }
        p.close();
        r.close();
        return list;
    }
    
    public List<SO> getSEduProgramActive() throws SQLException, IOException {
        String query = "select id, name, department_id, semester_count, semester_current from edu_program where semester_current < semester_count";
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
    
    public void addEduProgram(EduProgram s) throws SQLException, IOException {
        String query = "insert into edu_program(name, department_id, semester_count) values(?,?,?)";
        PreparedStatement p = DatabaseConnection.getInstance().getConnection().prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
        p.setString(1, s.getName());
        p.setInt(2, s.getDepartmentId());
        p.setInt(3, s.getSemesterCount());
        p.execute();
        ResultSet r = p.getGeneratedKeys();
        if(r.next()) {
            s.setId(r.getInt(1));
        }
        r.close();
        p.close();
    }
    
    public boolean updateEduProgram(EduProgram s) throws SQLException, IOException {
        String query = "update edu_program set name=?,semester_count=?,semester_current=? where id=? and semester_count>=semester_current";
        PreparedStatement p = DatabaseConnection.getInstance().getConnection().prepareStatement(query);
        p.setString(1, s.getName());
        p.setInt(2, s.getSemesterCount());
        p.setInt(3, s.getSemesterCurrent());
        p.setInt(4, s.getId());
        int rtn = p.executeUpdate();
        p.close();
        return rtn == 1;
    }
    public boolean delEduProgram(int id) throws SQLException, IOException {
        String query = "delete from edu_program where id=?";
        PreparedStatement p = DatabaseConnection.getInstance().getConnection().prepareStatement(query);
        p.setInt(1, id);
        int rtn = p.executeUpdate();
        p.close();
        return rtn == 1;
    }
    public EduProgram getEduProgram(int id) throws SQLException, IOException {
        String query = "select name, department_id, semester_count, semester_current from edu_program where id=?";
        PreparedStatement p = DatabaseConnection.getInstance().getConnection().prepareStatement(query);
        p.setInt(1, id);
        ResultSet r = p.executeQuery();
        EduProgram eduProgram;
        eduProgram = null;
        if(r.next()) {
            eduProgram = new EduProgram(id, r.getString(1), r.getInt(2), r.getInt(3), r.getInt(4));
        }
        r.close();
        p.close();
        return eduProgram;
    }
    
    public List<EduProgramDetail> getEduProgDetail(int eduProgId) throws SQLException, IOException {
        String query = "select id, subject_id, semester from edu_program_detail where edu_program_id=? order by semester";
        PreparedStatement p = DatabaseConnection.getInstance().getConnection().prepareStatement(query);
        p.setInt(1, eduProgId);
        ResultSet r = p.executeQuery();
        List<EduProgramDetail> list = new ArrayList<>();
        while(r.next()) {
            list.add(new EduProgramDetail(r.getInt(1), eduProgId, r.getInt(2), r.getInt(3)));
        }
        p.close();
        r.close();
        return list;
    }
    
    public List<EduProgramDetail> getEduProgDetailBySemester(int eduProgId, int semester) throws SQLException, IOException {
        String query = "select id, subject_id from edu_program_detail where edu_program_id=? and semester=?";
        PreparedStatement p = DatabaseConnection.getInstance().getConnection().prepareStatement(query);
        p.setInt(1, eduProgId);
        p.setInt(2, semester);
        ResultSet r = p.executeQuery();
        List<EduProgramDetail> list = new ArrayList<>();
        while(r.next()) {
            list.add(new EduProgramDetail(r.getInt(1), eduProgId, r.getInt(2), semester));
        }
        p.close();
        r.close();
        return list;
    }
    
    public void addEduProgDetail(EduProgramDetail e) throws SQLException, IOException {
        String query = "insert into edu_program_detail(edu_program_id, subject_id, semester) values(?,?,?)";
        PreparedStatement p = DatabaseConnection.getInstance().getConnection().prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
        p.setInt(1, e.getEduProgramId());
        p.setInt(2, e.getSubjectId());
        p.setInt(3, e.getSemester());
        p.execute();
        ResultSet r = p.getGeneratedKeys();
        if(r.next()) {
            e.setId(r.getInt(1));
        }
        r.close();
        p.close();
    }
    
    public boolean updateEduProgDetail(EduProgramDetail e) throws SQLException, IOException {
        String query = "update edu_program_detail set subject_id=?,semester=? where id=?";
        PreparedStatement p = DatabaseConnection.getInstance().getConnection().prepareStatement(query);
        p.setInt(1, e.getSubjectId());
        p.setInt(2, e.getSemester());
        p.setInt(3, e.getId());
        int rtn = p.executeUpdate();
        p.close();
        return rtn == 1;
    }
    
    public boolean delEduProgDetail(int id) throws SQLException, IOException {
        String query = "delete from edu_program_detail where id=?";
        PreparedStatement p = DatabaseConnection.getInstance().getConnection().prepareStatement(query);
        p.setInt(1, id);
        int rtn = p.executeUpdate();
        p.close();
        return rtn == 1;
    }
    
    public HashMap<Integer, EduProgram> getEduProgList() throws SQLException, IOException {
        String query = "select id, name, department_id, semester_count, semester_current from edu_program";
        PreparedStatement p = DatabaseConnection.getInstance().getConnection().prepareStatement(query);
        ResultSet r = p.executeQuery();
        HashMap<Integer, EduProgram> list = new HashMap<>();
        while(r.next()) {
            EduProgram eduProg = new EduProgram(r.getInt(1), r.getString(2), r.getInt(3), r.getInt(4), r.getInt(5));
            list.put(eduProg.getId(), eduProg);
        }
        p.close();
        r.close();
        return list;
    }
    
    public int getMaxSemester(int eduProgId) throws SQLException, IOException {
        String query = "select max(semester) from edu_program_detail where edu_program_id=?";
        PreparedStatement p = DatabaseConnection.getInstance().getConnection().prepareStatement(query);
        p.setInt(1, eduProgId);
        ResultSet r = p.executeQuery();
        int rtn = 0;
        if(r.next()) {
            rtn = r.getInt(1);
        }
        p.close();
        r.close();
        return rtn;
    }
}
