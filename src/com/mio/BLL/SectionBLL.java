
package com.mio.BLL;

import com.mio.DAL.SectionAccess;
import com.mio.DTO.Section;
import java.util.ArrayList;
import java.util.List;

public class SectionBLL {
    private static SectionBLL instance;
    public static SectionBLL getInstance() {
        if(instance == null)
            instance = new SectionBLL();
        return instance;
    }    
    private SectionBLL() {}
    
    public List<Section> getSectionList() {
        try {
            return SectionAccess.getInstance().getSectionList();
        } catch(Exception e) {
            return new ArrayList<>();
        }
    }
    public Section getCurSection() {
        try {
            return SectionAccess.getInstance().getCurSection();
        } catch(Exception e) {
            return null;
        }
    }
    
    public Section getSectionById(int id) {
        try {
            return SectionAccess.getInstance().getSectionById(id);
        } catch(Exception e) {
            return null;
        }
    }
    
    public Section.SectionStatus getCurSectionStatus() {
        try {
            switch (SectionAccess.getInstance().getCurSectionStatus()) {
                case 0 -> {
                    return Section.SectionStatus.NORMAL;
                }
                case 1 -> {
                    return Section.SectionStatus.NEW_SECTION;
                }
                default -> {
                    return Section.SectionStatus.SCHEDULING;
                }

            }
        } catch(Exception e) {
            return null;
        }
    }
    
    public boolean setCurSectionStatus(Section.SectionStatus status) {
        try {
            switch (status) {
                case NORMAL -> {
                    return SectionAccess.getInstance().setCurSectionStatus(0);
                }
                case NEW_SECTION -> {
                    return SectionAccess.getInstance().setCurSectionStatus(1);
                }
                default -> {
                    return SectionAccess.getInstance().setCurSectionStatus(2);
                }
            }
        } catch(Exception e) {
            return false;
        }
    }
    
    public boolean setCurSection(int sectionId) {
        try {
            return SectionAccess.getInstance().setCurSection(sectionId);
        } catch(Exception e) {
            return false;
        }
    }
    
    public void addSection(Section s) {
        try {
            SectionAccess.getInstance().addSection(s);
        } catch(Exception e) {
            
        }
    }
    
    public boolean updateSection(Section s) {
        try {
            return SectionAccess.getInstance().updateSection(s);
        } catch(Exception e) {
            return false;
        }
    }
    public boolean delSection(int id) {
        try {
            return SectionAccess.getInstance().delSection(id);
        } catch(Exception e) {
            return false;
        } 
    }
    public Section getLastSection() {
        try {
            return SectionAccess.getInstance().getLastSection();
        } catch(Exception e) {
            return null;
        }
    }
}
