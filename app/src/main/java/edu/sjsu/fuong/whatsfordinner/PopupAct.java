package edu.sjsu.fuong.whatsfordinner;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;

/**
 * Created by franc on Feb/23/2018.
 */

public class PopupAct extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.popuplay);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int widthDM = dm.widthPixels;
        int heightDM = dm.heightPixels;

        getWindow().setLayout((int)(widthDM*.8),(int)(heightDM*.25));
    }
}
