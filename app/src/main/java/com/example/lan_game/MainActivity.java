package com.example.lan_game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final Button start = findViewById(R.id.startBtn);
        final Button settings = findViewById(R.id.settingsBtn);
        final Button quit = findViewById(R.id.exitBtn);

        quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                System.exit(0);
            }
        });

        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToSettings();
            }
        });

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToPlayerNaming();
            }
        });
    }

    private void goToSettings()
    {
        Intent intent = new Intent(this, Settings.class);
        startActivity(intent);
    }

    private void goToPlayerNaming()
    {
        Intent intent = new Intent(this, PlayerNaming.class);
        startActivity(intent);
    }
}