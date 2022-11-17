package de.fi003.osp.entity;

import javax.persistence.*;
import lombok.Data;

@Entity
@Data
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String vorname;
    private String email;
    private int classId;
    private long birthDate;
    private String photo;

    public int getId(){
        return this.id;
    }

    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name = name;
    }

    public String getVorname(){
        return this.vorname;
    }
    public void setVorname(String vorname){
        this.vorname = vorname;
    }

    public String getEmail(){
        return this.email;
    }
    public void setEmail(String email){
        this.email = email;
    }

    public int getClassId(){
        return this.classId;
    }
    public void setClassId(int classId){
        this.classId = classId;
    }

    public long getBirthDate(){
        return this.birthDate;
    }
    public void setBirthDate(long birthDate){
        this.birthDate = birthDate;
    }

    public String getPhoto(){
        return this.photo;
    }

    public void setPhoto(String photo){
        this.photo = photo;
    }
}
