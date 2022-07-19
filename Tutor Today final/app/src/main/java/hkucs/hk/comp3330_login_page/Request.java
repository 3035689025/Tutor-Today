package hkucs.hk.comp3330_login_page;

public class Request {

    private int request_ID;
    private int gender; // boy / girl / others
    private String current_grade;
    private int lessons_per_week;
    private String specifications;
    private int salary; //per hour
    private String lesson_length;
    private String district;
    private boolean ongoing;
    private int view_count;
    private int subject;
    private int application_count;
    private String parent_username;

    //constructor without ID
    public Request( int gender, String current_grade, int lessons_per_week, String specifications, int salary, String lesson_length, String district, boolean ongoing, int view_count, int subject, int application_count, String parent_username){
        this.request_ID = 0;
        this.gender = gender;
        this.current_grade = current_grade;
        this.lessons_per_week = lessons_per_week;
        this.specifications = specifications;
        this.salary = salary;
        this.lesson_length = lesson_length;
        this.district = district;
        this.ongoing = ongoing;
        this.view_count = view_count;
        this.subject = subject;
        this.application_count = application_count;
        this.parent_username = parent_username;
    }

    //constructor with ID
    public Request( int request_ID,  int gender, String current_grade, int lessons_per_week, String specifications, int salary, String lesson_length, String district, boolean ongoing, int view_count, int subject, int application_count, String parent_username){
        this.request_ID = request_ID;
        this.gender = gender;
        this.current_grade = current_grade;
        this.lessons_per_week = lessons_per_week;
        this.specifications = specifications;
        this.salary = salary;
        this.lesson_length = lesson_length;
        this.district = district;
        this.ongoing = ongoing;
        this.view_count = view_count;
        this.subject = subject;
        this.application_count = application_count;
        this.parent_username = parent_username;
    }


    public void setRequest_ID( int id) {this.request_ID = id; }

    public int getRequest_ID() {return request_ID; }

    public void setGender( int gender) {this.gender = gender; }

    public int getGender() {return gender;}

    public void setCurrent_grade(String grade) {this.current_grade = grade;}

    public String getCurrent_grade() {return current_grade;}

    public void setLessons_per_week (int lessons) {this.lessons_per_week = lessons;}

    public int getLessons_per_week () {return lessons_per_week;}

    public void setSpecifications (String specifications) {this.specifications = specifications;}

    public String getSpecifications() {return specifications;}

    public void setSalary (int salary) {this.salary = salary;}

    public int getSalary () {return this.salary;}

    public void setLesson_length(String lesson_length) {this.lesson_length = lesson_length;}

    public String getLesson_length() {return this.lesson_length;}

    public void setDistrict (String district) { this.district = district;}

    public String getDistrict () {return this.district;}

    public void setOngoing (boolean ongoing) {this.ongoing = ongoing;}

    public boolean getOngoing () {return this.ongoing;}

    public void setView_count(int view_count) {this.view_count = view_count;}

    public int getView_count () {return view_count;}

    public void setSubject (int subject) {this.subject = subject;}

    public int getSubject () {return this.subject;}

    public void setApplication_count(int application_count) { this.application_count = application_count; }

    public int getApplication_count() { return application_count; }

    public void setParent_username(String username) { this.parent_username = username; }

    public String getParent_username () {return this.parent_username;}
}
