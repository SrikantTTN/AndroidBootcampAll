package com.example.srikant.day5;

import android.Manifest;
import android.content.ContentProviderOperation;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ContactFragment.FetchCallback, AddContactFragment.AddContactCallback, ContactAdapter.recyclerItemClick {
    ArrayList<Contact> ar = null;
    FragmentManager fm = getSupportFragmentManager();
    FragmentTransaction ft = fm.beginTransaction();

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ContactFragment contactFragment = new ContactFragment();
        ft.replace(R.id.fragContainer, contactFragment, "FetchContact");
        ft.addToBackStack("Fetch");
        ft.commit();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.readC:
                if (!requestPermissionIfRequired(new String[]{Manifest.permission.READ_CONTACTS},100)) {
                    readContacts();
                }
                return;
            case R.id.deleteC:
                if (!requestPermissionIfRequired(new String[]{Manifest.permission.WRITE_CONTACTS,Manifest.permission.READ_CONTACTS},101)) {
                    deleteContact();
                }
                return;
            case R.id.addC:
                if (!requestPermissionIfRequired(new String[]{Manifest.permission.WRITE_CONTACTS,Manifest.permission.READ_CONTACTS},102)) {
                    addContact();
                }
                return;
        }

    }

    private void addContact() {
        AddContactFragment addContactFragment = new AddContactFragment();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragContainer, addContactFragment, "AddFragment");
        ft.addToBackStack("Add");
        ft.commit();
    }

    private void deleteContact() {
        getContacts();
        if (ar.size() > 0) {
            populateDeletableListFragment(ar);
        } else {
            Toast.makeText(this, "No Contact Found", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean requestPermissionIfRequired(String[] permissions,int requestCode) {
        List<String> toAsk = new ArrayList<>();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            for (String p : permissions) {
                if (checkSelfPermission(p) != PackageManager.PERMISSION_GRANTED) {
                    toAsk.add(p);
                }
            }
            if (toAsk.size() == 0) {
                return false;
            } else {
                String[] askPermission = new String[toAsk.size()];
                for (int i = 0; i < toAsk.size(); i++) {
                    askPermission[i] = toAsk.get(i);
                }
                requestPermissions(askPermission, requestCode);
                return true;
            }
        }
        return false;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 100 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            readContacts();
        } else if(requestCode == 101 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            deleteContact();
        } else if(requestCode == 102 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            addContact();
        }else{
            Toast.makeText(this, "Sorry can't access contacts without permission", Toast.LENGTH_LONG).show();
        }
    }

    private void getContacts(){
        ar = new ArrayList<Contact>();
        ContentResolver cr = getContentResolver();
        Uri contactTableUri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        Cursor cursor = cr.query(contactTableUri, null, null, null, null);
        while(cursor.moveToNext()) {
            Contact p = new Contact();
            p.setName(cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)));
            if (Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.HAS_PHONE_NUMBER))) > 0) {
                p.setNumber(cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.NUMBER)));
            }
            p.setId(Long.parseLong(cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.CONTACT_ID))));
            ar.add(p);
        }
    }

    private void readContacts() {
        getContacts();
        if (ar.size() > 0) {
            populateListFragment(ar);
        } else {
            Toast.makeText(this, "No Contact Found", Toast.LENGTH_SHORT).show();
        }
    }

    private void populateDeletableListFragment(ArrayList<Contact> ar) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ContactListFragment contactListFragment = new ContactListFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("ContactList", ar);
        bundle.putString("Deletable", "Yes");
        contactListFragment.setArguments(bundle);
        ft.replace(R.id.fragContainer, contactListFragment, "ContactDeleteFragment");
        ft.addToBackStack("DeletableList");
        ft.commit();
    }


    private void populateListFragment(ArrayList<Contact> ar) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ContactListFragment contactListFragment = new ContactListFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("ContactList", ar);
        contactListFragment.setArguments(bundle);
        ft.replace(R.id.fragContainer, contactListFragment, "ContactFragment");
        ft.addToBackStack("List");
        ft.commit();
    }

    @Override
    public void onAddButtonClick(String cName, String cNumber) {
        ArrayList<ContentProviderOperation> ops = new ArrayList<ContentProviderOperation>();

        ops.add(ContentProviderOperation.newInsert(
                ContactsContract.RawContacts.CONTENT_URI)
                .withValue(ContactsContract.RawContacts.ACCOUNT_TYPE, null)
                .withValue(ContactsContract.RawContacts.ACCOUNT_NAME, null)
                .build());

        if (cName != null || !cName.isEmpty()) {
            ops.add(ContentProviderOperation.newInsert(
                    ContactsContract.Data.CONTENT_URI)
                    .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
                    .withValue(ContactsContract.Data.MIMETYPE,
                            ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE)
                    .withValue(
                            ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME,
                            cName).build());
        }

        if (cNumber != null || !cNumber.isEmpty()) {
            ops.add(ContentProviderOperation.
                    newInsert(ContactsContract.Data.CONTENT_URI)
                    .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
                    .withValue(ContactsContract.Data.MIMETYPE,
                            ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE)
                    .withValue(ContactsContract.CommonDataKinds.Phone.NUMBER, cNumber)
                    .withValue(ContactsContract.CommonDataKinds.Phone.TYPE,
                            ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE)
                    .build());
        }
        try {
            getContentResolver().applyBatch(ContactsContract.AUTHORITY, ops);
            Toast.makeText(this, "Added Contact", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Exception: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        getSupportFragmentManager().popBackStack();
    }

    @Override
    public void deleteContact(long id, int position) {
        String selection = ContactsContract.RawContacts.CONTACT_ID + " = ?";
        String[] args = {"" + id};
        long deletedId = getContentResolver().delete(ContactsContract.RawContacts.CONTENT_URI, selection, args);
        Log.d("Deleted", "deleteContact: " + deletedId);
    }
}
