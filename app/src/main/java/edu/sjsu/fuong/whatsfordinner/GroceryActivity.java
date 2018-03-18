package edu.sjsu.fuong.whatsfordinner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.baoyz.swipemenulistview.SwipeMenuListView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GroceryActivity extends AppCompatActivity {

    private static final String TAG = "GroceryActivity";

    private ArrayList<ArrayList<String>> groceryDish;
    private ArrayList<ArrayList<String>> allDishes;
    private HashMap<String, Integer> mealDish;
    private Spinner spinner;
    private SwipeMenuListView listView;
    private ArrayList<String> spinnerStringList;
    private ArrayList<String> listViewStringList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grocery);

        // Data
        allDishes = getGroceryDish();
        mealDish = getmealDish();
        spinnerStringList = new ArrayList<>();
        spinnerStringList = new ArrayList<>();
        groceryDish       = new ArrayList<>();

        // View settings
        spinner = (Spinner) findViewById(R.id.spinner);
        listView = (SwipeMenuListView) findViewById(R.id.listView);

        for(Map.Entry<String, Integer> e : mealDish.entrySet()) {
            String key = e.getKey();
            Integer value = e.getValue();
            for(int i = 0; i < allDishes.size(); i++){
                if(allDishes.get(i).get(0).equals(key)){
                    String item = value + "X of " + key;
                    System.out.println("item " + item);
                    spinnerStringList.add(item);
                    groceryDish.add(allDishes.get(i));
                }
                else{
                    System.out.println(key + "not included");
                }
            }
        }

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinnerStringList);
        System.out.println("Adapter Length: " + dataAdapter.getCount());
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);


    }

    private ArrayList<ArrayList<String>> getGroceryDish() {
        ArrayList<ArrayList<String>> savedGroceryDishArrayList = null;

        try {
            FileInputStream inputStream = openFileInput("groceryDishes");
            ObjectInputStream in = new ObjectInputStream(inputStream);
            savedGroceryDishArrayList = (ArrayList<ArrayList<String>>) in.readObject();
            in.close();
            inputStream.close();

        }
        catch (FileNotFoundException e){
            savedGroceryDishArrayList = getDishes();
        }
        catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return savedGroceryDishArrayList;
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
