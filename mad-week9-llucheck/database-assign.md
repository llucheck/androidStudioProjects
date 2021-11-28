# Rating App Revised
In this assignment, you will add a Recycler View and a database to your Ratings app.

### Requirements for this assignment:
- A database to store ratings is added to the app.  This includes annotating your model class, adding DAO, Database and Repository classes.
- `MainActivity` contains a recycler view that displays the ratings in the database.
- `LiveData` is used so that list reflects database updates (requires a `ViewModel`).
- User is allowed to add ratings to the database

### Grading

This assignment is worth **18 points**

Your assignment will be evaluated based on the following rubric:
- Layout contains a `RecyclerView`: 2 pts.
- App contains an Adapter class that binds data from the model to the `ViewHolder`: 2 pts.
- `RecyclerView` correctly displays data from the Adapter: 2 pts.
- Model class contains database annotations: 2 pts.
- App contains DAO and Database classes: 2 pts.
- App contains a repository class: 3 pts.
- Live data is used: 3 pts.
- User can add a rating to the database: 2 pts.
