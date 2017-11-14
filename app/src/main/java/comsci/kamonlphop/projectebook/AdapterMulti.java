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

public class AdapterMulti extends BaseAdapter {

    private Context objcontext;
    private String[] picmultiString, namemultiStident;


    public AdapterMulti(Context context, String[] picString, String[] nameStident) {
        this.objcontext = context;
        this.picmultiString = picString; // ชื่อ
        this.namemultiStident = nameStident; // รูป


    }

    @Override
    public int getCount() {
        return namemultiStident.length;
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
        View view1 = objLayoutInflater.inflate(R.layout.listviewmulti, parent, false);

        //show name
        TextView namebookTextView = (TextView) view1.findViewById(R.id.namebookmulti);


        //show pic
        ImageView picImageView = (ImageView) view1.findViewById(R.id.imgmulti);


        Picasso.with(objcontext).load(picmultiString[position]).into(picImageView);
        namebookTextView.setText(namemultiStident[position]);


        return view1;
    }
}

