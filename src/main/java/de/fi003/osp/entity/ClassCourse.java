package de.fi003.osp.entity;

import javax.persistence.*;
import lombok.Data;

@Entity
@Data
public class ClassCourse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int classId;
    private int courseId;


    public int getId(){
        return this.id;
    }

    public int getClassId() {
        return this.classId;
    }
    public void setClassId( int classId ) {
        this.classId = classId;
    }

    public int getCourseId() {
        return this.courseId;
    }
    public void setCourseId( int courseId ) {
        this.courseId = courseId;
    }

}
