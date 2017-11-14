package comsci.kamonlphop.projectebook;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {
    private StudentTABLE objStudentTABLE;
    private TeacherTABLE objTeacherTABLE;
    private AdminTABLE objAdminTABLE;
    private LessonTABLE objLessonTABLE;
    private Filebook objFilebook ;
    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String NameMem = "nameKey";
    public static final String Lastname = "Lastname";
    public static final String Email = "Email";
    SharedPreferences sharedpreferences;


    //public TeacherTABLE objTeacherTABLE;
    //public StatisticsTABLE objStatisticsTABLE;

    EditText usernameEditText,passwordEditText;
    //String usernameString , passwordString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        connect();
        synJSONtoSQL();



    }//endactivity



    private void synJSONtoSQL() {
        StrictMode.ThreadPolicy myPolicy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(myPolicy);

        int intTimes = 0;
        while(intTimes <=4){
            InputStream objInputStream = null;
            String strJSON = null;
            String strstudent =  "http://5711020660038.sci.dusit.ac.th/student.php";
            String strteacher = "http://5711020660038.sci.dusit.ac.th/teacher.php";
            String stradmin = "http://5711020660038.sci.dusit.ac.th/admin.php";
            String strlesson = "http://5711020660038.sci.dusit.ac.th/lesson.php";
            String strfilebook = "http://5711020660038.sci.dusit.ac.th/filebook.php";
            HttpPost objhttpPost= null;

            try {
                HttpClient objHttpClient = new DefaultHttpClient();
                switch (intTimes) {
                    case 0:
                        objhttpPost = new HttpPost(strstudent);
                        break;
                    case 1:
                        objhttpPost = new HttpPost(strteacher);
                        break;
                    case 2:
                        objhttpPost = new HttpPost(stradmin);
                        break;
                    case 3 :
                        objhttpPost = new HttpPost(strlesson);
                        break;
                    default:
                        objhttpPost = new HttpPost(strfilebook);
                        break;

                }
                HttpResponse objHttpResponse = objHttpClient.execute(objhttpPost);
                HttpEntity objHttpEntity = objHttpResponse.getEntity();
                objInputStream = objHttpEntity.getContent();

            } catch (Exception e) {
                Log.d("ebook","InputStream ==>"+e.toString());
            }
            try{
                InputStreamReader objInputStreamReader = new InputStreamReader(objInputStream,"UTF-8");
                BufferedReader objBufferedReader = new BufferedReader(objInputStreamReader);
                StringBuilder objStringBuilder = new StringBuilder();
                String strLine = null;
                while((strLine = objBufferedReader.readLine()) != null){
                    objStringBuilder.append(strLine);
                }
                objInputStream.close();
                strJSON = objStringBuilder.toString();
            }catch (Exception e){
                Log.d("str","strJSON"+e.toString());
            }
            try{
                JSONArray objJsonArray = new JSONArray(strJSON);
                for(int i =0;i<objJsonArray.length();i++){
                    JSONObject jsonObject = objJsonArray.getJSONObject(i);
                    switch (intTimes){
                        case 0:
                            String IDstudent = jsonObject.getString("id_student");
                            String strName = jsonObject.getString("name_student");
                            String strLastname = jsonObject.getString("lastname_student");
                            String strPassword = jsonObject.getString("password_student");
                            String strEmail = jsonObject.getString("email_student");
                            objStudentTABLE.addNewstudent(IDstudent,strName,strLastname,strPassword,strEmail);
                            break;
                        case 1:
                            String strIDteacher = jsonObject.getString("id_teacher");
                            String strnameteacher = jsonObject.getString("name_teacher");
                            String strlastnameteacher = jsonObject.getString("lastname_teacher");
                            String strpassteacher = jsonObject.getString("password_teacher");
                            String stremailteacher = jsonObject.getString("email_teacher");
                            objTeacherTABLE.addteachernewmember(strIDteacher,strnameteacher,strlastnameteacher,strpassteacher,stremailteacher);
                            break;
                        case 2:
                            String strIDadmin = jsonObject.getString("id_admin");
                            String strnameadmin = jsonObject.getString("name_admin");
                            String strlastnameadmin = jsonObject.getString("lastname_admin");
                            String strpassadmin = jsonObject.getString("password_admin");
                            String stremailadmin = jsonObject.getString("email_admin");
                            objAdminTABLE.addadminnewmember(strIDadmin,strnameadmin,strlastnameadmin,strpassadmin,stremailadmin);
                            break;
                        case 3:
                            String strIDlesson = jsonObject.getString("id_lesson");
                            String strnamelesson = jsonObject.getString("name_lesson");
                            String strIDEbook = jsonObject.getString("id_teacher");
                            String stridfilelessbook = jsonObject.getString("id_filebook");
                            String strpicbook = jsonObject.getString("pic_lesson");
                            String strmajorbook = jsonObject.getString("major_lesson");


                            objLessonTABLE.addlessonnewmember(strIDlesson,strnamelesson,strIDEbook,stridfilelessbook,strpicbook,strmajorbook);
                            break;

                        default:
                            String stridfilebook = jsonObject.getString("id_filebook");
                            String strfilebook2 = jsonObject.getString("file_filebook");

                            objFilebook.addfilenewmember(stridfilebook,strfilebook2);
                            break;
                    }
                }

            } catch(Exception e){
                Log.d("str","strJSON"+e.toString());
            }
            intTimes +=1;
        }

    }




    //login
    public void clicklogin(View view){


        usernameEditText = (EditText) findViewById(R.id.username);
        passwordEditText = (EditText) findViewById(R.id.password);

        String usernameString=usernameEditText.getText().toString().trim();
        String passwordString=passwordEditText.getText().toString().trim();
        String name1 = null;
        String lastname1 = null;
        String email1 = null;

        if(usernameString.equals("")||passwordString.equals("")){
            Toast.makeText(getApplicationContext(),"กรุณากรอกให้ครบ", Toast.LENGTH_SHORT).show();
        }else{/*
            Intent intent = new Intent(MainActivity.this,homeebook.class);
            Toast.makeText(getApplicationContext(),"ยินดีต้อนรับเข้าสู้ระบบ",Toast.LENGTH_SHORT).show();
            startActivity(intent);*/

            checkUserpassword(usernameString,passwordString,name1,lastname1,email1);

        }


    }

    private void checkUserpassword(String usernameString, String passwordString, String name1, String lastname1, String email1) {
        try{
            String[] strMyResult = objStudentTABLE.searchUserpassword(usernameString);

            if (passwordString.equals(strMyResult[3])){
                name1 = strMyResult[1];
                lastname1 = strMyResult[2];
                email1 = strMyResult[4];
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putString(NameMem, name1);
                editor.putString(Lastname, lastname1);
                editor.putString(Email, email1);
                editor.commit();
                Intent intent = new Intent(MainActivity.this,Homemain.class);
                startActivity(intent);
                //passwordtrue
                Toast.makeText(getApplicationContext(),"ยินตอนรับเข้าสู้ระบบ",Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(getApplicationContext(),"Pasword false",Toast.LENGTH_SHORT).show();
            }



        }catch (Exception e){

            Toast.makeText(getApplicationContext(),"ไม่มี"+usernameString+"ในฐานข้อข้อมูลเรา",Toast.LENGTH_SHORT).show();
        }

    }

    //endlogin
    /*private void  checkUserpassword(String usernameString,String passwordString,String name1){

        try{
            String[] strMyResult = objStudentTABLE.searchUserpassword(usernameString);
            if (passwordString.equals(strMyResult[3])){
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putString(NameMem, strname);
                editor.putString(idMem, id);
                editor.commit();
                Intent intent = new Intent(MainActivity.this,Homemain.class);
                startActivity(intent);
                //passwordtrue
                Toast.makeText(getApplicationContext(),"ยินตอนรับเข้าสู้ระบบ",Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(getApplicationContext(),"Pasword false",Toast.LENGTH_SHORT).show();
            }



        }catch (Exception e){

            Toast.makeText(getApplicationContext(),"ไม่มี"+usernameString+"ในฐานข้อข้อมูลเรา",Toast.LENGTH_SHORT).show();
        }

    }//end checkUserpassword*/
    private void connect(){
        // objTeacherTABLE = new TeacherTABLE(this);
        // objStatisticsTABLE = new StatisticsTABLE(this);
        objStudentTABLE = new StudentTABLE(this);
        objTeacherTABLE = new TeacherTABLE(this);
        objAdminTABLE = new AdminTABLE(this);
        objLessonTABLE = new LessonTABLE(this);
        objFilebook = new Filebook(this);
    }

}//endclass


