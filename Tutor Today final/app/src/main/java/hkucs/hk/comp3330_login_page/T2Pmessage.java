package hkucs.hk.comp3330_login_page;

public class T2Pmessage {

    private String parent_username;
    private int T2Prequest_ID;
    private int T2Psubject;
    private String tutor_username;
    private String email;
    private boolean ongoing;

    public T2Pmessage(String parent_username, int T2Prequest_ID, int T2Psubject, String tutor_username, String email, boolean ongoing){
        this.parent_username = parent_username;
        this.T2Prequest_ID = T2Prequest_ID;
        this.T2Psubject = T2Psubject;
        this.tutor_username = tutor_username;
        this.email = email;
        this.ongoing = ongoing;
    }

    public void setParent_username (String parent_username) {
        this.parent_username = parent_username;
    }

    public String getParent_username () {
        return this.parent_username;
    }

    public void setT2PRequest_ID (int T2Prequest_ID) {
        this.T2Prequest_ID = T2Prequest_ID;
    }

    public int getT2PRequest_ID () { return this.T2Prequest_ID; }

    public void setT2PSubject (int T2Psubject){
        this.T2Psubject = T2Psubject;
    }

    public int getT2PSubject() {
        return this.T2Psubject;
    }

    public void setTutor_username( String tutor_username){
        this.tutor_username = tutor_username;
    }

    public String getTutor_username () {
        return this.tutor_username;
    }

    public void setEmail (String email) {
        this.email = email;
    }

    public String getEmail (){
        return this.email;
    }

    public void setOngoing (boolean ongoing) {
        this.ongoing = ongoing;
    }

    public boolean getOngoing() {
        return this.ongoing;
    }

}
