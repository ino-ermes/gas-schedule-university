/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mio.BLL.algorithm;

import java.util.ArrayList;

/**
 *
 * @author Mio
 */
public class GACourseClass {

    public GACourseClass(int id, GATeacher teacher, GACourse course, ArrayList<GAStudentGroup> groups, int size, boolean lab, int duration) {
        this.id = id;
        this.teacher = teacher;
        this.course = course;
        this.groups = groups;
        this.size = size;
        this.lab = lab;
        this.duration = duration;
        this.slotsBusy = new boolean[GASchedule.DAYS_NUM * GASchedule.DAY_HOURS];
        for(int i = 0; i != slotsBusy.length; ++i) {
            this.slotsBusy[i] = false;
        }
    }
    
    public GACourseClass(int id, GATeacher teacher, GACourse course, ArrayList<GAStudentGroup> groups, int duration) {
        this.id = id;
        this.teacher = teacher;
        this.course = course;
        this.groups = groups;
        this.size = 0;
        for(GAStudentGroup s: groups) {
            size += s.getSize();
        }
        this.lab = course.isLab();
        this.duration = duration;
        this.slotsBusy = new boolean[GASchedule.DAYS_NUM * GASchedule.DAY_HOURS];
        for(int i = 0; i != slotsBusy.length; ++i) {
            this.slotsBusy[i] = false;
        }
    }

    public GACourseClass() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public GATeacher getTeacher() {
        return teacher;
    }

    public void setTeacher(GATeacher teacher) {
        this.teacher = teacher;
    }

    public GACourse getCourse() {
        return course;
    }

    public void setCourse(GACourse course) {
        this.course = course;
    }

    public ArrayList<GAStudentGroup> getGroups() {
        return groups;
    }

    public void setGroups(ArrayList<GAStudentGroup> groups) {
        this.groups = groups;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public boolean isLab() {
        return lab;
    }

    public void setLab(boolean lab) {
        this.lab = lab;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public boolean overlapTeacher(GACourseClass other) {
        return this.teacher.equals(other.teacher);
    }
    
    public boolean overlapGroup(GACourseClass other) {
        for(GAStudentGroup s1: groups) {
            for(GAStudentGroup s2: other.groups) {
                if(s1.equals(s2))
                    return true;
            }
        }
        return false;
    }
    
    public boolean isBusy(int day, int time, int duration) {
        for(int i = duration - 1; i >= 0; --i) {
            if(this.slotsBusy[(time + i) * GASchedule.DAYS_NUM + day])
                return true;
        }
        return false;
    }
    
    public void setBusy(int day, int time, int duration, boolean busy) {
        for(int i = duration - 1; i >= 0; --i) {
            this.slotsBusy[(time + i) * GASchedule.DAYS_NUM + day] = busy;
        }
    } 
    public void setBusy(String busy) {
        for(int i = 0; i != slotsBusy.length; ++i) {
            slotsBusy[i] = busy.charAt(i) == '1';
        }
    }
    
    private int id;
    private GATeacher teacher;
    private GACourse course;
    private ArrayList<GAStudentGroup> groups;
    private int size;
    private boolean lab;
    private int duration;
    private boolean[] slotsBusy;
}
