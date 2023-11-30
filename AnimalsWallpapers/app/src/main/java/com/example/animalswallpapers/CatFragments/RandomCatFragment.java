package com.example.animalswallpapers.CatFragments;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.animalswallpapers.CatActivity;
import com.example.animalswallpapers.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;


public class RandomCatFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";
//
//     //TODO: Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;

    public RandomCatFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
     * @return A new instance of fragment RandomCatFragment.
     */
    // TODO: Rename and change types and number of parameters
//    public static RandomCatFragment newInstance(String param1, String param2) {
//        RandomCatFragment fragment = new RandomCatFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }

//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
//    }


//            CatActivity catActivity = new CatActivity();


    private ImageView imgRandomCat;
    private static final String CAT_API_URL = "https://cataas.com/cat";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_random_cat, container, false);

        imgRandomCat = view.findViewById(R.id.imgRandomCat);

        imgRandomCat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            FetchCatImageTask fetchCatImageTask = new FetchCatImageTask();
            fetchCatImageTask.execute();
            }
        });
        return view;
    }

    private class FetchCatImageTask extends AsyncTask<Void, Void, Bitmap> {

        @Override
        protected Bitmap doInBackground(Void... voids) {
            try {
                URL url = new URL(CAT_API_URL); //Create a URL object from CAT_API_URL
                HttpURLConnection connection = (HttpURLConnection) url.openConnection(); //Open a connection to the URL.
                connection.setDoInput(true); //Set input stream to true and connect to the URL.
                connection.connect(); // connection connected
                InputStream inputStream = connection.getInputStream(); //Get the input stream from the connection.
//                Use BitmapFactory.decodeStream to decode the input stream into a Bitmap
//                and Return the decoded Bitmap.
                return BitmapFactory.decodeStream(inputStream);

            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        /*
        The onPostExecute method is called after the background task is completed. It receives the result from the background task.
         In this case, it sets the fetched Bitmap to the ImageView (catImageView) if the result is not null.
        */
        @Override
        protected void onPostExecute(Bitmap result) {
            super.onPostExecute(result);
            if (result != null) {
                imgRandomCat.setImageBitmap(result);
            }
        }
    } // end of FetchCatImageTask


}
/*
This code effectively fetches an image from a URL using an AsyncTask and displays it in an ImageView within an Android activity.
Keep in mind that using libraries like Glide or Picasso can simplify the process of loading and displaying images, handling caching,
and optimizing memory usage.
 */