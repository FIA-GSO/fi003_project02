package de.fi003.osp.entity;

import javax.persistence.*;

import lombok.Data;
@Entity
@Data
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String vorname;
    private String login;
    private String password;
    private String email;
    private String phone;

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

    public String getLogin(){
        return this.login;
    }
    public void setLogin(String login){
        this.login = login;
    }

    public String getPassword(){
        return this.password;
    }
    public void setPassword(String password){
        this.password = password;
    }

    public String getEmail(){
        return this.email;
    }
    public void setEmail(String email){
        this.email = email;
    }

    public String getPhone(){
        return this.phone;
    }
    public void setPhone(String phone){
        this.phone = phone;
    }

    public Teacher orElseThrow(Object object) {
        return null;
    }
}
