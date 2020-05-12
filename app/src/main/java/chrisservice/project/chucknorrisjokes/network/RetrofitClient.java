package chrisservice.project.chucknorrisjokes.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static final String BASE_URL = "https://api.icndb.com/";

    private static JokeApi jokeApi;

    public static JokeApi getJokeApi() {

        if (jokeApi == null) {
            createJokeApi();
        }
        return jokeApi;

    }

    private static void createJokeApi() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        jokeApi = retrofit.create(JokeApi.class);

    }


}
