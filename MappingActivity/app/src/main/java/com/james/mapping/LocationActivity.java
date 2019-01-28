package com.james.mapping;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;

public class LocationActivity extends AppCompatActivity implements View.OnClickListener {
    MapView mv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        Button button = (Button)findViewById(R.id.goButton);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        EditText et1 = (EditText)findViewById(R.id.latitude);
        EditText et2 = (EditText)findViewById(R.id.longitude);
        double lon = 0;
        double lat = 0;

        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        if(v.getId() == R.id.goButton && !et1.getText().toString().equals("") && !et2.getText().toString().equals("")) {
            lon = Double.parseDouble(et1.getText().toString());
            lat = Double.parseDouble(et2.getText().toString());
        }
        bundle.putDouble("com.james.mapping.longitude", lon);
        bundle.putDouble("com.james.mapping.latitude", lat);
        intent.putExtras(bundle);
        setResult(RESULT_OK, intent);
        finish();
    }
}
