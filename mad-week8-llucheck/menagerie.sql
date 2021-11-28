--drop tables if exist
DROP TABLE IF EXISTS pet;
DROP TABLE IF EXISTS owner;
DROP TABLE IF EXISTS daycare;

--create new tables
CREATE TABLE owner (
  id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,
  first_name TEXT,
  last_name TEXT,
  email TEXT
);

CREATE TABLE pet (
  id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,
  name TEXT,
  species TEXT,
  sex TEXT,
  birth_date TEXT,
  death_date TEXT,
  owner_id INTEGER,
  CONSTRAINT petfk_pet_id FOREIGN KEY(owner_id) REFERENCES owner(id)
);



--create indexes
CREATE INDEX idx_pet_name ON pet(name);
CREATE INDEX idx_owner_last ON owner(last_name);
CREATE UNIQUE INDEX idx_owner_email ON owner(email);


--insert data into tables
INSERT INTO owner(first_name, last_name, email) VALUES("Rodney","Hughes","rodney@gmail.com");
INSERT INTO owner(first_name, last_name, email) VALUES("Irma","Riley","ir@outlook.com");
INSERT INTO owner(first_name, last_name, email) VALUES("Lisa","Brock","lbrock@lewisu.edu");
INSERT INTO owner(first_name, last_name, email) VALUES("Lora","Carpenter","carpenter@yahoo.com");
INSERT INTO owner(first_name, last_name, email) VALUES("Hannah","Hubbard","hh@gmail.com");
INSERT INTO owner(first_name, last_name, email) VALUES("Johnnie","Ruiz","jr@outlook.com");

INSERT INTO pet(name, species, sex, birth_date, death_date, owner_id) VALUES("Bella","Dog","F","2005-11-08", "2017-10-01", 1);
INSERT INTO pet(name, species, sex, birth_date,  death_date, owner_id) VALUES("Bailey","Dog","F","2015-09-16",null, 1);
INSERT INTO pet(name, species, sex, birth_date,  death_date, owner_id) VALUES("Max","Cat","M","2011-01-14",null, 3);
INSERT INTO pet(name, species, sex, birth_date,  death_date, owner_id) VALUES("Zeus","Dog","M","2013-06-15",null, 2);
INSERT INTO pet(name, species, sex, birth_date,  death_date, owner_id) VALUES("Milo","Dog","M","2011-03-22",null, 4);
INSERT INTO pet(name, species, sex, birth_date,  death_date, owner_id) VALUES("Simba","Cat","M","2010-05-02",null, 5);
INSERT INTO pet(name, species, sex, birth_date,  death_date, owner_id) VALUES("Chloe","Cat","F","2011-02-22",null, 6);
