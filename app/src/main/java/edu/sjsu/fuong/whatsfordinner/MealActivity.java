package edu.sjsu.fuong.whatsfordinner;

import android.content.ContentValues;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.Spinner;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.logging.Logger;

public class MealActivity extends AppCompatActivity
{
    private ArrayList<String> dishNameArrayList;
    private ArrayAdapter<String> dishNameAdapter;
    private HashMap<String, Integer> mealDish;

    // UI
    private Spinner mondayBreakfastSpinner;
    private Spinner mondayLunchSpinner;
    private Spinner mondayDinnerSpinner;

    private Spinner tuesdayBreakfastSpinner;
    private Spinner tuesdayLunchSpinner;
    private Spinner tuesdayDinnerSpinner;

    private Spinner wednesdayBreakfastSpinner;
    private Spinner wednesdayLunchSpinner;
    private Spinner wednesdayDinnerSpinner;

    private Spinner thursdayBreakfastSpinner;
    private Spinner thursdayLunchSpinner;
    private Spinner thursdayDinnerSpinner;

    private Spinner fridayBreakfastSpinner;
    private Spinner fridayLunchSpinner;
    private Spinner fridayDinnerSpinner;

    private Spinner saturdayBreakfastSpinner;
    private Spinner saturdayLunchSpinner;
    private Spinner saturdayDinnerSpinner;

    private Spinner sundayBreakfastSpinner;
    private Spinner sundayLunchSpinner;
    private Spinner sundayDinnerSpinner;

    private Button saveButton;
    private ScrollView scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal);

        mealDish = getmealDish();
        dishNameArrayList = new ArrayList<>();
        dishNameArrayList.add("Eating Out");
        final int dishTotal = dishNameArrayList.size();
        for(Map.Entry<String, Integer> e : mealDish.entrySet()) {
            String key = e.getKey();
            Integer value = e.getValue();

            while(value != 0){
                dishNameArrayList.add(key);
                value--;
            }
        }

        dishNameAdapter = new ArrayAdapter<String>(MealActivity.this, android.R.layout.simple_spinner_item, dishNameArrayList);
        dishNameAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // UI
        scrollView = (ScrollView) findViewById(R.id.scrollView);
        // Monday
        mondayBreakfastSpinner = (Spinner) findViewById(R.id.mondayBreakfastSpinner);
        mondayBreakfastSpinner.setPrompt("Eating Out");
        mondayBreakfastSpinner.setAdapter(dishNameAdapter);

        mondayBreakfastSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String itemAt = mondayBreakfastSpinner.getItemAtPosition(i).toString();

                if(itemAt.equals("Eating Out")){

                }
                else{
                    dishNameArrayList.remove(i);
                    mondayBreakfastSpinner.setEnabled(false);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        mondayLunchSpinner = (Spinner) findViewById(R.id.mondayLunchSpinner);
        mondayLunchSpinner.setPrompt("Eating Out");
        mondayLunchSpinner.setAdapter(dishNameAdapter);
        mondayLunchSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String itemAt = mondayLunchSpinner.getItemAtPosition(i).toString();

                if(itemAt.equals("Eating Out")){

                }
                else{
                    dishNameArrayList.remove(i);
                    mondayLunchSpinner.setEnabled(false);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        mondayDinnerSpinner = (Spinner) findViewById(R.id.mondayDinnerSpinner);
        mondayDinnerSpinner.setPrompt("Eating Out");
        mondayDinnerSpinner.setAdapter(dishNameAdapter);
        mondayDinnerSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String itemAt = mondayDinnerSpinner.getItemAtPosition(i).toString();

                if(itemAt.equals("Eating Out")){

                }
                else{
                    dishNameArrayList.remove(i);
                    mondayDinnerSpinner.setEnabled(false);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        // Tuesday
        tuesdayBreakfastSpinner = (Spinner) findViewById(R.id.tuesdayBreakfastSpinner);
        tuesdayBreakfastSpinner.setPrompt("Eating Out");
        tuesdayBreakfastSpinner.setAdapter(dishNameAdapter);

        tuesdayBreakfastSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String itemAt = tuesdayBreakfastSpinner.getItemAtPosition(i).toString();

                if(itemAt.equals("Eating Out")){

                }
                else{
                    dishNameArrayList.remove(i);
                    tuesdayBreakfastSpinner.setEnabled(false);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        tuesdayLunchSpinner = (Spinner) findViewById(R.id.tuesdayLunchSpinner);
        tuesdayLunchSpinner.setPrompt("Eating Out");
        tuesdayLunchSpinner.setAdapter(dishNameAdapter);
        tuesdayLunchSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String itemAt = tuesdayLunchSpinner.getItemAtPosition(i).toString();

                if(itemAt.equals("Eating Out")){

                }
                else{
                    dishNameArrayList.remove(i);
                    tuesdayLunchSpinner.setEnabled(false);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        tuesdayDinnerSpinner = (Spinner) findViewById(R.id.tuesdayDinnerSpinner);
        tuesdayDinnerSpinner.setPrompt("Eating Out");
        tuesdayDinnerSpinner.setAdapter(dishNameAdapter);
        tuesdayDinnerSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String itemAt = tuesdayDinnerSpinner.getItemAtPosition(i).toString();

                if(itemAt.equals("Eating Out")){

                }
                else{
                    dishNameArrayList.remove(i);
                    tuesdayDinnerSpinner.setEnabled(false);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        // WEDNESDAY
        wednesdayBreakfastSpinner = (Spinner) findViewById(R.id.wednesdayBreakfastSpinner);
        wednesdayBreakfastSpinner.setPrompt("Eating Out");
        wednesdayBreakfastSpinner.setAdapter(dishNameAdapter);

        wednesdayBreakfastSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String itemAt = wednesdayBreakfastSpinner.getItemAtPosition(i).toString();

                if(itemAt.equals("Eating Out")){

                }
                else{
                    dishNameArrayList.remove(i);
                    wednesdayBreakfastSpinner.setEnabled(false);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        wednesdayLunchSpinner = (Spinner) findViewById(R.id.wednesdayLunchSpinner);
        wednesdayLunchSpinner.setPrompt("Eating Out");
        wednesdayLunchSpinner.setAdapter(dishNameAdapter);
        wednesdayLunchSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String itemAt = wednesdayLunchSpinner.getItemAtPosition(i).toString();

                if(itemAt.equals("Eating Out")){

                }
                else{
                    dishNameArrayList.remove(i);
                    wednesdayLunchSpinner.setEnabled(false);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        wednesdayDinnerSpinner = (Spinner) findViewById(R.id.wednesdayDinnerSpinner);
        wednesdayDinnerSpinner.setPrompt("Eating Out");
        wednesdayDinnerSpinner.setAdapter(dishNameAdapter);
        wednesdayDinnerSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String itemAt = wednesdayDinnerSpinner.getItemAtPosition(i).toString();

                if(itemAt.equals("Eating Out")){

                }
                else{
                    dishNameArrayList.remove(i);
                    wednesdayDinnerSpinner.setEnabled(false);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        // THURSDAY
        thursdayBreakfastSpinner = (Spinner) findViewById(R.id.thursdayBreakfastSpinner);
        thursdayBreakfastSpinner.setPrompt("Eating Out");
        thursdayBreakfastSpinner.setAdapter(dishNameAdapter);

        thursdayBreakfastSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String itemAt = thursdayBreakfastSpinner.getItemAtPosition(i).toString();

                if(itemAt.equals("Eating Out")){

                }
                else{
                    dishNameArrayList.remove(i);
                    thursdayBreakfastSpinner.setEnabled(false);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        thursdayLunchSpinner = (Spinner) findViewById(R.id.thursdayLunchSpinner);
        thursdayLunchSpinner.setPrompt("Eating Out");
        thursdayLunchSpinner.setAdapter(dishNameAdapter);
        thursdayLunchSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String itemAt = thursdayLunchSpinner.getItemAtPosition(i).toString();

                if(itemAt.equals("Eating Out")){

                }
                else{
                    dishNameArrayList.remove(i);
                    thursdayLunchSpinner.setEnabled(false);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        thursdayDinnerSpinner = (Spinner) findViewById(R.id.thursdayDinnerSpinner);
        thursdayDinnerSpinner.setPrompt("Eating Out");
        thursdayDinnerSpinner.setAdapter(dishNameAdapter);
        thursdayDinnerSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String itemAt = thursdayDinnerSpinner.getItemAtPosition(i).toString();

                if(itemAt.equals("Eating Out")){

                }
                else{
                    dishNameArrayList.remove(i);
                    thursdayDinnerSpinner.setEnabled(false);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        // FRIDAY
        fridayBreakfastSpinner = (Spinner) findViewById(R.id.fridayBreakfastSpinner);
        fridayBreakfastSpinner.setPrompt("Eating Out");
        fridayBreakfastSpinner.setAdapter(dishNameAdapter);

        fridayBreakfastSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String itemAt = fridayBreakfastSpinner.getItemAtPosition(i).toString();

                if(itemAt.equals("Eating Out")){

                }
                else{
                    dishNameArrayList.remove(i);
                    fridayBreakfastSpinner.setEnabled(false);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        fridayLunchSpinner = (Spinner) findViewById(R.id.fridayLunchSpinner);
        fridayLunchSpinner.setPrompt("Eating Out");
        fridayLunchSpinner.setAdapter(dishNameAdapter);
        fridayLunchSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String itemAt = fridayLunchSpinner.getItemAtPosition(i).toString();

                if(itemAt.equals("Eating Out")){

                }
                else{
                    dishNameArrayList.remove(i);
                    fridayLunchSpinner.setEnabled(false);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        fridayDinnerSpinner = (Spinner) findViewById(R.id.fridayDinnerSpinner);
        fridayDinnerSpinner.setPrompt("Eating Out");
        fridayDinnerSpinner.setAdapter(dishNameAdapter);
        fridayDinnerSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String itemAt = fridayDinnerSpinner.getItemAtPosition(i).toString();

                if(itemAt.equals("Eating Out")){

                }
                else{
                    dishNameArrayList.remove(i);
                    fridayDinnerSpinner.setEnabled(false);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        // SATURDAY
        saturdayBreakfastSpinner = (Spinner) findViewById(R.id.saturdayBreakfastSpinner);
        saturdayBreakfastSpinner.setPrompt("Eating Out");
        saturdayBreakfastSpinner.setAdapter(dishNameAdapter);

        saturdayBreakfastSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String itemAt = saturdayBreakfastSpinner.getItemAtPosition(i).toString();

                if(itemAt.equals("Eating Out")){

                }
                else{
                    dishNameArrayList.remove(i);
                    saturdayBreakfastSpinner.setEnabled(false);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        saturdayLunchSpinner = (Spinner) findViewById(R.id.saturdayLunchSpinner);
        saturdayLunchSpinner.setPrompt("Eating Out");
        saturdayLunchSpinner.setAdapter(dishNameAdapter);
        saturdayLunchSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String itemAt = saturdayLunchSpinner.getItemAtPosition(i).toString();

                if(itemAt.equals("Eating Out")){

                }
                else{
                    dishNameArrayList.remove(i);
                    saturdayLunchSpinner.setEnabled(false);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        saturdayDinnerSpinner = (Spinner) findViewById(R.id.saturdayDinnerSpinner);
        saturdayDinnerSpinner.setPrompt("Eating Out");
        saturdayDinnerSpinner.setAdapter(dishNameAdapter);
        saturdayDinnerSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String itemAt = saturdayDinnerSpinner.getItemAtPosition(i).toString();

                if(itemAt.equals("Eating Out")){

                }
                else{
                    dishNameArrayList.remove(i);
                    saturdayDinnerSpinner.setEnabled(false);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        // SUNDAY
        sundayBreakfastSpinner = (Spinner) findViewById(R.id.sundayBreakfastSpinner);
        sundayBreakfastSpinner.setPrompt("Eating Out");
        sundayBreakfastSpinner.setAdapter(dishNameAdapter);

        sundayBreakfastSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String itemAt = sundayBreakfastSpinner.getItemAtPosition(i).toString();

                if(itemAt.equals("Eating Out")){

                }
                else{
                    dishNameArrayList.remove(i);
                    sundayBreakfastSpinner.setEnabled(false);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        sundayLunchSpinner = (Spinner) findViewById(R.id.sundayLunchSpinner);
        sundayLunchSpinner.setPrompt("Eating Out");
        sundayLunchSpinner.setAdapter(dishNameAdapter);
        sundayLunchSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String itemAt = sundayLunchSpinner.getItemAtPosition(i).toString();

                if(itemAt.equals("Eating Out")){

                }
                else{
                    dishNameArrayList.remove(i);
                    sundayLunchSpinner.setEnabled(false);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        sundayDinnerSpinner = (Spinner) findViewById(R.id.sundayDinnerSpinner);
        sundayDinnerSpinner.setPrompt("Eating Out");
        sundayDinnerSpinner.setAdapter(dishNameAdapter);
        sundayDinnerSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String itemAt = sundayDinnerSpinner.getItemAtPosition(i).toString();

                if(itemAt.equals("Eating Out")){

                }
                else{
                    dishNameArrayList.remove(i);
                    sundayDinnerSpinner.setEnabled(false);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        // Save
        saveButton = (Button) findViewById(R.id.saveButton);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dishNameArrayList.remove(0);
                HashMap<String, Integer> saveMapDishNameQty = new HashMap<>();
                for(String t: dishNameArrayList) {
                    Integer i = saveMapDishNameQty.get(t);
                    if (i ==  null) {
                        i = 0;
                    }
                    saveMapDishNameQty.put(t, i + 1);
                }
                // Testing
                for(Map.Entry<String, Integer> e : saveMapDishNameQty.entrySet()) {
                    String key = e.getKey();
                    Integer value = e.getValue();
                    System.out.println("Key " + key + " Value " + value);
                }
                // Save the new Meal Dish
                saveMealDish(saveMapDishNameQty);
                Bitmap bm = getBitmapFromView(scrollView, scrollView.getChildAt(0).getHeight(), scrollView.getChildAt(0).getWidth());
                CaptureImage(bm);
                //MediaStore.Images.Media.insertImage(getContentResolver(), bm, "YourMealPlan" , "");
                finish();
            }
        });
    }


    // HashMap contains dishName and Quantity
    private void saveMealDish(HashMap<String, Integer> mealDish) {
        try {
            FileOutputStream fileOutputStream = openFileOutput("mealDishHashMap", Context.MODE_PRIVATE);
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
            FileInputStream inputStream = openFileInput("mealDishHashMap");
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

    private Bitmap getBitmapFromView(View view, int height, int width) {
        Bitmap bitmap = Bitmap.createBitmap(width, height,Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Drawable bgDrawable =view.getBackground();
        if (bgDrawable!=null)
            bgDrawable.draw(canvas);
        else
            canvas.drawColor(Color.WHITE);
        view.draw(canvas);
        return bitmap;
    }

    public void CaptureImage(Bitmap bitmap) {

        try {
            File imageFile;
            File dir;


            dir = new File(Environment.getExternalStorageDirectory(), "FolderName");

            boolean success = true;
            if (!dir.exists()) {
                success = dir.mkdirs();
            }
            if (success) {
                java.util.Date date = new java.util.Date();
                imageFile = new File(dir.getAbsolutePath()
                        + File.separator
                        + new java.sql.Timestamp(date.getTime()).toString()
                        + "Image.jpg");

                imageFile.createNewFile();
            } else {
                return;
            }
            ByteArrayOutputStream ostream = new ByteArrayOutputStream();

            // save image into gallery
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, ostream);

            FileOutputStream fout = new FileOutputStream(imageFile);
            fout.write(ostream.toByteArray());
            fout.close();
            ContentValues values = new ContentValues();

            values.put(MediaStore.Images.Media.DATE_TAKEN,
                    System.currentTimeMillis());
            values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
            values.put(MediaStore.MediaColumns.DATA,
                    imageFile.getAbsolutePath());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
