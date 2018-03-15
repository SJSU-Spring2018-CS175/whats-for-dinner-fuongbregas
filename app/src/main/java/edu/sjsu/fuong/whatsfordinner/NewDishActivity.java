package edu.sjsu.fuong.whatsfordinner;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
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
import java.util.HashMap;

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


    public ArrayList<String> ingredient;
    public ArrayList<String> currentDish;
    public ArrayList<ArrayList<String>> allDishes;
    public ArrayList<ArrayList<String>> testLenght;

    HashMap<String, SerializableBitmap> savedHashMap;

    public ArrayAdapter<String> ingredientAdapter;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_dish);

        recipeName = (EditText) findViewById(R.id.recipeName);
        submitButton = (Button) findViewById(R.id.submitButt);
        addPicture = (ImageButton) findViewById(R.id.addPicture);
        imageView = (ImageView) findViewById(R.id.imageView);
        Drawable id = getResources().getDrawable(R.drawable.burger);
        imageView.setImageDrawable(id);


        // Dish Name
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

        imageView = (ImageView) findViewById(R.id.imageView);

        testLenght = (ArrayList<ArrayList<String>>) getIntent().getSerializableExtra("dishList");
        System.out.println("Length of allDishes " + testLenght.size());
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
                    int condition = 0;
                    int counter = 0;
                    currentDish.add(recipeName.getText().toString()); // index = 0
                    // index = 1
                    if((item1.getText().toString().isEmpty() && !quantity1.getText().toString().isEmpty() && !unit1.getText().toString().isEmpty()) // _XX
                            || (item1.getText().toString().isEmpty() && !quantity1.getText().toString().isEmpty() && unit1.getText().toString().isEmpty()) // _X_
                            || (item1.getText().toString().isEmpty() && quantity1.getText().toString().isEmpty()  && !unit1.getText().toString().isEmpty())){ //__X
                        Toast.makeText(NewDishActivity.this, "Item 1 name can't be left empty!", Toast.LENGTH_LONG).show();
                        condition = 1;
                    }

                    else if (item1.getText().toString().isEmpty() && quantity1.getText().toString().isEmpty() && unit1.getText().toString().isEmpty()) { // _ _ _
                        currentDish.add(""); // Add empty string
                    }
                    else if ((!item1.getText().toString().isEmpty() && quantity1.getText().toString().isEmpty() && unit1.getText().toString().isEmpty()) // X__
                            || (!item1.getText().toString().isEmpty() && quantity1.getText().toString().isEmpty() && !unit1.getText().toString().isEmpty()) // X_X
                            || (!item1.getText().toString().isEmpty() && !quantity1.getText().toString().isEmpty() && unit1.getText().toString().isEmpty())) // XX_
                    {
                        condition = 1;
                        Toast.makeText(NewDishActivity.this, "Quantity 1 or Unit 1 can't be left empty", Toast.LENGTH_LONG).show();
                    }

                    else if (!item1.getText().toString().isEmpty() && !quantity1.getText().toString().isEmpty() && !unit1.getText().toString().isEmpty()){
                        currentDish.add(item1.getText().toString() + " " + quantity1.getText().toString() + " " + unit1.getText().toString());
                    }

                    // index 2
                    if((item2.getText().toString().isEmpty() && !quantity2.getText().toString().isEmpty() && !unit2.getText().toString().isEmpty()) // _XX
                            || (item2.getText().toString().isEmpty() && !quantity2.getText().toString().isEmpty() && unit2.getText().toString().isEmpty()) // _X_
                            || (item2.getText().toString().isEmpty() && quantity2.getText().toString().isEmpty()  && !unit2.getText().toString().isEmpty())){ //__X
                        Toast.makeText(NewDishActivity.this, "Item 2 name can't be left empty!", Toast.LENGTH_LONG).show();
                        condition = 1;
                    }

                    else if (item2.getText().toString().isEmpty() && quantity2.getText().toString().isEmpty() && unit2.getText().toString().isEmpty()) { // _ _ _
                        currentDish.add(""); // Add empty string
                    }
                    else if ((!item2.getText().toString().isEmpty() && quantity2.getText().toString().isEmpty() && unit2.getText().toString().isEmpty()) // X__
                            || (!item2.getText().toString().isEmpty() && quantity2.getText().toString().isEmpty() && !unit2.getText().toString().isEmpty()) // X_X
                            || (!item2.getText().toString().isEmpty() && !quantity2.getText().toString().isEmpty() && unit2.getText().toString().isEmpty())) // XX_
                    {
                        condition = 1;
                        Toast.makeText(NewDishActivity.this, "Quantity 2 or Unit 2 can't be left empty", Toast.LENGTH_LONG).show();
                    }

                    else if (!item2.getText().toString().isEmpty() && !quantity2.getText().toString().isEmpty() && !unit2.getText().toString().isEmpty()){
                        currentDish.add(item2.getText().toString() + " " + quantity2.getText().toString() + " " + unit2.getText().toString());
                    }

                    // Index 3
                    if((item3.getText().toString().isEmpty() && !quantity3.getText().toString().isEmpty() && !unit3.getText().toString().isEmpty()) // _XX
                            || (item3.getText().toString().isEmpty() && !quantity3.getText().toString().isEmpty() && unit3.getText().toString().isEmpty()) // _X_
                            || (item3.getText().toString().isEmpty() && quantity3.getText().toString().isEmpty()  && !unit3.getText().toString().isEmpty())){ //__X
                        Toast.makeText(NewDishActivity.this, "Item 3 name can't be left empty!", Toast.LENGTH_LONG).show();
                        condition = 1;
                    }

                    else if (item3.getText().toString().isEmpty() && quantity3.getText().toString().isEmpty() && unit3.getText().toString().isEmpty()) { // _ _ _
                        currentDish.add(""); // Add empty string
                    }
                    else if ((!item3.getText().toString().isEmpty() && quantity3.getText().toString().isEmpty() && unit3.getText().toString().isEmpty()) // X__
                            || (!item3.getText().toString().isEmpty() && quantity3.getText().toString().isEmpty() && !unit3.getText().toString().isEmpty()) // X_X
                            || (!item3.getText().toString().isEmpty() && !quantity3.getText().toString().isEmpty() && unit3.getText().toString().isEmpty())) // XX_
                    {
                        condition = 1;
                        Toast.makeText(NewDishActivity.this, "Quantity 3 or Unit 3 can't be left empty", Toast.LENGTH_LONG).show();
                    }

                    else if (!item3.getText().toString().isEmpty() && !quantity3.getText().toString().isEmpty() && !unit3.getText().toString().isEmpty()){
                        currentDish.add(item3.getText().toString() + " " + quantity3.getText().toString() + " " + unit3.getText().toString());
                    }

                    // Index 4
                    if((item4.getText().toString().isEmpty() && !quantity4.getText().toString().isEmpty() && !unit4.getText().toString().isEmpty()) // _XX
                            || (item4.getText().toString().isEmpty() && !quantity4.getText().toString().isEmpty() && unit4.getText().toString().isEmpty()) // _X_
                            || (item4.getText().toString().isEmpty() && quantity4.getText().toString().isEmpty()  && !unit4.getText().toString().isEmpty())){ //__X
                        Toast.makeText(NewDishActivity.this, "Item 4 name can't be left empty!", Toast.LENGTH_LONG).show();
                        condition = 1;
                    }

                    else if (item4.getText().toString().isEmpty() && quantity4.getText().toString().isEmpty() && unit4.getText().toString().isEmpty()) { // _ _ _
                        currentDish.add(""); // Add empty string
                    }
                    else if ((!item4.getText().toString().isEmpty() && quantity4.getText().toString().isEmpty() && unit4.getText().toString().isEmpty()) // X__
                            || (!item4.getText().toString().isEmpty() && quantity4.getText().toString().isEmpty() && !unit4.getText().toString().isEmpty()) // X_X
                            || (!item4.getText().toString().isEmpty() && !quantity4.getText().toString().isEmpty() && unit4.getText().toString().isEmpty())) // XX_
                    {
                        condition = 1;
                        Toast.makeText(NewDishActivity.this, "Quantity 4 or Unit 4 can't be left empty", Toast.LENGTH_LONG).show();
                    }

                    else if (!item4.getText().toString().isEmpty() && !quantity4.getText().toString().isEmpty() && !quantity4.getText().toString().isEmpty()){
                        currentDish.add(item4.getText().toString() + " " + quantity4.getText().toString() + " " + unit4.getText().toString());
                    }

                    // Index 5
                    if((item5.getText().toString().isEmpty() && !quantity5.getText().toString().isEmpty() && !unit5.getText().toString().isEmpty()) // _XX
                            || (item5.getText().toString().isEmpty() && !quantity5.getText().toString().isEmpty() && unit5.getText().toString().isEmpty()) // _X_
                            || (item5.getText().toString().isEmpty() && quantity5.getText().toString().isEmpty()  && !unit5.getText().toString().isEmpty())){ //__X
                        Toast.makeText(NewDishActivity.this, "Item 5 name can't be left empty!", Toast.LENGTH_LONG).show();
                        condition = 1;
                    }

                    else if (item5.getText().toString().isEmpty() && quantity5.getText().toString().isEmpty() && unit5.getText().toString().isEmpty()) { // _ _ _
                        currentDish.add(""); // Add empty string
                    }
                    else if ((!item5.getText().toString().isEmpty() && quantity5.getText().toString().isEmpty() && unit5.getText().toString().isEmpty()) // X__
                            || (!item5.getText().toString().isEmpty() && quantity5.getText().toString().isEmpty() && !unit5.getText().toString().isEmpty()) // X_X
                            || (!item5.getText().toString().isEmpty() && !quantity5.getText().toString().isEmpty() && unit5.getText().toString().isEmpty())) // XX_
                    {
                        condition = 1;
                        Toast.makeText(NewDishActivity.this, "Quantity 5 or Unit 5 can't be left empty", Toast.LENGTH_LONG).show();
                    }

                    else if (!item5.getText().toString().isEmpty() && !quantity5.getText().toString().isEmpty() && !quantity5.getText().toString().isEmpty()){
                        currentDish.add(item5.getText().toString() + " " + quantity5.getText().toString() + " " + unit5.getText().toString());
                    }

                    // Index 6
                    if((item6.getText().toString().isEmpty() && !quantity6.getText().toString().isEmpty() && !unit6.getText().toString().isEmpty()) // _XX
                            || (item6.getText().toString().isEmpty() && !quantity6.getText().toString().isEmpty() && unit6.getText().toString().isEmpty()) // _X_
                            || (item6.getText().toString().isEmpty() && quantity6.getText().toString().isEmpty()  && !unit6.getText().toString().isEmpty())){ //__X
                        Toast.makeText(NewDishActivity.this, "Item 6 name can't be left empty!", Toast.LENGTH_LONG).show();
                        condition = 1;
                    }

                    else if (item6.getText().toString().isEmpty() && quantity6.getText().toString().isEmpty() && unit6.getText().toString().isEmpty()) { // _ _ _
                        currentDish.add(""); // Add empty string
                    }
                    else if ((!item6.getText().toString().isEmpty() && quantity6.getText().toString().isEmpty() && unit6.getText().toString().isEmpty()) // X__
                            || (!item6.getText().toString().isEmpty() && quantity6.getText().toString().isEmpty() && !unit6.getText().toString().isEmpty()) // X_X
                            || (!item6.getText().toString().isEmpty() && !quantity6.getText().toString().isEmpty() && unit6.getText().toString().isEmpty())) // XX_
                    {
                        condition = 1;
                        Toast.makeText(NewDishActivity.this, "Quantity 6 or Unit 6 can't be left empty", Toast.LENGTH_LONG).show();
                    }

                    else if (!item6.getText().toString().isEmpty() && !quantity6.getText().toString().isEmpty() && !quantity6.getText().toString().isEmpty()){
                        currentDish.add(item6.getText().toString() + " " + quantity6.getText().toString() + " " + unit6.getText().toString());
                    }

                    // Index 7
                    if((item7.getText().toString().isEmpty() && !quantity7.getText().toString().isEmpty() && !unit7.getText().toString().isEmpty()) // _XX
                            || (item7.getText().toString().isEmpty() && !quantity7.getText().toString().isEmpty() && unit7.getText().toString().isEmpty()) // _X_
                            || (item7.getText().toString().isEmpty() && quantity7.getText().toString().isEmpty()  && !unit7.getText().toString().isEmpty())){ //__X
                        Toast.makeText(NewDishActivity.this, "Item 7 name can't be left empty!", Toast.LENGTH_LONG).show();
                        condition = 1;
                    }

                    else if (item7.getText().toString().isEmpty() && quantity7.getText().toString().isEmpty() && unit7.getText().toString().isEmpty()) { // _ _ _
                        currentDish.add(""); // Add empty string
                    }
                    else if ((!item7.getText().toString().isEmpty() && quantity7.getText().toString().isEmpty() && unit7.getText().toString().isEmpty()) // X__
                            || (!item7.getText().toString().isEmpty() && quantity7.getText().toString().isEmpty() && !unit7.getText().toString().isEmpty()) // X_X
                            || (!item7.getText().toString().isEmpty() && !quantity7.getText().toString().isEmpty() && unit7.getText().toString().isEmpty())) // XX_
                    {
                        condition = 1;
                        Toast.makeText(NewDishActivity.this, "Quantity 7 or Unit 7 can't be left empty", Toast.LENGTH_LONG).show();
                    }

                    else if (!item7.getText().toString().isEmpty() && !quantity7.getText().toString().isEmpty() && !quantity7.getText().toString().isEmpty()){
                        currentDish.add(item7.getText().toString() + " " + quantity7.getText().toString() + " " + unit7.getText().toString());
                    }

                    // Index 8
                    if((item8.getText().toString().isEmpty() && !quantity8.getText().toString().isEmpty() && !unit8.getText().toString().isEmpty()) // _XX
                            || (item8.getText().toString().isEmpty() && !quantity8.getText().toString().isEmpty() && unit8.getText().toString().isEmpty()) // _X_
                            || (item8.getText().toString().isEmpty() && quantity8.getText().toString().isEmpty()  && !unit8.getText().toString().isEmpty())){ //__X
                        Toast.makeText(NewDishActivity.this, "Item 8 name can't be left empty!", Toast.LENGTH_LONG).show();
                        condition = 1;
                    }

                    else if (item8.getText().toString().isEmpty() && quantity8.getText().toString().isEmpty() && unit8.getText().toString().isEmpty()) { // _ _ _
                        currentDish.add(""); // Add empty string
                    }
                    else if ((!item8.getText().toString().isEmpty() && quantity8.getText().toString().isEmpty() && unit8.getText().toString().isEmpty()) // X__
                            || (!item8.getText().toString().isEmpty() && quantity8.getText().toString().isEmpty() && !unit8.getText().toString().isEmpty()) // X_X
                            || (!item8.getText().toString().isEmpty() && !quantity8.getText().toString().isEmpty() && unit8.getText().toString().isEmpty())) // XX_
                    {
                        condition = 1;
                        Toast.makeText(NewDishActivity.this, "Quantity 8 or Unit 8 can't be left empty", Toast.LENGTH_LONG).show();
                    }

                    else if (!item8.getText().toString().isEmpty() && !quantity8.getText().toString().isEmpty() && !quantity8.getText().toString().isEmpty()){
                        currentDish.add(item8.getText().toString() + " " + quantity8.getText().toString() + " " + unit8.getText().toString());
                    }

                    // Index 9
                    if((item9.getText().toString().isEmpty() && !quantity9.getText().toString().isEmpty() && !unit9.getText().toString().isEmpty()) // _XX
                            || (item9.getText().toString().isEmpty() && !quantity9.getText().toString().isEmpty() && unit9.getText().toString().isEmpty()) // _X_
                            || (item9.getText().toString().isEmpty() && quantity9.getText().toString().isEmpty()  && !unit9.getText().toString().isEmpty())){ //__X
                        Toast.makeText(NewDishActivity.this, "Item 9 name can't be left empty!", Toast.LENGTH_LONG).show();
                        condition = 1;
                    }

                    else if (item9.getText().toString().isEmpty() && quantity9.getText().toString().isEmpty() && unit9.getText().toString().isEmpty()) { // _ _ _
                        currentDish.add(""); // Add empty string
                    }
                    else if ((!item9.getText().toString().isEmpty() && quantity9.getText().toString().isEmpty() && unit9.getText().toString().isEmpty()) // X__
                            || (!item9.getText().toString().isEmpty() && quantity9.getText().toString().isEmpty() && !unit9.getText().toString().isEmpty()) // X_X
                            || (!item9.getText().toString().isEmpty() && !quantity9.getText().toString().isEmpty() && unit9.getText().toString().isEmpty())) // XX_
                    {
                        condition = 1;
                        Toast.makeText(NewDishActivity.this, "Quantity 9 or Unit 9 can't be left empty", Toast.LENGTH_LONG).show();
                    }

                    else if (!item9.getText().toString().isEmpty() && !quantity9.getText().toString().isEmpty() && !quantity9.getText().toString().isEmpty()){
                        currentDish.add(item9.getText().toString() + " " + quantity9.getText().toString() + " " + unit9.getText().toString());
                    }

                    // Index 10
                    if((item10.getText().toString().isEmpty() && !quantity10.getText().toString().isEmpty() && !unit10.getText().toString().isEmpty()) // _XX
                            || (item10.getText().toString().isEmpty() && !quantity10.getText().toString().isEmpty() && unit10.getText().toString().isEmpty()) // _X_
                            || (item10.getText().toString().isEmpty() && quantity10.getText().toString().isEmpty()  && !unit10.getText().toString().isEmpty())){ //__X
                        Toast.makeText(NewDishActivity.this, "Item 10 name can't be left empty!", Toast.LENGTH_LONG).show();
                        condition = 1;
                    }

                    else if (item10.getText().toString().isEmpty() && quantity10.getText().toString().isEmpty() && unit10.getText().toString().isEmpty()) { // _ _ _
                        currentDish.add(""); // Add empty string
                    }
                    else if ((!item10.getText().toString().isEmpty() && quantity10.getText().toString().isEmpty() && unit10.getText().toString().isEmpty()) // X__
                            || (!item10.getText().toString().isEmpty() && quantity10.getText().toString().isEmpty() && !unit10.getText().toString().isEmpty()) // X_X
                            || (!item10.getText().toString().isEmpty() && !quantity10.getText().toString().isEmpty() && unit10.getText().toString().isEmpty())) // XX_
                    {
                        condition = 1;
                        Toast.makeText(NewDishActivity.this, "Quantity 10 or Unit 10 can't be left empty", Toast.LENGTH_LONG).show();
                    }

                    else if (!item10.getText().toString().isEmpty() && !quantity10.getText().toString().isEmpty() && !quantity10.getText().toString().isEmpty()){
                        currentDish.add(item10.getText().toString() + " " + quantity10.getText().toString() + " " + unit10.getText().toString());
                    }

                    /*
                    currentDish.add(item2.getText().toString()); // index = 2
                    currentDish.add(item3.getText().toString()); // index = 3
                    currentDish.add(item4.getText().toString()); // index = 4
                    currentDish.add(item5.getText().toString()); // index = 5
                    currentDish.add(item6.getText().toString()); // index = 6
                    currentDish.add(item7.getText().toString()); // index = 7
                    currentDish.add(item8.getText().toString()); // index = 8
                    currentDish.add(item9.getText().toString()); // index = 9
                    currentDish.add(item10.getText().toString());// index = 10

                    */

                    // Everything is fine, condition is 0
                    if(condition == 0){
                        currentDish.add(descriptionText.getText().toString()); // index = 11 Description
                        currentDish.add(recipeName.getText().toString() + "bitmapName"); // index = 12 Bitmap

                        for (int i = 0; i < allDishes.size(); i++) {
                            if (allDishes.get(i).get(0).toLowerCase().equals(currentDish.get(0).toLowerCase())) {
                                counter = i;
                                System.out.println("Duplicated Dish");
                            } else {

                            }
                        }

                        // Replace in allDish arraylist
                        if (counter != 0) {
                            allDishes.set(counter, currentDish);
                        } else {
                            // No dup, add to the arrayList
                            System.out.println("No Duplicated, add new dish");
                            allDishes.add(currentDish);
                        }
                        // Save to file
                        saveDishList(allDishes); // save arrayList
                        saveBitMap(imageView, currentDish.get(12)); // save bitmap object
                        Intent welcomeScreen = new Intent(NewDishActivity.this, Welcome.class);
                        startActivity(welcomeScreen);
                        finish();
                    }
                    else{

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

    private void saveBitMap(ImageView imageView, String name){
        try{
            BitmapDrawable drawable = (BitmapDrawable) imageView.getDrawable();
            Bitmap bitmap = drawable.getBitmap();

            SerializableBitmap serializableBitmap = new SerializableBitmap(bitmap);

            savedHashMap = getSavedHashMap("savedHashMap");

            savedHashMap.put(name, serializableBitmap);

            FileOutputStream  fileOutputStream  = openFileOutput("savedHashMap",Context.MODE_PRIVATE);
            ObjectOutputStream out = new ObjectOutputStream(fileOutputStream);
            out.writeObject(savedHashMap);
            out.close();
            fileOutputStream.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
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
