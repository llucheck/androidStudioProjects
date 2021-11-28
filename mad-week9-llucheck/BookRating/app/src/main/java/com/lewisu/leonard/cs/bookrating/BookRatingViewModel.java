package com.lewisu.leonard.cs.bookrating;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class BookRatingViewModel extends AndroidViewModel {
    private LiveData<List<BookRating>> ratings;
    private BookRatingRepository repository;

    public BookRatingViewModel(@NonNull Application application){
        super(application);
        repository = new BookRatingRepository(application);
        ratings = repository.getAllRatings();
    }

    LiveData<List<BookRating>> getRatings(){
        return ratings;
    }

    void insertRatings(BookRating rating){
        repository.insertRating(rating);
    }

    void deleteRating(int id){
        repository.deleteRating(id);
    }
}
