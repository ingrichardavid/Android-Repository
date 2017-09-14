package com.example.ing_richardavid.ejemplo03as;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.graphics.drawable.DrawableWrapper;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

public class activity_ejemplo03as extends Activity {

    /**
     * Objects, variables and constants.
     */

    private boolean clic = true;
    private Drawable drawableColorWhite = new ColorDrawable(Color.WHITE);

    /**
     * Components.
     */

    private TextView txtVUsuario;
    private EditText editTUsuario;
    private TextView txtVClave;
    private EditText editTClave;
    private TextView txtVMensaje;
    private LinearLayout linearLayout;
    private LinearLayout.LayoutParams layoutParams;
    private LinearLayout mainLinearLayout;
    private PopupWindow popupWindow;
    private Button btnAceptar;
    private Button btnLimpiar;

    /**
     * Functions: Activity.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.txtVUsuario = new TextView(this);
        this.txtVUsuario.setText("Usuario: ");

        this.editTUsuario = new EditText(this);

        this.txtVClave = new TextView(this);
        this.txtVClave.setText("Clave:");

        this.editTClave = new EditText(this);

        this.mainLinearLayout = new LinearLayout(this);
        this.mainLinearLayout.setOrientation(LinearLayout.HORIZONTAL);

        this.layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );

        this.mainLinearLayout.addView(this.txtVUsuario, this.layoutParams);
        this.mainLinearLayout.addView(this.editTUsuario, this.layoutParams);
        this.mainLinearLayout.addView(this.txtVClave, this.layoutParams);
        this.mainLinearLayout.addView(this.editTClave, this.layoutParams);

        this.btnAceptar = new Button(this);
        this.btnAceptar.setText("Aceptar");

        this.btnLimpiar = new Button(this);
        this.btnLimpiar.setText("Limpiar");

        this.txtVMensaje = new TextView(this);

        this.linearLayout = new LinearLayout(this);
        this.linearLayout.setOrientation(LinearLayout.VERTICAL);
        this.linearLayout.addView(this.txtVMensaje, this.layoutParams);

        this.popupWindow = new PopupWindow(this);
        this.popupWindow.setContentView(this.linearLayout);

        this.mainLinearLayout.addView(this.btnAceptar, this.layoutParams);
        this.mainLinearLayout.addView(this.btnLimpiar, this.layoutParams);

        this.setContentView(this.mainLinearLayout);

        this.btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (clic) {
                    txtVMensaje.setText("Usuario: " + editTUsuario.getText().toString() + "\n" + "Clave: " + editTClave.getText().toString());

                    popupWindow.showAtLocation(linearLayout, Gravity.CENTER_HORIZONTAL, 0, 0);
                    popupWindow.update(0, 0, 400, 100);

                    clic = false;
                } else {
                    popupWindow.dismiss();

                    clic = true;
                }
            }
        });

        this.btnLimpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editTUsuario.setText("");
                editTUsuario.requestFocus();

                editTClave.setText("");

                popupWindow.setBackgroundDrawable(drawableColorWhite);
            }
        });

        //setContentView(R.layout.activity_ejemplo03as);
    }
}
