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

public class MainActivity extends AppCompatActivity {

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

        //mv.setBuiltInZoomControls(true);
        mv.getController().setZoom(19.0);
        mv.getController().setCenter(new GeoPoint(50.917615,-1.335753));
        mv.setTileSource(TileSourceFactory.MAPNIK);
    }

   /* public void onClick(View view){
        EditText et1 = (EditText)findViewById(R.id.latitude);
        EditText et2 = (EditText)findViewById(R.id.longitude);
        double lon = Double.parseDouble(et1.getText().toString());
        double lat = Double.parseDouble(et2.getText().toString());
        mv.getController().setCenter(new GeoPoint(lon, lat));
    }*/

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
}