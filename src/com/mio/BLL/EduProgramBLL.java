
package com.mio.BLL;

import com.mio.DAL.EduProgramAccess;
import com.mio.DTO.EduProgram;
import com.mio.DTO.EduProgramDetail;
import com.mio.DTO.SO;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class EduProgramBLL {
    private static EduProgramBLL instance;
    public static EduProgramBLL getInstance() {
        if(instance == null)
            instance = new EduProgramBLL();
        return instance;
    }    
    private EduProgramBLL() {}
    
    public List<EduProgram> getEduProgramByDepId(int department_id) {
        try {
            return EduProgramAccess.getInstance().getEduProgramByDepId(department_id);
        } catch(Exception e) {
            return new ArrayList<EduProgram>();
        }
    }
    
    public boolean nextSemester(int eduId) {
        try {
            return EduProgramAccess.getInstance().nextSemester(eduId);
        } catch(Exception e) {
            return false;
        }
    }
    
    public List<SO> getSEduProgramByDepId(int department_id) {
        try {
            return EduProgramAccess.getInstance().getSEduProgramByDepId(department_id);
        } catch(Exception e) {
            return new ArrayList<>();
        }
    }
    
    public List<EduProgram> getEduProgramActive() {
        try {
            return EduProgramAccess.getInstance().getEduProgramActive();
        } catch(Exception e) {
            return new ArrayList<>();
        }
    }
    
    public List<SO> getSEduProgramActive() {
        try {
            return EduProgramAccess.getInstance().getSEduProgramActive();
        } catch(Exception e) {
            return new ArrayList<>();
        }
    }
    
    public void addEduProgram(EduProgram s) {
        try {
            EduProgramAccess.getInstance().addEduProgram(s);
        } catch(Exception e) {
            System.err.println(e);
        }
    }
    public EduProgram getEduProgram(int id) {
        try {
            return EduProgramAccess.getInstance().getEduProgram(id);
        } catch(Exception e) {
            return null;
        }
    }
    public boolean updateEduProgram(EduProgram s) {
        try {
            if(s.getSemesterCurrent() > s.getSemesterCount())
                return false;
            return EduProgramAccess.getInstance().updateEduProgram(s);
        } catch(Exception e) {
            return false;
        }
    }
    public boolean delEduProgram(int id) {
        try {
            return EduProgramAccess.getInstance().delEduProgram(id);
        } catch(Exception e) {
            return false;
        }
    }
    public List<EduProgramDetail> getEduProgDetail(int eduProgId) {
        try {
            return EduProgramAccess.getInstance().getEduProgDetail(eduProgId);
        } catch(Exception ex) {
            return null;
        }
    }
    public List<EduProgramDetail> getEduProgDetailBySemester(int eduProgId, int semester) {
        try {
            return EduProgramAccess.getInstance().getEduProgDetailBySemester(eduProgId, semester);
        } catch(Exception ex) {
            return null;
        }
    }
    
    public void addEduProgDetail(EduProgramDetail e) {
        try {
            EduProgramAccess.getInstance().addEduProgDetail(e);
        } catch(Exception ex) {
        }
    }
    
    public boolean updateEduProgDetail(EduProgramDetail e) {
        try {
            return EduProgramAccess.getInstance().updateEduProgDetail(e);
        } catch(Exception ex) {
            return false;
        }
    }
    
    public boolean delEduProgDetail(int id) {
        try {
            return EduProgramAccess.getInstance().delEduProgDetail(id);
        } catch(Exception ex) {
            return false;
        }
    }
    
    public HashMap<Integer, EduProgram> getEduProgList() {
        try {
            return EduProgramAccess.getInstance().getEduProgList();
        } catch(Exception e) {
            return new HashMap<>();
        }
    }
     public int getMaxSemester(int eduProgId) {
         try {
             return EduProgramAccess.getInstance().getMaxSemester(eduProgId);
         } catch(Exception e) {
             return 0;
         }
     }
}
