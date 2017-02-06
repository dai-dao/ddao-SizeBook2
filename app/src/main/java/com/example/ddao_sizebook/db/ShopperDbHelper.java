package com.example.ddao_sizebook.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

/**
 * Created by ddao on 2/5/17.
 */

public class ShopperDbHelper extends SQLiteOpenHelper {

    public ShopperDbHelper(Context context) {
        super(context, ShopperInformation.DB_NAME, null, ShopperInformation.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + ShopperInformation.ShopperEntry.TABLE + " ( " +
                ShopperInformation.ShopperEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ShopperInformation.ShopperEntry.Name+ " TEXT NOT NULL, " +
                ShopperInformation.ShopperEntry.Date+ " TEXT, " +
                ShopperInformation.ShopperEntry.Neck+ " TEXT, " +
                ShopperInformation.ShopperEntry.Bust + " TEXT, " +
                ShopperInformation.ShopperEntry.Chest+ " TEXT, " +
                ShopperInformation.ShopperEntry.Waist+ " TEXT, " +
                ShopperInformation.ShopperEntry.Hip  + " TEXT, " +
                ShopperInformation.ShopperEntry.Inseam+ " TEXT, " +
                ShopperInformation.ShopperEntry.Comment+ " TEXT);";

        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + ShopperInformation.ShopperEntry.TABLE);
        onCreate(db);
    }
}
