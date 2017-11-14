package comsci.kamonlphop.projectebook;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by USER on 14/11/2560.
 */

public class TeacherTABLE {
    private MySQLiteOpenHelper objMySQLiteOpenHelper;
    private SQLiteDatabase writeSqLiteDatabase , readSqLiteDatabase;

    public static final String TABLE_B = "Teacher";
    public static final String ID_TEACHER = "id_teacher";
    public static final String NAME_TEACHER= "name_teacher";
    public static final String LASTNAME_TEACHER = "lastname_teacher";
    public static final String PASSWOD_TEACHER ="password_teacher";
    public static final String EMAIL_TEACHER= "email_teacher";

    public TeacherTABLE(Context context){
        objMySQLiteOpenHelper = new MySQLiteOpenHelper(context);
        writeSqLiteDatabase = objMySQLiteOpenHelper.getWritableDatabase();
        readSqLiteDatabase = objMySQLiteOpenHelper.getReadableDatabase();
    }

    public long addteachernewmember (String strIDteacher , String strnameteacher, String strlastnameteacher, String strpassteacher , String stremailteacher){
        ContentValues objContentValues = new ContentValues();
        objContentValues.put(objMySQLiteOpenHelper.ID_TEACHER,strIDteacher);
        objContentValues.put(objMySQLiteOpenHelper.NAME_TEACHER,strnameteacher);
        objContentValues.put(objMySQLiteOpenHelper.LASTNAME_TEACHER,strlastnameteacher);
        objContentValues.put(objMySQLiteOpenHelper.PASSWOD_TEACHER,strpassteacher);
        objContentValues.put(objMySQLiteOpenHelper.EMAIL_TEACHER,stremailteacher);
        return readSqLiteDatabase.insert(objMySQLiteOpenHelper.TABLE_B,null,objContentValues);

    }
    public String[] searchteacherpassword(String usernameString){
        try{
            String[] strResult = null;
            Cursor objCursor = readSqLiteDatabase.query(TABLE_B ,
                    new String[]{ID_TEACHER,NAME_TEACHER,LASTNAME_TEACHER,PASSWOD_TEACHER,EMAIL_TEACHER},
                    NAME_TEACHER +"=?",new String[]{String.valueOf(usernameString)},
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
}