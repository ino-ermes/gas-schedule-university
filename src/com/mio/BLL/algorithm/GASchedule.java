/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mio.BLL.algorithm;

import com.mio.DTO.CourseClass;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 *
 * @author Mio
 */
public class GASchedule {

    public boolean[] getCriteria() {
        return criteria;
    }

    public Map<GACourseClass, Integer> getClasses() {
        return classes;
    }
    
    public ArrayList<ArrayList<GACourseClass>> getSlots() {
        return slots;
    }

    public static final int DAYS_NUM = 6;

    public static final int DAY_HOURS = 10;

    private static final Random rand = new Random();

    private float fitness;
    private int cost;
    private final boolean[] criteria;
    private final ArrayList<ArrayList<GACourseClass>> slots;
    private final Map<GACourseClass, Integer> classes;

    public float getFitness() {
        return fitness;
    }
    
    public GASchedule(boolean setupOnly) {
        criteria = new boolean[Configuration.getInstance().getCourseClasses().size() * 8];
        slots = new ArrayList<>();
        classes = new HashMap<>();
        for (int i = DAYS_NUM * DAY_HOURS * Configuration.getInstance().getRooms().size(); i != 0; --i) {
            slots.add(new ArrayList<>());
        }
        
        if(setupOnly) return;
        
        for (GACourseClass c : Configuration.getInstance().getCourseClasses()) {
            int nr = Configuration.getInstance().getRooms().size();
            int dur = c.getDuration();
            int day = rand.nextInt(DAYS_NUM);
            int room = rand.nextInt(nr);
            int time = rand.nextInt(DAY_HOURS + 1 - dur);
            //if(time <= 4 && time + dur > 5) time = rand.nextInt(5 + 1 - dur);
            
            int pos = day * nr * DAY_HOURS + room * DAY_HOURS + time;

            for (int i = dur - 1; i >= 0; --i) {
                slots.get(pos + i).add(c);
            }

            classes.put(c, pos);
        }
    }
    
    public GASchedule(List<CourseClass> ccs, HashMap<Integer, Integer> roomId_Index) {
        this(true);
        int nr = roomId_Index.size();
        int j = 0;
        for (GACourseClass c : Configuration.getInstance().getCourseClasses()) {
            CourseClass cc = ccs.get(j);
            int dur = cc.getDuration();
            int day = cc.getTimeSlot() % 6;
            int time = cc.getTimeSlot() / 6;
            int room = roomId_Index.get(cc.getRoomId());
            //if(time <= 4 && time + dur > 5) time = rand.nextInt(5 + 1 - dur);
            
            int pos = day * nr * DAY_HOURS + room * DAY_HOURS + time;

            for (int i = dur - 1; i >= 0; --i) {
                slots.get(pos + i).add(c);
            }
            classes.put(c, pos);
            ++j;
        }
    }

    public GASchedule makeCopy() {
        GASchedule newS = new GASchedule(true);
        for(Map.Entry<GACourseClass, Integer> entry: classes.entrySet()) {
            newS.getClasses().put(entry.getKey(), entry.getValue());
        }
        for(int i = slots.size() - 1; i >= 0; --i) {
            for(GACourseClass cc: slots.get(i)) {
                newS.slots.get(i).add(cc);
            }
        }
        newS.fitness = fitness;
        for(int i = 0; i != getCriteria().length; ++i) {
            newS.criteria[i] = criteria[i];
        }
        return newS;
    }
    
    public void calcFitness() {
        cost = 0;
        
	int numberOfRooms = Configuration.getInstance().getRooms().size();
	int daySize = DAY_HOURS * numberOfRooms;

	int ci = 0;
        for(Map.Entry<GACourseClass, Integer> entry: getClasses().entrySet()) {
            // phân rã các tọa độ
            int p = entry.getValue();
            int day = p / daySize;
            int time = p % daySize;
            int room = time / DAY_HOURS;
            time = time % DAY_HOURS;
            
            int dur = entry.getKey().getDuration();
            
            // trùng phòng
            boolean ro = false;
            for(int i = dur - 1; i >=0; --i) {
                if(slots.get(p + i).size() > 1) {
                    ro = true;
                    cost += 20;
                    break;
                }
            }
            
            criteria[ci + 0] = !ro;
            
            GACourseClass cc = entry.getKey();
            GARoom r = Configuration.getInstance().getRooms().get(room);
            // sức chứa của phòng
            criteria[ ci + 1 ] = r.getSize() >= cc.getSize();
            if( !criteria[ ci + 1 ] )
                cost += 20;
            
            // phòng có trang bị máy tính
            criteria[ ci + 2 ] = cc.isLab() == r.isLab();
            if( !criteria[ ci + 2 ] )
                    cost += 20;

            boolean to = false, go = false;
            // trùng lịch giáo viên / nhóm học sinh
            tgOverlap:
            for( int i = numberOfRooms, t = day * daySize + time; i > 0; i--, t += DAY_HOURS )
            {
                // từng giờ học của học phần
                for( int j = dur - 1; j >= 0; j-- )
                {
                    ArrayList<GACourseClass> cl = slots.get(t + j);
                    for(GACourseClass it: cl) {
                        if(!cc.equals(it)) {
                            // trùng lịch giáo viên?
                            if( !to && cc.overlapTeacher(it))
                                to = true;
                            // trùng lịch nhóm lớp?
                            if( !go && cc.overlapGroup(it))
                                go = true;
                            // trùng cả 2?
                            if(to && go)
                                break tgOverlap;
                        }
                    }
                }
            }
            
            // gíao viên ko bị trùng tiết nào?
            if(to)
                cost += 20;
            criteria[ ci + 3 ] = !to;
            
            // nhóm lớp ko bị trùng tiết nào?
            if(go)
                cost += 20;
            criteria[ ci + 4 ] = !go;

            // gio bangiao vien
            if(cc.getTeacher().isBusy(day, time, dur)) {
                criteria[ci + 5] = false;
                cost += 20;
            } else {
                criteria[ci + 5] = true;
            }
            
            // phong ban
            if(r.isBusy(day, time, dur)) {
                criteria[ci + 6] = false;
                cost += 20;
            } else {
                criteria[ci + 6] = true;
            }
            // hoc phan ban
            if(cc.isBusy(day, time, dur)) {
                criteria[ci + 7] = false;
                cost += 20;
            } else {
                criteria[ci + 7] = true;
            }
            
            ci += 8;
        }
        for(GATeacher t: Configuration.getInstance().getTeachers().values()) {
            GACourseClass[] cl = new GACourseClass[t.getClasses().size()];
            for(int i = 0; i != cl.length; ++i) {
                cl[i] = t.getClasses().get(i);
            }
            int[] timeSlots = new int[cl.length];
            for(int i = 0; i != cl.length; ++i) {
                int p = classes.get(cl[i]);
                int day = p / daySize;
                int time = p % daySize;
                time = time % DAY_HOURS;
                timeSlots[i] = time * DAYS_NUM + day;
            }
            quicksort(timeSlots, 0, timeSlots.length - 1, cl);
            int befDay = -1, dayCount = 0;
            for(int i = 0; i != cl.length; ++i) {
                int day = timeSlots[i] % DAYS_NUM;
                int time = timeSlots[i] / DAYS_NUM;
                if(day != befDay) {
                    ++dayCount;
                } else {
                    int befTime = timeSlots[i - 1] / DAYS_NUM;
                    if(time - befTime - cl[i - 1].getDuration() > 0) {
                        cost += time - befTime - cl[i - 1].getDuration();
                    }
                }
                befDay = day;
            }
            cost += dayCount;
        }
        fitness = 1f / (cost + 1f);
    }

    public GASchedule crossover(GASchedule other) {
        GASchedule child = new GASchedule(true);

        boolean[] cp = new boolean[classes.size()];
        for(int i = 0; i != cp.length; ++i) {
            cp[i] = false;
        }
        
        int nCrossover = 1 + rand.nextInt(Math.min(10, cp.length));
        for(int i = 0; i != nCrossover; ++i) {
            while(true) {
                int p = rand.nextInt(cp.length);
                if(!cp[p]) {
                    cp[p] = true;
                    break;
                }
            }
        }
        
        boolean first = rand.nextBoolean();
        int c = 0;
        for(Map.Entry<GACourseClass, Integer> entry: classes.entrySet()) {
            if(first) {
                int p = entry.getValue();
                child.classes.put(entry.getKey(), p);
                for(int i = entry.getKey().getDuration() - 1; i >= 0; --i) {
                    child.slots.get(p + i).add(entry.getKey());
                }
            } else {
                int p = other.classes.get(entry.getKey());
                child.classes.put(entry.getKey(), p);
                for(int i = entry.getKey().getDuration() - 1; i >= 0; --i) {
                    child.slots.get(p + i).add(entry.getKey());
                }
            }
            if(cp[c])
                first = !first;
            ++c;
        }
        child.calcFitness();
        return child;
    }
    
    public void mutation() {
                
        int nMutation = 1 + rand.nextInt(Math.min(10, classes.size()));
        for(int i = nMutation; i > 0; --i) {
            
            GACourseClass cc = Configuration.getInstance().getCourseClasses().get(rand.nextInt(classes.size()));
            
            int pos1 = classes.get(cc);
            
            int nr = Configuration.getInstance().getRooms().size();
            int dur = cc.getDuration();
            int day = rand.nextInt(DAYS_NUM);
            int room = rand.nextInt(nr);
            int time = rand.nextInt(DAY_HOURS + 1 - dur);
            //if(time <= 4 && time + dur > 5) time = rand.nextInt(5 + 1 - dur);
            int pos2 = day * nr * DAY_HOURS + room * DAY_HOURS + time;
            
            for(int j = dur - 1; j >= 0; --j) {
                slots.get(pos1 + j).remove(cc);
                slots.get(pos2 + j).add(cc);
            }
            
            classes.put(cc, pos2);
        }
        calcFitness();
    }
    private int partition(int[] arr, int p, int r, GACourseClass[] cls) {
        int x = arr[r];
        int i = p - 1;
        for(int j = p; j < r; ++j) {
            if(arr[j] <= x) {
                ++i;
                int tmp = arr[i];
                arr[i] = arr[j];
                arr[j] = tmp;
                
                GACourseClass cl = cls[i];
                cls[i] = cls[j];
                cls[j] = cl;
            }
        }
        int tmp = arr[i + 1];
        arr[i + 1] = arr[r];
        arr[r] = tmp;
        
        GACourseClass cl = cls[i + 1];
        cls[i + 1] = cls[r];
        cls[r] = cl;
        
        return i + 1;
    }
    
    private void quicksort(int[] arr, int p, int r, GACourseClass[] cls) {
        if(p < r) {
            int q = partition(arr, p, r, cls);
            quicksort(arr, p, q - 1, cls);
            quicksort(arr, q + 1, r, cls);
        }
    }
}
