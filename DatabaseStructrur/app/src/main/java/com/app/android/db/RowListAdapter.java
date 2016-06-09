package com.app.android.db;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.android.db.dbhelper.SubModel;
import com.loopj.android.image.SmartImageView;

import java.util.ArrayList;
import java.util.List;

public class RowListAdapter extends ArrayAdapter {

    private List<SubModel> list = new ArrayList<SubModel>();
    Context context;

    public RowListAdapter(Context context, int resource, List<SubModel> list) {
        super(context, resource);
        this.context = context;
        this.list = list;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        ViewHolder holder;
        SubModel model = list.get(position);
        if(view == null) {
            holder = new ViewHolder();
            view = LayoutInflater.from(context).inflate(R.layout.row, null);
            holder.imageView = (SmartImageView) view.findViewById(R.id.my_image);
            holder.head = (TextView)view.findViewById(R.id.head);
            holder.dec = (TextView)view.findViewById(R.id.dec);
            view.setTag(holder);
        }else {
            holder = (ViewHolder) view.getTag();
        }

        holder.imageView.setImageUrl(model.url.getFieldDb().toString());
        holder.head.setText(model.category_name.getFieldDb().toString());
        holder.dec.setText(model.category_language.getFieldDb().toString());

        return view;
    }

    class ViewHolder{
        TextView head,dec;
        SmartImageView imageView;
    }


}

