
package com.mio.BLL.algorithm;

/**
 *
 * @author Mio
 */
public class GARoom {

    public GARoom(int id, String name, boolean lab, int size) {
        this.id = id;
        this.name = name;
        this.lab = lab;
        this.size = size;
        this.slotsBusy = new boolean[GASchedule.DAYS_NUM * GASchedule.DAY_HOURS];
        for(int i = 0; i != slotsBusy.length; ++i) {
            this.slotsBusy[i] = false;
        }
    }

    public GARoom() {
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

    public boolean isLab() {
        return lab;
    }

    public void setLab(boolean lab) {
        this.lab = lab;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
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
    private String name;
    private boolean lab;
    private int size;
    private boolean[] slotsBusy;
}
