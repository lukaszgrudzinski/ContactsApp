package com.example.contactsapp;

import android.content.Intent;
import android.os.Bundle;

import com.example.contactsapp.contacts.ContactListContent;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends AppCompatActivity implements ContactFragment.OnListFragmentInteractionListener {

    public static final String contactExtra = "contactExtra";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public void onListFragmentClickInteraction(ContactListContent.Contact contact, int position) {
        startInfoActivity(contact,position);
    }

    @Override
    public void onListFragmentLongClickInteraction(int position) {

    }

    public void AddNewContact()
    {
        Intent myIntent = new Intent(MainActivity.this, NewContactActivity.class);
       // myIntent.putExtra("key", value); //Optional parameters
        MainActivity.this.startActivity(myIntent);

    }

    public void DeleteContact(View view)
    {
        ContactListContent.RemoveItem(0);
        (( ContactFragment) getSupportFragmentManager().findFragmentById(R.id.contactFragment)).notifyDataChange();
    }

    private void startInfoActivity(ContactListContent.Contact contact, int position)
    {
        Intent intent = new Intent(this, ContactInfoActivity.class);
        intent.putExtra(contactExtra,contact);
        startActivity(intent);
    }
}
