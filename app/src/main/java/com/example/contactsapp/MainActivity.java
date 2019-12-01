package com.example.contactsapp;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.ViewParent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.contactsapp.contacts.ContactListContent;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class MainActivity extends AppCompatActivity
        implements ContactFragment.OnListFragmentInteractionListener,
        DeleteDialog.OnDeleteDialogInteractionListener{

    public static final String contactExtra = "contactExtra";
    AudioPlayer audioPlayer;
    private int currentItemPosition = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        audioPlayer = new AudioPlayer();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddNewContact();
            }
        });

    }

    @Override
    public void onResume(){
        super.onResume();
        (( ContactFragment) getSupportFragmentManager().findFragmentById(R.id.contactFragment)).notifyDataChange();
    }

    @Override
    public void onPause()
    {
       super.onPause();
       audioPlayer.stop();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public void onListFragmentClickInteraction(ContactListContent.Contact contact, int position) {
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
            displayTaskFragment(contact);
        else
            startInfoActivity(contact,position);
    }

    @Override
    public void onListFragmentLongClickInteraction(ContactListContent.Contact contact, int position) {
        audioPlayer.play(this,contact.soundId);
    }

    public void AddNewContact()
    {
        Intent myIntent = new Intent(MainActivity.this, NewContactActivity.class);
       // myIntent.putExtra("key", value); //Optional parameters
        MainActivity.this.startActivity(myIntent);

    }

    private void startInfoActivity(ContactListContent.Contact contact, int position)
    {
        Intent intent = new Intent(this, ContactInfoActivity.class);
        intent.putExtra(contactExtra,contact);
        startActivity(intent);
    }
    private void displayTaskFragment(ContactListContent.Contact contact)
    {
        ContactInfoFragment contactInfoFragment = ((ContactInfoFragment) getSupportFragmentManager().findFragmentById(R.id.contactInfoFragment));
        if(contactInfoFragment!=null)
            contactInfoFragment.displayContact(contact);
    }

    public void showDeleteDialog(View view)
    {
        DeleteDialog.newInstance().show(getSupportFragmentManager(),getString(R.string.delete_dialog_tag));
        currentItemPosition = getIndexOfSelectedContact(view);
    }

    private int getIndexOfSelectedContact(View view) {
        ViewParent a = view.getParent();
        ViewParent b = a.getParent();
        return ((RecyclerView)b).indexOfChild((View)a);
    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {
        ContactListContent.RemoveItem(currentItemPosition);
        ((ContactFragment) getSupportFragmentManager().findFragmentById(R.id.contactFragment)).notifyDataChange();
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {
        //:c
    }
}
