package comsci.kamonlphop.projectebook;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

public class Networkbook extends AppCompatActivity {
    private LessonTABLE objLessonTABLE;
    private ListView net_workListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_networkbook);

        bindwidgit();
        Cdatabase();
        Clistview();
    }

    private void Clistview() {
        final String[] picString = objLessonTABLE.readLessonnetwork(4);
        final String[] nameStident = objLessonTABLE.readLessonnetwork(1);

        //String[] picString = {"glrgwpogawaw"};
        //String[] nameStident = {"aawawfef"};
        final AdapterNetwork objAdapterNetwork = new AdapterNetwork(Networkbook.this, picString, nameStident);
        net_workListView.setAdapter(objAdapterNetwork);
    }

    private void Cdatabase() {
        objLessonTABLE = new LessonTABLE(this);
    }

    private void bindwidgit() {
        net_workListView = (ListView) findViewById(R.id.livnetwork);
    }

    ////////////////////////////////////////back//////////////////////////////////////////////////
    public void backmenubook(View view){
        finish();
    }
}
