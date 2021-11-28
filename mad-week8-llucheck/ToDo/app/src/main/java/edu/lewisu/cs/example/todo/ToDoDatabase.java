package edu.lewisu.cs.example.todo;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;


@Database(entities = {ToDo.class}, version = 1, exportSchema = false)
public abstract class ToDoDatabase extends RoomDatabase {

    public abstract ToDoDao toDoDao();

    private static ToDoDatabase INSTANCE;

    static ToDoDatabase getDatabase(final Context context){
        if(INSTANCE == null){
            synchronized (ToDoDatabase.class){
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), ToDoDatabase.class, "todo_database").allowMainThreadQueries().build();
                }
            }
        }
        return INSTANCE;
    }
}
