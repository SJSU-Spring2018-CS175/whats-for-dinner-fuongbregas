package edu.sjsu.fuong.whatsfordinner;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.app.FragmentTransaction;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;


public class PortraitFrag extends Fragment  {

    private ListView listViewPortrait;

    public ArrayList<ArrayList<String>> allDishes;
    public ArrayList<String> dishNames; // Use to display the ListView
    public ArrayList<String> currentDish; // Selected Dish

    public HashMap<String, Integer> mealDish;
    public Integer dishQuantity = 0;
    public String currentDishName;

    // For Meal Activity


    public PortraitFrag() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Bundle args = getArguments();
        dishNames = new ArrayList<>();
        //setRetainInstance(true);

        if(args == null){
            //Toast.makeText(PortraitFrag.this.getActivity(), "Args is Empty", Toast.LENGTH_LONG).show();
        }else{
            // Get ArrayList from RecipeAct
            allDishes = (ArrayList<ArrayList<String>>) args.getSerializable("dishList");
            System.out.println("Length of allDishes " + allDishes.size());
            // Get all the string at index 0, which are names of dishes

            for(int i = 0; i < allDishes.size(); i++){
                System.out.println("Dish Name: " + allDishes.get(i).get(0));
                dishNames.add(allDishes.get(i).get(0));
            }
            System.out.println("Length of dishNames " + dishNames.size());
            //System.out.println("dishNames Item port: " + dishNames.get(1));

        }


        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_portrait, container, false);
        view.setBackgroundColor(Color.WHITE);
        ArrayAdapter portraitAdapter = new ArrayAdapter(PortraitFrag.this.getActivity(), android.R.layout.simple_expandable_list_item_1, dishNames);
        listViewPortrait = (ListView) view.findViewById(R.id.listViewPortrait);
        listViewPortrait.setAdapter(portraitAdapter);

        listViewPortrait.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                mealDish = getmealDish();
                currentDishName = (String) adapterView.getItemAtPosition(i);
                if(mealDish.get(currentDishName) == null){
                    dishQuantity = dishQuantity + 1;
                }
                else{
                    dishQuantity = mealDish.get(currentDishName) + 1;
                }

                System.out.println("currentDishName value: " + currentDishName);
                System.out.println("dishQuantity value: " + dishQuantity);
                Toast.makeText(getActivity(), currentDishName + " is added " + dishQuantity +" times", Toast.LENGTH_SHORT).show();
                // If Dish is not in hashMap, add or replace
                mealDish.put(currentDishName, dishQuantity);
                saveMealDish(mealDish);
            }
        });

        listViewPortrait.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if(!hasFocus){
                    System.out.println("currentDishName value: " + currentDishName);
                    System.out.println("dishQuantity value: " + dishQuantity);

                    currentDishName = "";
                    dishQuantity = 0;
                }
                else{
                    System.out.println("Still Focusing");
                }
            }
        });

        listViewPortrait.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                currentDish = new ArrayList<>(); // used to assigned the selected dish from allDishes
                String dishSelected = (String) adapterView.getItemAtPosition(i); // Compare this string to get the dish from allDished


                for(int index = 0; index < allDishes.size(); index ++){
                    if(!dishSelected.equals(allDishes.get(i).get(0))){
                        System.out.println("Not at index " + index);
                    }
                    else{
                        currentDish = allDishes.get(i);
                        break;
                    }
                }

                if(currentDish  != null){
                    Intent intent = new Intent(getActivity(), EditDishActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("currentDish", currentDish);
                    intent.putExtras(bundle);
                    startActivity(intent);
                    return true;
                }
                else{
                    System.out.println("Can't start Activity");
                    return false;
                }
            }
        });

        return view;
    }

    private void saveMealDish(HashMap<String, Integer> mealDish) {
        try {
            FileOutputStream fileOutputStream = getActivity().openFileOutput("mealDishHashMap", Context.MODE_PRIVATE);
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
            FileInputStream inputStream = getActivity().openFileInput("mealDishHashMap");
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
