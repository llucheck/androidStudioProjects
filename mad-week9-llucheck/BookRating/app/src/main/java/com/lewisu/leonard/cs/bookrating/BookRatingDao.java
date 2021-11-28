package com.lewisu.leonard.cs.bookrating;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface BookRatingDao {

    @Insert
    void insertRating(BookRating... ratings);

    @Query("DELETE FROM bookrating Where id = :id")
    void deleteRating(int id);

    @Query("SELECT * from BookRating ORDER BY bookName, authorName")
    LiveData<List<BookRating>> getAllRatings();

}
