package comsci.kamonlphop.projectebook;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by USER on 14/11/2560.
 */

public class StudentTABLE {

    private MySQLiteOpenHelper objMySQLiteOpenHelper;
    private SQLiteDatabase writeSqLiteDatabase, readSqLiteDatabase;

    public static final String TABLE= "student";
    public static final String ID_STUDENT = "id_student";
    public static final String NAME_STUDENT= "name_student";
    public static final String LASTNAME_STUDENT = "lastname_student";
    public static final String PASSWOD_STUDENT="password_student";
    public static final String EMAIL_STUDENT= "email_student";


    public StudentTABLE(Context context){
        objMySQLiteOpenHelper = new MySQLiteOpenHelper(context);
        writeSqLiteDatabase = objMySQLiteOpenHelper.getWritableDatabase();
        readSqLiteDatabase = objMySQLiteOpenHelper.getReadableDatabase();
    }//constructor

    public String[] searchUserpassword(String usernameString){
        try{
            String[] strResult = null;
            Cursor objCursor = readSqLiteDatabase.query(TABLE ,
                    new String[]{ID_STUDENT,NAME_STUDENT,LASTNAME_STUDENT,PASSWOD_STUDENT,EMAIL_STUDENT},
                    ID_STUDENT +"=?"
                    ,new String[]{String.valueOf(usernameString)},
                    null,null,null,null);
            if (objCursor !=null){
                if (objCursor.moveToFirst()){
                    strResult = new String[5];
                    for(int i = 0;i<5;i++ ){
                        strResult[i] = objCursor.getString(i);
                    }
                }
            }
            objCursor.close();
            return strResult;
        }catch (Exception e){
            return null;

        }
        // return new String[0];

    }//end searchUserpassword

    public long addNewstudent(String IDstudent ,String strName, String strLastname, String strPassword, String strEmail){
        ContentValues objContentValues = new ContentValues();
        objContentValues.put(ID_STUDENT,IDstudent);
        objContentValues.put(NAME_STUDENT,strName);
        objContentValues.put(LASTNAME_STUDENT,strLastname);
        objContentValues.put(PASSWOD_STUDENT,strPassword);
        objContentValues.put(EMAIL_STUDENT,strEmail);
        return readSqLiteDatabase.insert(TABLE,null,objContentValues);

    }//end add student
}//endstudent

