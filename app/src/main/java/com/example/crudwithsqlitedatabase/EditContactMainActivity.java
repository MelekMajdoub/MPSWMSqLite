package com.example.crudwithsqlitedatabase;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import database.ContactDB;
import entities.Contact;

public class EditContactMainActivity extends AppCompatActivity {

    private EditText editName;
    private EditText editPhone;
    private Button buttonsave;
    private Button buttonBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_contact_main);
        Intent intent= getIntent();
        Contact contact= (Contact) intent.getParcelableExtra("contact");
        this.editName=(EditText) findViewById(R.id.editName);
        this.editPhone=(EditText) findViewById(R.id.editPhone);

        this.editName.setText(contact.getName());
        this.editPhone.setText(contact.getPhone());

        this.buttonBack=(Button) findViewById(R.id.buttonBack);
        this.buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(EditContactMainActivity.this, MainActivity.class);
                startActivity(intent1);
            }
        });

        this.buttonsave=(Button) findViewById(R.id.buttonSave);
        this.buttonsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContactDB contactDB = new ContactDB((getBaseContext()));
                contact.setName(editName.getText().toString());
                contact.setPhone(editPhone.getText().toString());
                if(contactDB.update(contact)){
                    Intent intent1 = new Intent(EditContactMainActivity.this, MainActivity.class);
                    startActivity(intent1);
                }else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                    builder.setMessage("Fail");
                    builder.setCancelable(false);
                    builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                        }
                    });
                    builder.create().show();
                }
            }
        });
    }
}