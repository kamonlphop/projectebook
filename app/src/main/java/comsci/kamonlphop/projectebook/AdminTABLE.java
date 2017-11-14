package comsci.kamonlphop.projectebook;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by USER on 14/11/2560.
 */

public class AdminTABLE {
    private MySQLiteOpenHelper objMySQLiteOpenHelper;
    private SQLiteDatabase writeSqLiteDatabase , readSqLiteDatabase;

    public static final String TABLE_C = "admin";
    public static final String ID_ADMIN = "id_admin";
    public static final String NAME_ADMIN= "name_admin";
    public static final String LASTNAME_ADMIN = "lastname_admin";
    public static final String PASSWOD_ADMIN="password_admin";
    public static final String EMAIL_ADMIN= "email_admin";

    public AdminTABLE(Context context){
        objMySQLiteOpenHelper = new MySQLiteOpenHelper(context);
        writeSqLiteDatabase = objMySQLiteOpenHelper.getWritableDatabase();
        readSqLiteDatabase = objMySQLiteOpenHelper.getReadableDatabase();
    }

    public long addadminnewmember (String strIDadmin , String strnameadmin, String strlastnameadmin, String strpassadmin , String stremailadmin){
        ContentValues objContentValues = new ContentValues();
        objContentValues.put(objMySQLiteOpenHelper.ID_ADMIN,strIDadmin);
        objContentValues.put(objMySQLiteOpenHelper.NAME_ADMIN,strnameadmin);
        objContentValues.put(objMySQLiteOpenHelper.LASTNAME_ADMIN,strlastnameadmin);
        objContentValues.put(objMySQLiteOpenHelper.PASSWOD_ADMIN,strlastnameadmin);
        objContentValues.put(objMySQLiteOpenHelper.EMAIL_ADMIN,stremailadmin);
        return readSqLiteDatabase.insert(objMySQLiteOpenHelper.TABLE_C,null,objContentValues);

    }
    public String[] searchadminpassword(String usernameString){
        try{
            String[] strResult = null;
            Cursor objCursor = readSqLiteDatabase.query(TABLE_C ,
                    new String[]{ID_ADMIN,NAME_ADMIN,LASTNAME_ADMIN,PASSWOD_ADMIN,EMAIL_ADMIN},
                    NAME_ADMIN +"=?",new String[]{String.valueOf(usernameString)},
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
