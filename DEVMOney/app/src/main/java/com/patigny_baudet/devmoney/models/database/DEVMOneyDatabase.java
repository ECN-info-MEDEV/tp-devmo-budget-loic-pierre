package com.patigny_baudet.devmoney.models.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

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
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), DEVMOneyDatabase.class, "devmoneyDatabase.db").build();
                }
            }
        }
        return INSTANCE;
    }
}
