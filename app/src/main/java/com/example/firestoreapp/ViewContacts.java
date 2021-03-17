package com.example.firestoreapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ViewContacts extends AppCompatActivity {
    FirebaseFirestore firestore;
    List<Contact> contacts = new ArrayList<>();
    ListView lv;

    int contactsNumber = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_contacts);

        lv = findViewById(R.id.listView);
        firestore = FirebaseFirestore.getInstance();
        CollectionReference collectionReference = firestore.collection("contacts");
        Task<QuerySnapshot> querySnapshotTask = collectionReference.get();

        querySnapshotTask.addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot snapshot : task.getResult()) {
                        Contact contact = snapshot.toObject(Contact.class);
                        contacts.add(contact);
                    }
                    lv.setAdapter(new ContactsAdapter(getApplicationContext(), contacts));
                } else {
                    Toast.makeText(ViewContacts.this, "Error While Read Data, Try Again Later", Toast.LENGTH_SHORT).show();
                }
            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), ContactsDetailsActivity.class);
//                intent.putExtra("position" ,position);
                intent.putExtra("name", contacts.get(position).getName());
                intent.putExtra("phone", contacts.get(position).getPhone());
                intent.putExtra("address", contacts.get(position).getAddress());
                intent.putExtra("email", contacts.get(position).getEmail());
                startActivity(intent);
            }
        });
    }
}