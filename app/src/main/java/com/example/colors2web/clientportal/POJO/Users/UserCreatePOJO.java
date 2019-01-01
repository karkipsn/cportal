package com.example.colors2web.clientportal.POJO.Users;

public class UserCreatePOJO {


    private String special_program;
    private String activated;
    private String first_name;
    private String last_name;
    private String email;
    private String password;
    private String password_confirm;

    public UserCreatePOJO(String special_program, String activated, String first_name, String last_name,
                          String email, String password, String password_confirm) {
        this.special_program = special_program;
        this.activated = activated;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.password = password;
        this.password_confirm = password_confirm;
    }

    public String getSpecial_program() {
        return special_program;
    }

    public void setSpecial_program(String special_program) {
        this.special_program = special_program;
    }

    public String getActivated() {
        return activated;
    }

    public void setActivated(String activated) {
        this.activated = activated;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword_confirm() {
        return password_confirm;
    }

    public void setPassword_confirm(String password_confirm) {
        this.password_confirm = password_confirm;
    }
}
