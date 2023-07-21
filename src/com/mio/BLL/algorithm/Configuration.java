
package com.mio.BLL.algorithm;

import com.mio.BLL.RoomBLL;
import com.mio.BLL.StudentGroupBLL;
import com.mio.BLL.SubjectBLL;
import com.mio.BLL.TeacherBLL;
import com.mio.DTO.CourseClass;
import com.mio.DTO.Room;
import com.mio.DTO.StudentGroup;
import com.mio.DTO.Subject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Mio
 */
public class Configuration {

    public boolean isEmpty() {
        return empty;
    }

    public HashMap<Integer, GATeacher> getTeachers() {
        return teachers;
    }

    public HashMap<Integer, GAStudentGroup> getGroups() {
        return groups;
    }

    public HashMap<Integer, GACourse> getCourses() {
        return courses;
    }

    public HashMap<Integer, GARoom> getRooms() {
        return rooms;
    }

    public ArrayList<GACourseClass> getCourseClasses() {
        return courseClasses;
    }

    private HashMap<Integer, GATeacher> teachers;
    private HashMap<Integer, GAStudentGroup> groups;
    private HashMap<Integer, GACourse> courses;
    private HashMap<Integer, GARoom> rooms;
    private ArrayList<GACourseClass> courseClasses;
    
    private boolean empty;

    private Configuration() {
        empty = true;
    }
    
    private static Configuration instance;
    
    public static Configuration getInstance() {
        if(instance == null) {
            instance = new Configuration();
            instance.teachers = new HashMap<>();
            instance.groups = new HashMap<>();
            instance.courses = new HashMap<>();
            instance.rooms = new HashMap<>();
            instance.courseClasses = new ArrayList<>();
        }
        return instance;
    } 
    
    public static void clear() {
        instance = null;
    }
    
    public void init(List<CourseClass> ccs, List<Room> rs) {
        empty = false;

        GATeacher teacher;
        GAStudentGroup group;
        StudentGroup gr;
        ArrayList<GAStudentGroup> groupsPercc;
        GACourse course;
        Subject s;
        GARoom room;
        
        for(CourseClass cc: ccs) {
            teacher = teachers.get(cc.getTeacherId());
            if(teacher == null) {
                teacher = new GATeacher(cc.getTeacherId(), TeacherBLL.getInstance().getTeacherName(cc.getTeacherId()));
                teacher.setBusy(TeacherBLL.getInstance().getBusy(teacher.getId()));
                teachers.put(teacher.getId(), teacher);
            }
            groupsPercc = new ArrayList<>();
            for(int grId: cc.getStudentGroupId()) {
                group = groups.get(grId);
                if(group == null) {
                    gr = StudentGroupBLL.getInstance().getStudentGroup(grId);
                    group = new GAStudentGroup(grId, gr.getName(), gr.getSize());
                    groups.put(grId, group);
                }
                groupsPercc.add(group);
            }
            course = courses.get(cc.getSubjectId());
            if(course == null) {
                s = SubjectBLL.getInstance().getSubject(cc.getSubjectId());
                course = new GACourse(s.getId(), s.getName(), s.isRequireLab());
                courses.put(course.getId(), course);
            }
            GACourseClass gcc = new GACourseClass(cc.getId(), teacher, course, groupsPercc, cc.getSize(), cc.isRequireLab(), cc.getDuration());
            courseClasses.add(gcc);
            gcc.getTeacher().addClass(gcc);
        }
        int i = 0;
        for(Room rr: rs) {
            room = new GARoom(rr.getId(), rr.getName(), rr.isLab(), rr.getSize());
            room.setBusy(RoomBLL.getInstance().getBusy(room.getId()));
            rooms.put(i++, room);
        }
    }
}
