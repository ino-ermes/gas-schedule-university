
package com.mio.DAL;

import com.mio.DTO.CourseClass;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ClassAccess {
    private static ClassAccess instance;
    public static ClassAccess getInstance() {
        if(instance == null)
            instance = new ClassAccess();
        return instance;
    }    
    private ClassAccess() {}
    
    public List<CourseClass> getClassByRoom(int roomId, int sectionId) throws SQLException, IOException {
        String query = "select c.id, c.subject_id, c.teacher_id, c.duration, c.size, c.require_lab, c.time_slot, c.week_start, c.week_end, class_group.student_group_id from (select id, subject_id, teacher_id, duration, size, require_lab, time_slot, week_start, week_end from class where section_id = ? and room_id = ?) as c left join class_group on c.id = class_group.class_id;";
        PreparedStatement p = DatabaseConnection.getInstance().getConnection().prepareStatement(query);
        p.setInt(1, sectionId);
        p.setInt(2, roomId);
        ResultSet r = p.executeQuery();
        HashMap<Integer, CourseClass> courseClasses = new HashMap<>();
        List<CourseClass> list = new ArrayList<>();
        while(r.next()) {
            CourseClass cc = new CourseClass(r.getInt(1), r.getInt(2), r.getInt(3), sectionId, r.getInt(4), r.getInt(5), r.getBoolean(6), roomId, r.getInt(7), r.getInt(8), r.getInt(9));
            CourseClass bef = courseClasses.get(cc.getId());
            if(bef != null) {
                bef.addStudentGroupId(r.getInt(10));
            } else {
                courseClasses.put(cc.getId(), cc);
                list.add(cc);
                cc.addStudentGroupId(r.getInt(10));
            }
        }
        r.close();
        p.close();
        return list;
    }
    
    public List<CourseClass> getClassByTeacher(int teacherId, int sectionId) throws SQLException, IOException {
        String query = "select c.id, c.subject_id, c.duration, c.size, c.require_lab, c.room_id, c.time_slot, c.week_start, c.week_end, class_group.student_group_id from (select id, subject_id, duration, size, require_lab, room_id, time_slot, week_start, week_end from class where section_id = ? and teacher_id = ?) as c left join class_group on c.id = class_group.class_id;";
        PreparedStatement p = DatabaseConnection.getInstance().getConnection().prepareStatement(query);
        p.setInt(1, sectionId);
        p.setInt(2, teacherId);
        ResultSet r = p.executeQuery();
        HashMap<Integer, CourseClass> courseClasses = new HashMap<>();
        List<CourseClass> list = new ArrayList<>();
        while(r.next()) {
            CourseClass cc = new CourseClass(r.getInt(1), r.getInt(2), teacherId, sectionId, r.getInt(3), r.getInt(4), r.getBoolean(5), r.getInt(6), r.getInt(7), r.getInt(8), r.getInt(9));
            CourseClass bef = courseClasses.get(cc.getId());
            if(bef != null) {
                bef.addStudentGroupId(r.getInt(10));
            } else {
                courseClasses.put(cc.getId(), cc);
                list.add(cc);
                cc.addStudentGroupId(r.getInt(10));
            }
        }
        r.close();
        p.close();
        return list;
    }
    
    public List<CourseClass> getClassByStudentGroup(int groupId, int sectionId) throws SQLException, IOException {
        String query = "select id, subject_id, teacher_id, duration, size, require_lab, room_id, time_slot, week_start, week_end from (select class_id from class_group where student_group_id = ?) as gr join (select * from class where section_id = ?) as cl on gr.class_id = cl.id";
        PreparedStatement p = DatabaseConnection.getInstance().getConnection().prepareStatement(query);
        p.setInt(1, groupId);
        p.setInt(2, sectionId);
        ResultSet r = p.executeQuery();
        List<CourseClass> list = new ArrayList<>();
        while(r.next()) {
            CourseClass cc = new CourseClass(r.getInt(1), r.getInt(2), r.getInt(3), sectionId, r.getInt(4), r.getInt(5), r.getBoolean(6), r.getInt(7), r.getInt(8), r.getInt(9), r.getInt(10));
            cc.addStudentGroupId(groupId);
            list.add(cc);
        }
        r.close();
        p.close();
        return list;
    }
    
    public List<CourseClass> getClassBySection(int sectionId) throws SQLException, IOException {
        String query = "select c.id, c.subject_id, c.teacher_id, c.duration, c.size, c.require_lab, c.room_id, c.time_slot, c.week_start, c.week_end, class_group.student_group_id from (select id, subject_id, teacher_id, duration, size, require_lab, room_id, time_slot, week_start, week_end from class where section_id = ?) as c left join class_group on c.id = class_group.class_id;";
        PreparedStatement p = DatabaseConnection.getInstance().getConnection().prepareStatement(query);
        p.setInt(1, sectionId);
        ResultSet r = p.executeQuery();
        HashMap<Integer, CourseClass> courseClasses = new HashMap<>();
        List<CourseClass> list = new ArrayList<>();
        int roomId = 0;
        while(r.next()) {
            CourseClass cc = new CourseClass(r.getInt(1), r.getInt(2), r.getInt(3), sectionId, r.getInt(4), r.getInt(5), r.getBoolean(6), (roomId = r.getInt(7)) == 0 ? -1 : roomId, r.getInt(8), r.getInt(9), r.getInt(10));
            CourseClass bef = courseClasses.get(cc.getId());
            if(bef != null) {
                bef.addStudentGroupId(r.getInt(11));
            } else {
                courseClasses.put(cc.getId(), cc);
                list.add(cc);
                cc.addStudentGroupId(r.getInt(11));
            }
        }
        r.close();
        p.close();
        return list;
    }
    
    public void addClassWithoutSchedule(CourseClass cc) throws SQLException, IOException {
        String query = "insert into class(subject_id, teacher_id, section_id, duration, size, require_lab) values(?,?,?,?,?,?)";
        PreparedStatement p = DatabaseConnection.getInstance().getConnection().prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
        p.setInt(1, cc.getSubjectId());
        p.setInt(2, cc.getTeacherId());
        p.setInt(3, cc.getSectionId());
        p.setInt(4, cc.getDuration());
        p.setInt(5, cc.getSize());
        p.setBoolean(6, cc.isRequireLab());

        p.execute();
        ResultSet r = p.getGeneratedKeys();
        if(r.next()) {
            cc.setId(r.getInt(1));
            query = "insert into class_group(class_id, student_group_id) values(?,?)";
            p.close();
            p = DatabaseConnection.getInstance().getConnection().prepareStatement(query);
            p.setInt(1, cc.getId());
            for(int groupId: cc.getStudentGroupId()) {
                p.setInt(2, groupId);
                p.executeUpdate();
            }
        }
        r.close();
        p.close();
    }
    public void addClass(CourseClass cc) throws SQLException, IOException {
        String query = "insert into class(subject_id, teacher_id, section_id, duration, size, require_lab, room_id, time_slot, week_start, week_end) values(?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement p = DatabaseConnection.getInstance().getConnection().prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
        p.setInt(1, cc.getSubjectId());
        p.setInt(2, cc.getTeacherId());
        p.setInt(3, cc.getSectionId());
        p.setInt(4, cc.getDuration());
        p.setInt(5, cc.getSize());
        p.setBoolean(6, cc.isRequireLab());
        p.setInt(7, cc.getRoomId());
        p.setInt(8, cc.getTimeSlot());
        p.setInt(9, cc.getWeekStart());
        p.setInt(10, cc.getWeekEnd());
        p.execute();
        ResultSet r = p.getGeneratedKeys();
        if(r.next()) {
            cc.setId(r.getInt(1));
            query = "insert into class_group(class_id, student_group_id) values(?,?)";
            p.close();
            p = DatabaseConnection.getInstance().getConnection().prepareStatement(query);
            p.setInt(1, cc.getId());
            for(int groupId: cc.getStudentGroupId()) {
                p.setInt(2, groupId);
                p.executeUpdate();
            }
        }
        r.close();
        p.close();
    }
    
    public void updateClass(CourseClass cc) throws SQLException, IOException {
        String query = "delete from class_group where class_id=?";
        PreparedStatement p = DatabaseConnection.getInstance().getConnection().prepareStatement(query);
        p.setInt(1, cc.getId());
        p.executeUpdate();
        p.close();
        
        query = "update class set subject_id=?,teacher_id=?,section_id=?,duration=?,size=?,require_lab=?,room_id=?,time_slot=?,week_start=?,week_end=? where id=?";
        p = DatabaseConnection.getInstance().getConnection().prepareStatement(query);  
        p.setInt(1, cc.getSubjectId());
        p.setInt(2, cc.getTeacherId());
        p.setInt(3, cc.getSectionId());
        p.setInt(4, cc.getDuration());
        p.setInt(5, cc.getSize());
        p.setBoolean(6, cc.isRequireLab());
        p.setInt(7, cc.getRoomId());
        p.setInt(8, cc.getTimeSlot());
        p.setInt(9, cc.getWeekStart());
        p.setInt(10, cc.getWeekEnd());
        p.setInt(11, cc.getId());
        p.executeUpdate();
        p.close();
        
        query = "insert into class_group(class_id, student_group_id) values(?,?)";
        p = DatabaseConnection.getInstance().getConnection().prepareStatement(query);
        p.setInt(1, cc.getId());
        for(int groupId: cc.getStudentGroupId()) {
            p.setInt(2, groupId);
            p.executeUpdate();
        }
        p.close();
    }
    
    public void updateClassWithoutSchedule(CourseClass cc) throws SQLException, IOException {
        String query = "delete from class_group where class_id=?";
        PreparedStatement p = DatabaseConnection.getInstance().getConnection().prepareStatement(query);
        p.setInt(1, cc.getId());
        p.executeUpdate();
        p.close();
        
        query = "update class set subject_id=?,teacher_id=?,section_id=?,duration=?,size=?,require_lab=? where id=?";
        p = DatabaseConnection.getInstance().getConnection().prepareStatement(query);  
        p.setInt(1, cc.getSubjectId());
        p.setInt(2, cc.getTeacherId());
        p.setInt(3, cc.getSectionId());
        p.setInt(4, cc.getDuration());
        p.setInt(5, cc.getSize());
        p.setBoolean(6, cc.isRequireLab());
        p.setInt(7, cc.getId());
        p.executeUpdate();
        p.close();
        
        query = "insert into class_group(class_id, student_group_id) values(?,?)";
        p = DatabaseConnection.getInstance().getConnection().prepareStatement(query);
        p.setInt(1, cc.getId());
        for(int groupId: cc.getStudentGroupId()) {
            p.setInt(2, groupId);
            p.executeUpdate();
        }
        p.close();
    }
    
    public boolean delClass(int id) throws SQLException, IOException {
        String query = "delete from class_group where class_id=?";
        PreparedStatement p = DatabaseConnection.getInstance().getConnection().prepareStatement(query);
        p.setInt(1, id);
        p.executeUpdate();
        p.close();
        
        query = "delete from class where id=?";
        p = DatabaseConnection.getInstance().getConnection().prepareStatement(query);
        p.setInt(1, id);
        int rtn = p.executeUpdate();
        p.close();
        return rtn == 1;
    }
    public String getBusy(int id) throws SQLException, IOException {
        String query = "select busy from class where id=?";
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
        String query = "update class set busy=? where id=?";
        PreparedStatement p = DatabaseConnection.getInstance().getConnection().prepareStatement(query);
        p.setString(1, busy);
        p.setInt(2, id);
        int rtn = p.executeUpdate();
        p.close();
        return rtn == 1;
    }
}
