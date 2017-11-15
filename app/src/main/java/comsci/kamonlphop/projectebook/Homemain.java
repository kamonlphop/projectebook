package comsci.kamonlphop.projectebook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Homemain extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homemain);
    }
    //////////////////////////////////  link go profile///////////////////////////////////////////
public  void cliprofile(View view){
    Intent profileIntent = new Intent(Homemain.this,Profile.class);
    startActivity(profileIntent);
}
/////////////////////////////////////// end profile ////////////////////////////////////////////////
    ///////////////////////////////// link  go ebook ///////////////////////////////////////////////
public void cliebook(View view){
    Intent ebookIntent = new Intent(Homemain.this,Menubook.class);
    startActivity(ebookIntent);

}
/////////////////////////////////////end ebook/////////////////////////////////////////////////////
    ////////////////////////////// link go download book////////////////////////////////////////////
    public void clidownload(View view){


        Intent downIntent = new Intent(Homemain.this,Downloadbook.class);
        startActivity(downIntent);
    }
//////////////////////////////////end download book////////////////////////////////////////////////
    ///////////////////////////// logout/////////////////////////////////////////////////////////////
    public void clilogout(View view){
        finish();
    }
    //////////////////////////////end logout///////////////////////////////////////////////////////
}
