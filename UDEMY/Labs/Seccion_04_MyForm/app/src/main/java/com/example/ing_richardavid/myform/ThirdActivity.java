package com.example.ing_richardavid.myform;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.ing_richardavid.myform.Utils.Functions;
import com.example.ing_richardavid.myform.Utils.Messages;

public class ThirdActivity extends AppCompatActivity implements View.OnClickListener {

    /**
     * Objects, variables and constants.
     */

    public static final String parameterName = "name";
    public static final String parameterGreeting = "greeting";
    public static final String parameterAge = "age";
    private String name;
    private boolean greeting = true;
    private String age;

    /**
     * View components.
     */

    private ImageButton imgBtnGo;
    private Button btnGo;
    private Bundle bundle;

    /**
     * Functions: AppCompatActivity.
     * @param savedInstanceState
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        /**
         * Activate arrow go back.
         */

        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        /**
         * Components registering.
         */

        this.imgBtnGo = (ImageButton)findViewById(R.id.thirdActivityIdImgBtnGo);
        this.imgBtnGo.setOnClickListener(this);

        this.btnGo = (Button)findViewById(R.id.thirdActivityIdBtnShare);

        /**
         * Functions.
         */

        this.loadData();
    }

    /**
     * Functions: OnClickListener.
     */

    @Override
    public void onClick(View view) {
        if (this.imgBtnGo.equals(view)) {
            this.imgBtnGo.setVisibility(View.INVISIBLE);

            this.btnGo.setVisibility(View.VISIBLE);

            this.showMessage();
        }
    }

    /**
     * Functions: Self.
     */

    private void loadData() {
        this.bundle = this.getIntent().getExtras();

        if (this.bundle != null) {
            this.name = this.bundle.getString(ThirdActivity.parameterName);
            this.greeting = this.bundle.getBoolean(ThirdActivity.parameterGreeting);
            this.age = this.bundle.getString(ThirdActivity.parameterAge);
        }
    }

    private void showMessage() {
        if (this.greeting) {
            Functions.toastLong(this, "Hello " + this.name + ", How have you been for " + this.age + " years? " + "#MyForm");
        } else {
            Functions.toastLong(this, "I hope to see you soon " + this.name + ", before you turn " + this.age + " #MyForm");
        }
    }
}