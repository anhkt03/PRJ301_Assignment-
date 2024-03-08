/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author kieuthanhtheanh
 */
public class Lecturer {
    private int leid;
    private String MSGV;
    private String lename;
    private boolean gender;
    private String email;

    public int getLeid() {
        return leid;
    }

    public void setLeid(int leid) {
        this.leid = leid;
    }

    public String getMSGV() {
        return MSGV;
    }

    public void setMSGV(String MSGV) {
        this.MSGV = MSGV;
    }

    public String getLename() {
        return lename;
    }

    public void setLename(String lename) {
        this.lename = lename;
    }

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    
    
}
