package es.fjaviles.ApiRest;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiAdapter {

    private static ApiService API_SERVICE;
    private static final String API_URL = "https://apifron99asp.azurewebsites.net/api/";

    public static ApiService getApiService(){

        /*HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder httpCliente = new OkHttpClient.Builder();
        httpCliente.addInterceptor(logging);*/

        if (API_SERVICE == null){

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(API_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    //.client(httpCliente.build())
                    .build();
            API_SERVICE = retrofit.create(ApiService.class);

        }

        return API_SERVICE;
    }

}
