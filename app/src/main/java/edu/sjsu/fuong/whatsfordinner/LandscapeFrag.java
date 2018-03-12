package edu.sjsu.fuong.whatsfordinner;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
//import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;


public class LandscapeFrag extends Fragment {

    public ArrayList<ArrayList<String>> allDishes;
    public ArrayList<String> dishNames;
    ArrayList<String> currentDish;

    private ListView dishesList;
    private TextView dishName;
    private TextView ingredientsDetails;
    private TextView descriptionDetails;
    private ImageView imageView;

    public LandscapeFrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_landscape, container, false);
        view.setBackgroundColor(Color.WHITE);

        //int length;

        Intent intent = getActivity().getIntent();
        Bundle args = intent.getExtras();

        if(args == null){

        }
        else{
            allDishes = (ArrayList<ArrayList<String>>) args.getSerializable("dishList");
            //length = allDishes.size();
            dishNames = new ArrayList<>();
            for(int i = 0; i < allDishes.size(); i++){
                dishNames.add(allDishes.get(i).get(0));
                System.out.println("dishNames Item land: " + dishNames.get(i));
            }
            System.out.println("Length dishName in land" + dishNames.size());
        }


        dishName = (TextView) view.findViewById(R.id.dishName);
        imageView = (ImageView) view.findViewById(R.id.imageView);
        ingredientsDetails = (TextView) view.findViewById(R.id.ingredientsDetails);
        descriptionDetails = (TextView) view.findViewById(R.id.descriptionDetails);

        dishesList = (ListView)  view.findViewById(R.id.listViewLand);


        ArrayAdapter<String> landscapeAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, dishNames);
        //ArrayAdapter landscapeAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, dishNames);
        //System.out.println("Adapter Length Land" + landscapeAdapter.getCount());
        dishesList.setAdapter(landscapeAdapter);

        dishesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
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

                // Test
                for(int index = 0; index < currentDish.size(); index++){
                    System.out.println("Inside CurrentDish " + index + " " + currentDish.get(index));
                }

                dishName.setText(currentDish.get(0));
                descriptionDetails.setText(currentDish.get(11));
                // Only show non-empty ingredients
                String ingredientsOfDish = "";
                for(int index = 1; index < 11; index ++){
                    if(currentDish.get(index).isEmpty()){

                    }
                    else{
                        ingredientsOfDish = ingredientsOfDish + currentDish.get(index) + "\n";
                    }
                }
                ingredientsDetails.setText(ingredientsOfDish);

                // Display picture of the dish
                Bitmap currentDishBitmap = getSavedBitmap(currentDish.get(12));
                imageView.setImageBitmap(currentDishBitmap);

            }
        });



        return view;
    }

    private Bitmap getSavedBitmap(String bitmapName) {
        Bitmap savedBitmap = null;

        try {
            FileInputStream inputStream = getActivity().openFileInput(bitmapName);
            ObjectInputStream in = new ObjectInputStream(inputStream);
            savedBitmap = (Bitmap) in.readObject();
            in.close();
            inputStream.close();

        }
        catch (FileNotFoundException fnf){
            System.out.println("Picture Not Found!");
        }
        catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return savedBitmap;
    }

}
