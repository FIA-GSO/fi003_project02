package de.fi003.osp.entity;

import javax.persistence.*;


@Entity
@Table(name = "app_classes")
public class Class {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private long startDate;
    private long endDate;
    private int teacherId;


    public int getId(){
        return this.id;
    }

    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name = name;
    }

    public long getStartDate(){
        return this.startDate;
    }
    public void setStartDate(long startDate){
        this.startDate = startDate;
    }

    public long getEndDate(){
        return this.endDate;
    }
    public void setEndDate(long endDate){
        this.endDate = endDate;
    }

    public int getTeacherId(){
        return this.teacherId;
    }
    public void setTeacherId(int teacherId){
        this.teacherId = teacherId;
    }
}
