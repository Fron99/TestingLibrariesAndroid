package es.fjaviles.ApiRest;

import java.util.ArrayList;

import es.fjaviles.ApiRest.Model.Person;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {

    @GET("personas")
    Call<ArrayList<Person>> getPersons();

    @GET("personas")
    Call<Person> getPerson();

    @DELETE("personas/{id}")
    Call<Integer> deletePerson(@Path("id") int personId);

}
