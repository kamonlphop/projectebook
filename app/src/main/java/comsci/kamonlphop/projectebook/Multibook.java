package comsci.kamonlphop.projectebook;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

public class Multibook extends AppCompatActivity {

    private LessonTABLE objLessonTABLE;
    private ListView multiListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multibook);    bindwidgit();
        Cdatabase();
        Clistview();
    }

    private void Clistview() {
        final String[] picmultiString = objLessonTABLE.readLessonmulti(4);
        final String[] namemultiStident = objLessonTABLE.readLessonmulti(1);

        //String[] picString = {"glrgwpogawaw"};
        //String[] nameStident = {"aawawfef"};
        final AdapterNetwork objAdapterNetwork = new AdapterNetwork(Multibook.this, picmultiString, namemultiStident);
        multiListView.setAdapter(objAdapterNetwork);
    }

    private void Cdatabase() {
        objLessonTABLE = new LessonTABLE(this);
    }

    private void bindwidgit() {
        multiListView = (ListView) findViewById(R.id.listmulti);
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////4
    public void back(View view ){
        finish();
    }
}
