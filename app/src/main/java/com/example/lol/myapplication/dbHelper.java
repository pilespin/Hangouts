package com.example.lol.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.EditText;

/**
 * Created by pilespin on 2/1/17.
 */

public class dbHelper extends SQLiteOpenHelper {

    private static int VERSION_DB = 1;
    private static String NAME_DB = "db.db";

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
        db.execSQL("DROP TABLE contacts ;");
        onCreate(db);
    }

    public boolean insertContact(Contact contact) {
        Log.d("------ MY LG ------ : ", "Called function create contact");

        SQLiteDatabase bdd = this.getWritableDatabase();

        //insert
        //Création d'un ContentValues (fonctionne comme une HashMap)
        ContentValues values = new ContentValues();
        //on lui ajoute une valeur associée à une clé (qui est le nom de la colonne dans laquelle on veut mettre la valeur)
        if (contact.getFirstname() != null && contact.getFirstname().length() > 0)
            values.put("firstname", contact.getFirstname());
        if (contact.getLastname() != null && contact.getLastname().length() > 0)
            values.put("lastname", contact.getLastname());
        if (contact.getPhone() != null && contact.getPhone().length() > 0)
            values.put("phone", contact.getPhone());
        if (contact.getEmail() != null && contact.getEmail().length() > 0)
            values.put("email", contact.getEmail());
        if (contact.getCity() != null && contact.getCity().length() > 0)
            values.put("city", contact.getCity());
        //on insère l'objet dans la BDD via le ContentValues
        long ret = bdd.insert("contacts", null, values);

        if (ret != -1)
            return (true);
        else
            return (false);
    }


    public boolean deleteContactByPhone(Context context, String phone) {

        dbHelper dbHelper = new dbHelper(context);
        SQLiteDatabase bdd = dbHelper.getWritableDatabase();

        long ret = bdd.delete("contacts", "phone=?", new String[]{phone});

        if (ret > 0)
            Log.d("------ MY LOG ------ : ", "delete ROW OK");
        else
            Log.d("------ MY LOG ------ : ", "delete NOT DELETE KO");

        if (ret != -1)
            return (true);
        else
            return (false);

    }
}
