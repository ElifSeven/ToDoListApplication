package model;


import javax.persistence.*;

@Entity
@Table(name = "login")
public class User {


    @SequenceGenerator(name = "generator", sequenceName = "LOGIN_SEQ_ID")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "generator")
    @Column
    private int userId;


    @Column(name = "userName")
    private String userName;


    @Column(name = "password")
    private String password;

    @Column(name = "eMail")
    private String eMail;


    public int getUserId() {
        return userId;
    }

    public void setUserId(int id) {
        this.userId = id;
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

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + userId +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", eMail='" + eMail + '\'' +
                '}';
    }
}