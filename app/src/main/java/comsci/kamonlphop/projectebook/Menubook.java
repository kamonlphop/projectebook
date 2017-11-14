package comsci.kamonlphop.projectebook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Menubook extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menubook);
    }

    public void clinetwork(View view){
        Intent networkIntent = new Intent(Menubook.this,Networkbook.class);
        startActivity(networkIntent);

    }
    public void clidatabase(View view){
        Intent databaseIntent = new Intent(Menubook.this,DataBasebook.class);
        startActivity(databaseIntent);
    }
    public void climulti(View view){

        Intent multiIntent = new Intent(Menubook.this,Multibook.class);
        startActivity(multiIntent);
    }
    ////////////////////////////////////// back ////////////////////////////////////////////////////
    public void back(View view){

        finish();

    }
    /////////////////////////////////////end //////////////////////////////////////////////////////
}
