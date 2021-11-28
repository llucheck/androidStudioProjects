package com.lewisu.leonard.cs.bookrating;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class BookRatingRepository {
    private BookRatingDao dao;
    private LiveData<List<BookRating>> ratings;

    BookRatingRepository(Application application){
        BookRatingDatabase database = BookRatingDatabase.getDatabase(application);
        dao = database.bookRatingDao();
        ratings = dao.getAllRatings();

    }

    LiveData<List<BookRating>> getAllRatings(){
        return ratings;
    }

    void insertRating(BookRating rating){
        new InsertAsync(dao).execute(rating);
    }

    void deleteRating(int id){
        new DeleteAsync(dao).execute(id);
    }

    private static class InsertAsync extends AsyncTask<BookRating, Void, Void>{
        private final BookRatingDao dao;

        public InsertAsync(BookRatingDao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(BookRating... bookRatings) {
            dao.insertRating(bookRatings[0]);
            return null;
        }
    }

    private static class DeleteAsync extends AsyncTask<Integer, Void, Void>{
        private BookRatingDao dao;

        public DeleteAsync(BookRatingDao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(Integer... integers) {
            dao.deleteRating(integers[0]);
            return null;
        }
    }

}
