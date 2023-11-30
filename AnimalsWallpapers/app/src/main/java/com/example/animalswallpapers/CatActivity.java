package com.example.animalswallpapers;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import android.widget.ImageView;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.animalswallpapers.CatFragments.CuteCatFragment;
import com.example.animalswallpapers.CatFragments.RandomCatFragment;
import com.example.animalswallpapers.DogFragments.RandomDogFragment;
import com.example.animalswallpapers.databinding.ActivityCatBinding;
import com.google.android.material.navigation.NavigationView;


public class CatActivity extends AppCompatActivity {

    ActionBarDrawerToggle actionBarDrawerToggle;
    ActivityCatBinding binding;
    NavigationView navigationViewCat;
    Toolbar toolbarCat;
    public DrawerLayout drawerLayoutCat;

        private ImageView imgRandomCat;
        private static final String CAT_API_URL = "https://cataas.com/cat";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCatBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

//        find views by Id
        toolbarCat = findViewById(R.id.toolbarCat);
        drawerLayoutCat = findViewById(R.id.drawerlayoutCat);
        navigationViewCat = findViewById(R.id.navigationCat);

        setSupportActionBar(toolbarCat);

//        set navigation drawer
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayoutCat, toolbarCat, R.string.nav_open, R.string.nav_close);
        drawerLayoutCat.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

       nav();

    } //end of onCreate fxn


//        String url = "https://cataas.com/cat";

//    navigation method
    public void nav(){

        binding.navigationCat.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int id = item.getItemId();

                if(id == R.id.menuRandomCat){
                    replaceFragment(new RandomCatFragment());
                }else if(id == R.id.menuCuteCat){
                    replaceFragment(new CuteCatFragment());
                }else if(id == R.id.menuRandomDog){
                    replaceFragment(new RandomDogFragment());
                }

                drawerLayoutCat.closeDrawers();
                return true;
            }
        });
    }


/*
    private class FetchCatImageTask extends AsyncTask<Void, Void, Bitmap> {

        @Override
        protected Bitmap doInBackground(Void... voids) {
            try {
                URL url = new URL(CAT_API_URL);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.connect();
                InputStream inputStream = connection.getInputStream();
                return BitmapFactory.decodeStream(inputStream);

            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            super.onPostExecute(result);

            if (result != null) {
                imgRandomCat.setImageBitmap(result);
            }
        }

    } // end of FetchCatImageTask
*/

//        replace fragment method
    public void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.framelayout, fragment);
        fragmentTransaction.commit();

    }

} // end of CatActivity class