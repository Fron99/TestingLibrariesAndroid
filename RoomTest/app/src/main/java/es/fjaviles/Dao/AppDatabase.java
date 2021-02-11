package es.fjaviles.Dao;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import es.fjaviles.Dao.Model.Person;

@Database(entities = {Person.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract PersonDao personDao();

    private static AppDatabase INSTANCE;

    public static AppDatabase getDatabase(final Context context){

        if (INSTANCE == null){
            synchronized (AppDatabase.class){
                if (INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                            AppDatabase.class, "crudPersons.db").allowMainThreadQueries().build();
                }
            }
        }

        return INSTANCE;

    }

}

