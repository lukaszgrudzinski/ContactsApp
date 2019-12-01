package com.example.contactsapp;


import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.contactsapp.contacts.ContactListContent;


/**
 * A simple {@link Fragment} subclass.
 */
public class ContactInfoFragment extends Fragment {


    public ContactInfoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
        Intent intent = getActivity().getIntent();
        if(intent!=null)
        {
            ContactListContent.Contact receivedContact = intent.getParcelableExtra(MainActivity.contactExtra);
            if(receivedContact!=null)
                displayContact(receivedContact);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_contact_info, container, false);
    }

    public void displayContact(ContactListContent.Contact contact)
    {
        FragmentActivity activity = getActivity();
        TextView ContactName = activity.findViewById(R.id.contactName);
        TextView ContactNumber = activity.findViewById(R.id.contactPhoneNumber);
        TextView ContactBirthday = activity.findViewById(R.id.contactBirthday);
        TextView ContactSound = activity.findViewById(R.id.contactSound);
        ContactName.setText(contact.name);
        ContactNumber.setText(getString(R.string.phone_number_info)+" "+contact.phoneNumber);
        ContactBirthday.setText(getString(R.string.birthday_info)+" "+contact.birthday);
        ContactSound.setText(getString(R.string.sound_info)+" "+getNameOfSound(contact.soundId));
        ((ImageView) activity.findViewById(R.id.contactImage)).setImageDrawable(activity.getResources().getDrawable(contact.picId));
    }

    private String getNameOfSound(int soundId)
    {
        Resources res = getResources();
        String[] ringtones = res.getStringArray(R.array.ringtones);
        return ringtones[ringtones.length-(2-soundId+R.raw.audio3)];
    }
}
