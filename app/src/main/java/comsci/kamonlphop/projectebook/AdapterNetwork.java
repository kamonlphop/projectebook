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
 * Created by USER on 14/11/2560.
 */

public class AdapterNetwork extends BaseAdapter {

    private Context objcontext;
    private String[] picString, nameStident;

    public AdapterNetwork(Context context, String[] picString, String[] nameStident) {
        this.objcontext = context;
        this.picString = picString; // ชื่อ
        this.nameStident = nameStident; // รูป


    }

    @Override
    public int getCount() {
        return nameStident.length;
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
        View view1 = objLayoutInflater.inflate(R.layout.listviewnetwork, parent, false);

        //show name
        TextView namebookTextView = (TextView) view1.findViewById(R.id.namebooknetwork);


        //show pic
        ImageView picImageView = (ImageView) view1.findViewById(R.id.imgnetwork);


        Picasso.with(objcontext).load(picString[position]).into(picImageView);
        namebookTextView.setText(nameStident[position]);


        return view1;
    }
}
