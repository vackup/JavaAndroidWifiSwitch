package com.example.hernanzaldivar.wifiswitchjava;

import android.content.Context;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static String INTERNETWIFI = "guest";
    public static String DRONEWIFI = "Bebop";
    public static int INTERNETWIFI_ID = 0;
    public static int DRONEWIFI_ID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WifiManager wifiManager = (WifiManager)getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        List<WifiConfiguration> wifis = wifiManager.getConfiguredNetworks();

        for (WifiConfiguration wifiCfg : wifis){
            if (wifiCfg.SSID.startsWith("\"" + this.INTERNETWIFI)){
                if (wifiCfg.networkId>this.INTERNETWIFI_ID)
                    this.INTERNETWIFI_ID=wifiCfg.networkId;
            }
            if (wifiCfg.SSID.startsWith("\"" + this.DRONEWIFI)){
                if (wifiCfg.networkId>this.DRONEWIFI_ID)
                    this.DRONEWIFI_ID=wifiCfg.networkId;
            }
        }

        this.connectToDroneWifi(wifiManager);

        this.connectToInternetWifi(wifiManager);
    }

    private void connectToInternetWifi(WifiManager wifiManager) {
        wifiManager.disconnect();
        if (this.INTERNETWIFI_ID==0){
            wifiManager.enableNetwork(23, true);
        }else{
            wifiManager.enableNetwork(this.INTERNETWIFI_ID, true);
        }

        wifiManager.reconnect();
    }

    private void connectToDroneWifi(WifiManager wifiManager) {
        wifiManager.disconnect();

        if (this.DRONEWIFI_ID==0){
            wifiManager.enableNetwork(26, true);
        }else {
            wifiManager.enableNetwork(this.DRONEWIFI_ID, true);
        }
        wifiManager.reconnect();
    }
}
