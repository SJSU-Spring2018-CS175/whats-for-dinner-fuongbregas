package edu.sjsu.fuong.whatsfordinner;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
//import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;


public class LandscapeFrag extends Fragment {

    public ArrayList<ArrayList<String>> allDishes;
    public ArrayList<String> dishNames;

    private ListView dishesList;

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




        dishesList = (ListView)  view.findViewById(R.id.listViewLand);

        ArrayAdapter<String> landscapeAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, dishNames);
        //ArrayAdapter landscapeAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, dishNames);
        //System.out.println("Adapter Length Land" + landscapeAdapter.getCount());
        dishesList.setAdapter(landscapeAdapter);

        return view;
    }
}
