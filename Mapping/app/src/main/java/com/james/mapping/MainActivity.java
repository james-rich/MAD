package com.james.mapping;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    MapView mv;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);

        // This line sets the user agent, a requirement to download OSM maps
        Configuration.getInstance().load(this, PreferenceManager.getDefaultSharedPreferences(this));

        setContentView(R.layout.activity_main);

        mv = (MapView)findViewById(R.id.map1);

        mv.setBuiltInZoomControls(true);
        mv.getController().setZoom(19.0);
        mv.getController().setCenter(new GeoPoint(50.917615,-1.335753));
        mv.setTileSource(TileSourceFactory.MAPNIK);

        Button button = (Button)findViewById(R.id.goButton);
        button.setOnClickListener(this);
    }

    public void onClick(View view){
        EditText et1 = (EditText)findViewById(R.id.latitude);
        EditText et2 = (EditText)findViewById(R.id.longitude);
        if(!et1.getText().toString().equals("") && !et2.getText().toString().equals("")) {
            double lon = Double.parseDouble(et1.getText().toString());
            double lat = Double.parseDouble(et2.getText().toString());
            mv.getController().setCenter(new GeoPoint(lon, lat));
        }else{
            mv.getController().setCenter(new GeoPoint(50.9, -1.3));

        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}