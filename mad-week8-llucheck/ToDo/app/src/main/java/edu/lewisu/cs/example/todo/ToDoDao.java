package edu.lewisu.cs.example.todo;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ToDoDao {
    @Insert
    void insert(ToDo... todo);

    @Update
    void UpdateTodos(ToDo... todo);

    @Delete
    void deleteTodos(ToDo... todo);

    @Query("DELETE FROM todo_table")
    void deleteAll();

    @Query("SELECT * FROM todo_table WHERE id= :id")
    ToDo getToDo(int id);

    @Query("SELECT * FROM todo_table ORDER BY task_title ASC")
    List<ToDo> getAllToDos();
}
