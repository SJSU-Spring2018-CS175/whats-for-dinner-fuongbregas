package edu.sjsu.fuong.whatsfordinner;

import android.app.FragmentTransaction;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class RecipeActivity extends AppCompatActivity implements Serializable {

    public ArrayList<ArrayList<String>> allDishes;
    private Bitmap testBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);


        allDishes = getDishes();
        //testBitmap = getSavedBitmap(allDishes.get(0).get(12));

        Bundle bundle = new Bundle();

        bundle.putSerializable("dishList", allDishes);

        Configuration configuration = getResources().getConfiguration();

        // Step 2.1: Get a Fragment object
        android.app.FragmentManager fragmentManager = getFragmentManager();

        // Step 2.2: Get a fragment transaction from the fragment manager object
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        // Determine the orientation landscape or portrait
        if(configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            LandscapeFrag landscapeFragment = new LandscapeFrag();
            //landscapeFragment.setArguments(bundle);
            fragmentTransaction.add(android.R.id.content, landscapeFragment);
            fragmentTransaction.commit();
        }

        if(configuration.orientation == Configuration.ORIENTATION_PORTRAIT){
            PortraitFrag portraitFragment = new PortraitFrag();
            portraitFragment.setArguments(bundle);
            fragmentTransaction.add(android.R.id.content, portraitFragment);
            fragmentTransaction.commit();
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


}
