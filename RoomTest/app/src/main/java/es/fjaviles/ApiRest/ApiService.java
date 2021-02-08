package es.fjaviles.ApiRest;

import java.util.ArrayList;

import es.fjaviles.ApiRest.Model.Person;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiService {

    String CONTROLLER_PERSON = "Personas";

    @GET(CONTROLLER_PERSON)
    Call<ArrayList<Person>> getPersons();

    @GET(CONTROLLER_PERSON+"/{id}")
    Call<Person> getPerson(@Path("id") int personId);

    @DELETE(CONTROLLER_PERSON+"/{id}")
    Call<Integer> removePerson(@Path("id") int personId);

    @Headers({"Content-type: application/json"})
    @POST(CONTROLLER_PERSON)
    Call<Integer> addPerson(@Body Person person);

    @Headers({"Content-Type: application/json"})
    @PUT(CONTROLLER_PERSON+"/{id}")
    Call<Integer> modifyPerson(@Path("id") int id, @Body Person body);

}
