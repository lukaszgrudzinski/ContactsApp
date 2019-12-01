package com.example.contactsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.contactsapp.contacts.ContactListContent;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import static org.apache.commons.validator.GenericValidator.isDate;

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


        if (ValidateFields(nameBox, dateBox, phoneBox)) return;

        ContactListContent.addItem(new ContactListContent.Contact(
                nameBox.getText().toString()+ " "+ surnameBox.getText().toString(),
                phoneBox.getText().toString(),
                dateBox.getText().toString(),
                ""));
        finish();
    }

    private boolean ValidateFields(EditText nameBox, EditText dateBox, EditText phoneBox) {
        if(nameBox.getText().toString().trim().isEmpty())
        {
            Toast.makeText(getApplicationContext(), getString(R.string.empty_name_msg), Toast.LENGTH_LONG).show();
            return true;
        }
        if(!isDateValid(dateBox.getText().toString()))
        {
            Toast.makeText(getApplicationContext(), getString(R.string.invalid_date_toast_msg), Toast.LENGTH_LONG).show();
            return true;
        }
        if(phoneBox.getText().toString().length()<6)
        {
            Toast.makeText(getApplicationContext(), getString(R.string.phone_number_invalid_msg), Toast.LENGTH_LONG).show();
            return true;
        }
        return false;
    }

    public boolean isDateValid(String dateStr) {
        return isDate(dateStr.trim(),"DD.MM",true);
    }
}
