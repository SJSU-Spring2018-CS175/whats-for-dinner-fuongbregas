package edu.sjsu.fuong.whatsfordinner;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class Welcome extends AppCompatActivity {

    private ImageView whatsfordinner;
    private PopupWindow popupWindow;
    private LayoutInflater layoutInflater;
    private ConstraintLayout welcomeLay;

    // Button list
    private Button mealButton;
    private Button receipeButton;
    private Button grocButton;
    private Button newDishButton;

    public ArrayList<ArrayList<String>> allDishes;
    public ArrayList<String> ingredient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        allDishes = getDishes();
        ingredient = getIngredient();

        whatsfordinner = (ImageView) findViewById(R.id.whatsfordinner);
        welcomeLay = (ConstraintLayout) findViewById(R.id.welcomeLay);
        mealButton = (Button) findViewById(R.id.mealButton);
        receipeButton = (Button) findViewById(R.id.recipesButton);
        grocButton = (Button) findViewById(R.id.groceriesButton);
        newDishButton = (Button) findViewById(R.id.newDishButton);

        whatsfordinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Welcome.this, PopupAct.class));
            }
        });

        mealButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mealIntent = new Intent(Welcome.this, MealActivity.class);
                startActivity(mealIntent);
            }
        });

        newDishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newDish = new Intent(Welcome.this, NewDishActivity.class);
                // Send ArrayList
                newDish.putStringArrayListExtra("ingredientList", (ArrayList<String>) ingredient);
                newDish.putExtra("dishList", (ArrayList<ArrayList<String>>) allDishes);
                startActivity(newDish);
            }
        });

        receipeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }


    private ArrayList<ArrayList<String>> getDishes() {
        ArrayList<ArrayList<String>> savedDishedArrayList = null;

        try {
            FileInputStream inputStream = openFileInput("dishesArrayList");
            ObjectInputStream in = new ObjectInputStream(inputStream);
            savedDishedArrayList = (ArrayList<ArrayList<String>>) in.readObject();
            in.close();
            inputStream.close();

        }
        catch (FileNotFoundException e){
            savedDishedArrayList = new ArrayList<>();
        }
        catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return savedDishedArrayList;
    }

    private ArrayList<String> getIngredient() {
        ArrayList<String> savedIngredientList = null;

        try {
            FileInputStream inputStream = openFileInput("ingredientArrayList");
            ObjectInputStream in = new ObjectInputStream(inputStream);
            savedIngredientList = (ArrayList<String>) in.readObject();
            in.close();
            inputStream.close();

        }
        catch (FileNotFoundException fnf){
            savedIngredientList = new ArrayList<>();
        }
        catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return savedIngredientList;
    }
}
