package edu.lewisu.cs.example.todo;

import android.app.Application;

import java.util.List;

public class ToDoRespository {
    private ToDoDao toDoDao;

    public ToDoRespository(Application application) {
        ToDoDatabase db = ToDoDatabase.getDatabase(application);
        toDoDao = db.toDoDao();
    }

    List<ToDo> getAllToDos(){
        return toDoDao.getAllToDos();
    }

    ToDo getToDo(int id){
        return toDoDao.getToDo(id);
    }

    void deleteToDo(ToDo toDo) {
        toDoDao.deleteTodos(toDo);
    }

    void insert(ToDo toDo){
        toDoDao.insert(toDo);
    }

    void update(ToDo toDo){
        toDoDao.UpdateTodos(toDo);
    }

    void initialize(){
        toDoDao.deleteAll();
    }
}
