package comsci.kamonlphop.projectebook;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by USER on 14/11/2560.
 */

public class Filebook {
    private MySQLiteOpenHelper objMySQLiteOpenHelper;
    private SQLiteDatabase writeSqLiteDatabase , readSqLiteDatabase;

    public static final String TABLE_E = "Filebook";
    public static final String id_filebook = "id_filebook";
    public static final String file_filebook= "file_filebook";

    public Filebook(Context context){
        objMySQLiteOpenHelper = new MySQLiteOpenHelper(context);
        writeSqLiteDatabase = objMySQLiteOpenHelper.getWritableDatabase();
        readSqLiteDatabase = objMySQLiteOpenHelper.getReadableDatabase();
    }

    public long addfilenewmember (String stridfile , String strfilebook){
        ContentValues objContentValues = new ContentValues();

        objContentValues.put(objMySQLiteOpenHelper.id_filebook,stridfile);
        objContentValues.put(objMySQLiteOpenHelper.file_filebook,strfilebook);

        return readSqLiteDatabase.insert(objMySQLiteOpenHelper.TABLE_E,null,objContentValues);

    }
    public String[] searchteacherpassword(String usernameString){
        try{
            String[] strResult = null;
            Cursor objCursor = readSqLiteDatabase.query(TABLE_E ,
                    new String[]{id_filebook,file_filebook,},

                    file_filebook +"=?",new String[]{String.valueOf(usernameString)},
                    null,null,null,null);
            if (objCursor !=null){
                if (objCursor.moveToFirst()){
                    strResult = new String[4];
                    for(int i = 0;i<4;i++ ){
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

