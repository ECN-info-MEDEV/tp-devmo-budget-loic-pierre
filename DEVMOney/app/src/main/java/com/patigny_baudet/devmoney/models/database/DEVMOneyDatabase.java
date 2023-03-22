package com.patigny_baudet.devmoney.models.database;

import android.content.ContentValues;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.OnConflictStrategy;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.patigny_baudet.devmoney.models.Category;
import com.patigny_baudet.devmoney.models.Operation;
import com.patigny_baudet.devmoney.models.database.dao.CategoryDao;
import com.patigny_baudet.devmoney.models.database.dao.OperationDao;

@Database(entities = {Category.class, Operation.class}, version = 1, exportSchema = false)
public abstract class DEVMOneyDatabase extends RoomDatabase {

    // Singleton
    private static volatile DEVMOneyDatabase INSTANCE;

    // DAO
    public abstract CategoryDao categoryDao();
    public abstract OperationDao operationDao();

    // Instance
    public static DEVMOneyDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (DEVMOneyDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), DEVMOneyDatabase.class, "devmoneyDatabase.db").addCallback(prepopulateDatabase()).build();
                }
            }
        }
        return INSTANCE;
    }

    // Prepopulate
    private static Callback prepopulateDatabase() {
        return new Callback() {

            @Override
            public void onCreate(@NonNull SupportSQLiteDatabase db) {
                super.onCreate(db);

                // Categories

                ContentValues contentValues = new ContentValues();
                contentValues.put("id", 0);
                contentValues.put("name", "Housing");
                contentValues.put("description", "Housing expenses");
                contentValues.put("color", "#FA5E5E");
                db.insert("Category", OnConflictStrategy.IGNORE, contentValues);

                contentValues = new ContentValues();
                contentValues.put("id", 1);
                contentValues.put("name", "Transport");
                contentValues.put("description", "Transport expenses");
                contentValues.put("color", "#556EF5");
                db.insert("Category", OnConflictStrategy.IGNORE, contentValues);

                contentValues = new ContentValues();
                contentValues.put("id", 2);
                contentValues.put("name", "Food");
                contentValues.put("description", "Food expenses");
                contentValues.put("color", "#2CBE5E");
                db.insert("Category", OnConflictStrategy.IGNORE, contentValues);

                contentValues = new ContentValues();
                contentValues.put("id", 3);
                contentValues.put("name", "Income");
                contentValues.put("description", "Income description");
                contentValues.put("color", "#D9D9D9");
                db.insert("Category", OnConflictStrategy.IGNORE, contentValues);


                // Operations

                contentValues = new ContentValues();
                contentValues.put("id", 0);
                contentValues.put("name", "Rent");
                contentValues.put("description", "Rent for the house");
                contentValues.put("date", "2023-02-28");
                contentValues.put("amount", -1200);
                contentValues.put("category_id", 0);
                db.insert("Operation", OnConflictStrategy.IGNORE, contentValues);

                contentValues = new ContentValues();
                contentValues.put("id", 1);
                contentValues.put("name", "Gas");
                contentValues.put("description", "Gas bill");
                contentValues.put("date", "2023-02-28");
                contentValues.put("amount", -120);
                contentValues.put("category_id", 1);
                db.insert("Operation", OnConflictStrategy.IGNORE, contentValues);

                contentValues = new ContentValues();
                contentValues.put("id", 2);
                contentValues.put("name", "Public transport");
                contentValues.put("description", "Public transport description");
                contentValues.put("date", "2023-03-02");
                contentValues.put("amount", -80);
                contentValues.put("category_id", 1);
                db.insert("Operation", OnConflictStrategy.IGNORE, contentValues);

                contentValues = new ContentValues();
                contentValues.put("id", 3);
                contentValues.put("name", "Food");
                contentValues.put("description", "Food description");
                contentValues.put("date", "2023-03-03");
                contentValues.put("amount", -400);
                contentValues.put("category_id", 2);
                db.insert("Operation", OnConflictStrategy.IGNORE, contentValues);

                contentValues = new ContentValues();
                contentValues.put("id", 4);
                contentValues.put("name", "Income");
                contentValues.put("description", "Salary");
                contentValues.put("date", "2023-03-03");
                contentValues.put("amount", 2000);
                contentValues.put("category_id", 3);
                db.insert("Operation", OnConflictStrategy.IGNORE, contentValues);
            }
        };
    }
}
