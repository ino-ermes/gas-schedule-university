/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mio.BLL;

import com.mio.DAL.DepartmentAccess;
import com.mio.DTO.Department;
import java.util.List;

/**
 *
 * @author Mio
 */
public class DepartmentBLL {
    private static DepartmentBLL instance;
    public static DepartmentBLL getInstance() {
        if(instance == null)
            instance = new DepartmentBLL();
        return instance;
    }    
    private DepartmentBLL() {}
    
    public void addDepartment(Department dep){
        if(dep.getName().isBlank())
            return;
        try {
            DepartmentAccess.getInstance().addDepartment(dep);
        } catch(Exception e) {
        }
    }
    public List<Department> getDepartmentList() {
        try {
            return DepartmentAccess.getInstance().getDepartmentList();
        } catch(Exception e) {
            return null;
        }
    }
    public boolean updateDepartment(Department dep) {
        try {
            return DepartmentAccess.getInstance().updateDepartment(dep);
        } catch (Exception e){
            return false;
        }
    }
    public boolean delDepartment(int id) {
        try {
            return DepartmentAccess.getInstance().delDepartment(id);
        } catch (Exception e){
            return false;
        }
    }
    public String getNameById(int id) {
        try {
            return DepartmentAccess.getInstance().getNameById(id);
        } catch(Exception e) {
            return "";
        }
    }
}
