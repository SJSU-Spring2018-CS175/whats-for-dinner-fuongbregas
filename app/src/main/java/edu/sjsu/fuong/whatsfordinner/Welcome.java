package edu.sjsu.fuong.whatsfordinner;

import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;

public class Welcome extends AppCompatActivity {

    private ImageView whatsfordinner;
    private PopupWindow popupWindow;
    private LayoutInflater layoutInflater;
    private ConstraintLayout welcomeLay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        whatsfordinner = (ImageView) findViewById(R.id.whatsfordinner);
        welcomeLay = (ConstraintLayout) findViewById(R.id.welcomeLay);

        whatsfordinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                layoutInflater = (LayoutInflater) getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                ViewGroup container = (ViewGroup) layoutInflater.inflate(R.layout.popuplay, null);

                popupWindow = new PopupWindow(container, 300, 300, true);
                popupWindow.showAtLocation(welcomeLay, Gravity.NO_GRAVITY, 300, 300);

                container.setOnTouchListener(new View.OnTouchListener() {

                    @Override
                    public boolean onTouch(View view, MotionEvent motionEvent) {
                        popupWindow.dismiss();
                        return true;
                    }
                });
            }
        });
    }
}
