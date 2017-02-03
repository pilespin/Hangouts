package com.example.lol.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by pilespin on 2/1/17.
 */

public class dbHelper extends SQLiteOpenHelper {

    private static int VERSION_DB = 1;
    private static String NAME_DB = "bdddddd.db";

    public dbHelper(Context context) {
        super(context, NAME_DB, null, VERSION_DB);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE contacts (" +
                "ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "firstname TEXT NOT NULL, " +
                "lastname TEXT," +
                "phone TEXT NOT NULL UNIQUE," +
                "email TEXT," +
                "city TEXT);"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //On peut faire ce qu'on veut ici moi j'ai décidé de supprimer la table et de la recréer
        //comme ça lorsque je change la version les id repartent de 0
        db.execSQL("DROP TABLE contacts ;");
        onCreate(db);
    }

    public void insertContact(Contact contact) {
        Log.d("------ MY LG ------ : ", "Called function create contact");


        SQLiteDatabase bdd = this.getWritableDatabase();

        //insert
        //Création d'un ContentValues (fonctionne comme une HashMap)
        ContentValues values = new ContentValues();
        //on lui ajoute une valeur associée à une clé (qui est le nom de la colonne dans laquelle on veut mettre la valeur)
        if (contact.getFirstname() != null)
            values.put("firstname", contact.getFirstname());
        if (contact.getLastname() != null)
            values.put("lastname", contact.getLastname());
        if (contact.getPhone() != null)
            values.put("phone", contact.getPhone());
        if (contact.getEmail() != null)
            values.put("email", contact.getEmail());
        if (contact.getCity() != null)
            values.put("city", contact.getCity());
        //on insère l'objet dans la BDD via le ContentValues
        bdd.insert("contacts", null, values);
    }
}
