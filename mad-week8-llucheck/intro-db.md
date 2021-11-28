# Introduction to Relational Databases

## Relational Database Model
- The relational structure is the most commonly used today. 
- Relational databases logically store data in tables
  - Each row represents an instance of data
    - Each column represents an attribute
- Each row in a table must be unique.  
  - Primary key is the column or columns that uniquely identify a rows
  - Often use an ID column as the primary key
- The tables of records can be connected by common key values. 
- Android includes a built-in relational database, [SQLite](https://www.sqlite.org/index.html).

## SQL
 - Structured Query Language (SQL)
- Standard language for accessing relational database management systems. SQL is **not** a programming language.  
- Used to store data to and retrieve data from a database
- Not case sensitive
- Statements end with a semi-colon

### Common SQL commands
- `CREATE`:  Creates a database or table
- `DROP`:  Deletes a database or table
- `DELETE`:  Deletes records from a table
- `INSERT`:  Adds records to a table
- `SELECT`:  Retrieves records from a table
- `UPDATE`:  Updates records in a table


### Creating Tables
- Syntax 

```sql
CREATE TABLE table_name (   
  column_name_1 data_type [column_attributes]
  [, column_name_2 data_type [column_attributes]]..)
```

- SQLite Data Types
  - `NULL`
  - `INTEGER`
  - `REAL`
  - `TEXT`
  - `BLOB`
- SQLite Column Constraints
  - `NOT NULL`
  - `UNIQUE`
  - `PRIMARY KEY`
  - `AUTOINCREMENT`
  - `CHECK`
  - `DEFAULT`

- Example:  Creating a table with column attributes

```sql
CREATE TABLE tasks_table
(
  id integer primary key autoincrement,
  description text not null,
  priority integer,
  done integer default 0
)
```

### Retrieving data
- Use the `SELECT` statement
  - This is the most commonly used SQL statement
- Basic statement: *SELECT field1, field2 FROM table;*
  - Example: `SELECT description, priority FROM tasks;`
- Use `*` to select all fields
  - Example: `SELECT * FROM tasks;`

### Retrieving data with specific criteria
- Use a `WHERE` clause
  - Example: `SELECT * FROM tasks WHERE done = 0;`
- Useful comparison operators:
  - `=` , `>` , `<,` `>=,` `<=`
  - `!=` or `<>` (not equal)
  - `IS NOT NULL`
  - `IS NULL`
  - `LIKE` and `NOT LIKE`(Uses pattern matching)
    - `% `wildcard-matches any number of characters
    - wildcard-matches a single character

### Retrieving data in a particular order
- Use the `ORDER BY` clause
- Example: `SELECT * FROM tasks ORDER BY date_due;`
  - Default order is ascending `ASC`
    - To sort in descending order use the keyword `DESC`

### Retrieving data from multiple tables
- `INNER JOIN`:  Combines rows from two tables by matching columns that are common to the table
- Example: `SELECT project_name, description, priority FROM projects INNER JOIN tasks ON projects._id = tasks.project_id;`

### Insert
- Use an `INSERT` statement
- Example:
`INSERT INTO tasks(description, priority) VALUES ('Prepare lecture', '2');`

### Updating records
- Use the `UPDATE` statement
- Example:
`UPDATE tasks SET priority = 1;`
  - This will update all rows in the table
- Use a `WHERE` clause to restrict the update
  - Example:
```
UPDATE tasks SET priority = 3
WHERE description LIKE '%campus%';
```

### Deleting records
Use the DELETE FROM statement
Combine with a WHERE clause or all data in the table will be deleted
Example:
```
DELETE FROM tasks 
WHERE done = 1;
```


## Lab Exercise

- Log into the server
- Clone this repository or upload the sql script
- Create the database

```
sqlite3 menagerie.sqlite
```


- Add the tables by running the script

```
.read menagerie.sql
```

- View the tables
  - There are two tables: owner and pet

```
.tables
```

### Basic `SELECT`
- List the name, species and gender of all pets
- List all columns for all pets
- List all owner information
- List owner first and last names


### `ORDER BY` clause
- List all pets ordered by name
- List all pets ordered by name in descending order
- List all owners alphabetically by last name
- List all pets by owner_id and name within owner_id



### `WHERE` clause
- List all dogs
- List all pets owned by Rodney Hughes (owner_id 1)
- List all living pets, order by owner


### Multiple tables
- List all pets and their owner names
- List all owners and their living pets


### Inserts
- Insert yourself as a pet owner
- Insert a new, living pet into the pet table with yourself as the owner. Be sure to set the death date as NULL.



### Update
- Change the pet you added to a horse. Be sure to use a WHERE clause so that you do not change the species of all pets in the table.



### Delete
- Delete the pet that you added.  Be sure to use a WHERE clause
