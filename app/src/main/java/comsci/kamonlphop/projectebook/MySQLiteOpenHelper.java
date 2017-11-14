package comsci.kamonlphop.projectebook;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by USER on 14/11/2560.
 */

public class MySQLiteOpenHelper extends SQLiteOpenHelper {

    private final String TAG = getClass().getSimpleName();
    private SQLiteDatabase sqLiteDatabase;


    public static final String DataBase_Name = "Ebookcomscitest.db";
    public static final int DataBase_Version = 1;
    // student
    public static final String TABLE = "Student";
    public static final String ID_STUDENT = "ID_Student";
    public static final String NAME_STUDENT = "Name_Student";
    public static final String LASTNAME_STUDENT = "Lastname_Student";
    public static final String PASSWOD_STUDENT ="Password_Student";
    public static final String EMAIL_STUDENT = "Email_Student";
    public String CREATE_STUDENT ="create table "+TABLE+" ("+ID_STUDENT+" text primary key , "+NAME_STUDENT+" text, "+LASTNAME_STUDENT+" text, "+PASSWOD_STUDENT+" text, "+EMAIL_STUDENT+" text);";

    public static final String TABLE_B = "Teacher";
    public static final String ID_TEACHER = "ID_Teacher";
    public static final String NAME_TEACHER= "Name_Teacher";
    public static final String LASTNAME_TEACHER = "Lastname_Teacher";
    public static final String PASSWOD_TEACHER ="Password_Teacher";
    public static final String EMAIL_TEACHER= "Email_Teacher";
    public String CREATE_TEACHER ="create table "+TABLE_B+" ("+ID_TEACHER+" text primary key , "+NAME_TEACHER+" text, "+LASTNAME_TEACHER+" text, "+PASSWOD_TEACHER+" text, "+EMAIL_TEACHER+" text);";

    public static final String TABLE_C = "admin";
    public static final String ID_ADMIN = "ID_Admin";
    public static final String NAME_ADMIN= "Name_Admin";
    public static final String LASTNAME_ADMIN = "Lastname_Admin";
    public static final String PASSWOD_ADMIN="Password_Admin";
    public static final String EMAIL_ADMIN= "Email_Admin";
    public String CREATE_ADMIN ="create table "+TABLE_C+" ("+ID_ADMIN+" text primary key , "+NAME_ADMIN+" text, "+LASTNAME_ADMIN+" text, "+PASSWOD_ADMIN+" text, "+EMAIL_ADMIN+" text);";

    public static final String TABLE_D = "Lesson";
    public static final String ID_Lesson = "id_lesson";
    public static final String NAME_Lesson= "name_lesson";
    public static final String ID_teacher = "id_teacher";
    public static final String ID_FileBook = "id_filebook";
    public static final String picBook = "pic_lesson";
    public static final String majorBook = "major_lesson";
    public String CREATE_LESSON ="create table "+TABLE_D+" ("+ID_Lesson+" text primary key , "+NAME_Lesson+" text, "+ID_teacher+" text, "+ID_FileBook+" text, "+picBook+" text, "+majorBook+" text);";

    public static final String TABLE_E = "Filebook";
    public static final String id_filebook = "id_filebook";
    public static final String file_filebook= "file_filebook";
    public String CREATE_FILEBOOK ="create table "+TABLE_E+" ("+id_filebook+" text primary key , "+file_filebook+" text);";


    public MySQLiteOpenHelper(Context context) {
        super(context, DataBase_Name, null, DataBase_Version);

    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        Log.i(TAG, CREATE_STUDENT);
        Log.i(TAG, CREATE_TEACHER);
        Log.i(TAG, CREATE_ADMIN);
        Log.i(TAG, CREATE_LESSON);
        Log.i(TAG, CREATE_FILEBOOK);

        db.execSQL(CREATE_STUDENT);
        db.execSQL(CREATE_TEACHER);
        db.execSQL(CREATE_ADMIN);
        db.execSQL(CREATE_LESSON);
        db.execSQL(CREATE_FILEBOOK);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // copy เค้ามา อย่าสงสับ
        String DROP_FRIEND_TABLE = "DROP TABLE IF EXISTS " + TABLE;

        db.execSQL(DROP_FRIEND_TABLE);

        Log.i(TAG, "Upgrade Database from " + oldVersion + " to " + newVersion);

        onCreate(db);
    }
    //ยังติดปัญหาเรื่อง method
    public List<String> getFriendList() {
        List<String> ALLUSER = new ArrayList<String>();

        sqLiteDatabase = this.getWritableDatabase();

        Cursor cursor = sqLiteDatabase.query
                (TABLE, null, null, null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        while(!cursor.isAfterLast()) {

            ALLUSER.add(cursor.getLong(0) + " " +
                    cursor.getString(1) + " " +
                    cursor.getString(2));

            cursor.moveToNext();
        }

        sqLiteDatabase.close();

        return ALLUSER;
    }
    //
}
