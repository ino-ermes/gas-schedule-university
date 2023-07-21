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
public class GATeacher {
    public GATeacher(int id, String name) {
        this.id = id;
        this.name = name;
        this.slotsBusy = new boolean[GASchedule.DAYS_NUM * GASchedule.DAY_HOURS];
        classes = new ArrayList<>();
        for(int i = 0; i != slotsBusy.length; ++i) {
            this.slotsBusy[i] = false;
        }
    }

    public GATeacher() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
    
    public void addClass(GACourseClass cc) {
        classes.add(cc);
    }
    
    public ArrayList<GACourseClass> getClasses() {
        return classes;
    }
    
    private int id;
    private String name;
    private ArrayList<GACourseClass> classes;
    private boolean[] slotsBusy;
    
    public void setBusy(String busy) {
        for(int i = 0; i != slotsBusy.length; ++i) {
            slotsBusy[i] = busy.charAt(i) == '1';
        }
    }
}