package comsci.kamonlphop.projectebook;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

public class DataBasebook extends AppCompatActivity {

    private LessonTABLE objLessonTABLE;
    private ListView data_baseListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_basebook);
        bindwidgit();
        Cdatabase();
        Clistview();
    }

    private void Clistview() {
        final String[] picdataString = objLessonTABLE.readLessondata(4);
        final String[] namedataStident = objLessonTABLE.readLessondata(1);

        //String[] picString = {"glrgwpogawaw"};
        //String[] nameStident = {"aawawfef"};
        final AdapterDatabase objAdapterDatabase = new AdapterDatabase(DataBasebook.this, picdataString, namedataStident);
        data_baseListView.setAdapter(objAdapterDatabase);
    }

    private void Cdatabase() {
        objLessonTABLE = new LessonTABLE(this);
    }

    private void bindwidgit() {
        data_baseListView = (ListView) findViewById(R.id.lisdatabase);
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////
    public void back(View view){
        finish();

    }
}
