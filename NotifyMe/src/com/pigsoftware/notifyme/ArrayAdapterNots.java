package com.pigsoftware.notifyme;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ArrayAdapterNots extends ArrayAdapter<Notification> {
  private final Context context;
  private final Notification[] values;

  public ArrayAdapterNots(Context context, Notification[] values) {
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
    
    
    
    textView.setText(values[position].NOTIFICATION_TITLE);
    textView1.setText(values[position].NOTIFICATION_MESSAGE);
    textView2.setText(values[position].NOTIFICATION_TIME);
    ImageView imageView = (ImageView) rowView.findViewById(R.id.imageGroup);
    imageView.setImageBitmap(Utils.getGroup(values[position].GROUP_ID).GROUP_IMAGE);

    return rowView;
  }
} 