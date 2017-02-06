package com.example.ddao_sizebook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by ddao on 2/5/17.
 */

/**
 * Adapted the idea from this site: 
 * http://geekswithblogs.net/bosuch/archive/2011/01/31/android---create-a-custom-multi-line-listview-bound-to-an.aspx
 * A custom Adapter class to display the Shopper Information on the ListView
 */
public class ShopperAdapter extends BaseAdapter {
    private static ArrayList<Shopper> shopperArrayList;

    private LayoutInflater mInflater;

    public ShopperAdapter(Context context, ArrayList<Shopper> results) {
        shopperArrayList = results;
        mInflater = LayoutInflater.from(context);
    }

    public int getCount() {
        return shopperArrayList.size();
    }

    public Object getItem(int position) {
        return shopperArrayList.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public void clear() {
        shopperArrayList = null;
    }

    public void addAll(ArrayList<Shopper> Shoppers) {
        shopperArrayList = Shoppers;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.custom_row_view, null);
            holder = new ViewHolder();

            holder.sName = (TextView) convertView.findViewById(R.id.rowName);
            holder.sBust = (TextView) convertView.findViewById(R.id.rowBust);
            holder.sChest = (TextView) convertView.findViewById(R.id.rowChest);
            holder.sInseam= (TextView) convertView.findViewById(R.id.rowInseam);
            holder.sWaist = (TextView) convertView.findViewById(R.id.rowWaist);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.sName.setText("Name: " + shopperArrayList.get(position).getName());
        holder.sBust.setText("Bust: " + String.valueOf(shopperArrayList.get(position).getBust()));
        holder.sChest.setText("Chest: " + String.valueOf(shopperArrayList.get(position).getChest()));
        holder.sInseam.setText("Inseam: " + String.valueOf(shopperArrayList.get(position).getInseam()));
        holder.sWaist.setText("Waist: " + String.valueOf(shopperArrayList.get(position).getWaist()));

        return convertView;
    }

    static class ViewHolder {
        TextView sName;
        TextView sBust;
        TextView sChest;
        TextView sInseam;
        TextView sWaist;
    }
}
