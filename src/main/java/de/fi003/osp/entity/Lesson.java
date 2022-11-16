package de.fi003.osp.entity;
import javax.persistence.*;
import lombok.Data;
@Entity
@Data
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int teacherId;
    private int courseId;
    private int classId;
    private String roomCode;
    private long startDatetime;
    private long endDatetime;
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

    public int getCourseId(){
        return this.courseId;
    }
    public void setCourseId(int courseId){
        this.courseId = courseId;
    }   

    public int getClassId(){
        return this.classId;
    }
    public void setClassId(int classId){
        this.classId = classId;
    }   

    public String getRoomCode(){
        return this.roomCode;
    }
    public void setRoomCode(String roomCode){
        this.roomCode = roomCode;
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

    public String getDescription(){
        return this.description;
    }
    public void setDescription(String description){
        this.description = description;
    }

}
