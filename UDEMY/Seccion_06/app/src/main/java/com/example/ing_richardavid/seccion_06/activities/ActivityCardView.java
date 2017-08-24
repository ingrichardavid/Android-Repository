package com.example.ing_richardavid.seccion_06.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ing_richardavid.seccion_06.R;

public class ActivityCardView extends AppCompatActivity implements View.OnClickListener {

    /**
     * Objects, variables and contants.
     */

    private final String titleView = "Simple CardView";

    /**
     * View components.
     */

    private TextView txtVMessage;
    private Button btnGoToRVAndCV;
    private Intent intent;

    /**
     * Functions: AppCompatActivity.
     * @param savedInstanceState
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_view);

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

        this.txtVMessage = (TextView)findViewById(R.id.activityCVIdTVMessage);
        this.txtVMessage.setText("¡Hola! Así se usa un CardView.");

        this.btnGoToRVAndCV = (Button)findViewById(R.id.activityCVIdBtnGoToRVAndCV);
        this.btnGoToRVAndCV.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (this.btnGoToRVAndCV.equals(view)) {
            this.intent = new Intent(this, ActivityRecyclerViewAndCardView.class);

            this.startActivity(this.intent);
        }
    }
}
