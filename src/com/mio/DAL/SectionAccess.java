
package com.mio.DAL;

import com.mio.DTO.SO;
import com.mio.DTO.Section;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SectionAccess {
    private static SectionAccess instance;
    public static SectionAccess getInstance() {
        if(instance == null)
            instance = new SectionAccess();
        return instance;
    }    
    private SectionAccess() {}
    
    public List<Section> getSectionList() throws SQLException, IOException {
        String query = "select * from section";
        PreparedStatement p = DatabaseConnection.getInstance().getConnection().prepareStatement(query);
        ResultSet r = p.executeQuery();
        List<Section> list = new ArrayList<>();
        while(r.next()) {
            list.add(new Section(r.getInt(1), r.getInt(2), r.getString(3)));
        }
        r.close();
        p.close();
        return list;
    }
    
    public Section getCurSection() throws SQLException, IOException {
        String query = "select section_id from cur_section";
        PreparedStatement p = DatabaseConnection.getInstance().getConnection().prepareStatement(query);
        ResultSet r = p.executeQuery();
        r.next();
        int sectionId = r.getInt(1);
        r.close();
        p.close();
        return getSectionById(sectionId);
    }
    
    public Section getSectionById(int id) throws SQLException, IOException {
        String query = "select * from section where id=?";
        PreparedStatement p = DatabaseConnection.getInstance().getConnection().prepareStatement(query);
        p.setInt(1, id);
        ResultSet r = p.executeQuery();
        Section s = null;
        if(r.next()) {
            s = new Section(r.getInt(1), r.getInt(2), r.getString(3));
        } 
        r.close();
        p.close();
        return s;
    }
    
    public int getCurSectionStatus() throws SQLException, IOException {
        String query = "select section_status from cur_section";
        PreparedStatement p = DatabaseConnection.getInstance().getConnection().prepareStatement(query);
        ResultSet r = p.executeQuery();
        r.next();
        int status = r.getInt(1);
        r.close();
        p.close();
        return status;
    }
    
    public boolean setCurSectionStatus(int status) throws SQLException, IOException {
        String query = "update cur_section set section_status=?";
        PreparedStatement p = DatabaseConnection.getInstance().getConnection().prepareStatement(query);
        p.setInt(1, status);
        int rtn = p.executeUpdate();
        p.close();
        return rtn == 1;
    }
    
    public boolean setCurSection(int sectionId) throws SQLException, IOException {
        String query = "update cur_section set section_id=?";
        PreparedStatement p = DatabaseConnection.getInstance().getConnection().prepareStatement(query);
        p.setInt(1, sectionId);
        int rtn = p.executeUpdate();
        p.close();
        return rtn == 1;
    }
    
    public void addSection(Section s) throws SQLException, IOException {
        String query = "insert into section(year, decription) values(?,?)";
        PreparedStatement p = DatabaseConnection.getInstance().getConnection().prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
        p.setInt(1, s.getYear());
        p.setString(2, s.getDecription());
        p.execute();
        ResultSet r = p.getGeneratedKeys();
        if(r.next()) {
            s.setId(r.getInt(1));
        }
        r.close();
        p.close();
    }
    
    public boolean updateSection(Section s) throws SQLException, IOException {
        String query = "update section set year=?,decription=? where id=?";
        PreparedStatement p = DatabaseConnection.getInstance().getConnection().prepareStatement(query);
        p.setInt(1, s.getYear());
        p.setString(2, s.getDecription());
        p.setInt(3, s.getId());
        int rtn = p.executeUpdate();
        p.close();
        return rtn == 1;
    }
    
    public boolean delSection(int id) throws SQLException, IOException {
        String query = "delete from section where id=?";
        PreparedStatement p = DatabaseConnection.getInstance().getConnection().prepareStatement(query);
        p.setInt(1, id);
        int rtn = p.executeUpdate();
        p.close();
        return rtn == 1;
    }
    
    public Section getLastSection() throws SQLException, IOException {
        String query = "select * from section order by id desc limit 1";
        PreparedStatement p = DatabaseConnection.getInstance().getConnection().prepareStatement(query);
        ResultSet r = p.executeQuery();
        Section s = null;
        if(r.next()) {
            s = new Section(r.getInt(1), r.getInt(2), r.getString(3));
        }
        return  s;
    }
}
