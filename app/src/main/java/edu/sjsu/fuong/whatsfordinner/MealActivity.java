package edu.sjsu.fuong.whatsfordinner;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MealActivity extends AppCompatActivity
{
    private ArrayList<String> dishNameArrayList;
    private ArrayAdapter<String> dishNameAdapter;
    private HashMap<String, Integer> mealDish;

    // UI
    private Spinner mondayBreakfastSpinner;
    private Spinner mondayLunchSpinner;
    private Spinner mondayDinnerSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal);

        mealDish = getmealDish();
        dishNameArrayList = new ArrayList<>();
        dishNameArrayList.add("Eating Out");
        final int dishTotal = dishNameArrayList.size();
        for(Map.Entry<String, Integer> e : mealDish.entrySet()) {
            String key = e.getKey();
            Integer value = e.getValue();

            while(value != 0){
                dishNameArrayList.add(key);
                value--;
            }
        }

        dishNameAdapter = new ArrayAdapter<String>(MealActivity.this, android.R.layout.simple_spinner_item, dishNameArrayList);
        dishNameAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // UI
        mondayBreakfastSpinner = (Spinner) findViewById(R.id.mondayBreakfastSpinner);
        mondayBreakfastSpinner.setPrompt("Eating Out");
        mondayBreakfastSpinner.setAdapter(dishNameAdapter);

        mondayBreakfastSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String itemAt = mondayBreakfastSpinner.getItemAtPosition(i).toString();

                if(itemAt.equals("Eating Out")){

                }
                else{
                    dishNameArrayList.remove(i);
                    mondayBreakfastSpinner.setEnabled(false);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        mondayLunchSpinner = (Spinner) findViewById(R.id.mondayLunchSpinner);
        mondayLunchSpinner.setPrompt("Eating Out");
        mondayLunchSpinner.setAdapter(dishNameAdapter);

        mondayDinnerSpinner = (Spinner) findViewById(R.id.mondayDinnerSpinner);
        mondayDinnerSpinner.setPrompt("Eating Out");
        mondayDinnerSpinner.setAdapter(dishNameAdapter);

    }


    // HashMap contains dishName and Quantity
    private void saveMealDish(HashMap<String, Integer> mealDish) {
        try {
            FileOutputStream fileOutputStream = openFileOutput("mealDishHashMap", Context.MODE_PRIVATE);
            ObjectOutputStream out = new ObjectOutputStream(fileOutputStream);
            out.writeObject(mealDish);
            out.close();
            fileOutputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private HashMap<String, Integer> getmealDish() {
        HashMap<String, Integer> savedmealDish = null;

        try {
            FileInputStream inputStream = openFileInput("mealDishHashMap");
            ObjectInputStream in = new ObjectInputStream(inputStream);
            savedmealDish = (HashMap<String, Integer>) in.readObject();
            in.close();
            inputStream.close();

        }
        catch (FileNotFoundException fnf){
            savedmealDish = new HashMap<>();
        }
        catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return savedmealDish;
    }




}
