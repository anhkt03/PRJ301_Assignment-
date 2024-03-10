/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.util.ArrayList;

/**
 *
 * @author kieuthanhtheanh
 */
public class Role {
    private int roleid;
    private String rolename;
    private ArrayList<Account> account = new ArrayList<>();

    public int getRoleid() {
        return roleid;
    }

    public void setRoleid(int roleid) {
        this.roleid = roleid;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public ArrayList<Account> getAccount() {
        return account;
    }

    public void setAccount(ArrayList<Account> account) {
        this.account = account;
    }
            
    
}
