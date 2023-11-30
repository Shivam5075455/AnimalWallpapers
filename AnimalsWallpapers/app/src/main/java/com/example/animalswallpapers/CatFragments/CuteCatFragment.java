package com.example.animalswallpapers.CatFragments;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.animalswallpapers.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CuteCatFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CuteCatFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CuteCatFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CuteCatFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CuteCatFragment newInstance(String param1, String param2) {
        CuteCatFragment fragment = new CuteCatFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

     private ImageView imgCuteCat;
    private static final String url = "https://cataas.com/cat/cute";
//    private static final String url = "https://dog.ceo/api/breeds/image/random";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cute_cat, container, false);

        imgCuteCat = view.findViewById(R.id.imgCuteCat);

        imgCuteCat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FetchCatImageTask fetchCatImageTask = new FetchCatImageTask();
                fetchCatImageTask.execute();
            }
        });


        return view;
    }

    private class FetchCatImageTask extends AsyncTask<Void, Void, Bitmap>{
        @Override
        protected Bitmap doInBackground(Void... Voids){
            try{
                URL url1 = new URL(url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url1.openConnection();
                httpURLConnection.setDoInput(true);
                httpURLConnection.connect();
//                get Input Stream
                InputStream inputStream = httpURLConnection.getInputStream();
                return BitmapFactory.decodeStream(inputStream);

            }catch (IOException e){
                e.printStackTrace();
                return null;
            }
        }
        @Override
        protected void onPostExecute(Bitmap bitmapResult){

            if(bitmapResult != null){

                imgCuteCat.setImageBitmap(bitmapResult);
            }
        }
    } // end of FetchCatImageTask class
}