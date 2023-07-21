/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mio.DTO;

/**
 *
 * @author Mio
 */
public class EduProgramDetail {

    public EduProgramDetail(int id, int eduProgramId, int subjectId, int semester) {
        this.id = id;
        this.eduProgramId = eduProgramId;
        this.subjectId = subjectId;
        this.semester = semester;
    }

    public EduProgramDetail() {
    }
    
    public EduProgramDetail(EduProgramDetail e) {
        this.id = e.id;
        this.eduProgramId = e.eduProgramId;
        this.subjectId = e.subjectId;
        this.semester = e.semester;
    }
    
    public void copy(EduProgramDetail e) {
        this.id = e.id;
        this.eduProgramId = e.eduProgramId;
        this.subjectId = e.subjectId;
        this.semester = e.semester;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEduProgramId() {
        return eduProgramId;
    }

    public void setEduProgramId(int eduProgramId) {
        this.eduProgramId = eduProgramId;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }


    private int id;
    private int eduProgramId;
    private int subjectId;
    private int semester;
}
