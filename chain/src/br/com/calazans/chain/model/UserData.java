package br.com.calazans.chain.model;

public class UserData {

    private String password;

    private String role;

    public UserData(String password) {
        this.password = password;
    }

    public UserData(String password, String role) {
        this.password = password;
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }
}
