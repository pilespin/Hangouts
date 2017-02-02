package com.example.lol.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by pilespin on 2/1/17.
 */

public class dbHelper extends SQLiteOpenHelper {

    public dbHelper(Context context, String dbname, int version) {
        super(context, dbname, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE table_livres (" +
                "ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "ISBN TEXT NOT NULL, " +
                "Titre TEXT NOT NULL);"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //On peut faire ce qu'on veut ici moi j'ai décidé de supprimer la table et de la recréer
        //comme ça lorsque je change la version les id repartent de 0
        db.execSQL("DROP TABLE tables_livres ;");
        onCreate(db);
    }
}
