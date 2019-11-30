package com.example.contactsapp;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.contactsapp.ContactFragment.OnListFragmentInteractionListener;
import com.example.contactsapp.contacts.ContactListContent.Contact;

import java.util.List;
import java.util.Random;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Contact} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyContactRecyclerViewAdapter extends RecyclerView.Adapter<MyContactRecyclerViewAdapter.ViewHolder> {

    private final List<Contact> mValues;
    private final OnListFragmentInteractionListener mListener;

    public MyContactRecyclerViewAdapter(List<Contact> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_contact, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        Contact contact = mValues.get(position);
        holder.mItem = contact;
        holder.mNameView.setText(contact.name);
        Context context = holder.mView.getContext();

        holder.mItemImageView.setImageDrawable(context.getResources().getDrawable(getRandomAvatar()));

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentClickInteraction(holder.mItem,position);
                }
            }
        });
    }
    private int getRandomAvatar() {
        switch (new Random().nextInt(10)) {
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

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mNameView;
        public final ImageView mItemImageView;
        public Contact mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mNameView = (TextView) view.findViewById(R.id.content);
            mItemImageView = (ImageView) view.findViewById(R.id.item_image);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mNameView.getText() + "'";
        }



    }
}
