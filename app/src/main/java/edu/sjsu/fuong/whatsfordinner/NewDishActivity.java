package edu.sjsu.fuong.whatsfordinner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;

public class NewDishActivity extends AppCompatActivity {

    private EditText recipeName;
    private Button submitButton;
    private ImageButton addPicture;
    private AutoCompleteTextView item1;

    public ArrayList<String> recipeList;
    public ArrayList<ArrayList<String>> allDishes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_dish);

        recipeName = (EditText) findViewById(R.id.recipeName);
        submitButton = (Button) findViewById(R.id.submitButt);






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
                        }
                        // If no, add new dishes to all dish list
                        if(i >= allDishes.size()){
                            allDishes.add(recipeList);

                            System.out.println("Add new dish" );
                        }
                    }
                }
            }
        });
    }
}
