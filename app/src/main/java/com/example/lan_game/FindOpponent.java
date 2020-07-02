package com.example.lan_game;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.nsd.NsdManager;
import android.net.nsd.NsdServiceInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.net.ServerSocket;

public class FindOpponent extends AppCompatActivity {

    String serviceName = "";
    ServerSocket serverSocket;
    int localPort;
    NsdManager.RegistrationListener registrationListener;
    NsdManager nsdManager;
    TextView errorMessanger;

    String playerName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_opponent);
        playerName = getIntent().getStringExtra("playerName");


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

        final Button quit = findViewById(R.id.exitFindOpponent);
        quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        errorMessanger = findViewById(R.id.logger);

        /*initializeRegistrationListener();
        initializeServerSocket();
        registerService(serverSocket.getLocalPort());*/




    }

    public void registerService(int port)
    {
        NsdServiceInfo serviceInfo = new NsdServiceInfo();

        serviceInfo.setServiceName(playerName);
        serviceInfo.setServiceType("_tictactoe._tcp");
        serviceInfo.setPort(port);

        nsdManager = (NsdManager) getApplicationContext().getSystemService(Context.NSD_SERVICE);
        nsdManager.registerService(serviceInfo, NsdManager.PROTOCOL_DNS_SD, registrationListener);

    }

    public void initializeServerSocket() {
        try {
            serverSocket = new ServerSocket(0);
        }
        catch (IOException e)
        {
            addErrorMessage(this, "Server Socket initialisation failed" + e.getMessage());
        }

        localPort = serverSocket.getLocalPort();
    }


    public void initializeRegistrationListener() {
            registrationListener = new NsdManager.RegistrationListener() {

            @Override
            public void onServiceRegistered(NsdServiceInfo NsdServiceInfo)
            {
                serviceName = NsdServiceInfo.getServiceName();
            }

            @Override
            public void onRegistrationFailed(NsdServiceInfo serviceInfo, int errorCode)
            {
                addErrorMessage(this, "Registration Failed ErrorCode"+Integer.toString(errorCode));
            }

            @Override
            public void onServiceUnregistered(NsdServiceInfo arg0)
            {

            }

            @Override
            public void onUnregistrationFailed(NsdServiceInfo serviceInfo, int errorCode)
            {
                addErrorMessage(this, "Unregistration Failed ErrorCode"+Integer.toString(errorCode));
            }
        };
    }

    public void addErrorMessage(Object fromWhere, String errorMessage)
    {
        errorMessanger.setText(errorMessanger.getText() + "\n" + "From:" + fromWhere.getClass().toString() + "  msg:" + errorMessage);
    }


    private void goToSettings()
    {
        Intent intent = new Intent(this, Settings.class);
        startActivity(intent);
    }


}