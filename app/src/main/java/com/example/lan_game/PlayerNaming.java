package com.example.lan_game;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class PlayerNaming extends AppCompatActivity {
    EditText name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_naming);

        name = findViewById(R.id.playerName);
        final Button next = findViewById(R.id.confirmName);


        final Button settings = findViewById(R.id.settingsBtnPN);
        final Button sound = findViewById(R.id.soundBtnPN);
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToSettings();
            }
        });
        sound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });



        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToFindOpponent();
            }
        });
    }


    private void goToFindOpponent()
    {
        Intent intent = new Intent(this, FindOpponent.class);
        intent.putExtra("playerName", name.getText());
        startActivity(intent);
    }

    private void goToSettings()
    {
        Intent intent = new Intent(this, Settings.class);
        startActivity(intent);
    }
}