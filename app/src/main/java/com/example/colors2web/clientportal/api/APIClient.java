package com.example.colors2web.clientportal.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class APIClient {

    private static Retrofit retrofit = null;

    public static Retrofit getClient() {

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

//        Gson b_deserializer = new GsonBuilder().setLenient()
//                .registerTypeAdapter(OrderDetails.class, new BoxdeSerializer()).
//                        create();


        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        //OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        OkHttpClient.Builder builder = new OkHttpClient.Builder().addInterceptor(interceptor);
        builder.connectTimeout(5, TimeUnit.MINUTES)
                .writeTimeout(5, TimeUnit.MINUTES)
                .readTimeout(5, TimeUnit.MINUTES);

        OkHttpClient client = builder.build();


        retrofit = new Retrofit.Builder()
//           .baseUrl("http://192.168.0.109/zummix/zummix-api/public/")
//             .baseUrl("http://2db93c8c.ngrok.io/zummix-api/public/")
//                .baseUrl("http://192.168.0.116:8080/api/")
                .baseUrl("https://zumsapi.com/")
                .addConverterFactory(GsonConverterFactory.create(gson))
//                .addConverterFactory(GsonConverterFactory.create(b_deserializer))
                .client(client)
                .build();

        return retrofit;
    }
//    FOr local host run your project with your ip address with hosts in laravel
//    php artisan serve --host 192.168.0.110 --port 8080

}
