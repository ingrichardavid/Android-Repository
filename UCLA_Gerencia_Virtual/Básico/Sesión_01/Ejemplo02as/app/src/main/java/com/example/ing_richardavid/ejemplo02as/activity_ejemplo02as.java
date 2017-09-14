package com.example.ing_richardavid.ejemplo02as;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class activity_ejemplo02as extends AppCompatActivity {

    /**
     * Components.
     */

    private LinearLayout linearLayout;
    private TextView textView;
    private EditText editText;
    private LinearLayout.LayoutParams layoutParams;

    /**
     * Functions: AppCompatActivity.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.linearLayout = new LinearLayout(this);
        this.linearLayout.setOrientation(LinearLayout.HORIZONTAL);

        this.textView = new TextView(this);
        this.textView.setText("Usuario: ");

        this.editText = new EditText(this);
        this.editText.setText("");

        this.layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);

        this.linearLayout.addView(this.textView, this.layoutParams);
        this.linearLayout.addView(this.editText, this.layoutParams);

        this.setContentView(this.linearLayout);
        //setContentView(R.layout.activity_ejemplo02as);
    }

}
