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
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class NewDishActivity extends AppCompatActivity {

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
    private EditText descriptionText;

    private ImageView imageView;


    public ArrayList<String> ingredient;
    public ArrayList<String> currentDish;
    public ArrayList<ArrayList<String>> allDishes;

    public ArrayAdapter<String> ingredientAdapter;

    // Image from device
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1 && resultCode == RESULT_OK && data != null && data.getData() != null){
            Uri uri = data.getData();

            try{
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                imageView = (ImageView) findViewById(R.id.imageView);
                imageView.setImageBitmap(bitmap);

            } catch (FileNotFoundException e) {
                System.out.println("File not found");
            } catch (IOException e) {
                System.out.println("Image selection error");
            }

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_dish);

        recipeName = (EditText) findViewById(R.id.recipeName);
        submitButton = (Button) findViewById(R.id.submitButt);
        addPicture = (ImageButton) findViewById(R.id.addPicture);

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

        descriptionText = (EditText) findViewById(R.id.descriptionText);

        imageView = (ImageView) findViewById(R.id.imageView);

        //allDishes = (ArrayList<ArrayList<String>>) getIntent().getSerializableExtra("dishList");
        //ingredient = (ArrayList<String>) getIntent().getSerializableExtra("ingredientList");

        // allDishes array list
        allDishes = getDishes();
        currentDish = new ArrayList<>();

        // Ingredient arraylist
        ingredient = getIngredient();

        // make dropdown with AutoCompleteTextView
        ingredientAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line,ingredient);
        item1.setAdapter(ingredientAdapter);
        item2.setAdapter(ingredientAdapter);

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

        addPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent choosePicture = new Intent();
                // Show only images, no videos or anything else
                choosePicture.setType("image/*");
                choosePicture.setAction(Intent.ACTION_GET_CONTENT);
                // Always show the chooser (if there are multiple options available)
                startActivityForResult(Intent.createChooser(choosePicture, "Select Picture"), 1);


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
                    ingredientAdapter = new ArrayAdapter<String>(NewDishActivity.this, android.R.layout.simple_dropdown_item_1line,ingredient);
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
                    ingredientAdapter = new ArrayAdapter<String>(NewDishActivity.this, android.R.layout.simple_dropdown_item_1line,ingredient);
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
                    ingredientAdapter = new ArrayAdapter<String>(NewDishActivity.this, android.R.layout.simple_dropdown_item_1line,ingredient);
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
                    ingredientAdapter = new ArrayAdapter<String>(NewDishActivity.this, android.R.layout.simple_dropdown_item_1line,ingredient);
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
                    ingredientAdapter = new ArrayAdapter<String>(NewDishActivity.this, android.R.layout.simple_dropdown_item_1line,ingredient);
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
                    ingredientAdapter = new ArrayAdapter<String>(NewDishActivity.this, android.R.layout.simple_dropdown_item_1line,ingredient);
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
                    ingredientAdapter = new ArrayAdapter<String>(NewDishActivity.this, android.R.layout.simple_dropdown_item_1line,ingredient);
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
                    ingredientAdapter = new ArrayAdapter<String>(NewDishActivity.this, android.R.layout.simple_dropdown_item_1line,ingredient);
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
                    ingredientAdapter = new ArrayAdapter<String>(NewDishActivity.this, android.R.layout.simple_dropdown_item_1line,ingredient);
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
                    ingredientAdapter = new ArrayAdapter<String>(NewDishActivity.this, android.R.layout.simple_dropdown_item_1line,ingredient);
                    item10.setAdapter(ingredientAdapter);
                }
            }
        });



        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(recipeName.getText().toString().isEmpty()){
                    Toast.makeText(NewDishActivity.this, "Recipe's name is empty!",Toast.LENGTH_LONG).show();
                }
                else {
                    int counter = 0;
                    currentDish.add(recipeName.getText().toString()); // index = 0
                    currentDish.add(item1.getText().toString()); // index = 1
                    currentDish.add(item2.getText().toString()); // index = 2
                    currentDish.add(item3.getText().toString()); // index = 3
                    currentDish.add(item4.getText().toString()); // index = 4
                    currentDish.add(item5.getText().toString()); // index = 5
                    currentDish.add(item6.getText().toString()); // index = 6
                    currentDish.add(item7.getText().toString()); // index = 7
                    currentDish.add(item8.getText().toString()); // index = 8
                    currentDish.add(item9.getText().toString()); // index = 9
                    currentDish.add(item10.getText().toString());// index = 10
                    currentDish.add(descriptionText.getText().toString()); // index = 11
                    currentDish.add(recipeName.getText().toString() + "bitmapName"); // index = 12

                    for(int i = 0; i < allDishes.size(); i++){
                        if(allDishes.get(i).get(0).equals(currentDish.get(0))){
                            counter = i;
                            System.out.println("Duplicated Dish");
                        }
                        else{

                        }
                    }

                    // Replace in allDish arraylist
                    if(counter != 0){
                        allDishes.set(counter, currentDish);
                    }
                    else{
                        // No dup, add to the arrayList
                        System.out.println("No Duplicated, add new dish");
                        allDishes.add(currentDish);
                    }

                    // Save to file
                    saveDishList(allDishes); // save arrayList
                    saveBitMap(imageView); // save bitmap object
                    Intent welcomeScreen = new Intent(NewDishActivity.this, Welcome.class);
                    startActivity(welcomeScreen);
                }
            }
        });
    }

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

    private void saveDishList(ArrayList<ArrayList<String>> allDishes) {
        try {
            FileOutputStream fileOutputStream = openFileOutput("dishesArrayList", Context.MODE_PRIVATE);
            ObjectOutputStream out = new ObjectOutputStream(fileOutputStream);
            out.writeObject(allDishes);
            out.close();
            fileOutputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveBitMap(ImageView imageView){
        try{
            imageView.buildDrawingCache();
            Bitmap bmap = imageView.getDrawingCache();

            FileOutputStream  fileOutputStream  = openFileOutput(currentDish.get(12),Context.MODE_PRIVATE);
            ObjectOutputStream out = new ObjectOutputStream(fileOutputStream);
            out.writeObject(bmap);
            out.close();
            fileOutputStream.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
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
