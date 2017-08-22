package com.example.ing_richardavid.myform;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.ing_richardavid.myform.Utils.Functions;
import com.example.ing_richardavid.myform.Utils.Messages;

public class InitialActivity extends AppCompatActivity implements View.OnClickListener {

    /**
     * View components.
     */

    private EditText editTxtName;
    private Button btnCancel;
    private Button btnOk;
    private Intent intent;

    /**
     * Functions: AppCompatActivity.
     * @param savedInstanceState
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial);

        /**
         * Force and change icon in action bar.
         */

        this.getSupportActionBar().setDisplayShowHomeEnabled(true);
        this.getSupportActionBar().setIcon(R.mipmap.ic_myicon);

        /**
         * Components registering.
         */

        this.editTxtName = (EditText)findViewById(R.id.initialActivityIdEditTxt);

        this.btnCancel = (Button)findViewById(R.id.initialActivityIdBtnCancel);
        this.btnCancel.setOnClickListener(this);

        this.btnOk = (Button)findViewById(R.id.initialActivityIdBtnOk);
        this.btnOk.setOnClickListener(this);
    }

    /**
     * Functions: OnClickListener.
     */

    @Override
    public void onClick(View view) {
        if (this.btnCancel.equals(view)) {
            this.cleanFields();
        } else if (this.btnOk.equals(view)) {
            if (this.validateFields()) {
                this.intent = new Intent(this, SecondActivity.class);
                this.intent.putExtra(SecondActivity.parameterName, this.editTxtName.getText().toString());

                this.startActivity(this.intent);
            }
        }
    }

    /**
     * Functions: Self.
     */

    private boolean validateFields() {
        if (this.editTxtName.getText().toString().isEmpty()) {
            Functions.toastShort(this, Messages.INITIAL_ACTIVITY_EMPTY_NAME);

            this.editTxtName.requestFocus();

            return false;
        }

        return true;
    }

    private void cleanFields() {
        this.editTxtName.setText("");
    }
}