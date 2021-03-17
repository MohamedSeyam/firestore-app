package com.example.firestoreapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class ContactsDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts_details);

        String name = getIntent().getStringExtra("name");
        String phone = getIntent().getStringExtra("phone");
        String address = getIntent().getStringExtra("address");
        String email = getIntent().getStringExtra("email");

        TextView nameTv =findViewById(R.id.name);
        TextView phoneTv =findViewById(R.id.phone);
        TextView addressTv =findViewById(R.id.address);
        TextView emailTv = findViewById(R.id.email);

        nameTv.setText(name+"");
        phoneTv.setText(phone+"");
        addressTv.setText(address+"");
        emailTv.setText(email+"");
    }
}