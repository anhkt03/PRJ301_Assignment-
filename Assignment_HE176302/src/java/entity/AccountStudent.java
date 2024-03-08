/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author kieuthanhtheanh
 */
public class AccountStudent {
    private String usernames;
    private String passwords;
    private int accountids;

    public int getAccountid() {
        return accountids;
    }

    public void setAccountid(int accountid) {
        this.accountids = accountid;
    }
    
    public String getUsername() {
        return usernames;
    }

    public void setUsername(String username) {
        this.usernames = username;
    }

    public String getPassword() {
        return passwords;
    }

    public void setPassword(String password) {
        this.passwords = password;
    }
}
