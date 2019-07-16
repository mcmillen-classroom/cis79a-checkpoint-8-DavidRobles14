package com.davidrobles.myplacesapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{

    private static final int REQUEST_PLACE_CREATE = 1;
    private ListView mListView;
    private ArrayAdapter<Place> mArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListView = (ListView) findViewById(R.id.list_view);

        ArrayList<Place> myPlaces = new ArrayList<Place>();
        myPlaces.add(new Place("Laney College", "Near Lake Merritt"));
        myPlaces.add(new Place("Berkeley College", "Near UC Berkeley"));
        myPlaces.add(new Place("College of Alameda", "On Alameda Island"));
        myPlaces.add(new Place("Merritt College", "Near Skyline"));

        mArrayAdapter = new PlaceArrayAdapter(this, myPlaces);

        mListView.setAdapter(mArrayAdapter);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        if(item.getItemId() == R.id.main_menu_create)
        {
            launchCreateActivity();
            return true;
        }

        return false;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK && requestCode == REQUEST_PLACE_CREATE)
        {
            if(data != null)
            {
                String name = data.getStringExtra("place_name");
                String description = data.getStringExtra("place_description");

                Place added = new Place(name, description);
                mArrayAdapter.add(added);
            }
        }
    }

    private void launchCreateActivity()
    {
        Intent createIntent =  new Intent(this, PlaceCreateActivity.class);
        startActivityForResult(createIntent, REQUEST_PLACE_CREATE);
    }
}
