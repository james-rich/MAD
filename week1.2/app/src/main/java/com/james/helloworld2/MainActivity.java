package com.james.helloworld2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button b = (Button) findViewById(R.id.btn1);
        b.setOnClickListener(this);
    }

    public void onClick(View view){
        TextView textView = (TextView) findViewById(R.id.tv1);
        EditText editText = (EditText) findViewById(R.id.et1);
        double feet = Double.parseDouble(editText.getText().toString());
        double meters = feet* 0.305;
        textView.setText(String.valueOf(meters));
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
