package hkucs.hk.comp3330_login_page;

public class Tutor {
    private String email;
    private String username;
    private String password;
    private String self_introduction;
    private String qualifications;
    private int profile_pic_id;

    public Tutor(String email, String username, String password, String self_introduction, String qualifications, int profile_pic_id){
        this.email = email;
        this.username = username;
        this.password = password;
        this.self_introduction = self_introduction;
        this.qualifications = qualifications;
        this.profile_pic_id = profile_pic_id;
    }

    public Tutor(String email, String username, String password){
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return this.email;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public String getUsername() {
        return this.username;
    }

    public void setPassword(String password){
        this.password =password;
    }

    public String getPassword() {
        return this.password;
    }

    public void setSelf_introduction(String self_introduction) {
        this.self_introduction = self_introduction;
    }

    public String getSelf_introduction() {
        return self_introduction;
    }

    public void setQualifications(String qualifications) {
        this.qualifications = qualifications;
    }

    public String getQualifications() {
        return qualifications;
    }

    public void setProfile_pic_id(int profile_pic_id){this.profile_pic_id = profile_pic_id;}

    public int getProfile_pic_id() {return profile_pic_id;}
}
