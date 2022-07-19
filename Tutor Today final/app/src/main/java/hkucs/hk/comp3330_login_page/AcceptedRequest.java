package hkucs.hk.comp3330_login_page;

public class AcceptedRequest {

    private int accepted_request_ID;
    private String accepted_tutor_username;

    public AcceptedRequest(int accepted_request_ID, String accepted_tutor_username){
        this.accepted_request_ID = accepted_request_ID;
        this.accepted_tutor_username = accepted_tutor_username;
    }

    public void setAccepted_request_ID (int accepted_request_ID) {
        this.accepted_request_ID = accepted_request_ID;
    }

    public int getAccepted_request_ID () {
        return this.accepted_request_ID;
    }

    public void setAccepted_tutor_username (String accepted_tutor_username) {
        this.accepted_tutor_username = accepted_tutor_username;
    }

    public String getAccepted_tutor_username () {
        return this.accepted_tutor_username;
    }


}
