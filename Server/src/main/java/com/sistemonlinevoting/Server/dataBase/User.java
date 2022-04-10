package com.sistemonlinevoting.Server.dataBase;

import com.sun.istack.NotNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class User {

    private @Id @GeneratedValue Long id;
    private  @Column(nullable = false) String fname;
    private  @Column(nullable = false) String lname;
    private  @Column(nullable = false) String username;
    private  @Column(nullable = false) String cnp;
    private  @Column(nullable = false) String phone;
    private  @Column(nullable = false) String email;
    private  @Column(nullable = false) String password;
    private  @Column(nullable = false) String sessionKey;

    public User(String fname, String lname, String username, String cnp, String phone, String email, String password, String sessionKey){
        this.fname = fname;
        this.lname = lname;
        this.username = username;
        this.cnp = cnp;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.sessionKey = sessionKey;
    }

    public Long getId(){
        return this.id;
    }

    public String getFname(){
        return this.fname;
    }

    public String getLname(){
        return this.lname;
    }

    public String getUsername(){
        return this.username;
    }

    public String getCnp(){
        return this.cnp;
    }

    public String getPhone(){
        return this.phone;
    }

    public String getEmail(){
        return this.email;
    }

    public String getPassword(){
        return this.password;
    }

    public String getSessionKey() {
        return sessionKey;
    }

    public void setId(Long id){
        this.id = id;
    }

    public void setFname(String fname){
        this.fname = fname;
    }

    public void setLname(String lname){
        this.lname = lname;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public void setCnp(String cnp){
        this.cnp = cnp;
    }

    public void setPhone(String phone){
        this.phone = phone;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public void setSessionKey(String sessionKey){
        this.sessionKey = sessionKey;
    }

    @Override
    public boolean equals(Object o){

        if(this == o){
            return true;
        }
        if(!(o instanceof User)){
            return false;
        }
        User user = (User) o;
        return Objects.equals(this.id,user.id) && Objects.equals(this.lname,user.lname) && Objects.equals(this.fname,user.fname)
                && Objects.equals(this.cnp,user.cnp) && Objects.equals(this.email,user.email) && Objects.equals(this.phone,user.phone)
                && Objects.equals(this.password,user.password) && Objects.equals(this.username,user.username);
    }

    @Override
    public String toString(){
        return "User{" + "id=" + this.id + ", fname=" + this.fname + ", lname=" + this.lname + ", username=" + this.username +
                ", cnp=" + this.cnp + ", email=" + this.email + ", phone=" + this.phone + "}";
    }
}
