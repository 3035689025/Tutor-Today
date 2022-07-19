package hkucs.hk.comp3330_login_page;

import android.app.DownloadManager;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    // database name
    private static final String databaseName = "TutorToday";

    // table names
    private static final String parent = "Parent";
    private static final String tutor = "Tutor";
    private static final String request = "Request";
    private static final String T2PMessage = "Tutor_to_parent_message";
    private static final String acceptedRequest = "Accepted_request";


    // column names for tutor
    private static final String tutor_col_username = "Tutor_username";
    private static final String tutor_col_password = "Tutor_password";
    private static final String tutor_col_email = "Tutor_email";
    private static final String tutor_col_selfIntroduction = "Self_introduction";
    private static final String tutor_col_qualifications = "Qualifications";
    private static final String tutor_col_profilePicID = "Profile_Pic_ID";



    // column names for parent
    private static final String parent_col_username = "Parent_username";
    private static final String parent_col_password = "Parent_password";
    private static final String parent_col_email = "Parent_email";


    // column name for request
    private static final String request_col_gender = "Gender";
    private static final String request_col_currentGrade = "Current_grade";
    private static final String request_col_lessonsPerWeek = "Lessons_per_week";
    private static final String request_col_specification = "Specification";
    private static final String request_col_salary = "Salary";
    private static final String request_col_district = "District";
    private static final String request_col_ongoing = "Ongoing";
    private static final String request_col_requestID = "Request_ID";
    private static final String request_col_viewCount = "View_count";
    private static final String request_col_subject = "Subject";
    private static final String request_col_applicationCount = "Number_of_applicants";
    private static final String request_col_lessonLength = "Lesson_Length";
    private static final String request_col_parentUsername = "Parent_username";


    // column name for T2P message
    private static final String T2P_col_parentUsername = "Parent_username";
    private static final String T2P_col_requestID = "Request_ID";
    private static final String T2P_col_subject = "Subject";
    private static final String T2P_col_tutorUsername = "Tutor_username";
    private static final String T2P_col_email = "Email";
    private static final String T2P_col_ongoing = "Ongoing";


    // column name for accepted request
    private static final String AR_col_requestID = "Request_ID";
    private static final String AR_col_tutorUsername = "Tutor_username";



    // create table tutor
    private static final String create_tutor =
            "CREATE TABLE " + tutor + " ( " +
                    tutor_col_email + " PRIMARY KEY, " +
                    tutor_col_username + " TEXT NOT NULL, " +
                    tutor_col_password + " TEXT NOT NULL, " +
                    tutor_col_selfIntroduction + " TEXT, " +
                    tutor_col_qualifications + " TEXT, " +
                    tutor_col_profilePicID + " TEXT ); ";


    // create table parent
    private static final String create_parent =
            "CREATE TABLE " + parent + " ( " +
                    parent_col_email + " PRIMARY KEY, " +
                    parent_col_username + " TEXT NOT NULL, " +
                    parent_col_password + " TEXT NOT NULL ); ";


    // create table request
    private static final String create_request =
            "CREATE TABLE " + request + " ( " +
                    request_col_requestID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    request_col_parentUsername + " TEXT NOT NULL, " +
                    request_col_gender + " TEXT NOT NULL, " +
                    request_col_currentGrade + " TEXT NOT NULL, " +
                    request_col_subject + " TEXT NOT NULL, " +
                    request_col_salary + " TEXT NOT NULL, " +
                    request_col_district + " TEXT NOT NULL, " +
                    request_col_lessonsPerWeek + " TEXT NOT NULL, " +
                    request_col_lessonLength + " TEXT NOT NULL, " +
                    request_col_ongoing + " TEXT NOT NULL, " +
                    request_col_viewCount + " TEXT NOT NULL, " +
                    request_col_applicationCount + " TEXT NOT NULL, " +
                    request_col_specification  + " TEXT NOT NULL );" ;


    // create table for T2P message
    private static final String create_T2P =
            "CREATE TABLE " + T2PMessage + " ( " +
                    T2P_col_parentUsername + " TEXT NOT NULL, " +
                    T2P_col_requestID + " INTEGER NOT NULL, " +
                    T2P_col_subject + " TEXT NOT NULL, " +
                    T2P_col_tutorUsername + " TEXT NOT NULL, " +
                    T2P_col_email + " TEXT NOT NULL, " +
                    T2P_col_ongoing + " TEXT NOT NULL, " +
                    "PRIMARY KEY ( " + T2P_col_tutorUsername +
                    ", " + T2P_col_requestID + " ));";


    // create table accepted request
    private static final String create_AR =
            "CREATE TABLE " + acceptedRequest + " ( " +
                    AR_col_requestID + " INTEGER PRIMARY KEY, " +
                    AR_col_tutorUsername + " TEXT );";

    public DatabaseHelper(Context context) {
        super(context, databaseName, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqliteDB){
        sqliteDB.execSQL(create_tutor);
        sqliteDB.execSQL(create_parent);
        sqliteDB.execSQL(create_request);
        sqliteDB.execSQL(create_T2P);
        sqliteDB.execSQL(create_AR);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqliteDB, int oldVersion, int newVersion){
        sqliteDB.execSQL("DROP TABLE IF EXISTS " + parent);
        sqliteDB.execSQL("DROP TABLE IF EXISTS " + tutor);
        sqliteDB.execSQL("DROP TABLE IF EXISTS " + request);
        sqliteDB.execSQL("DROP TABLE IF EXISTS " + T2PMessage);
        sqliteDB.execSQL("DROP TABLE IF EXISTS " + acceptedRequest);
        onCreate(sqliteDB);
    }


    public List<Request> getAllRequestTutorMessage(){
        SQLiteDatabase sqliteDB = getReadableDatabase();
        String[] columns = {
                request_col_requestID, request_col_parentUsername,
                request_col_gender, request_col_currentGrade,
                request_col_subject, request_col_salary,
                request_col_district, request_col_lessonsPerWeek,
                request_col_lessonLength, request_col_ongoing,
                request_col_viewCount, request_col_applicationCount,
                request_col_specification
        };

        Cursor cursor = sqliteDB.query(request,
                columns, null, null,
                null, null, null);


        List<Request> requestsList = new ArrayList<>();
        while(cursor.moveToNext()) {
            int requestID = cursor.getInt(0);
            String parentUsername = cursor.getString(1);
            int gender = cursor.getInt(2);
            String currentGrade = cursor.getString(3);
            int subject = cursor.getInt(4);
            int salary = cursor.getInt(5);
            String district = cursor.getString(6);
            int lessonPerWeek = cursor.getInt(7);
            String lessonLength = cursor.getString(8);
            boolean ongoing = cursor.getInt(9) > 0;
            int viewCount = cursor.getInt(10);
            int applicationCount = cursor.getInt(11);
            String specification = cursor.getString(12);

            Request request = new Request( requestID,
                    gender, currentGrade, lessonPerWeek,
                    specification, salary, lessonLength,
                    district, ongoing, viewCount,
                    subject, applicationCount, parentUsername
            );

            requestsList.add(request);
        }

        cursor.close();
        return requestsList;
    }

    //for getting all requests
    public List<Request> getAllRequest() {
        SQLiteDatabase sqliteDB = getReadableDatabase();
        String[] columns = {
                request_col_requestID, request_col_parentUsername,
                request_col_gender, request_col_currentGrade,
                request_col_subject, request_col_salary,
                request_col_district, request_col_lessonsPerWeek,
                request_col_lessonLength, request_col_ongoing,
                request_col_viewCount, request_col_applicationCount,
                request_col_specification
        };

        String selection = request_col_ongoing + " = ?;";
        String[] selectionArgs = {String.valueOf(1)};
        Cursor cursor = sqliteDB.query(request,
                columns, selection, selectionArgs,
                null, null, null);


        List<Request> requestsList = new ArrayList<>();
        while(cursor.moveToNext()) {
            int requestID = cursor.getInt(0);
            String parentUsername = cursor.getString(1);
            int gender = cursor.getInt(2);
            String currentGrade = cursor.getString(3);
            int subject = cursor.getInt(4);
            int salary = cursor.getInt(5);
            String district = cursor.getString(6);
            int lessonPerWeek = cursor.getInt(7);
            String lessonLength = cursor.getString(8);
            boolean ongoing = cursor.getInt(9) > 0;
            int viewCount = cursor.getInt(10);
            int applicationCount = cursor.getInt(11);
            String specification = cursor.getString(12);

            Request request = new Request( requestID,
                    gender, currentGrade, lessonPerWeek,
                    specification, salary, lessonLength,
                    district, ongoing, viewCount,
                    subject, applicationCount, parentUsername
            );

            requestsList.add(request);
        }

        cursor.close();
        return requestsList;

    }

    public List<Request> getAllRequestByParent(String passInUsername){
        SQLiteDatabase sqliteDB = getReadableDatabase();
        String[] columns = {
                request_col_requestID, request_col_parentUsername,
                request_col_gender, request_col_currentGrade,
                request_col_subject, request_col_salary,
                request_col_district, request_col_lessonsPerWeek,
                request_col_lessonLength, request_col_ongoing,
                request_col_viewCount, request_col_applicationCount,
                request_col_specification
        };

        String selection = request_col_ongoing + " = ?;";
        String[] selectionArgs = {String.valueOf(1)};
        Cursor cursor = sqliteDB.query(request,
                columns, selection, selectionArgs,
                null, null, null);


        List<Request> requestsList = new ArrayList<>();
        while(cursor.moveToNext()) {
            int requestID = cursor.getInt(0);
            String parentUsername = cursor.getString(1);
            int gender = cursor.getInt(2);
            String currentGrade = cursor.getString(3);
            int subject = cursor.getInt(4);
            int salary = cursor.getInt(5);
            String district = cursor.getString(6);
            int lessonPerWeek = cursor.getInt(7);
            String lessonLength = cursor.getString(8);
            boolean ongoing = cursor.getInt(9) > 0;
            int viewCount = cursor.getInt(10);
            int applicationCount = cursor.getInt(11);
            String specification = cursor.getString(12);

            if (parentUsername.equals(passInUsername)){
                Request request = new Request( requestID,
                        gender, currentGrade, lessonPerWeek,
                        specification, salary, lessonLength,
                        district, ongoing, viewCount,
                        subject, applicationCount, parentUsername
                );

                requestsList.add(request);
            }


        }

        cursor.close();
        return requestsList;

    }

    public Request findByRequestID(int passInRequestID){
        SQLiteDatabase sqliteDB = getWritableDatabase();
        String columns[] = {
                request_col_requestID, request_col_parentUsername,
                request_col_gender, request_col_currentGrade,
                request_col_subject, request_col_salary,
                request_col_district, request_col_lessonsPerWeek,
                request_col_lessonLength, request_col_ongoing,
                request_col_viewCount, request_col_applicationCount,
                request_col_specification
        };

        String selection = request_col_requestID + " = ?;";
        String[] selectionArgs = {String.valueOf(passInRequestID)};

        Cursor cursor = sqliteDB.query(request, columns, selection, selectionArgs,
                null,
                null,
                null);

        Request returnRequest = null;

        if (cursor.moveToNext()){
            int requestID = cursor.getInt(0);
            String parentUsername = cursor.getString(1);
            int gender = cursor.getInt(2);
            String currentGrade = cursor.getString(3);
            int subject = cursor.getInt(4);
            int salary = cursor.getInt(5);
            String district = cursor.getString(6);
            int lessonPerWeek = cursor.getInt(7);
            String lessonLength = cursor.getString(8);
            boolean ongoing = cursor.getInt(9) > 0;
            int viewCount = cursor.getInt(10);
            int applicationCount = cursor.getInt(11);
            String specification = cursor.getString(12);

            returnRequest = new Request( requestID,
                    gender, currentGrade, lessonPerWeek,
                    specification, salary, lessonLength,
                    district, ongoing, viewCount,
                    subject, applicationCount, parentUsername
            );

        }
        cursor.close();
        return returnRequest;
    }

    public Parent findByParentUsername(String passInUsername){
        SQLiteDatabase sqliteDB = getWritableDatabase();
        String columns[] = {
                parent_col_email,
                parent_col_username,
                parent_col_password
        };

        String selection = parent_col_username + " = ?;";
        String[] selectionArgs = {String.valueOf(passInUsername)};

        Cursor cursor = sqliteDB.query(parent, columns, selection, selectionArgs,
                null,
                null,
                null);

        Parent parent = null;

        if (cursor.moveToNext()){
            String email = cursor.getString(0);
            String username = cursor.getString(1);
            String password = cursor.getString(2);

            parent = new Parent(email, username, password);

        }
        cursor.close();

        return parent;
    }

    // for writing into the request table
    public long insertRequest(Request request){
        SQLiteDatabase sqliteDB = getWritableDatabase();
        ContentValues requestValue = new ContentValues();
        requestValue.put(request_col_parentUsername, request.getParent_username());
        requestValue.put(request_col_gender, request.getGender());
        requestValue.put(request_col_currentGrade, request.getCurrent_grade());
        requestValue.put(request_col_lessonsPerWeek, request.getLessons_per_week());
        requestValue.put(request_col_specification, request.getSpecifications());
        requestValue.put(request_col_salary, request.getSalary());
        requestValue.put(request_col_lessonLength, request.getLesson_length());
        requestValue.put(request_col_district, request.getDistrict());
        requestValue.put(request_col_ongoing, request.getOngoing());
        requestValue.put(request_col_viewCount, request.getView_count());
        requestValue.put(request_col_subject, request.getSubject());
        requestValue.put(request_col_applicationCount, request.getApplication_count());
        //requestValue.put(request_col_requestID, request.getRequest_ID());

        return sqliteDB.insert("Request", null, requestValue);
    }

    //for writing into the parent login table
    public long insertParent(Parent parent){
        SQLiteDatabase sqliteDB = getWritableDatabase();
        ContentValues parentValve = new ContentValues();
        parentValve.put(parent_col_email, parent.getEmail());
        parentValve.put(parent_col_username, parent.getUsername());
        parentValve.put(parent_col_password, parent.getPassword());

        return sqliteDB.insert("Parent", null, parentValve);
    }

    //for checking if parent and username are taken
    public int checkParentEmailUsername(String passInEmail, String passInUsername) {
        SQLiteDatabase sqliteDB = getReadableDatabase();
        String columns[] = {
                parent_col_email,
                parent_col_username,
                parent_col_password
        };

        Cursor cursor = sqliteDB.query(parent, columns,
                null,
                null,
                null,
                null,
                null);

        List<Parent> parentList = new ArrayList<>();

        while (cursor.moveToNext()){
            String email = cursor.getString(0);
            String username = cursor.getString(1);
            String password = cursor.getString(2);

            Parent parent = new Parent(email, username, password);

            parentList.add(parent);
        }

        cursor.close();

        //check email, username (0 = success, 1 = email invalid, 2 = username invalid)
        for (int i = 0; i < parentList.size(); i++){
            if (parentList.get(i).getEmail().equals(passInEmail)){
                return 1;
            }
            if (parentList.get(i).getUsername().equals(passInUsername)){
                return 2;
            }

        }
        return 0; //success
    }

    //check parent email login
    public String checkParentEmailPassword(String passInEmail, String passInPassword){

        SQLiteDatabase sqliteDB = getReadableDatabase();
        String columns[] = {
                parent_col_email,
                parent_col_username,
                parent_col_password
        };

        Cursor cursor = sqliteDB.query(parent, columns,
                null,
                null,
                null,
                null,
                null);

        List<Parent> parentList = new ArrayList<>();

        while (cursor.moveToNext()){
            String email = cursor.getString(0);
            String username = cursor.getString(1);
            String password = cursor.getString(2);

            Parent parent = new Parent(email, username, password);

            parentList.add(parent);
        }

        cursor.close();

        //check if login is valid (username = success,
        // " error email" = email not registered,
        // " error password" = email, password mismatch

        for (int i = 0; i < parentList.size(); i++){
            if (parentList.get(i).getEmail().equals(passInEmail)){
                if (parentList.get(i).getPassword().equals(passInPassword)){
                    return parentList.get(i).getUsername();
                }
                else{
                    return " error password";
                }
            }
        }

        return " error email";
    }

    // for writing into tutor table
    public long insertTutor(Tutor tutor){
        SQLiteDatabase sqliteDB = getWritableDatabase();
        ContentValues tutorValve = new ContentValues();
        tutorValve.put(tutor_col_email, tutor.getEmail());
        tutorValve.put(tutor_col_username, tutor.getUsername());
        tutorValve.put(tutor_col_password, tutor.getPassword());
        tutorValve.put(tutor_col_selfIntroduction, tutor.getSelf_introduction());
        tutorValve.put(tutor_col_qualifications, tutor.getQualifications());
        tutorValve.put(tutor_col_profilePicID, tutor.getProfile_pic_id());

        return sqliteDB.insert("Tutor", null, tutorValve);
    }

    //for tutor check email register
    public int checkTutorEmailUsername(String passInEmail, String passInUsername) {
        SQLiteDatabase sqliteDB = getReadableDatabase();
        String columns[] = {
                tutor_col_email,
                tutor_col_username,
                tutor_col_password,
                tutor_col_selfIntroduction,
                tutor_col_qualifications,
                tutor_col_profilePicID
        };

        Cursor cursor = sqliteDB.query(tutor, columns,
                null,
                null,
                null,
                null,
                null);

        List<Tutor> tutorList = new ArrayList<>();

        while (cursor.moveToNext()) {
            String email = cursor.getString(0);
            String username = cursor.getString(1);
            String password = cursor.getString(2);
            String selfIntroduction = cursor.getString(3);
            String qualifications = cursor.getString(4);
            int profilePicID = cursor.getInt(5);

            Tutor tutor = new Tutor(email, username, password,
                    selfIntroduction, qualifications, profilePicID);

            tutorList.add(tutor);
        }

        cursor.close();

        //check email, username (0 = success, 1 = email invalid, 2 = username invalid)
        for (int i = 0; i < tutorList.size(); i++) {
            if (tutorList.get(i).getEmail().equals(passInEmail)) {
                return 1;
            }
            if (tutorList.get(i).getUsername().equals(passInUsername)) {
                return 2;
            }

        }
        return 0; //success
    }


    //for check tutor email login
    public String checkTutorEmailPassword(String passInEmail, String passInPassword){

        SQLiteDatabase sqliteDB = getReadableDatabase();
        String columns[] = {
                tutor_col_email,
                tutor_col_username,
                tutor_col_password,
                tutor_col_selfIntroduction,
                tutor_col_qualifications,
                tutor_col_profilePicID
        };

        Cursor cursor = sqliteDB.query(tutor, columns,
                null,
                null,
                null,
                null,
                null);

        List<Tutor> tutorList = new ArrayList<>();

        while (cursor.moveToNext()){
            String email = cursor.getString(0);
            String username = cursor.getString(1);
            String password = cursor.getString(2);
            String selfIntroduction = cursor.getString(3);
            String qualifications = cursor.getString(4);
            int profilePicID = cursor.getInt(5);

            Tutor tutor = new Tutor(email, username, password,
                    selfIntroduction, qualifications, profilePicID);

            tutorList.add(tutor);
        }

        cursor.close();

        //check if login is valid (username = success,
        // " error email" = email not registered,
        // " error password" = email, password mismatch

        for (int i = 0; i < tutorList.size(); i++){
            if (tutorList.get(i).getEmail().equals(passInEmail)){
                if (tutorList.get(i).getPassword().equals(passInPassword)){
                    return tutorList.get(i).getUsername();
                }
                else{
                    return " error password";
                }
            }
        }

        return " error email";
    }


    //for getting a specific tutor
    public Tutor getTutor(String passInUsername){
        SQLiteDatabase sqliteDB = getWritableDatabase();
        String columns[] = {
                tutor_col_email,
                tutor_col_username,
                tutor_col_password,
                tutor_col_selfIntroduction,
                tutor_col_qualifications,
                tutor_col_profilePicID
        };

        String selection = tutor_col_username + " = ?;";
        String[] selectionArgs = {passInUsername};

        Cursor cursor = sqliteDB.query(tutor, columns, selection, selectionArgs,
                null,
                null,
                null);

        Tutor tutor = null;

        if (cursor.moveToNext()){
            String email = cursor.getString(0);
            String username = cursor.getString(1);
            String password = cursor.getString(2);
            String selfIntroduction = cursor.getString(3);
            String qualifications = cursor.getString(4);
            int profilePicID = cursor.getInt(5);

            tutor = new Tutor(email, username, password,
                    selfIntroduction, qualifications, profilePicID);

        }

        cursor.close();
        return tutor;
    }


    /*
    for updating tutor profile (edit tutor profile)
    checks with username
    return -1 if not successful

     */
    public int updateTutorProfile(Tutor tutor){
        SQLiteDatabase sqliteDB = getWritableDatabase();
        ContentValues tutorValve = new ContentValues();

        tutorValve.put(tutor_col_email, tutor.getEmail());
        tutorValve.put(tutor_col_username, tutor.getUsername());
        tutorValve.put(tutor_col_password, tutor.getPassword());
        tutorValve.put(tutor_col_selfIntroduction, tutor.getSelf_introduction());
        tutorValve.put(tutor_col_qualifications, tutor.getQualifications());
        tutorValve.put(tutor_col_profilePicID, tutor.getProfile_pic_id());

        String whereClause = tutor_col_username + " = ?;";
        String[] whereArgs = {tutor.getUsername()};
        return sqliteDB.update("Tutor", tutorValve, whereClause, whereArgs);
    }

    //view count ++
    public void updateViewCount(int requestID){
        SQLiteDatabase sqliteDB = getWritableDatabase();
        ContentValues values = new ContentValues();
        sqliteDB.execSQL("UPDATE " + request + " SET " +
                        request_col_viewCount + "=" + request_col_viewCount +
                        "+1" + " WHERE " + request_col_requestID + "=?",
                new String[] { String.valueOf(requestID) } );

    }

    //app count ++
    public void updateApplicationCount(int requestID){
        SQLiteDatabase sqliteDB = getWritableDatabase();
        ContentValues values = new ContentValues();
        sqliteDB.execSQL("UPDATE " + request + " SET " +
                        request_col_applicationCount + "=" + request_col_applicationCount +
                        "+1" + " WHERE " + request_col_requestID + "=?",
                new String[] { String.valueOf(requestID) } );

    }

    public long insertT2PMessage(Request passInRequest, String passInTUsername, String passInPEmail){
        SQLiteDatabase sqliteDB = getWritableDatabase();
        ContentValues T2Pvalue = new ContentValues();
        T2Pvalue.put(T2P_col_parentUsername, passInRequest.getParent_username());
        T2Pvalue.put(T2P_col_requestID, passInRequest.getRequest_ID());
        T2Pvalue.put(T2P_col_subject, passInRequest.getSubject());
        T2Pvalue.put(T2P_col_tutorUsername, passInTUsername);
        T2Pvalue.put(T2P_col_email, passInPEmail);
        T2Pvalue.put(T2P_col_ongoing, true);

        return sqliteDB.insert(T2PMessage, null, T2Pvalue);
    }


    public List<T2Pmessage> returnMessageByParentUsername(String passInParentUsername){
        SQLiteDatabase sqliteDB = getWritableDatabase();
        String[] columns = {
                T2P_col_parentUsername,
                T2P_col_requestID,
                T2P_col_subject,
                T2P_col_tutorUsername,
                T2P_col_email,
                T2P_col_ongoing
        };

        String selection = T2P_col_parentUsername + " = ?;";
        String[] selectionArgs = {passInParentUsername};
        Cursor cursor = sqliteDB.query(T2PMessage, columns, selection, selectionArgs,
                null, null, null);

        List<T2Pmessage> t2pmessageList = new ArrayList<>();

        while (cursor.moveToNext()) {
            String parentUsername = cursor.getString(0);
            int requestID = cursor.getInt(1);
            int subject = cursor.getInt(2);
            String tutorUsername = cursor.getString(3);
            String email = cursor.getString(4);
            boolean ongoing = cursor.getInt(5) > 0;

            if (ongoing == true){
                T2Pmessage t2pmessage = new T2Pmessage(parentUsername, requestID,
                        subject, tutorUsername, email, ongoing);

                t2pmessageList.add(t2pmessage);
            }



        }
        cursor.close();
        return t2pmessageList;

    }

    public Parent getParent(String passInUsername){
        // get the data by old username first
        SQLiteDatabase sqliteDB = getWritableDatabase();
        String columns[] = {
                parent_col_email,
                parent_col_username,
                parent_col_password
        };

        String selection = parent_col_username + " = ?;";
        String[] selectionArgs = {passInUsername};

        Cursor cursor = sqliteDB.query(parent, columns, selection, selectionArgs,
                null,
                null,
                null);

        Parent parent = null;

        if (cursor.moveToNext()){
            String email = cursor.getString(0);
            String username = cursor.getString(1);
            String password = cursor.getString(2);

            parent = new Parent(email, username, password);

        }
        cursor.close();
        return parent;
    }

    public void updateParentUsername(String passInEmail, String newUsername){
        SQLiteDatabase sqliteDB = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(parent_col_username, newUsername);
        sqliteDB.update(parent, values,
                parent_col_email + " = ?",
                new String[]{passInEmail});
    }

    public void updateTutorUsername(String passInEmail, String newUsername){
        SQLiteDatabase sqliteDB = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(tutor_col_username, newUsername);
        sqliteDB.update(tutor, values,
                tutor_col_email + " = ?",
                new String[]{passInEmail});
    }

    public void updateParentPassword(String passInEmail, String newPassword){
        SQLiteDatabase sqliteDB = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(parent_col_password, newPassword);
        sqliteDB.update(parent, values,
                parent_col_email + " = ?",
                new String[]{passInEmail});
    }

    public void updateTutorPassword(String passInEmail, String newPassword){
        SQLiteDatabase sqliteDB = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(tutor_col_password, newPassword);
        sqliteDB.update(tutor, values,
                tutor_col_email + " = ?",
                new String[]{passInEmail});
    }

    public void setOngoingTofalse4tutorMessages(int passInRequestID){
        SQLiteDatabase sqliteDB = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(T2P_col_ongoing, false);
        sqliteDB.update(T2PMessage, values,
                "Request_ID = ?", new String[]{String.valueOf(passInRequestID)});
    }

    public void setOngoingTofalse4Request(int requestID){
        SQLiteDatabase sqliteDB = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(request_col_ongoing, false);
        sqliteDB.update(request, values,
                request_col_requestID + " = ?",
                new String[]{String.valueOf(requestID)});
    }

    public long insertAcceptedRequest(int passInRequestID, String passInTutorUsername){
        SQLiteDatabase sqliteDB = getWritableDatabase();
        ContentValues ARValue = new ContentValues();
        ARValue.put(AR_col_requestID, passInRequestID);
        ARValue.put(AR_col_tutorUsername, passInTutorUsername);

        return sqliteDB.insert(acceptedRequest, null, ARValue);
    }


    public List<T2Pmessage> returnMessageByTutorUsername(String passInUsername){
        SQLiteDatabase sqliteDB = getWritableDatabase();
        String[] columns = {
                T2P_col_parentUsername,
                T2P_col_requestID,
                T2P_col_subject,
                T2P_col_tutorUsername,
                T2P_col_email,
                T2P_col_ongoing
        };

        String selection = T2P_col_tutorUsername + " = ?;";
        String[] selectionArgs = {passInUsername};
        Cursor cursor = sqliteDB.query(T2PMessage, columns, selection, selectionArgs,
                null, null, null);

        List<T2Pmessage> t2pmessageList = new ArrayList<>();

        while (cursor.moveToNext()) {
            String parentUsername = cursor.getString(0);
            int requestID = cursor.getInt(1);
            int subject = cursor.getInt(2);
            String tutorUsername = cursor.getString(3);
            String email = cursor.getString(4);
            boolean ongoing = cursor.getInt(5) > 0;


            T2Pmessage t2pmessage = new T2Pmessage(parentUsername, requestID,
                    subject, tutorUsername, email, ongoing);

            t2pmessageList.add(t2pmessage);


        }
        cursor.close();
        return t2pmessageList;
    }

    public List<AcceptedRequest> getAllAcceptedRequest(String passInTutorUsername) {

        // get the accepted request list first
        SQLiteDatabase sqliteDB = getWritableDatabase();
        String[] columns = {
                AR_col_requestID,
                AR_col_tutorUsername
        };

        String selection = AR_col_tutorUsername + " = ?;";
        String[] selectionArgs = {passInTutorUsername};
        Cursor cursor = sqliteDB.query(acceptedRequest, columns, selection, selectionArgs,
                null, null, null);

        List<AcceptedRequest> acceptedRequestList = new ArrayList<>();

        while (cursor.moveToNext()) {
            int requestID = cursor.getInt(0);
            String tutorUsername = cursor.getString(1);

            AcceptedRequest acceptedRequest = new AcceptedRequest(requestID, tutorUsername);

            acceptedRequestList.add(acceptedRequest);


        }
        cursor.close();
        return acceptedRequestList;
    }

    public AcceptedRequest getAcceptedRequestByRequestID(int passInRequestID){
        SQLiteDatabase sqliteDB = getWritableDatabase();
        String columns[] = {
                AR_col_requestID,
                AR_col_tutorUsername
        };

        String selection = AR_col_requestID + " = ?;";
        String[] selectionArgs = {String.valueOf(passInRequestID)};

        Cursor cursor = sqliteDB.query(acceptedRequest, columns, selection, selectionArgs,
                null,
                null,
                null);

        AcceptedRequest acceptedRequest = null;

        if (cursor.moveToNext()){
            int requestID = cursor.getInt(0);
            String tutorUsername = cursor.getString(1);

            acceptedRequest = new AcceptedRequest(requestID, tutorUsername);

        }
        cursor.close();
        return acceptedRequest;
    }

    //parent has requested before
    public boolean parentRequested(String passInParentUsername) {
        SQLiteDatabase sqliteDB = getWritableDatabase();
        String columns[] = {
                request_col_requestID, request_col_parentUsername,
                request_col_gender, request_col_currentGrade,
                request_col_subject, request_col_salary,
                request_col_district, request_col_lessonsPerWeek,
                request_col_lessonLength, request_col_ongoing,
                request_col_viewCount, request_col_applicationCount,
                request_col_specification
        };

        String selection = request_col_parentUsername + " = ?;";
        String[] selectionArgs = {passInParentUsername};

        Cursor cursor = sqliteDB.query(request, columns, selection, selectionArgs,
                null,
                null,
                null);

        List<Request> returnRequestList = new ArrayList<>();

        while (cursor.moveToNext()) {
            int requestID = cursor.getInt(0);
            String parentUsername = cursor.getString(1);
            int gender = cursor.getInt(2);
            String currentGrade = cursor.getString(3);
            int subject = cursor.getInt(4);
            int salary = cursor.getInt(5);
            String district = cursor.getString(6);
            int lessonPerWeek = cursor.getInt(7);
            String lessonLength = cursor.getString(8);
            boolean ongoing = cursor.getInt(9) > 0;
            int viewCount = cursor.getInt(10);
            int applicationCount = cursor.getInt(11);
            String specification = cursor.getString(12);

            Request returnRequest = new Request(requestID,
                    gender, currentGrade, lessonPerWeek,
                    specification, salary, lessonLength,
                    district, ongoing, viewCount,
                    subject, applicationCount, parentUsername
            );

            returnRequestList.add(returnRequest);

        }
        cursor.close();
        if (returnRequestList.size() > 0) {
            // has made request previously
            return true;
        } else {
            return false;
        }
    }

    //tutor has applied before
    public boolean tutorApplied(String passInTutorUsername){

        SQLiteDatabase sqliteDB = getWritableDatabase();
        String[] columns = {
                T2P_col_parentUsername,
                T2P_col_requestID,
                T2P_col_subject,
                T2P_col_tutorUsername,
                T2P_col_email,
                T2P_col_ongoing
        };

        String selection = T2P_col_tutorUsername + " = ?;";
        String[] selectionArgs = {passInTutorUsername};
        Cursor cursor = sqliteDB.query(T2PMessage, columns, selection, selectionArgs,
                null, null, null);

        List<T2Pmessage> t2pmessageList = new ArrayList<>();

        while (cursor.moveToNext()) {
            String parentUsername = cursor.getString(0);
            int requestID = cursor.getInt(1);
            int subject = cursor.getInt(2);
            String tutorUsername = cursor.getString(3);
            String email = cursor.getString(4);
            boolean ongoing = cursor.getInt(5) > 0;


            T2Pmessage t2pmessage = new T2Pmessage(parentUsername, requestID,
                    subject, tutorUsername, email, ongoing);

            t2pmessageList.add(t2pmessage);


        }
        cursor.close();
        if (t2pmessageList.size()>0){
            // has made request previously
            return true;
        }
        else{
            return false;
        }

    }


}
