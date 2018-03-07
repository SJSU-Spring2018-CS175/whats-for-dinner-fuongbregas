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
    private ImageView imageView;


    public ArrayList<String> ingredient;
    public ArrayList<String> recipeList;
    public ArrayList<ArrayList<String>> allDishes;

    public ArrayAdapter<String> ingredientAdapter;


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
        item2 = (AutoCompleteTextView) findViewById(R.id.item2) ;

        //allDishes = (ArrayList<ArrayList<String>>) getIntent().getSerializableExtra("dishList");
        //ingredient = (ArrayList<String>) getIntent().getSerializableExtra("ingredientList");

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


        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(recipeName.getText().toString().isEmpty()){
                    Toast.makeText(NewDishActivity.this, "Recipe's name is empty!",Toast.LENGTH_LONG).show();
                }
                else {
                    // Create a new recipe
                    recipeList.set(0, recipeName.getText().toString());
                    recipeList.set(1, item1.getText().toString());

                    // Check if dish already exists
                    for(int i = 0; i < allDishes.size(); i++){
                        // If yes, replace
                        if(allDishes.get(i).get(0).equals(recipeName.getText().toString())){
                            allDishes.set(i, recipeList);


                            // test
                            System.out.println("Replace disk " + allDishes.get(i).get(0) + " at " + i);
                        }
                        // If no, add new dishes to all dish list
                        if(i >= allDishes.size()){
                            allDishes.add(recipeList);

                            System.out.println("Add new dish at position" + i);
                        }
                    }
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
