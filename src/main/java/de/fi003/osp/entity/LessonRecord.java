package de.fi003.osp.entity;

import javax.persistence.*;
import lombok.Data;

@Entity
@Data
public class LessonRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int teacherId;
    private int studentId;
    private int lessonId;
    private int note;
    private String description;


    public int getId(){
        return this.id;
    }

    public int getTeacherId(){
        return this.teacherId;
    }
    public void setTeacherId(int teacherId){
        this.teacherId = teacherId;
    }   

    public int getStudentId(){
        return this.studentId;
    }
    public void setStudentId(int studentId){
        this.studentId = studentId;
    }   

    public int getLessonId(){
        return this.lessonId;
    }
    public void setLessonId(int lessonId){
        this.lessonId = lessonId;
    }   

    public int getNote(){
        return this.note;
    }
    public void setNote(int note){
        this.note = note;
    }

    public String getDescription(){
        return this.description;
    }
    public void setDescription(String description){
        this.description = description;
    }

}
