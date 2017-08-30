package com.example.ing_richardavid.seccion_06_movies.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.ing_richardavid.seccion_06_movies.R;
import com.example.ing_richardavid.seccion_06_movies.adapters.AdapterListMovies;
import com.example.ing_richardavid.seccion_06_movies.adapters.interfaces.OnItemClickListener;
import com.example.ing_richardavid.seccion_06_movies.models.entities.Movie;

import java.util.ArrayList;
import java.util.List;

public class ActivityMain extends AppCompatActivity implements OnItemClickListener {

    /**
     * Objects, variables and constants.
     */

    private String titleView = "Movies";
    private List<Movie> listMovies;

    /**
     * View components.
     */

    private RecyclerView rvListMovies;
    private RecyclerView.Adapter rvAListMovies;
    private RecyclerView.LayoutManager rvLListMovies;

    /**
     * Functions: AppCompatActivity.
     * @param savedInstanceState
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * Add icon and title at status bar.
         */

        this.getSupportActionBar().setIcon(R.mipmap.ic_myicon);
        this.getSupportActionBar().setDisplayUseLogoEnabled(true);
        this.getSupportActionBar().setDisplayShowHomeEnabled(true);
        this.getSupportActionBar().setTitle(this.titleView);

        /**
         * Components registering.
         */

        this.listMovies = new ArrayList<Movie>() {{
            add(new Movie("John Wick", "Película de acción." , 0, R.drawable.movie_john_wick));
            add(new Movie("Momentum", "Película de acción", 0, R.drawable.movie_momentum));
            add(new Movie("Insurgent", "Ciencia ficción", 0, R.drawable.movie_insurgent));
            add(new Movie("Steve Jobs", "Película biográfica", 0, R.drawable.movie_steve_jobs));
            add(new Movie("The Accountant", "Película de acción", 0, R.drawable.movie_the_accountant));
        }};

        this.rvLListMovies = new LinearLayoutManager(this);

        this.rvAListMovies = new AdapterListMovies(this.listMovies, this, R.layout.layout_movies, this);

        this.rvListMovies = (RecyclerView)findViewById(R.id.activityMainIDRVMovies);
        this.rvListMovies.setAdapter(this.rvAListMovies);
        this.rvListMovies.setLayoutManager(this.rvLListMovies);
        this.rvListMovies.setHasFixedSize(true);
        this.rvListMovies.setItemAnimator(new DefaultItemAnimator());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.getMenuInflater().inflate(R.menu.menu_options, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuOptionsIdAddItem:
                this.addMovie();

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * Functions: OnItemClickListener.
     */

    @Override
    public void onItemClick(Object object, int position) {
        if (this.listMovies.get(position).getQuantity() < Movie.LIMIT_QUANTITY) {
            this.listMovies.get(position).setQuantity(this.listMovies.get(position).getQuantity() + 1);

            this.rvAListMovies.notifyDataSetChanged();
        }
    }

    /**
     * Functions: Self.
     */

    private void addMovie() {
        this.listMovies.add(new Movie("Desconocida", "No disponible", 0, R.drawable.movie_unknown));

        this.rvAListMovies.notifyItemInserted(this.listMovies.size() - 1);

        this.rvLListMovies.scrollToPosition(this.listMovies.size() - 1);
    }
}