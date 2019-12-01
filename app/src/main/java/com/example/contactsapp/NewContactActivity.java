package com.example.contactsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.contactsapp.contacts.ContactListContent;

public class NewContactActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_contact);
    }

    public void AddNewContact(View view) {
        EditText nameBox = findViewById(R.id.nameEditBox);
        EditText surnameBox = findViewById(R.id.surnameEditBox);
        EditText dateBox = findViewById(R.id.dateEditBox);
        EditText phoneBox = findViewById(R.id.phoneEditBox);
        Spinner ringtoneSpinner = findViewById(R.id.soundSpinner);

        ContactListContent.addItem(new ContactListContent.Contact(nameBox.getText().toString(),
                nameBox.getText().toString()+ " "+ surnameBox.getText().toString(),
                phoneBox.getText().toString(),
                dateBox.getText().toString(),
                ""));
        finish();
    }
}
