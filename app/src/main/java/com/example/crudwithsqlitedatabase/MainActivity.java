package com.example.crudwithsqlitedatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import adapters.ContactListAdapter;
import entities.Contact;
import database.*;
import android.content.*;

import java.io.Serializable;

public class MainActivity extends AppCompatActivity {

    private Button buttonAdd;
    private ListView listViewContacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.buttonAdd = (Button) findViewById(R.id.buttonAdd);

        this.buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(MainActivity.this, AddContactActivity.class);
                startActivity(intent1);
            }
        });

        ContactDB contactDB = new ContactDB(this);
        this.listViewContacts = (ListView) findViewById(R.id.listViewContacts);
        this.listViewContacts.setAdapter(new ContactListAdapter(this,contactDB.findAll() ));

        this.listViewContacts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Contact contact = contactDB.findAll().get(i);
                /*String name= contact.getName();
                String phone = contact.getPhone();*/

                Intent intent = new Intent(MainActivity.this, ContactDetailActivity.class);

                /*intent.putExtra("name", name);
                intent.putExtra("phone", phone);*/

                intent.putExtra("contact", contact);

                startActivity(intent);

            }
        });
    }
}