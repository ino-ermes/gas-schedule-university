
package com.mio.DAL;

import com.mio.DTO.Department;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DepartmentAccess {
    private static DepartmentAccess instance;
    public static DepartmentAccess getInstance() {
        if(instance == null)
            instance = new DepartmentAccess();
        return instance;
    }    
    private DepartmentAccess() {}
    
    public void addDepartment(Department dep) throws SQLException, IOException {
        String query = "insert into department(name) values(?)";
        PreparedStatement p = DatabaseConnection.getInstance().getConnection().prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
        p.setString(1, dep.getName());
        p.execute();
        ResultSet r = p.getGeneratedKeys();
        if(r.next()) {
            dep.setId(r.getInt(1));
        }
        r.close();
        p.close();
    }
    
    public List<Department> getDepartmentList() throws SQLException, IOException {
        String query = "select * from department";
        PreparedStatement p = DatabaseConnection.getInstance().getConnection().prepareStatement(query);
        ResultSet r = p.executeQuery();
        List<Department> list = new ArrayList<>();
        while(r.next()) {
            list.add(new Department(r.getInt(1), r.getString(2)));
        }
        p.close();
        r.close();
        return list;
    }
    
    public boolean updateDepartment(Department dep) throws SQLException, IOException {
        String query = "update department set name=? where id=?";
        PreparedStatement p = DatabaseConnection.getInstance().getConnection().prepareStatement(query);
        p.setString(1, dep.getName());
        p.setInt(2, dep.getId());
        boolean success = 1 == p.executeUpdate();
        p.close();
        return success;
    }
    
    public boolean delDepartment(int id) throws SQLException, IOException {
        String query = "delete from department where id=?";
        PreparedStatement p = DatabaseConnection.getInstance().getConnection().prepareStatement(query);
        p.setInt(1, id);
        int success = p.executeUpdate();
        p.close();
        return success == 1;
    }
    
    public String getNameById(int id) throws SQLException, IOException {
        String query = "select name from department where id=?";
        PreparedStatement p = DatabaseConnection.getInstance().getConnection().prepareStatement(query);
        p.setInt(1, id);
        String name = "";
        ResultSet r = p.executeQuery();
        if(r.next()) {
            name = r.getString(1);
        }
        r.close();
        p.close();
        return name;
    }
}
