package es.fjaviles.Dao;


import androidx.lifecycle.LiveData;
import androidx.room.ColumnInfo;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.PrimaryKey;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import es.fjaviles.Dao.Model.Person;

@Dao
public interface PersonDao {

    @Query("SELECT id, Name, Surname, Birthdate, Photo, Address,Telephone FROM Persons")
    List<Person> getPersons();

    @Query("SELECT id, Name, Surname, Birthdate, Photo, Address,Telephone FROM Persons WHERE id IN (:userIds)")
    List<Person> getPersonsById(int[] userIds);

    @Update
    void updatePersons(Person... users);

    @Insert
    void insertPersons(Person... users);

    @Delete
    void deletePersons(Person user);

}
