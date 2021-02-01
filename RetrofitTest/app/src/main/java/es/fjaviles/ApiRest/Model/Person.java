
package es.fjaviles.ApiRest.Model;

import androidx.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.lang.reflect.GenericArrayType;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Person {

    @SerializedName("Id")
    @Expose
    private int id;
    @SerializedName("Nombre")
    @Expose
    private String nombre;
    @SerializedName("Apellidos")
    @Expose
    private String apellidos;
    @SerializedName("FechaNacimiento")
    @Expose
    private String fechaNacimiento;
    @SerializedName("Foto")
    @Expose
    private String foto;
    @SerializedName("Direccion")
    @Expose
    private String direccion;
    @SerializedName("Telefono")
    @Expose
    private String telefono;
    @SerializedName("IdDepartamento")
    @Expose
    private int idDepartamento;

    public Person(){
        id = -1;
        nombre = "";
        apellidos = "";
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        fechaNacimiento = simpleDateFormat.format(new Date());
        foto = "";
        direccion = "";
        telefono = "";
        idDepartamento = 1;

    }

    public Person(int id, String nombre, String apellidos, String fechaNacimiento, String foto, String direccion, String telefono, int idDepartamento){
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fechaNacimiento = fechaNacimiento;
        this.foto = foto;
        this.direccion = direccion;
        this.telefono = telefono;
        this.idDepartamento = idDepartamento;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Person withId(int id) {
        this.id = id;
        return this;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Person withNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public Person withApellidos(String apellidos) {
        this.apellidos = apellidos;
        return this;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Person withFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
        return this;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public Person withFoto(String foto) {
        this.foto = foto;
        return this;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Person withDireccion(String direccion) {
        this.direccion = direccion;
        return this;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Person withTelefono(String telefono) {
        this.telefono = telefono;
        return this;
    }

    public int getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(int idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

    public Person withIdDepartamento(int idDepartamento) {
        this.idDepartamento = idDepartamento;
        return this;
    }

}
