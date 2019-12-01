package com.example.contactsapp.contacts;

import java.util.ArrayList;
import java.util.Date;
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

    private static final int COUNT = 5;

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

    public static int Id=0;

    private static Contact createDummyItem(int position) {
        return new Contact( "Item " + position, makeDetails(position),new Date().toString(),"drawable/user0");
    }

    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Item: ").append(position);
        for (int i = 0; i < position; i++) {
            builder.append("\nMore details information here.");
        }
        return builder.toString();
    }

    public static class Contact {
        public final int id;
        public final String name;
        public final String phoneNumber;
        public final String birthday;
        public final String picPath;

        public Contact(String name, String phoneNumber, String birthday, String picPath) {
            this.id = Id++;
            this.name = name;
            this.phoneNumber = phoneNumber;
            this.birthday = birthday;
            this.picPath = picPath;
        }

        @Override
        public String toString() {
            return name;
        }
    }
}
