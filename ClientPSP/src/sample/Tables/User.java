package sample.Tables;

import java.io.Serializable;

public class User implements Serializable {
    private String userName;
    private String login;
    private String password;

    public User(String userName, String login, String password) {
        this.userName = userName;
        this.login = login;
        this.password = password;
    }

    public User(User user){
        this.userName=user.userName;
        this.login=user.login;
        this.password=user.password;
    }

    public User(String login, String password){
        this.login = login;
        this.password= password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
