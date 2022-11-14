package de.fi003.osp.entity;

public class Course {
    private int id;
    private String name;
    private int teacherId;
    private long startDatetime;
    private long endDatetime;


    public int getId(){
        return this.id;
    }

    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name = name;
    }

    public int getTeacherId(){
        return this.teacherId;
    }
    public void setTeacherId(int teacherId){
        this.teacherId = teacherId;
    }   

    public long getStartDatetime(){
        return this.startDatetime;
    }
    public void setStartDatetime(long startDatetime){
        this.startDatetime = startDatetime;
    }

    public long getEndDatetime(){
        return this.endDatetime;
    }
    public void setEndDatetime(long endDatetime){
        this.endDatetime = endDatetime;
    }

}
