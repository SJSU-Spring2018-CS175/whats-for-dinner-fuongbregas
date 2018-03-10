package edu.sjsu.fuong.whatsfordinner;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.app.FragmentTransaction;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;



public class PortraitFrag extends Fragment  {

    private ListView listViewPortrait;

    public ArrayList<ArrayList<String>> allDishes;
    public ArrayList<String> dishNames;

    public PortraitFrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Bundle args = getArguments();
        dishNames = new ArrayList<>();

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
            System.out.println("dishNames Item port: " + dishNames.get(1));

        }


        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_portrait, container, false);
        view.setBackgroundColor(Color.WHITE);
        ArrayAdapter portraitAdapter = new ArrayAdapter(PortraitFrag.this.getActivity(), android.R.layout.simple_expandable_list_item_1, dishNames);
        listViewPortrait = (ListView) view.findViewById(R.id.listViewPortrait);
        listViewPortrait.setAdapter(portraitAdapter);

        return view;
    }






}
