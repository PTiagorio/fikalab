package com.example.ethandroid;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

public class HelpActivity extends Activity {
    TextView tv;
    EditText et;
    ConnectEth con = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        et = findViewById(R.id.usernameET);
        Bundle b = getIntent().getExtras();
        //String wAddr = null; // or other values
        if(b != null)
            //wAddr = b.getString("wAddr");
            //con = (ConnectEth) b.getSerializable("connectEth");
            con = (ConnectEth) getIntent().getSerializableExtra("connectEth");
        else
            Log.d("log","Erro");
        if(con.getwAddr() != null) {
            tv = findViewById(R.id.texto2);
            tv.setText("Wallet Address: " + con.getwAddr());
        }
        if(con.getUsername() != null)
        {
            et.setText(con.getUsername());
        }

    }

    @Override
    public void onBackPressed() {
        if(con != null)
            con.setUsername(et.getText().toString());
        super.onBackPressed();
        finish();
    }

    @Override
    protected void onStop() {
        if(con != null)
            con.setUsername(et.getText().toString());
        super.onStop();
    }
}
