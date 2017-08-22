package com.example.ing_richardavid.myform;

import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.ing_richardavid.myform.Utils.Functions;
import com.example.ing_richardavid.myform.Utils.Messages;

public class SecondActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener, SeekBar.OnSeekBarChangeListener, View.OnClickListener {

    /**
     * Objects, variables and constants.
     */

    public static final String parameterName = "name";
    private boolean greetingSelected = true;
    private final int AGE_MIN = 16;
    private final int AGE_MAX = 60;

    /**
     * View components.
     */

    private TextView txtVName;
    private RadioGroup rgGreetings;
    private RadioButton rbGreeting;
    private RadioButton rbFarewell;
    private SeekBar sbAge;
    private TextView txtVAgeValue;
    private Button btnOk;
    private Bundle bundle;
    private Intent intent;

    /**
     * Functions: AppCompatActivity.
     * @param savedInstanceState
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        /**
         * Activate arrow go back.
         */

        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        /**
         * Components registering.
         */

        this.txtVName = (TextView)findViewById(R.id.secondActivityIdTxtVName);

        this.sbAge = (SeekBar)findViewById(R.id.secondActivityIdSbAge);
        this.sbAge.setOnSeekBarChangeListener(this);

        this.rgGreetings = (RadioGroup)findViewById(R.id.secondActivityIdRgGreetings);
        this.rgGreetings.setOnCheckedChangeListener(this);

        this.rbGreeting = (RadioButton)findViewById(R.id.secondActivityIdRbGreeting);

        this.rbFarewell = (RadioButton)findViewById(R.id.secondActivityIdRbFarewell);

        this.txtVAgeValue = (TextView)findViewById(R.id.secondActivityIdTxtVAgeValue);

        this.btnOk = (Button)findViewById(R.id.secondActivityIdBtnOk);
        this.btnOk.setOnClickListener(this);

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
        if (this.btnOk.equals(view)) {
            if (this.validateFields()) {
                this.intent = new Intent(this, ThirdActivity.class);
                this.intent.putExtra(ThirdActivity.parameterName, this.txtVName.getText().toString());
                this.intent.putExtra(ThirdActivity.parameterGreeting, this.greetingSelected);
                this.intent.putExtra(ThirdActivity.parameterAge, this.txtVAgeValue.getText().toString());

                this.startActivity(this.intent);
            }
        }
    }

    /**
     * Functions: OnCheckedChangeListener.
     */

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
        if (this.rgGreetings.equals(radioGroup)) {
            switch (i) {
                case R.id.secondActivityIdRbGreeting:
                    this.greetingSelected = true;

                    break;
                case R.id.secondActivityIdRbFarewell:
                    this.greetingSelected = false;

                    break;
                default:
                    break;
            }
        }
    }

    /**
     * Functions: OnSeekBarChangeListener.
     */

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        if (this.sbAge.equals(seekBar)) {
            this.txtVAgeValue.setText(String.valueOf(i));
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    /**
     * Functions: Self.
     */

    private void loadData() {
        this.bundle = this.getIntent().getExtras();

        if (this.bundle != null) {
            this.txtVName.setText(this.bundle.getString(SecondActivity.parameterName));
        }
    }

    private boolean validateFields() {
        if (Integer.parseInt(this.txtVAgeValue.getText().toString()) < this.AGE_MIN || Integer.parseInt(this.txtVAgeValue.getText().toString()) > this.AGE_MAX) {
            Functions.toastShort(this, Messages.SECOND_ACTIVITY_AGE_LIMIT);

            return false;
        }

        return true;
    }
}