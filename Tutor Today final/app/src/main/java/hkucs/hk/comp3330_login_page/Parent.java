package hkucs.hk.comp3330_login_page;

public class Parent {
    private String email;
    private String username;
    private String password;

    public Parent(String email, String username, String password){
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public void setEmail(String email) {this.email = email;}

    public String getEmail() {return this.email;}

    public void setUsername(String username) {this.username = username;}

    public String getUsername() {return this.username;}

    public void setPassword(String password) {this.password = password;}

    public String getPassword() {return  this.password;}
}
