# Room

Room is a database layer on top of an SQLite database.  It simplifies working with a
relational database in Android

## Gradle Dependencies

```
// Room components
    implementation 'androidx.room:room-runtime:2.2.3'
    annotationProcessor 'androidx.room:room-compiler:2.2.3'


// Lifecycle components
     implementation 'androidx.lifecycle:lifecycle-extensions:2.2.0'
     annotationProcessor 'androidx.lifecycle:lifecycle-compiler:2.2.0'
```

## Entity
* This is the equivalent of a table.  
* To make it meaningful to Room, you must annotate it.
	* Each `@Entity` class represents an entity in a table
	* `@PrimaryKey`
	* `@NonNull`
	* `@ColumnInfo(name = "word")` specifies the name of the column in the table, if you want it to be different from the name of the member variable
	* Every field that's stored in the database needs to be either public or have a "getter" method.
	* You can find a complete list of annotations in the [Room package summary reference](https://developer.android.com/reference/androidx/room/package-summary.html).


## DAO (Data Access Object)
* In the DAO class, you specify SQL queries and associate them with method calls.
  * The compiler checks the SQL and generates queries from convenience annotations for common queries, such as `@Insert`, `@Update` and `@Delete`.
  * By default, all queries must be executed on a separate thread (more on this later)
* The DAO class must be an interface or an abstract class.  
* By default, all queries must be executed on a separate thread.
* Room uses the DAO to create a clean API for your code.


##  Room database
* This is a public abstract class that extends `RoomDatabase`
* Annotate the class to be a Room database, declare the entities that belong in the database and set the version number.
* Define the DAOs that work with the database. Provide an abstract "getter" method for each `@Dao`.
* Use the Singleton design pattern to prevent having multiple instances of the database opened at the same time.

## Repository
* Intermediary between view and data
* Instantiate this class and use these methods in your Activity class



References:
* [Android Developers Room Training Guide](https://developer.android.com/training/data-storage/room/index.html)
* [Android Room with a View](https://codelabs.developers.google.com/codelabs/android-room-with-a-view/index.html?index=..%2F..%2Findex#0)
