package com.richa.tracktouch_v1;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;


public class forFullScreen extends AppCompatActivity implements
        View.OnTouchListener,
        GestureDetector.OnGestureListener {

    private static final String TAG = "forFullScreen";
    private static final String FILE_NAME = "CoordinatesData.txt";
    ///data/data/com.richa.tracktouch_v1/files/CoordinatesData.txt

    public Button btnPrevious,btn_Next,btnSaveData,btnReadData;
    public ImageView imageview;
    public int index = 0;
    public int TotalImagesSize;
    public Context context;
    TextView tv;

    ArrayList<String> images;
    ArrayList<ImageJson> imageJsonList = new ArrayList<>();
    String json;
    private Object Graphics;

    public ArrayList<String> getAllShownImagesPath() {
        Uri uri;
        Cursor cursor;
        int column_index_data;
        // int column_index_folder_name;
        ArrayList<String> listOfAllImages = new ArrayList<>();
        String absolutePathOfImage;
        uri = android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI;

        String[] projection = { MediaStore.MediaColumns.DATA,
                MediaStore.Images.Media.BUCKET_DISPLAY_NAME };

        cursor = getApplication().getContentResolver().query(uri, projection, null,
                null, null);

        column_index_data = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
        // column_index_folder_name = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_DISPLAY_NAME);
        while (cursor.moveToNext()) {
            absolutePathOfImage = cursor.getString(column_index_data);

            listOfAllImages.add(absolutePathOfImage);
        }
        return listOfAllImages;
    }

    //int SELECT_IMAGE_CODE = 1;
    //using scale gesture detector for zoom
    ScaleGestureDetector scaleGestureDetector;
    float scaleFactor = 1.0f;
    private GestureDetector mGestureDetector;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_for_full_screen);

        imageview =findViewById(R.id.image);
        btnPrevious = findViewById(R.id.btnPrev);
        btn_Next = findViewById(R.id.btnNext);
        tv=(TextView) findViewById(R.id.coordinatesText);
        tv.setText("your desired text");
        tv.setVisibility(View.INVISIBLE);

        mGestureDetector = new GestureDetector(this, this);

        images = getAllShownImagesPath();
        TotalImagesSize = images.size();
        Log.e("forFullScreen", "onCreate: TotalImagesSize "+TotalImagesSize );

        // Buttons - Previous and Next

        if(index <= 0){
            btnPrevious.setVisibility(View.GONE);
        }else{
            btnPrevious.setVisibility(View.VISIBLE);
        }
        if(index >= (TotalImagesSize - 1 )){
            btn_Next.setVisibility(View.GONE);
        }else{
            btnPrevious.setVisibility(View.VISIBLE);
        }

        // loadData();

        ArrayList<String> finalImages1 = images;
        btnPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Glide.with(getApplicationContext()).load(finalImages1.get(index))
                        .placeholder(R.drawable.ic_launcher_background).centerCrop()
                        .into(imageview);
                Log.e("forFullScreen", "Image Index is: "+index );
                index--;

                if(index <= 0){
                    btnPrevious.setVisibility(View.GONE);
                }else{
                    btnPrevious.setVisibility(View.VISIBLE);
                }
                if(index >= (TotalImagesSize - 1 )){
                    btn_Next.setVisibility(View.GONE);
                }else{
                    btnPrevious.setVisibility(View.VISIBLE);
                }
            }
        });

        ArrayList<String> finalImages = images;
        btn_Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Glide.with(getApplicationContext()).load(finalImages.get(index))
                        .placeholder(R.drawable.ic_launcher_background).centerCrop()
                        .into(imageview);
                Log.e("forFullScreen", "Image Index is: "+index );
                index++;

                if(index <= 0){
                    btnPrevious.setVisibility(View.GONE);
                }else{
                    btnPrevious.setVisibility(View.VISIBLE);
                }
                if(index >= (TotalImagesSize - 1 )){
                    btn_Next.setVisibility(View.GONE);
                }else{
                    btnPrevious.setVisibility(View.VISIBLE);
                }
            }
        });

        // Buttons - Previous and Next

        imageview.setOnTouchListener(this);

        String imagepath = getIntent().getExtras().getString("imagepath");

        Toast.makeText(getApplicationContext(), "Imagepath: "+imagepath, Toast.LENGTH_SHORT).show();

        Log.e("forFullScreen", "onCreate: "+imagepath );

        Glide.with(this).load(imagepath).into(imageview);

    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {

        switch (ev.getActionMasked())
        {
            case MotionEvent.ACTION_DOWN:
                Log.d(TAG, "dispatchTouchEvent: Activity dispatchEvent DOWN");
                break;

            case MotionEvent.ACTION_MOVE:
                Log.d(TAG, "dispatchTouchEvent: Activity dispatchEvent MOVE");
                break;

            case MotionEvent.ACTION_UP:
                Log.d(TAG, "dispatchTouchEvent: Activity dispatchEvent UP");
                break;

            case MotionEvent.ACTION_CANCEL:
                Log.d(TAG, "dispatchTouchEvent: Activity dispatchEvent CANCEL");
                break;
        }


        boolean b = super.dispatchTouchEvent(ev);

        return b;
    }

    public boolean onInterceptTouchEvent(MotionEvent ev) {

        switch (ev.getActionMasked())
        {
            case MotionEvent.ACTION_DOWN:
                Log.d(TAG, " Activity onInterceptTouchEvent DOWN");
                break;

            case MotionEvent.ACTION_MOVE:
                Log.d(TAG, " Activity onInterceptTouchEvent MOVE");
                break;

            case MotionEvent.ACTION_UP:
                Log.d(TAG, " Activity onInterceptTouchEvent UP");
                break;

            case MotionEvent.ACTION_CANCEL:
                Log.d(TAG, " Activity onInterceptTouchEvent CANCEL");
                break;

        }

        boolean b = super.dispatchTouchEvent(ev);

        Log.d(TAG,"Activity onInterceptTouchEvent RETURNS"+b);

        return b;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {

        switch (ev.getActionMasked())
        {
            case MotionEvent.ACTION_DOWN:
                Log.d(TAG, " Activity onTouchEvent DOWN");
                break;

            case MotionEvent.ACTION_MOVE:
                Log.d(TAG, " Activity onTouchEvent MOVE");
                break;

            case MotionEvent.ACTION_UP:
                Log.d(TAG, " Activity onTouchEvent UP");
                break;

            case MotionEvent.ACTION_CANCEL:
                Log.d(TAG, " Activity onTouchEvent CANCEL");
                break;
        }

        boolean b = super.dispatchTouchEvent(ev);

        Log.d(TAG,"Activity onTouchEvent RETURNS"+b);

        return b;
    }

    public void save(View v){
        //String Fresult = json;
        FileOutputStream fos = null;

        try {
            fos = openFileOutput(FILE_NAME,MODE_PRIVATE); // only our app will acess file
            fos.write(json.getBytes());

            Toast.makeText(getApplicationContext(), "Saved to" + getFilesDir() + "/" + FILE_NAME, Toast.LENGTH_LONG).show();
            String path = String.valueOf(getFilesDir());
            Log.e("ForFullScreen","Saved file path is"+path);
            Log.e("ForFullScreen","Saved json filw write"+json);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(fos != null){
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void read(View v) throws FileNotFoundException {
            String data = json;
            FileInputStream fis = null;
            try {
                fis = openFileInput(FILE_NAME);
//                Scanner in = new Scanner(fis);
//
//                while (in.hasNext()){
//                    Log.i("forFullScreen","accessed");
//                    System.out.println(in.nextLine());
//                    Toast.makeText(getApplicationContext(), "File is from path  " + getFilesDir()+ "/" + FILE_NAME + "is ACCESSED", Toast.LENGTH_LONG).show();
//                }
                fis.read(json.getBytes());
                InputStreamReader isr = new InputStreamReader(fis);
                BufferedReader bufferedReader = new BufferedReader(isr);
                StringBuilder sb = new StringBuilder(json);
    
                Toast.makeText(getApplicationContext(), "Reading from" + getFilesDir() + "/" + FILE_NAME, Toast.LENGTH_LONG).show();
                String path = String.valueOf(getFilesDir());
                Log.e("ForFullScreen","Reading file path is"+path);
                System.out.println(sb);

                
//                String readingtext = sb.toString();
//
//                while ((readingtext = bufferedReader.readLine()) != null) {
//                    sb.append(readingtext);
//                }
//
//                Toast.makeText(getApplicationContext(), "Reading from" + getFilesDir() + "/" + FILE_NAME, Toast.LENGTH_LONG).show();
//                String path = String.valueOf(getFilesDir());
//
//                if (readingtext != null){
//                    sb.append(readingtext);
//                }
//                else{
//                    Toast.makeText(getApplicationContext(), "file is blank", Toast.LENGTH_LONG).show();
//                }
//
//                Log.e("ForFullScreen","Saved file path is"+path);
//                //  Log.e("ForFullScreen","Saved json filw read"+imageJsonList);
//                Log.e("ForFullScreen","Saved json filw read"+readingtext);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                if(fis != null){
                    try {
                        fis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {

        int action = motionEvent.getAction();
        switch(action) {
            case (MotionEvent.ACTION_DOWN) :

                // Co-ordinate ratio
                float imageViewWidth = imageview.getWidth();
                float imageViewHeight = imageview.getHeight();
                float imageWidth = imageview.getDrawable().getIntrinsicWidth();
                float imageHeight = imageview.getDrawable().getIntrinsicHeight();
                float x = motionEvent.getX();
                float y = motionEvent.getY();
                Log.d(TAG,"Activity: imageView.getWidth=" + imageViewWidth);
                Log.d(TAG,"Activity: imageView.getHeight=" + imageViewHeight);
                Log.d(TAG,"Activity: imageWidth=" + imageWidth);
                Log.d(TAG,"Activity: imageHeight=" + imageHeight);
                Log.d(TAG, "onTouch: (x,y): (" + x + " , " + y + ")");
                final float imageX = (imageWidth  * motionEvent.getX()) / imageViewWidth;
                final float imageY = (imageHeight  * motionEvent.getY()) / imageViewHeight;
                Log.d("RESULT", "onTouch: (x,y) Actual: (" + imageX + " , " + imageY + ")");

               // int result = Log.d(TAG, "(x,y) : (" + imageX + " , " + imageY + ")");

                String imageX1 = Float.toString(imageX);
                String imageY1 = Float.toString(imageY);

                String FINALimageX1 = Float.toString(imageX);
                String FINALimageY1 = Float.toString(imageY);

                if((imageX != 0) && (imageY != 0)){
                    Glide.with(getApplicationContext()).load(images.get(index))
                            .placeholder(R.drawable.ic_launcher_background).centerCrop()
                            .into(imageview);
                    index++;
//JSON ARRAY - WORKING ------------------------------------------------------------------------------JSON ARRAY - WORKING
//https://www.youtube.com/watch?v=f-kcvxYZrB4&list=PLrnPJCHvNZuBdsuDMl3I-EOEOnCh6JNF3&index=2

                   // Gson gson = new Gson();

                    String imagePath = getIntent().getExtras().getString("imagepath");

                    ImageJson imagejson = new ImageJson(imageX,imageY);
                    //String json = gson.toJson(imagejson);

                    imageJsonList.add(imagejson);

                    Gson gson = new Gson();

                    json = gson.toJson(imageJsonList);
                    Log.e("forFullScreen","final result:"+json);
                    Log.e("forFullScreen","data:"+imageJsonList);

                }
//                TextView textview = (TextView) findViewById(R.id.coordinatesText);
//                TextView tv1 = new TextView(this);
                tv.setX(imageX);
                tv.setY(imageY);
                tv.setText(imageX + "," + imageY);
                tv.setVisibility(View.VISIBLE);
                return true;

            case (MotionEvent.ACTION_MOVE) :
//                endCoordinate.x = motionEvent.getX();
//                endCoordinate.y = motionEvent.getY();
//                invalidateOptionsMenu();
                Log.d(TAG,"Activity : Action was MOVE");
//                Log.d(TAG, "onTouch: (x,y): (" + motionEvent.getX() + " , " + motionEvent.getY() + ")");
                return true;
            case (MotionEvent.ACTION_UP) :
//                drawRectangle = false;
                invalidateOptionsMenu();
                Log.d(TAG,"Activity: Action was UP");
//                Log.d(TAG, "onTouch: (x,y): (" + motionEvent.getX() + " , " + motionEvent.getY() + ")");
                return true;
            case (MotionEvent.ACTION_CANCEL) :
                Log.d(TAG,"Activity : Action was CANCEL");
                return true;
            case (MotionEvent.ACTION_OUTSIDE) :
                Log.d(TAG,"Movement occurred outside bounds " +
                        "of current screen element");
//                Log.d(TAG, "onTouch: (x,y): (" + motionEvent.getX() + " , " + motionEvent.getY() + ")");
                return true;
            default :
                return super.onTouchEvent(motionEvent);
        }


    }

    /*
        GestureDetector
     */
    @Override
    public boolean onDown(MotionEvent motionEvent) {
        Log.d(TAG, "onDown: called.");
        return false;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {
        Log.d(TAG, "onShowPress: called.");

    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        Log.d(TAG, "onSingleTapUp: called.");
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        Log.d(TAG, "onScroll: called.");
        return false;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {
        Log.d(TAG, "onLongPress: called.");


    }

    @Override
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        Log.d(TAG, "onFling: called.");
        return false;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1){

            imageview.setImageResource(Integer.parseInt(images.get(MainActivity.ImageAdapter.images.toArray().length)));

//            Uri uri = data.getData();
//            imageview.setImageURI(uri);
        }
    }
}

















