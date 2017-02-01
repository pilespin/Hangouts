package com.example.lol.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;


import java.util.ArrayList;
import java.util.List;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class MainActivity extends AppCompatActivity {

    public final static String EXTRA_MESSAGE = "com.example.lol.myapplication.";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);





        dbHelper dbh = new dbHelper(this);

        SQLiteDatabase db = dbh.getWritableDatabase();

//    SQLiteDatabase db = dbHelper.getReadableDatabase();

        // Define a projection that specifies which columns from the database
// you will actually use after this query.
        String[] projection = {
                dbHelper.FeedEntry._ID,
                dbHelper.FeedEntry.COLUMN_NAME_TITLE,
                dbHelper.FeedEntry.COLUMN_NAME_SUBTITLE
        };

        // Filter results WHERE "title" = 'My Title'
        String selection = dbHelper.FeedEntry.COLUMN_NAME_TITLE + " = ?";
        String[] selectionArgs = { "Title" };

        // How you want the results sorted in the resulting Cursor
        String sortOrder = dbHelper.FeedEntry.COLUMN_NAME_SUBTITLE + " DESC";

        Cursor cursor = db.query(
                dbHelper.FeedEntry.TABLE_NAME,                     // The table to query
                projection,                               // The columns to return
                selection,                                // The columns for the WHERE clause
                selectionArgs,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                sortOrder                                 // The sort order
        );

        List itemIds = new ArrayList<>();
        while(cursor.moveToNext()) {
            long itemId = cursor.getLong(
                    cursor.getColumnIndexOrThrow(dbHelper.FeedEntry._ID));
            itemIds.add(itemId);
            Log.d("------ MY LOG ------ : ", "test");

        }
        cursor.close();




    }

    public void createContact(View view) {
        Log.d("------ MY LOG ------ : ", "The createContact function was called");
        Intent intent = new Intent(this, createContact.class);
        startActivity(intent);
    }

}
