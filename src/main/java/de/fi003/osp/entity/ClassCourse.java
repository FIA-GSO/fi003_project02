package de.fi003.osp.entity;

public class ClassCourse {
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
