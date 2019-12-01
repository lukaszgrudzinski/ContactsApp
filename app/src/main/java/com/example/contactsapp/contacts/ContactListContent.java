package com.example.contactsapp.contacts;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.contactsapp.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class ContactListContent {

    /**
     * An array of sample (dummy) items.
     */
    public static final List<Contact> ITEMS = new ArrayList<Contact>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<String, Contact> ITEM_MAP = new HashMap<String, Contact>();

    private static final int COUNT = 3;

    static {
        // Add some sample items.
        for (int i = 1; i <= COUNT; i++) {
            addItem(createDummyItem(i));
        }
    }

    public static void addItem(Contact item) {
        ITEMS.add(item);
        ITEM_MAP.put(Integer.toString(item.id), item);
    }

    public static void RemoveItem(int position)
    {
        ITEM_MAP.remove(Integer.toString(ITEMS.get(position).id));
        ITEMS.remove(position);
    }

    public static int Id=0;

    private static Contact createDummyItem(int position) {
        return new Contact( "Person " + position, "+48604603172","13.09.1995", R.drawable.user3 +Id, R.raw.audio1+Id);
    }

    public static class Contact implements Parcelable {
        public final int id;
        public final String name;
        public final String phoneNumber;
        public final String birthday;
        public final int picId;
        public final int soundId;

        public Contact(String name, String phoneNumber, String birthday, int picId, int soundId) {
            this.soundId = soundId;
            this.id = Id++;
            this.name = name;
            this.phoneNumber = phoneNumber;
            this.birthday = birthday;
            this.picId = picId;
        }

        protected Contact(Parcel in) {
            id = in.readInt();
            name = in.readString();
            phoneNumber = in.readString();
            birthday = in.readString();
            picId = in.readInt();
            soundId = in.readInt();
        }

        public static final Creator<Contact> CREATOR = new Creator<Contact>() {
            @Override
            public Contact createFromParcel(Parcel in) {
                return new Contact(in);
            }

            @Override
            public Contact[] newArray(int size) {
                return new Contact[size];
            }
        };

        @Override
        public String toString() {
            return name;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(id);
            dest.writeString(name);
            dest.writeString(phoneNumber);
            dest.writeString(birthday);
            dest.writeInt(picId);
            dest.writeInt(soundId);
        }
    }
}
