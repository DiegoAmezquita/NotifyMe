package com.pigsoftware.notifyme;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MySimpleArrayAdapter extends ArrayAdapter<String> {
  private final Context context;
  private final String[] values;

  public MySimpleArrayAdapter(Context context, String[] values) {
    super(context, R.layout.rowlayout, values);
    this.context = context;
    this.values = values;
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    LayoutInflater inflater = (LayoutInflater) context
        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    View rowView = inflater.inflate(R.layout.rowlayout, parent, false);
    TextView textView = (TextView) rowView.findViewById(R.id.label);
    TextView textView1 = (TextView) rowView.findViewById(R.id.label1);
    TextView textView2 = (TextView) rowView.findViewById(R.id.labelTime);
    
    
    ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
    textView.setText("Not #"+(position+1));
    textView1.setText(values[position]);
    textView2.setText("12:43");
    // Change the icon for Windows and iPhone
     imageView.setImageResource(R.drawable.ic_launcher);

    return rowView;
  }
} 