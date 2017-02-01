package com.example.lol.myapplication;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;


import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class MainActivity extends AppCompatActivity {

    public final static String EXTRA_MESSAGE = "com.example.lol.myapplication.";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /////////////////////////////////////////////////////

        /////////////
        int VERSION_BDD = 1;
        String NOM_BDD = "eleves.db";

        String TABLE_LIVRES = "table_livres";
        String COL_ID = "ID";
        int NUM_COL_ID = 0;
        String COL_ISBN = "ISBN";
        int NUM_COL_ISBN = 1;
        String COL_TITRE = "Titre";
        int NUM_COL_TITRE = 2;

        /////////////

        dbHelper dbHelper = new dbHelper(this, NOM_BDD, null, VERSION_BDD);
        SQLiteDatabase bdd = dbHelper.getWritableDatabase();
//        bdd = maBaseSQLite.getWritableDatabase();

        //insert
        //Création d'un ContentValues (fonctionne comme une HashMap)
        ContentValues values = new ContentValues();
        //on lui ajoute une valeur associée à une clé (qui est le nom de la colonne dans laquelle on veut mettre la valeur)
        values.put(COL_ISBN, "12345678900");
        values.put(COL_TITRE, "Book title");
        //on insère l'objet dans la BDD via le ContentValues
        bdd.insert(TABLE_LIVRES, null, values);


        //read
        String titre = "Book title";
        Cursor c = bdd.query(TABLE_LIVRES, new String[] {COL_ID, COL_ISBN, COL_TITRE}, COL_TITRE + " LIKE \"" + titre +"\"", null, null, null, null);

        //Sinon on se place sur le premier élément
        c.moveToFirst();

        if (c.getCount() > 0) {
            Log.d("------ MY LG ------ : ", String.valueOf(c.getInt(NUM_COL_ID)));
            Log.d("------ MY LG ------ : ", String.valueOf(c.getString(NUM_COL_ISBN)));
            Log.d("------ MY LG ------ : ", String.valueOf(c.getString(NUM_COL_TITRE));

            c.getInt(NUM_COL_ID);
            c.getString(NUM_COL_ISBN);
            c.getString(NUM_COL_TITRE);
        }

//        Cursor cursor = db.query(
//                FeedEntry.TABLE_NAME,                     // The table to query
//                projection,                               // The columns to return
//                selection,                                // The columns for the WHERE clause
//                selectionArgs,                            // The values for the WHERE clause
//                null,                                     // don't group the rows
//                null,                                     // don't filter by row groups
//                sortOrder                                 // The sort order
//        );


        /////////////////////////////////////////////////////
    }

    public void createContact(View view) {
        Log.d("------ MY LOG ------ : ", "The createContact function was called");
        Intent intent = new Intent(this, createContact.class);
        startActivity(intent);
    }
}
