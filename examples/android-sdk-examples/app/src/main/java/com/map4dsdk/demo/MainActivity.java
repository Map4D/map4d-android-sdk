package com.map4dsdk.demo;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setOnListener();
        getSupportActionBar().setTitle(R.string.explorer);
    }

    private void setOnListener() {
        findViewById(R.id.btnImageView2D).setOnClickListener(this);
        findViewById(R.id.btnImageView3D).setOnClickListener(this);
        findViewById(R.id.btnCollections).setOnClickListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnImageView2D: {
                Intent map2dIntent = new Intent(this, Simple2DMapActivity.class);
                startActivity(map2dIntent);
                break;
            }
            case R.id.btnImageView3D: {
                Intent map3dIntent = new Intent(this, Simple3DMapActivity.class);
                startActivity(map3dIntent);
                break;
            }
            case R.id.btnCollections: {
                Intent functionsIntent = new Intent(this, CollectionsActivity.class);
                startActivity(functionsIntent);
                break;
            }
        }

    }
}
