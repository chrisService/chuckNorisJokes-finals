package chrisservice.project.chucknorrisjokes.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.renderscript.Sampler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import chrisservice.project.chucknorrisjokes.R;
import chrisservice.project.chucknorrisjokes.model.Joke;

public class JokesAdapter extends RecyclerView.Adapter<JokesAdapter.JokesViewHolder> {

    private List<Joke> jokes;
    Context context;

    public JokesAdapter(List<Joke> jokes, Context context) {
        this.jokes = jokes;
        this.context = context;
    }

    @NonNull
    @Override
    public JokesAdapter.JokesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_joke, parent, false);


        return new JokesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull JokesAdapter.JokesViewHolder holder, int position) {

        final Joke joke = jokes.get(holder.getAdapterPosition());

        holder.tvJokeNumber.setText("Joke #" + String.valueOf(holder.getAdapterPosition() + 1));

        holder.tvJoke.setText(joke.getJoke());

        holder.tvCategory.setText(String.valueOf(joke.getCategories()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, joke.getJoke());
                sendIntent.setType("text/plain");

                Intent shareIntent = Intent.createChooser(sendIntent, null);
                context.startActivity(shareIntent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return jokes.size();
    }

    public class JokesViewHolder extends RecyclerView.ViewHolder {

        TextView tvJokeNumber, tvJoke, tvCategory;


        public JokesViewHolder(@NonNull View itemView) {
            super(itemView);

            tvJokeNumber = itemView.findViewById(R.id.tvJokeNumber);
            tvJoke = itemView.findViewById(R.id.tvJoke);
            tvCategory = itemView.findViewById(R.id.tvCategory);
        }
    }
}
