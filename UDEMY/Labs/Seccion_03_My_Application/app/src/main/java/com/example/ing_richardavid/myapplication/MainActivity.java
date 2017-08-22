package com.example.ing_richardavid.myapplication;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.johnpersano.supertoasts.library.Style;
import com.github.johnpersano.supertoasts.library.SuperActivityToast;
import com.github.johnpersano.supertoasts.library.utils.PaletteUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Test.myToast(this, "On Create");

        SuperActivityToast.create(this, new Style(), Style.TYPE_BUTTON)
                .setButtonText("UNDO")
                .setOnButtonClickListener("good_tag_name", null, null)
                .setProgressBarColor(Color.WHITE)
                .setText("Email deleted")
                .setDuration(Style.DURATION_LONG)
                .setFrame(Style.FRAME_LOLLIPOP)
                .setColor(PaletteUtils.getSolidColor(PaletteUtils.MATERIAL_PURPLE))
                .setAnimations(Style.ANIMATIONS_POP).show();
    }

    @Override
    protected void onStart() {
        super.onStart();

        Test.myToast(this, "On Start");
    }

    @Override
    protected void onResume() {
        super.onResume();

        Test.myToast(this, "On Resume");
    }

    @Override
    protected void onPause() {
        super.onPause();

        Test.myToast(this, "On Pause");
    }

    @Override
    protected void onStop() {
        super.onStop();

        Test.myToast(this, "On Stop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        Test.myToast(this, "On Restart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Test.myToast(this, "On Destroy");
    }
}
