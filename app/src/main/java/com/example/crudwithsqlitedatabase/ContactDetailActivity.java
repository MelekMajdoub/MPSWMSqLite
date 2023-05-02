package com.example.crudwithsqlitedatabase;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import adapters.ContactListAdapter;
import entities.Contact;
import database.*;
import android.content.*;

import android.os.Bundle;
import android.widget.TextView;

public class ContactDetailActivity extends AppCompatActivity {

    private TextView textViewName;
    private TextView textViewPhone;
    private Button buttonBack;
    private Button buttonEdit;
    private Button buttonDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_detail);

        Intent intent= getIntent();
        Contact contact= (Contact) intent.getParcelableExtra("contact");
        //String name=intent.getStringExtra("name");
        //String phone=intent.getStringExtra("phone");

        this.textViewName=(TextView) findViewById(R.id.Name);
        this.textViewPhone=(TextView) findViewById(R.id.Phone);


        this.textViewName.setText(contact.getName());
        this.textViewPhone.setText(contact.getPhone());

        this.buttonBack=(Button) findViewById(R.id.Back);
        this.buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(ContactDetailActivity.this, MainActivity.class);
                startActivity(intent1);
            }
        });

        this.buttonDelete=(Button) findViewById(R.id.buttonDelete);
        this.buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setCancelable(false);
                builder.setTitle("confirm");
                builder.setMessage("Are you sure?");
                builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        ContactDB contactDB = new ContactDB(getBaseContext());
                        if(contactDB.delete(contact.getId())){
                            Intent intent1 = new Intent(ContactDetailActivity.this, MainActivity.class);
                            startActivity(intent1);
                        }else {
                            AlertDialog.Builder builder1= new AlertDialog.Builder(getBaseContext());
                            builder1.setMessage("Fail");
                            builder1.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.cancel();
                                }
                            });
                            builder1.create().show();
                        }
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builder.create().show();
            }
        });

        this.buttonEdit=(Button) findViewById(R.id.buttonEdit);
        this.buttonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1= new Intent(ContactDetailActivity.this, EditContactMainActivity.class);
                intent1.putExtra("contact", contact);
                startActivity(intent1);

            }
        });


    }
}