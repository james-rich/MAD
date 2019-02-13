package com.james.mapping;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MapChooseActivity extends AppCompatActivity  implements View.OnClickListener {
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_choose);

        Button regular = (Button)findViewById(R.id.btnRegular);
        regular.setOnClickListener(this);
        Button hikibikemap = (Button)findViewById(R.id.btnHikeBikeMap);
        hikibikemap.setOnClickListener(this);

    }

    public void onClick(View v){
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        boolean hikebikemap = false;
        if(v.getId() == R.id.btnHikeBikeMap) {
            hikebikemap = true;
        }
        bundle.putBoolean("com.james.mapping.hikebikemap", hikebikemap);
        intent.putExtras(bundle);
        setResult(RESULT_OK, intent);
        finish();
    }

}
