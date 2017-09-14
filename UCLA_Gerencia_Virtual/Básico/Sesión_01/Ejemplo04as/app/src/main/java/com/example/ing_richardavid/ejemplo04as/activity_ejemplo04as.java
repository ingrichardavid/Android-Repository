package com.example.ing_richardavid.ejemplo04as;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

public class activity_ejemplo04as extends AppCompatActivity {

    /**
     * Objects, variables and constants.
     */

    private boolean clic = true;

    /**
     * Components.
     */

    private TextView txtVMensaje;
    private EditText editTUsuario;
    private EditText editTClave;
    private Button btnAceptar;
    private Button btnLimpiar;
    private Button btnSalir;
    private LinearLayout linearLayout;
    private LinearLayout.LayoutParams layoutParams;
    private PopupWindow popupWindow;

    /**
     * Functions: AppCompatActivity.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejemplo04as);

        /**
         * Components registering.
         */

        this.editTUsuario = (EditText)findViewById(R.id.editTxtIdUsuario);
        this.editTClave = (EditText)findViewById(R.id.editTxtIdClave);
        this.btnAceptar = (Button)findViewById(R.id.btnIdAceptar);
        this.btnLimpiar = (Button)findViewById(R.id.btnIdLimpiar);
        this.btnSalir = (Button)findViewById(R.id.btnIdSalir);
        this.txtVMensaje = new TextView(this);

        this.layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );

        this.linearLayout = new LinearLayout(this);
        this.linearLayout.setOrientation(LinearLayout.VERTICAL);
        this.linearLayout.addView(this.txtVMensaje, this.layoutParams);

        this.popupWindow = new PopupWindow(this);
        this.popupWindow.setContentView(this.linearLayout);

        this.btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (clic) {
                    if (!editTUsuario.getText().toString().isEmpty() && !editTClave.getText().toString().isEmpty()) {
                        txtVMensaje.setText("Usuario: " + editTUsuario.getText().toString() + "\n" + "Clave: " + editTClave.getText().toString());

                        popupWindow.showAtLocation(linearLayout, Gravity.CENTER_HORIZONTAL, 0, 0);
                        popupWindow.update(0, 0, 400, 100);

                        clic = false;
                    }
                } else {
                    popupWindow.dismiss();

                    clic = true;
                }
            }
        });

        this.btnLimpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();

                editTUsuario.setText("");
                editTUsuario.requestFocus();

                editTClave.setText("");
            }
        });

        this.btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
