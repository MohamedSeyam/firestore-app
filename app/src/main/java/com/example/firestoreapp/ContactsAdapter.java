package com.example.firestoreapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class ContactsAdapter extends BaseAdapter {
    FirebaseFirestore firestore;
    Context context;
    List<Contact> contacts;

    public ContactsAdapter(Context context, List<Contact> contacts) {
        this.context = context;
        this.contacts = contacts;
        Toast.makeText(context, "Book Number :: " + contacts.size(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public int getCount() {
        return contacts.size();
    }

    @Override
    public Object getItem(int position) {
        return contacts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.contact_item_layout, null);
            ViewHolder vh = new ViewHolder();
            vh.contactName = convertView.findViewById(R.id.textView_name);
            convertView.setTag(vh);

        }
        ViewHolder vh = (ViewHolder) convertView.getTag();
        vh.contactName.setText(contacts.get(position).getName());


        return convertView;
    }

    class ViewHolder {
        TextView contactName;
    }
}
