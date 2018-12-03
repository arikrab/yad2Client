package com.company;

public class User {

    private boolean active;
    private String userName;
    private String password;

    public User() {
        this.userName=null;
        this.userName=null;
        this.active=false;
    }

///////////////////////////////////////////////////////////////////////////////
        //getters and setters//
    ///////////////////////////////////////////

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    ///////////////////////////////////////////////////////////////////////////////















    /////////////////////////////////////////////////////////////////////
    //hashCode//

    public int passHash(){
        int hash=7;
        for (int i=0;i<password.length();i++){
            hash*=(int)password.charAt(i);
        }
        return hash*331;
    }
    @Override
    public int hashCode() {
        int hash=13;
        for (int i=0;i<userName.length();i++){
            hash*=(int)userName.charAt(i);
        }
        return (hash*131)^passHash();
    }
}
//////////////////////////////////////////////////////////////////////////////
