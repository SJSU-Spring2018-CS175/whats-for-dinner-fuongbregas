package edu.sjsu.fuong.whatsfordinner;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static java.util.Arrays.*;

public class EditDishActivity extends AppCompatActivity {

    private EditText recipeName;
    private Button submitButton;
    private ImageButton addPicture;
    private AutoCompleteTextView item1;
    private AutoCompleteTextView item2;
    private AutoCompleteTextView item3;
    private AutoCompleteTextView item4;
    private AutoCompleteTextView item5;
    private AutoCompleteTextView item6;
    private AutoCompleteTextView item7;
    private AutoCompleteTextView item8;
    private AutoCompleteTextView item9;
    private AutoCompleteTextView item10;

    private EditText quantity1;
    private EditText quantity2;
    private EditText quantity3;
    private EditText quantity4;
    private EditText quantity5;
    private EditText quantity6;
    private EditText quantity7;
    private EditText quantity8;
    private EditText quantity9;
    private EditText quantity10;

    private EditText unit1;
    private EditText unit2;
    private EditText unit3;
    private EditText unit4;
    private EditText unit5;
    private EditText unit6;
    private EditText unit7;
    private EditText unit8;
    private EditText unit9;
    private EditText unit10;

    private EditText descriptionText;

    private ImageView imageView;

    // Dish Data
    public ArrayList<String> ingredient;
    public ArrayAdapter<String> ingredientAdapter;
    public ArrayList<String> currentDish; // Get from Landscape

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_dish);
        Bundle bundle = getIntent().getExtras();
        currentDish = (ArrayList<String>) bundle.get("currentDish");
        ingredient = getIngredient();

        if(currentDish!= null){
            for(int i = 0; i < currentDish.size(); i++){
                System.out.println(currentDish.get(i));
            }
        }
        else{
            System.out.println("Error currentDish");
        }


        recipeName = findViewById(R.id.recipeName);
        recipeName.setText(currentDish.get(0));
        submitButton = (Button) findViewById(R.id.submitButt);
        addPicture = (ImageButton) findViewById(R.id.addPicture);
        imageView = (ImageView) findViewById(R.id.imageView);

        // Ingredient Name
        item1 = (AutoCompleteTextView) findViewById(R.id.item1);
        item2 = (AutoCompleteTextView) findViewById(R.id.item2);
        item3 = (AutoCompleteTextView) findViewById(R.id.item3);
        item4 = (AutoCompleteTextView) findViewById(R.id.item4);
        item5 = (AutoCompleteTextView) findViewById(R.id.item5);
        item6 = (AutoCompleteTextView) findViewById(R.id.item6);
        item7 = (AutoCompleteTextView) findViewById(R.id.item7);
        item8 = (AutoCompleteTextView) findViewById(R.id.item8);
        item9 = (AutoCompleteTextView) findViewById(R.id.item9);
        item10 = (AutoCompleteTextView) findViewById(R.id.item10);

        // Quantity
        quantity1 = (EditText) findViewById(R.id.quantity1);
        quantity2 = (EditText) findViewById(R.id.quantity2);
        quantity3 = (EditText) findViewById(R.id.quantity3);
        quantity4 = (EditText) findViewById(R.id.quantity4);
        quantity5 = (EditText) findViewById(R.id.quantity5);
        quantity6 = (EditText) findViewById(R.id.quantity6);
        quantity7 = (EditText) findViewById(R.id.quantity7);
        quantity8 = (EditText) findViewById(R.id.quantity8);
        quantity9 = (EditText) findViewById(R.id.quantity9);
        quantity10 = (EditText) findViewById(R.id.quantity10);

        // Unit
        unit1 = (EditText) findViewById(R.id.unit1);
        unit2 = (EditText) findViewById(R.id.unit2);
        unit3 = (EditText) findViewById(R.id.unit3);
        unit4 = (EditText) findViewById(R.id.unit4);
        unit5 = (EditText) findViewById(R.id.unit5);
        unit6 = (EditText) findViewById(R.id.unit6);
        unit7 = (EditText) findViewById(R.id.unit7);
        unit8 = (EditText) findViewById(R.id.unit8);
        unit9 = (EditText) findViewById(R.id.unit9);
        unit10 = (EditText) findViewById(R.id.unit10);

        descriptionText = (EditText) findViewById(R.id.descriptionText);

        // Set Ingredient, Qty, Unit 1
        ArrayList<String> string1 = cutStringToWord(currentDish.get(1));
        quantity1.setText(string1.get(string1.size()-2));
        unit1.setText(string1.get(string1.size()-1));
        String item1String = "";
        for(int i = 0; i < string1.size() - 2; i++){
            item1String += string1.get(i) + " ";
        }
        item1.setText(item1String);

        // Set Ingredient, Qty, Unit 2
        ArrayList<String> string2 = cutStringToWord(currentDish.get(2));
        quantity2.setText(string2.get(string2.size()-2));
        unit2.setText(string2.get(string2.size()-1));
        String item2String = "";
        for(int i = 0; i < string2.size() - 2; i++){
            item2String += string2.get(i) + " ";
        }
        item2.setText(item2String);

        // Set Ingredient, Qty, Unit 3
        ArrayList<String> string3 = cutStringToWord(currentDish.get(3));
        quantity3.setText(string3.get(string3.size()-2));
        unit3.setText(string3.get(string3.size()-1));
        String item3String = "";
        for(int i = 0; i < string3.size() - 2; i++){
            item3String += string3.get(i) + " ";
        }
        item3.setText(item3String);

        // Set Ingredient, Qty, Unit 4
        ArrayList<String> string4 = cutStringToWord(currentDish.get(4));
        quantity4.setText(string4.get(string4.size()-2));
        unit4.setText(string4.get(string4.size()-1));
        String item4String = "";
        for(int i = 0; i < string4.size() - 2; i++){
            item4String += string4.get(i) + " ";
        }
        item4.setText(item4String);

        // Set Ingredient, Qty, Unit 5
        ArrayList<String> string5 = cutStringToWord(currentDish.get(5));
        quantity5.setText(string5.get(string5.size()-2));
        unit5.setText(string5.get(string5.size()-1));
        String item5String = "";
        for(int i = 0; i < string5.size() - 2; i++){
            item5String += string5.get(i) + " ";
        }
        item5.setText(item5String);

        // Set Ingredient, Qty, Unit 6
        ArrayList<String> string6 = cutStringToWord(currentDish.get(6));
        quantity5.setText(string6.get(string6.size()-2));
        unit5.setText(string6.get(string6.size()-1));
        String item6String = "";
        for(int i = 0; i < string6.size() - 2; i++){
            item6String += string6.get(i) + " ";
        }
        item6.setText(item6String);

        // Set Ingredient, Qty, Unit 7
        ArrayList<String> string7 = cutStringToWord(currentDish.get(7));
        quantity7.setText(string7.get(string7.size()-2));
        unit7.setText(string7.get(string7.size()-1));
        String item7String = "";
        for(int i = 0; i < string7.size() - 2; i++){
            item7String += string7.get(i) + " ";
        }
        item7.setText(item7String);

        // Set Ingredient, Qty, Unit 8
        ArrayList<String> string8 = cutStringToWord(currentDish.get(8));
        quantity8.setText(string8.get(string8.size()-2));
        unit8.setText(string8.get(string8.size()-1));
        String item8String = "";
        for(int i = 0; i < string8.size() - 2; i++){
            item8String += string8.get(i) + " ";
        }
        item8.setText(item8String);

        // Set Ingredient, Qty, Unit 9
        ArrayList<String> string9 = cutStringToWord(currentDish.get(9));
        quantity9.setText(string9.get(string9.size()-2));
        unit9.setText(string9.get(string9.size()-1));
        String item9String = "";
        for(int i = 0; i < string9.size() - 2; i++){
            item9String += string9.get(i) + " ";
        }
        item9.setText(item9String);

        // Set Ingredient, Qty, Unit 10
        ArrayList<String> string10 = cutStringToWord(currentDish.get(10));
        quantity10.setText(string10.get(string10.size()-2));
        unit10.setText(string10.get(string10.size()-1));
        String item10String = "";
        for(int i = 0; i < string10.size() - 2; i++){
            item10String += string10.get(i) + " ";
        }
        item10.setText(item10String);

        // make dropdown with AutoCompleteTextView
        ingredientAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line,ingredient);

        item1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                item1.showDropDown();
            }
        });

        item2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                item2.showDropDown();
            }
        });

        item3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                item3.showDropDown();
            }
        });

        item4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                item4.showDropDown();
            }
        });

        item5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                item5.showDropDown();
            }
        });

        item6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                item6.showDropDown();
            }
        });

        item7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                item7.showDropDown();
            }
        });

        item8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                item8.showDropDown();
            }
        });

        item9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                item9.showDropDown();
            }
        });

        item10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                item10.showDropDown();
            }
        });

        // If user still focus on
        item1.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if(!hasFocus){
                    ingredient = getIngredient();
                    if(!ingredient.contains(item1.getText().toString())){
                        ingredient.add(item1.getText().toString());
                        System.out.println("New Item added to Ingredient: " + item1.getText().toString());
                        saveIngredientList(ingredient);
                    }
                    else{
                        System.out.println("Already in Ingredient List");
                    }
                }
                else{
                    ingredientAdapter = new ArrayAdapter<String>(EditDishActivity.this, android.R.layout.simple_dropdown_item_1line,ingredient);
                    item1.setAdapter(ingredientAdapter);
                }

            }
        });

        item2.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if(!hasFocus){
                    ingredient = getIngredient();
                    if(!ingredient.contains(item2.getText().toString())){
                        ingredient.add(item2.getText().toString());
                        saveIngredientList(ingredient);
                    }
                    else{

                    }
                }
                else{
                    ingredientAdapter = new ArrayAdapter<String>(EditDishActivity.this, android.R.layout.simple_dropdown_item_1line,ingredient);
                    item2.setAdapter(ingredientAdapter);
                }
            }
        });

        item3.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if(!hasFocus){
                    ingredient = getIngredient();
                    if(!ingredient.contains(item3.getText().toString())){
                        ingredient.add(item3.getText().toString());
                        saveIngredientList(ingredient);
                    }
                    else{

                    }
                }
                else{
                    ingredientAdapter = new ArrayAdapter<String>(EditDishActivity.this, android.R.layout.simple_dropdown_item_1line,ingredient);
                    item3.setAdapter(ingredientAdapter);
                }
            }
        });

        item4.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if(!hasFocus){
                    ingredient = getIngredient();
                    if(!ingredient.contains(item4.getText().toString())){
                        ingredient.add(item4.getText().toString());
                        saveIngredientList(ingredient);
                    }
                    else{

                    }
                }
                else{
                    ingredientAdapter = new ArrayAdapter<String>(EditDishActivity.this, android.R.layout.simple_dropdown_item_1line,ingredient);
                    item4.setAdapter(ingredientAdapter);
                }
            }
        });

        item5.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if(!hasFocus){
                    ingredient = getIngredient();
                    if(!ingredient.contains(item5.getText().toString())){
                        ingredient.add(item5.getText().toString());
                        saveIngredientList(ingredient);
                    }
                    else{

                    }
                }
                else{
                    ingredientAdapter = new ArrayAdapter<String>(EditDishActivity.this, android.R.layout.simple_dropdown_item_1line,ingredient);
                    item5.setAdapter(ingredientAdapter);
                }
            }
        });

        item6.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if(!hasFocus){
                    ingredient = getIngredient();
                    if(!ingredient.contains(item6.getText().toString())){
                        ingredient.add(item6.getText().toString());
                        saveIngredientList(ingredient);
                    }
                    else{

                    }
                }
                else{
                    ingredientAdapter = new ArrayAdapter<String>(EditDishActivity.this, android.R.layout.simple_dropdown_item_1line,ingredient);
                    item6.setAdapter(ingredientAdapter);
                }
            }
        });

        item7.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if(!hasFocus){
                    ingredient = getIngredient();
                    if(!ingredient.contains(item7.getText().toString())){
                        ingredient.add(item7.getText().toString());
                        saveIngredientList(ingredient);
                    }
                    else{

                    }
                }
                else{
                    ingredientAdapter = new ArrayAdapter<String>(EditDishActivity.this, android.R.layout.simple_dropdown_item_1line,ingredient);
                    item7.setAdapter(ingredientAdapter);
                }
            }
        });

        item8.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if(!hasFocus){
                    ingredient = getIngredient();
                    if(!ingredient.contains(item8.getText().toString())){
                        ingredient.add(item8.getText().toString());
                        saveIngredientList(ingredient);
                    }
                    else{

                    }
                }
                else{
                    ingredientAdapter = new ArrayAdapter<String>(EditDishActivity.this, android.R.layout.simple_dropdown_item_1line,ingredient);
                    item8.setAdapter(ingredientAdapter);
                }
            }
        });

        item9.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if(!hasFocus){
                    ingredient = getIngredient();
                    if(!ingredient.contains(item9.getText().toString())){
                        ingredient.add(item9.getText().toString());
                        saveIngredientList(ingredient);
                    }
                    else{

                    }
                }
                else{
                    ingredientAdapter = new ArrayAdapter<String>(EditDishActivity.this, android.R.layout.simple_dropdown_item_1line,ingredient);
                    item9.setAdapter(ingredientAdapter);
                }
            }
        });

        item10.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if(!hasFocus){
                    ingredient = getIngredient();
                    if(!ingredient.contains(item10.getText().toString())){
                        ingredient.add(item10.getText().toString());
                        saveIngredientList(ingredient);
                    }
                    else{

                    }
                }
                else{
                    ingredientAdapter = new ArrayAdapter<String>(EditDishActivity.this, android.R.layout.simple_dropdown_item_1line,ingredient);
                    item10.setAdapter(ingredientAdapter);
                }
            }
        });
    }

    private HashMap<String, SerializableBitmap> getSavedHashMap(String filename) {
        HashMap<String, SerializableBitmap> savedHashMap = null;

        try {
            FileInputStream inputStream = openFileInput(filename);
            ObjectInputStream in = new ObjectInputStream(inputStream);
            savedHashMap = (HashMap<String, SerializableBitmap>) in.readObject();
            in.close();
            inputStream.close();

        }
        catch (FileNotFoundException fnf){
            savedHashMap = new HashMap<>();
        }
        catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return savedHashMap;
    }

    // Save Ingredient List to file
    private void saveIngredientList(ArrayList<String> ingredientList) {
        try {
            FileOutputStream fileOutputStream = openFileOutput("ingredientArrayList", Context.MODE_PRIVATE);
            ObjectOutputStream out = new ObjectOutputStream(fileOutputStream);
            out.writeObject(ingredientList);
            out.close();
            fileOutputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Load Ingredient List from File
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

    // Image from device
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1 && resultCode == RESULT_OK && data != null && data.getData() != null){
            Uri uri = data.getData();

            try{
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);

                imageView.setImageBitmap(bitmap);

            } catch (FileNotFoundException e) {
                System.out.println("File not found");
            } catch (IOException e) {
                System.out.println("Image selection error");
            }

        }
    }

    private ArrayList<String> cutStringToWord(String ingredientQuantityUnit){
        String[] items = ingredientQuantityUnit.split(" ");
        List list = asList(items);
        return new ArrayList<String>(list);
    }

}
