package com.example.ethandroid;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.Web3jService;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.http.HttpService;
import org.web3j.protocol.rx.Web3jRx;

import java.io.File;

public class ConnectEth {

    private Web3j web3;
    //private final String password = "walletPwd";
    private String password;
    private String    walletPath;
    private File walletDir;
    private Context context;
    //private String privateKey = "9928CE3F5D15DDBA026B855B4D1017E680777B0C17314F66B39E64D391427401";

    public ConnectEth(Context context){
        walletPath = context.getFilesDir().getAbsolutePath();
        walletDir = new File(walletPath);
        this.context = context;
    }
    public boolean connect(){

        web3 = Web3j.build(new HttpService("https://rinkeby.infura.io/v3/b321b2057ccc412da1a6cfc01c158880"));
        try {
            Web3ClientVersion clientVersion = web3.web3ClientVersion().sendAsync().get();
            if(!clientVersion.hasError()){
                //Connected

                Log.d("log","connect working");
                return true;
            }
            else {
                //Show Error
                Log.d("log","erro clientVersion: "+clientVersion.getError());
                return false;
            }


        }
        catch (Exception e) {
            //Show Error
            Log.d("log","erro connect: "+e);
            return false;
        }
    }

    public void wallet(String password){


        try{
            this.password=password;


            String x = WalletUtils.generateLightNewWalletFile(password,walletDir);

            //WalletUtils.generateNewWalletFile(password, walletDir);
            Log.d("log","well, "+walletDir+", result: "+x);
        }
        catch (Exception e){
            //Display an Error
            Log.d("log","erroWallet: "+e);
        }
    }

    public String getAddr(){
        try {
            Credentials credentials = WalletUtils.loadCredentials(password, walletDir);
            Log.d("log","addr well");
            return credentials.getAddress();
        }
        catch (Exception e){
            //Show Error
            Log.d("log","erroAddr: "+e);
            return "erroAddr: "+e;
        }
    }
}
