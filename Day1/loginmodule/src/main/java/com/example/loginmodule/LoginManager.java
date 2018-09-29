package com.example.loginmodule;

public class LoginManager {
    private String pass;

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    private String uname;

    public String validateLogin(){
        if(uname.equals("Srikant") && pass.equals("12345")){
            return "Validated";
        }
        else{
            return "Not Valid";
        }
    }


}