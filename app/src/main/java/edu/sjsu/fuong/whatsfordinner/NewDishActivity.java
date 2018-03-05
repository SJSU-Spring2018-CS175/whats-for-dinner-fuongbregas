package edu.sjsu.fuong.whatsfordinner;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class NewDishActivity extends AppCompatActivity {

    private EditText recipeName;
    private Button submitButton;
    private ImageButton addPicture;
    private AutoCompleteTextView item1;
    private ImageView imageView;


    public ArrayList<String> recipeList;
    public ArrayList<ArrayList<String>> allDishes;


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
}
