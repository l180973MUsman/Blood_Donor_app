package com.example.l180973assign1;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

public class DonorAdapter extends ArrayAdapter<Donor> implements Filterable {
    private Context mContext;
    private int mResource;
    ArrayList<Donor> sobj;
    ListView list;
    ArrayList<Donor> arrayList;
    FloatingActionButton calling;
    FloatingActionButton messaging;

    public DonorAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Donor> objects) {
        super(context, resource, objects);
        this.mContext = context;
        this.mResource = resource;
        arrayList = objects;
        sobj = new ArrayList<>(objects);
    }

    @NonNull
    @Override
    public Filter getFilter() {
        return filter;
    }
    Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            ArrayList<Donor> filterlist = new ArrayList<>();
            String nam,bg,loc,status;
            for (Donor D: sobj){
                nam = D.getName().toLowerCase();
                bg  = D.getB_group().toLowerCase();
                loc = D.getLoc().toLowerCase();
                status = D.getStatus().toLowerCase();
                if(nam.contains(constraint.toString().toLowerCase()) || bg.contains(constraint.toString().toLowerCase()) || loc.contains(constraint.toString().toLowerCase()) || status.contains(constraint.toString().toLowerCase()) )
                {
                    filterlist.add(D);
                }
            }
            FilterResults filter_r = new FilterResults();
            filter_r.values = filterlist;
            return filter_r;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {

            arrayList.clear();
            arrayList.addAll((Collection<? extends Donor>) results.values);
            notifyDataSetChanged();

        }
    };
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        convertView = layoutInflater.inflate(mResource, parent, false);
        TextView txtName = convertView.findViewById(R.id.txtName);
        TextView txtBG = convertView.findViewById(R.id.txtBG);
        TextView txtCon = convertView.findViewById(R.id.txtCon);
        TextView txtLoc = convertView.findViewById(R.id.txtLoc);
        TextView txtStat = convertView.findViewById(R.id.txtStat);
        calling = convertView.findViewById(R.id.call);
        messaging = convertView.findViewById(R.id.message);

        txtName.setText(getItem(position).getName());
        txtBG.setText(getItem(position).getB_group());
        txtCon.setText(getItem(position).getContact());
        txtLoc.setText(getItem(position).getLoc());
        txtStat.setText(getItem(position).getStatus());

        calling.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+getItem(position).getContact()));
                mContext.startActivity(intent);
            }
        });
        messaging.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto: " + getItem(position).getContact()));
                intent.putExtra("sms_body", "Hi are you available for Blood donation");
                mContext.startActivity(intent);
            }
        });

        return convertView;
    }
}
