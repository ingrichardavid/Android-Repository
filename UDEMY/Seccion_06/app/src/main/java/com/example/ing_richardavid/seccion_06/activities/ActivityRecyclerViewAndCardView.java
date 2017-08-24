package com.example.ing_richardavid.seccion_06.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.ing_richardavid.seccion_06.R;
import com.example.ing_richardavid.seccion_06.adapters.AdapterListMovies;
import com.example.ing_richardavid.seccion_06.adapters.interfaces.OnItemClickListener;
import com.example.ing_richardavid.seccion_06.models.entities.Movie;

import java.util.ArrayList;
import java.util.List;

public class ActivityRecyclerViewAndCardView extends AppCompatActivity implements OnItemClickListener {

    /**
     * Objects, variables and contants.
     */

    private final String titleView = "RecyclerView And CardView";
    private List<Movie> listMovies;
    private int counter = 0;

    /**
     * View components.
     */

    private RecyclerView rvListMovies;
    private RecyclerView.Adapter rvAListMovies;
    private RecyclerView.LayoutManager rvLListMovies;
    private MenuInflater menuInflater;

    /**
     * Functions: AppCompatActivity.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_and_card_view);

        /**
         * Add back button.
         */

        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        /**
         * Add title view.
         */

        this.getSupportActionBar().setTitle(this.titleView);

        /**
         * Components registering.
         */

        this.listMovies = new ArrayList<Movie>() {{
            add(new Movie("John Wick", R.drawable.movie_john_wick));
            add(new Movie("Momentum", R.drawable.movie_momentum));
            add(new Movie("Insurgent", R.drawable.movie_insurgent));
            add(new Movie("Steve Jobs", R.drawable.movie_steve_jobs));
            add(new Movie("The Accountant", R.drawable.movie_the_accountant));
        }};

        this.rvLListMovies = new LinearLayoutManager(this);

        this.rvAListMovies = new AdapterListMovies(this.listMovies, R.layout.layout_list_movies, this);

        this.rvListMovies = (RecyclerView)findViewById(R.id.activityRCAndCVIdRV);
        this.rvListMovies.setAdapter(this.rvAListMovies);
        this.rvListMovies.setLayoutManager(this.rvLListMovies);
        this.rvListMovies.setHasFixedSize(true);
        this.rvListMovies.setItemAnimator(new DefaultItemAnimator());
    }

    /**
     * Functions: OnItemClickListener.
     */

    @Override
    public void onItemClick(Object object, int position) {
        this.deleteMovie(position);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.menuInflater = this.getMenuInflater();
        this.menuInflater.inflate(R.menu.menu_options, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuOptionsIdAddItem:
                this.addMovie(0);

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * Functions: Self.
     */

    private void addMovie(int position) {
        this.listMovies.add(position, new Movie("Unknown: " + (++counter), R.drawable.movie_unknown));

        this.rvAListMovies.notifyItemInserted(position);

        this.rvLListMovies.scrollToPosition(position);
    }

    private void deleteMovie(int position) {
        this.listMovies.remove(position);

        this.rvAListMovies.notifyItemRemoved(position);
    }
}
