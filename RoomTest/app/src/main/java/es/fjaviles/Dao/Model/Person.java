package es.fjaviles.Dao.Model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Persons")
public class Person {

    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "Name")
    private String name;
    @ColumnInfo(name = "Surname")
    private String surname;
    @ColumnInfo(name = "Birthdate")
    private String birthdate;
    @ColumnInfo(name = "Photo")
    private String photo;
    @ColumnInfo(name = "Address")
    private String address;
    @ColumnInfo(name = "Telephone")
    private String telephone;

    public Person(int id, String name, String surname, String birthdate, String photo, String address, String telephone) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.birthdate = birthdate;
        this.photo = photo;
        this.address = address;
        this.telephone = telephone;
    }

    public Person(String name, String surname, String birthdate, String photo, String address, String telephone) {
        this.name = name;
        this.surname = surname;
        this.birthdate = birthdate;
        this.photo = photo;
        this.address = address;
        this.telephone = telephone;
    }

    public Person() {
        this.id = -1;
        this.name = "Defecto";
        this.surname = "Defecto";
        this.birthdate = "";
        this.photo = "";
        this.address = "Defecto";
        this.telephone = "654654654";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}
