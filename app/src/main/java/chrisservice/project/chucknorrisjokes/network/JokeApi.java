package chrisservice.project.chucknorrisjokes.network;

import chrisservice.project.chucknorrisjokes.model.GetJokesResponse;
import retrofit2.Call;
import retrofit2.http.GET;

public interface JokeApi {

    @GET("jokes/random/20")
    Call<GetJokesResponse> getRandomJokes();


}
