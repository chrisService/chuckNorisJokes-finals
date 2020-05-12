package chrisservice.project.chucknorrisjokes.fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import java.util.List;

import chrisservice.project.chucknorrisjokes.R;
import chrisservice.project.chucknorrisjokes.adapters.JokesAdapter;
import chrisservice.project.chucknorrisjokes.model.GetJokesResponse;
import chrisservice.project.chucknorrisjokes.model.Joke;
import chrisservice.project.chucknorrisjokes.network.JokeApi;
import chrisservice.project.chucknorrisjokes.network.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * A simple {@link Fragment} subclass.
 */
public class RandomJokesFragment extends Fragment {

    RecyclerView recyclerView;

    ProgressBar progressBar;

    SwipeRefreshLayout swipe;


    public RandomJokesFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_random_jokes, container, false);

        progressBar = rootView.findViewById(R.id.progresBar);

        recyclerView = rootView.findViewById(R.id.rvRandomJokes);

        swipe = rootView.findViewById(R.id.swipe);


        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                retrofitEnquer();
            }


        });

        LinearLayoutManager llManager = new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false);

        recyclerView.setLayoutManager(llManager);

        retrofitEnquer();


        return rootView;
    }


    private void retrofitEnquer() {


        RetrofitClient.getJokeApi().getRandomJokes().enqueue(new Callback<GetJokesResponse>() {
            @Override
            public void onResponse(Call<GetJokesResponse> call, Response<GetJokesResponse> response) {

                List<Joke> jokes = response.body().getValue();

                JokesAdapter jokesAdapter = new JokesAdapter(jokes, getContext());

                recyclerView.setAdapter(jokesAdapter);


                progressBar.setVisibility(View.GONE);

                recyclerView.setVisibility(View.VISIBLE);

                swipe.setRefreshing(false);

            }

            @Override
            public void onFailure(Call<GetJokesResponse> call, Throwable t) {

            }
        });


    }

}
