package com.muchunguzi.dictsupport;

/**
 * Created by muchunguzi on 9/28/17.
 */

public class Constants {


    //handling database connections
    public static final String DB_URL = "https://dictsupport.000webhostapp.com/connect/";

    public static final String REGISTRE_URL = DB_URL + "register.php";
    public static final String UPLOAD_URL = DB_URL + "upload.php";
    public static final String RECOVER_PASS_URL = DB_URL + "recover_pass.php";
    public static final String RECOVER_UPDATE_URL = DB_URL + "change_pass.php";
    public static final String REPLY_SMS = DB_URL + "reply_sms.php";
    public static final String MY_CHART = DB_URL + "my_chart.php";
    public static final String ROWS = DB_URL + "rows.php";
    public static final String REPLIED_MY_CHART = DB_URL + "my_replied_sms.php";
    public static final String REPLIED_ROWS = DB_URL + "replied_rows.php";
    public static final String MY_CHART_IT = DB_URL + "my_chart_it.php";
    public static final String ROWS_IT = DB_URL + "rows_it.php";

    public static final String DOWNLOAD_URL = DB_URL + "download.php";
    public static final String LOGIN_URL = DB_URL + "login.php";
    public static final String RETRIVE_URL = DB_URL + "retrive.php";
    public static final String ROWS_URL = DB_URL + "rows.php";
    public static final String RETRIVE_SPECIFIC_URL = DB_URL + "specific_retrive.php";
    public static final String ROWS_SPECIFIC_URL = DB_URL + "rows_specific.php";
    public static final String UPLOAD_ANNOUNCEMENT_URL = DB_URL + "upload_announcements.php";
    public static final String REGISTER_STAFF = DB_URL + "register_staffs.php";
    public static final String CHANGE_PASS_URL = DB_URL + "change_pass.php";
    public static final String ACCESS_ALLOW_REVKE = DB_URL + "revoke_allow_access.php";
    public static final String RETRIVE_ALL_ANNOUNCEMENTS = DB_URL + "retrive_all.php";
    public static final String RETRIVE_STAFFS = DB_URL + "retrive_staff.php";
    public static final String RETRIVE_STUDENTS = DB_URL + "retrive_students.php";
    public static final String RETRIVE_NAME = DB_URL + "retrive_name.php";
    public static final String ADMIN_CHANGE_PASS = DB_URL + "admin_change_pass.php";
    public static final String RETRIVE_MY_ANNOUNCEMENTS = DB_URL + "retrive_my_announcements.php";

    //handling computer science announcements
    public static final String RETRIVE_CS1 = DB_URL + "retrive_cs1.php";
    public static final String RETRIVE_CS2 = DB_URL + "retrive_cs2.php";
    public static final String RETRIVE_CS3 = DB_URL + "retrive_cs3.php";

    //handling password & reg_number comfirmation
    public static final String KEY_REG_NUMBER = "username";
    public static final String KEY_USER_STATUS = "status";
    public static final String KEY_USER_COURSE = "course";
    public static final String KEY_PASS = "password";
    public static final String KEY_NAME = "name";
    public static final String KEY_DEPARTMENT = "department";
    public static final String KEY_ROOM = "room";
    public static final String KEY_BLOCK = "block";
    public static final String KEY_REC_QN = "recovery_qn";
    public static final String KEY_REC_ANS = "recovery_ans";
    public static final String KEY_ACCESS = "access";

    public static final String KEY_TO = "to";
    public static final String KEY_FROM = "from1";
    public static final String KEY_MESSAGE = "message";
    public static final String KEY_STATUS_REPLY = "status";
    public static final String KEY_ID_MASSAGE = "id_message";


    //handling password change
    public static final String KEY_CURRENT_PASS = "password";
    public static final String KEY_NEW_PASS ="password_new";

    //handling registration of new staff
    public static final String KEY_EMP_NAME = "name";
    public static final String KEY_EMP_NUMBER = "emp_number";
    public static final String KEY_EMP_PASS = "password";

    //handling announcements contents
    public static final String KEY_TITLE = "title";
    public static final String KEY_CONTENT = "contents";
    public static final String KEY_PUBLISHER = "publisher";
    public static final String KEY_CATEGORY = "category";
    public static final String KEY_COURSE = "course";


}
