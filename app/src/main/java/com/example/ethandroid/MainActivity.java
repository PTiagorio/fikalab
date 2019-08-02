package com.example.ethandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.security.Provider;
import java.security.Security;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            setupBouncyCastle();
            ConnectEth con = new ConnectEth(this);
            if (con.connect()) {
                Toast.makeText(this, "Connected ", Toast.LENGTH_LONG).show();
            }
            Thread.sleep(Toast.LENGTH_LONG);
            con.wallet("pwd");
            Toast.makeText(this, "wallet created ", Toast.LENGTH_LONG).show();
            //con.getAddr();
            Toast.makeText(this, "Your address is " + con.getAddr(), Toast.LENGTH_LONG).show();
        }catch(Exception e){
            Log.d("log","erro main: "+e);
        }
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
