
package com.mio.DTO;

import java.util.ArrayList;

public class CourseClass {

    public CourseClass() {
        this.id = -1;
        this.subjectId = -1;
        this.teacherId = -1;
        this.sectionId = -1;
        this.duration = -1;
        this.size = 0;
        this.requireLab = false;
        this.roomId = -1;
        this.timeSlot = -1;
        this.weekStart = -1;
        this.weekEnd = -1;
        this.studentGroupId = new ArrayList<>();
    }

    public CourseClass(int id, int subjectId, int teacherId, int sectionId, int duration, int size, boolean requireLab, int roomId, int timeSlot, int weekStart, int weekEnd) {
        this.id = id;
        this.subjectId = subjectId;
        this.teacherId = teacherId;
        this.sectionId = sectionId;
        this.duration = duration;
        this.size = size;
        this.requireLab = requireLab;
        this.roomId = roomId;
        this.timeSlot = timeSlot;
        this.weekStart = weekStart;
        this.weekEnd = weekEnd;
        this.studentGroupId = new ArrayList<>();
    }

    public void copy(CourseClass other) {
        this.id = other.id;
        this.subjectId = other.subjectId;
        this.teacherId = other.teacherId;
        this.sectionId = other.sectionId;
        this.duration = other.duration;
        this.size = other.size;
        this.requireLab = other.requireLab;
        this.roomId = other.roomId;
        this.timeSlot = other.timeSlot;
        this.weekStart = other.weekStart;
        this.weekEnd = other.weekEnd;
        this.studentGroupId = other.studentGroupId;
    }
    
    public void addStudentGroupId(int id) {
        studentGroupId.add(id);
    }
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public int getSectionId() {
        return sectionId;
    }

    public void setSectionId(int sectionId) {
        this.sectionId = sectionId;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public boolean isRequireLab() {
        return requireLab;
    }

    public void setRequireLab(boolean requireLab) {
        this.requireLab = requireLab;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public int getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(int timeSlot) {
        this.timeSlot = timeSlot;
    }

    public int getWeekStart() {
        return weekStart;
    }

    public void setWeekStart(int weekStart) {
        this.weekStart = weekStart;
    }

    public int getWeekEnd() {
        return weekEnd;
    }

    public void setWeekEnd(int weekEnd) {
        this.weekEnd = weekEnd;
    }

    public ArrayList<Integer> getStudentGroupId() {
        return studentGroupId;
    }

    public void setStudentGroupId(ArrayList<Integer> studentGroupId) {
        this.studentGroupId = studentGroupId;
    }

    private int id;
    private int subjectId;
    private int teacherId;
    private int sectionId;
    private int duration;
    private int size;
    private boolean requireLab;
    private int roomId;
    private int timeSlot;
    private int weekStart;
    private int weekEnd;
    private ArrayList<Integer> studentGroupId;
}
