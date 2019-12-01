package com.example.contactsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.contactsapp.contacts.ContactListContent;

import java.util.Random;

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

        if (ValidateFields(nameBox, dateBox, phoneBox)) return;

        ContactListContent.addItem(new ContactListContent.Contact(
                nameBox.getText().toString()+ " "+ surnameBox.getText().toString(),
                phoneBox.getText().toString(),
                dateBox.getText().toString(),
                getRandomAvatar(), getSoundId()));
        finish();
    }

    private int getSoundId() {
        Spinner ringtoneSpinner = findViewById(R.id.soundSpinner);
        return ringtoneSpinner.getSelectedItemPosition()+R.raw.audio1;
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
        return isDate(dateStr.trim(),"DD.MM.yyyy",true);
    }

    private int getRandomAvatar() {
        switch (new Random(System.currentTimeMillis()).nextInt(10)) {
            case 0:
                return R.drawable.user0;
            case 1:
                return R.drawable.user1;
            case 2:
                return R.drawable.user2;
            case 3:
                return R.drawable.user3;
            case 4:
                return R.drawable.user4;
            case 5:
                return R.drawable.user5;
            case 6:
                return R.drawable.user6;
            case 7:
                return R.drawable.user7;
            case 8:
                return R.drawable.user8;
            case 9:
            default:
                return R.drawable.user9;
        }
    }
}
