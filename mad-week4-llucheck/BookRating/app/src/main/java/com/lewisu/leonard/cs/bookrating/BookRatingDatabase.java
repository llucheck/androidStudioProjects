package com.lewisu.leonard.cs.bookrating;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {BookRating.class}, version=1, exportSchema = false)
public abstract class BookRatingDatabase extends RoomDatabase {

    public abstract BookRatingDao bookRatingDao();

    private static BookRatingDatabase INSTANCE;

    static BookRatingDatabase getDatabase(final Context context){
        if(INSTANCE == null){
            synchronized (BookRatingDatabase.class){
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(), BookRatingDatabase.class, "rating_db").build();
            }
        }
        return INSTANCE;
    }

}
