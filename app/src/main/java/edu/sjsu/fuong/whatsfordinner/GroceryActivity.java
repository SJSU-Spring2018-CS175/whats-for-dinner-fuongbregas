package edu.sjsu.fuong.whatsfordinner;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;

public class GroceryActivity extends AppCompatActivity {

    private static final String TAG = "GroceryActivity";

    private ArrayList<ArrayList<String>> groceryDish;
    private ArrayList<ArrayList<String>> allDishes;
    private HashMap<String, Integer> mealDish;
    private Spinner spinner;
    private ArrayAdapter<String> dataAdapter;
    private SwipeMenuListView listView;
    private ArrayList<String> spinnerStringList;
    private ArrayList<String> listViewStringList;
    private ArrayAdapter<String> ingredientAdapter;
    private String dishName = "";
    private Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grocery);

        // Data
        allDishes = getGroceryDish();
        mealDish = getmealDish();
        spinnerStringList = new ArrayList<>();

        groceryDish       = new ArrayList<>();

        // View settings
        spinner = (Spinner) findViewById(R.id.spinner);
        listView = (SwipeMenuListView) findViewById(R.id.listView);
        saveButton = (Button) findViewById(R.id.saveButton);

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

        dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinnerStringList);
        System.out.println("Adapter Length: " + dataAdapter.getCount());
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(final AdapterView<?> adapterView, View view, int id, long l) {

                ArrayList<String> adapterString = cutStringToWord(spinner.getItemAtPosition(id).toString());
                System.out.println(spinner.getItemAtPosition(id).toString());

                for(int j = 2; j < adapterString.size(); j++){
                    dishName += adapterString.get(j) + " ";
                }
                System.out.println("dishName Groc: " + dishName);
                System.out.println("groceryDish size: " + groceryDish.size());

                final ArrayList<String> currentDishIngredients = new ArrayList<>();

                currentDishIngredients.add(groceryDish.get(id).get(1));
                currentDishIngredients.add(groceryDish.get(id).get(2));
                currentDishIngredients.add(groceryDish.get(id).get(3));
                currentDishIngredients.add(groceryDish.get(id).get(4));
                currentDishIngredients.add(groceryDish.get(id).get(5));
                currentDishIngredients.add(groceryDish.get(id).get(6));
                currentDishIngredients.add(groceryDish.get(id).get(7));
                currentDishIngredients.add(groceryDish.get(id).get(8));
                currentDishIngredients.add(groceryDish.get(id).get(9));
                currentDishIngredients.add(groceryDish.get(id).get(10));



                for(int j = 0; j < currentDishIngredients.size(); j++){
                    System.out.println("currentDishIngredients: " + currentDishIngredients.get(j));
                }

                ingredientAdapter = new ArrayAdapter<String>(GroceryActivity.this, android.R.layout.simple_list_item_1,
                                                                                currentDishIngredients);
                listView.setAdapter(ingredientAdapter);


                SwipeMenuCreator creator = new SwipeMenuCreator() {

                    @Override
                    public void create(SwipeMenu menu) {
                        // create "open" item
                        SwipeMenuItem openItem = new SwipeMenuItem(
                                getApplicationContext());
                        // set item background
                        openItem.setBackground(new ColorDrawable(Color.rgb(0x00, 0x99,
                                0x00)));
                        // set item width
                        openItem.setWidth(70);


                        openItem.setIcon(R.drawable.addd);
                        // add to menu
                        menu.addMenuItem(openItem);

                        // create "delete" item
                        SwipeMenuItem deleteItem = new SwipeMenuItem(
                                getApplicationContext());
                        // set item background
                        deleteItem.setBackground(new ColorDrawable(Color.rgb(0xff, 0xff,
                                0xff)));
                        // set item width
                        deleteItem.setWidth(300);



                        // set a icon
                        deleteItem.setIcon(R.drawable.minus);
                        // add to menu
                        menu.addMenuItem(deleteItem);
                    }
                };

                listView.setMenuCreator(creator);

                listView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                        String itemAt = listView.getItemAtPosition(position).toString();
                        if(!itemAt.equals("")){
                            switch (index) {
                                case 0:
                                    ArrayList<String> cutString = cutStringToWord(itemAt);
                                    int qty = Integer.parseInt(cutString.get(cutString.size()-2));
                                    qty += 1;
                                    String newValue = "";
                                    for(int i = 0; i < cutString.size() - 2; i++){
                                        newValue += cutString.get(i) + " ";
                                    }
                                    newValue = newValue + qty + " " + cutString.get(cutString.size()-1);
                                    currentDishIngredients.set(position, newValue);
                                    ingredientAdapter.notifyDataSetChanged();
                                    break;
                                case 1:
                                    ArrayList<String> cutString2 = cutStringToWord(itemAt);
                                    int qty2 = Integer.parseInt(cutString2.get(cutString2.size()-2));

                                    if(qty2 <= 0){
                                        qty2 = 0;
                                    }
                                    else{
                                        qty2 -= 1;
                                    }

                                    String newValue2 = "";
                                    for(int i = 0; i < cutString2.size() - 2; i++){
                                        newValue2 += cutString2.get(i) + " ";
                                    }
                                    View view = ingredientAdapter.getView(position, null, listView);

                                    if(qty2 == 0){
                                        TextView text = (TextView) view;
                                        text.setPaintFlags(text.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                                    }
                                    newValue = newValue2 + qty2 + " " + cutString2.get(cutString2.size()-1);

                                    currentDishIngredients.set(position, newValue);
                                    ingredientAdapter.notifyDataSetChanged();
                                    break;
                            }

                        }
                        else{

                        }

                        // false : close the menu; true : not close the menu
                        return false;
                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
            }
        });
    }

    private void saveGroceryList(ArrayList<ArrayList<String>> groceryDish) {
        try {
            FileOutputStream fileOutputStream = openFileOutput("groceryDishes", Context.MODE_PRIVATE);
            ObjectOutputStream out = new ObjectOutputStream(fileOutputStream);
            out.writeObject(allDishes);
            out.close();
            fileOutputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private ArrayList<ArrayList<String>> getGroceryDish() {
        ArrayList<ArrayList<String>> savedGroceryDishArrayList = null;
        ArrayList<ArrayList<String>>  allDishes = null;
        try {
            FileInputStream inputStream = openFileInput("groceryDishes");
            ObjectInputStream in = new ObjectInputStream(inputStream);
            savedGroceryDishArrayList = (ArrayList<ArrayList<String>>) in.readObject();
            in.close();
            inputStream.close();

            allDishes = getDishes();

            ArrayList<String> dishInGro = new ArrayList<>();
            ArrayList<String> dishInAll = new ArrayList<>();

            for(int i = 0; i < savedGroceryDishArrayList.size(); i++){
                dishInGro.add(savedGroceryDishArrayList.get(i).get(0));
            }

            for(int i = 0; i < allDishes.size(); i++){
                dishInAll.add(allDishes.get(i).get(0));
            }

            for(int i = 0; i < dishInAll.size(); i++){
                if(!dishInGro.contains(dishInAll.get(i))){
                    savedGroceryDishArrayList.add(allDishes.get(i));
                }
                else{
                    // Already in
                }
            }
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

    private ArrayList<String> cutStringToWord(String ingredientQuantityUnit){
        String[] items = ingredientQuantityUnit.split(" ");
        List list = asList(items);
        return new ArrayList<String>(list);
    }
}
