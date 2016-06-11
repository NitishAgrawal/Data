package com.app.android.db;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.app.android.db.dbhelper.SubModel;
import com.loopj.android.image.SmartImageView;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends ArrayAdapter {

    private ArrayList<SubModel> list ;
    Context context;

    public CustomAdapter(Context context, int resource, List objects) {
        super(context, resource, objects);
        this.context= context;
        list = (ArrayList) objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getCustomeView(position, convertView, parent);
    }

    public View getCustomeView(final int position, View convertView, ViewGroup parent) {
        final SubModel model = list.get(position);
        ViewHolder holder = null;
        if(convertView == null){
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.row, parent, false);
            holder.imageView = (SmartImageView) convertView.findViewById(R.id.my_image);
            holder.head = (TextView)convertView.findViewById(R.id.head);
            holder.dec = (TextView)convertView.findViewById(R.id.dec);
            holder.delete = (ImageButton)convertView.findViewById(R.id.delete);
            holder.delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(model.delete()){
                        list.remove(position);
                        notifyDataSetChanged();
                    }
                }
            });
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.imageView.setImageUrl(model.url.getFieldDb().toString());
        holder.head.setText(model.category_name.getFieldDb().toString());
        holder.dec.setText(model.category_language.getFieldDb().toString());
        return convertView;
    }

    class ViewHolder{
        TextView head,dec;
        SmartImageView imageView;
        ImageButton delete;
    }
}
