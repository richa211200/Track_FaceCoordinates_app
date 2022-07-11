package com.richa.tracktouch_v1;
import java.util.ArrayList;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.MediaStore.MediaColumns;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.ViewGroup;
//import android.widget.AdapterView;
//import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.collection.CircularArray;
import androidx.core.app.ActivityCompat;

import com.bumptech.glide.Glide;

public class MainActivity extends Activity {

    private static final String TAG = "MainActivity";

    public static ArrayList<String> listOfAllImages = new ArrayList<>();

//    //public int i; // global counter
//    int SELECT_IMAGE_CODE = 1;
//    ScaleGestureDetector scaleGestureDetector;
//    float scaleFactor = 1.0f;
//    private GestureDetector mGestureDetector;
    ImageView imageview;
    int position =0;


    /** The images. */
    public ArrayList<String> images;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE}, PackageManager.PERMISSION_GRANTED);
//        imageview = findViewById(R.id.image);
//        mGestureDetector = new GestureDetector(this, this);
//        //imageview.setOnTouchListener(this);

        GridView gallery = findViewById(R.id.galleryGridView);

        gallery.setAdapter(new ImageAdapter(this));
        int count = gallery.getAdapter().getCount();
        position = count;

        Log.i(TAG,"count : " + count);
        Log.i(TAG,"position : " + position);

        Toast.makeText(getApplicationContext(), "Total Images Count: "+count, Toast.LENGTH_SHORT).show();


//        gallery.setOnItemClickListener((parent, view, position, arg3) -> {
//            if (null != images && !images.isEmpty())
//                Toast.makeText(
//                        getApplicationContext(),
//                        "position " + position + " " + images.get(position),
//                        Toast.LENGTH_LONG).show();
//
//
//        });

        gallery.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(),forFullScreen.class);
                intent.putExtra("imagepath", ImageAdapter.images.get(position));
                startActivity(intent);
            }
        });

    }

    /**
     * The Class ImageAdapter.
     */
    static class ImageAdapter extends BaseAdapter {

        public static ArrayList<String> images;
        /** The context. */
        public Activity context;

        /**
         * Instantiates a new image adapter.
         *
         * @param localContext
         *            the local context
         */
        public ImageAdapter(Activity localContext) {
            context = localContext;
            images = getAllShownImagesPath(context);
        }

        public int getCount() {
            return images.size();
        }

        public Object getItem(int position) {
            return position;
        }

        public long getItemId(int position) {

            return position;
        }

        public View getView(final int position, View convertView,
                            ViewGroup parent) {
            ImageView picturesView;
            if (convertView == null) {
                picturesView = new ImageView(context);
                picturesView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                picturesView
                        .setLayoutParams(new GridView.LayoutParams(270, 270));

            } else {
                picturesView = (ImageView) convertView;
            }

            Glide.with(context).load(images.get(position))
                    .placeholder(R.drawable.ic_launcher_background).centerCrop()
                    .into(picturesView);

            return picturesView;
        }

        /**
         * Getting All Images Path.
         *
         * @param activity
         *            the activity
         * @return ArrayList with images Path
         */
        @SuppressLint("Recycle")
        private ArrayList<String> getAllShownImagesPath(Activity activity) {
            Uri uri;
            Cursor cursor;
            int column_index_data;
           // int column_index_folder_name;
            ArrayList<String> listOfAllImages = new ArrayList<>();
            String absolutePathOfImage;
            uri = android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI;

            String[] projection = { MediaColumns.DATA,
                    MediaStore.Images.Media.BUCKET_DISPLAY_NAME };

            cursor = activity.getContentResolver().query(uri, projection, null,
                    null, null);

            column_index_data = cursor.getColumnIndexOrThrow(MediaColumns.DATA);
           // column_index_folder_name = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_DISPLAY_NAME);
            while (cursor.moveToNext()) {
                absolutePathOfImage = cursor.getString(column_index_data);

                listOfAllImages.add(absolutePathOfImage);
            }
            return listOfAllImages;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1){

            imageview.setImageResource(Integer.parseInt(images.get(position)));
//            Uri uri = data.getData();
//            imageview.setImageURI(uri);
        }
    }
}