package com.james.mapping;
import android.content.Context;
import android.content.SharedPreferences;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.osmdroid.api.IGeoPoint;
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;

public class MainActivity extends AppCompatActivity {

    MapView mv;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);

        // This line sets the user agent, a requirement to download OSM maps
        setContentView(R.layout.activity_main);

        mv = (MapView)findViewById(R.id.map1);


        //mv.setVisibility(i);
        mv.getController().setZoom(16.0);
        mv.getController().setCenter(new GeoPoint(50.917615,-1.335753));
        mv.setTileSource(TileSourceFactory.HIKEBIKEMAP);
    }


    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId() == R.id.choosemap){
            Intent intent = new Intent(this, MapChooseActivity.class);
            startActivityForResult(intent, 0);
            return true;
        }
        if(item.getItemId() == R.id.setlocation){
            Intent intent = new Intent(this, LocationActivity.class);
            startActivityForResult(intent, 1);
            return true;
        }
        if(item.getItemId() == R.id.preferences){
            Intent intent = new Intent(this, PreferencesActivity.class);
            startActivityForResult(intent, 2);
            return true;
        }

        return false;
    }

    protected void onActivityResult(int requestCode,int resultCode,Intent intent)
    {

        if(requestCode==0)
        {

            if (resultCode==RESULT_OK)
            {
                Bundle extras=intent.getExtras();
                assert extras != null;
                boolean hikebikemap = extras.getBoolean("com.james.mapping.hikebikemap");
                if(hikebikemap)
                {
                    mv.setTileSource(TileSourceFactory.HIKEBIKEMAP);
                }
                else
                {
                    mv.setTileSource(TileSourceFactory.MAPNIK);
                }

            }
        }
        if(requestCode==1){
            if(resultCode == RESULT_OK){
                Bundle extras=intent.getExtras();
                if(extras!=null) {
                    double lon = extras.getDouble("com.james.mapping.longitude");
                    double lat = extras.getDouble("com.james.mapping.latitude");
                    mv.getController().setCenter(new GeoPoint(lon, lat));
                }
            }
        }
    }



    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        IGeoPoint var = mv.getMapCenter();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("lon", String.valueOf(var.getLongitude()));
        editor.putString("lat", String.valueOf(var.getLatitude()));
        editor.apply();
    }

    public void onResume(){
        super.onResume();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        double lat = Double.parseDouble ( prefs.getString("lat", "50.9") );
        double lon = Double.parseDouble ( prefs.getString("lon", "-1.4") );
        String viewType = prefs.getString("viewType", "MAPNIK");
        //mv.getController().setZoom(16.0);
        mv.getController().setCenter(new GeoPoint(lat,lon));
        Log.wtf("Return data", viewType);

        assert viewType != null;
        if(viewType.equals("HIKEBIKEMAP")){
            mv.setTileSource(TileSourceFactory.HIKEBIKEMAP);
        }else {
            mv.setTileSource(TileSourceFactory.MAPNIK);
        }
    }
}