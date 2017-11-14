package comsci.kamonlphop.projectebook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by USER on 15/11/2560.
 */

public class AdapterDatabase extends BaseAdapter {

    private Context objcontext;
    private String[] picdataString, namedataStident;


    public AdapterDatabase(Context context, String[] picString, String[] nameStident) {
        this.objcontext = context;
        this.picdataString = picString; // ชื่อ
        this.namedataStident = nameStident; // รูป


    }

    @Override
    public int getCount() {
        return namedataStident.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater objLayoutInflater = (LayoutInflater) objcontext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view1 = objLayoutInflater.inflate(R.layout.listviewdatabase, parent, false);

        //show name
        TextView namebookTextView = (TextView) view1.findViewById(R.id.namebookdatabase);


        //show pic
        ImageView picImageView = (ImageView) view1.findViewById(R.id.imgdata);


        Picasso.with(objcontext).load(picdataString[position]).into(picImageView);
        namebookTextView.setText(namedataStident[position]);


        return view1;
    }
}
