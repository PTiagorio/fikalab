package com.example.ethandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.net.Socket;
import java.security.Provider;
import java.security.Security;
import java.sql.Connection;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private ConnectEth con;
    private Button connect;
    private Button wallet;
    private Button addr;
    private Button deploy;
    private Button load;
    private Button up;
    private Button down;
    private Button left;
    private Button right;
    private Button history;
    private String name;

    private Map<String, List<String>> log;

    public MainActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        connect = findViewById(R.id.bConnect);
        connect.setText("Connect");
        wallet = findViewById(R.id.bWallet);
        wallet.setText("Create Wallet");
        addr = findViewById(R.id.bAddr);
        addr.setText("Get Wallet");
        deploy = findViewById(R.id.bDeploy);
        deploy.setText("deploy Contract");
        load = findViewById(R.id.bLoad);
        load.setText("Load Contract");


        try {
            setupBouncyCastle();

        }catch(Exception e){
            Log.d("log","erro main: "+e);
        }
    }

    public void onConnect(View view){
        try{
             con = new ConnectEth(this);
            if (con.connect()) {
                Toast.makeText(this, "Connected ", Toast.LENGTH_LONG).show();
            }
        }catch(Exception e){
            Toast.makeText(this,"erro connect, "+e,Toast.LENGTH_LONG).show();
        }
    }
    public void onWallet(View view){
        try{
            con.wallet("pwd");
            Toast.makeText(this, "wallet created ", Toast.LENGTH_LONG).show();
        }catch(Exception e){
            Toast.makeText(this,"erro connect, "+e,Toast.LENGTH_LONG).show();
        }

    }

    public void onAddr(View view){
        try{
            con.getAddr("pwd");
            Toast.makeText(this, "Your address is " + con.getAddr("pwd"), Toast.LENGTH_LONG).show();
        }catch(Exception e){
            Toast.makeText(this,"erro connect, "+e,Toast.LENGTH_LONG).show();
        }
    }

    public void onDeploy(View view){

        try{
            //con.deployContract();
            Background back = new Background(con);
            back.start();
            Toast.makeText(this, con.getContractAddr(), Toast.LENGTH_LONG).show();
        }catch(Exception e){
            Toast.makeText(this,"erro deploy, "+e,Toast.LENGTH_LONG).show();
        }
    }
    public void onLoad(View view){
        try{
            String t = con.loadContract();
            Toast.makeText(this, t, Toast.LENGTH_LONG).show();
        }catch(Exception e){
            Toast.makeText(this,"erro connect, "+e,Toast.LENGTH_LONG).show();
        }
    }
    public void onHistory(View view){
        Toast.makeText(this,""+con.getHistory(), Toast.LENGTH_LONG).show();
    }
    public void onUp(View view){
        con.Action("up",name);
    }

    public void onDown(View view){
        con.Action("down",name);
    }
    public void onRight(View view){
        con.Action("right",name);
    }
    public void onLetf(View view){
        con.Action("left",name);
    }


    public void getHistory(){
        log = con.getHistory();
    }


    private void setupBouncyCastle() {
        final Provider provider = Security.getProvider(BouncyCastleProvider.PROVIDER_NAME);
        if (provider == null) {
            // Web3j will set up the provider lazily when it's first used.
            return;
        }
        if (provider.getClass().equals(BouncyCastleProvider.class)) {
            // BC with same package name, shouldn't happen in real life.
            return;
        }
        // Android registers its own BC provider. As it might be outdated and might not include
        // all needed ciphers, we substitute it with a known BC bundled in the app.
        // Android's BC has its package rewritten to "com.android.org.bouncycastle" and because
        // of that it's possible to have another BC implementation loaded in VM.
        Security.removeProvider(BouncyCastleProvider.PROVIDER_NAME);
        Security.insertProviderAt(new BouncyCastleProvider(), 1);
    }
}

class Background extends Thread{

    private ConnectEth con;

    public Background(ConnectEth con){
        this.con = con;
    }
    @Override
    public void run(){
        con.deployContract();
    }
}
