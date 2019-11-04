package com.example.ethandroid;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.webkit.MimeTypeMap;
import android.widget.Toast;

import org.bouncycastle.asn1.smime.SMIMEEncryptionKeyPreferenceAttribute;
import org.reactivestreams.Subscription;
import org.web3j.abi.datatypes.generated.Bytes32;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.Wallet;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.Web3jService;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.http.HttpService;
import org.web3j.protocol.rx.Web3jRx;
import org.web3j.tuples.generated.Tuple2;
import org.web3j.tx.Contract;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.tx.gas.DefaultGasProvider;

import java.io.File;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.web3j.tx.gas.DefaultGasProvider.GAS_LIMIT;
import static org.web3j.tx.gas.DefaultGasProvider.GAS_PRICE;

public class ConnectEth {

    private Web3j web3;
    //private final String password = "walletPwd";
    private String password;
    private String    walletPath;
    private File walletDir;
    private Context context;
    private Credentials credentials;
    private String wAddr;
    private Contract_sol_test smartContract;
    private String contractAddr = "0x2036cda1ef0fecf329a12aa668dae5031c6d8cdc";//"0x21eddf45C64aD7643D3adbF2042E917a60b82beB";
    private String filename;
    //private String privateKey = "9928CE3F5D15DDBA026B855B4D1017E680777B0C17314F66B39E64D391427401";

    public ConnectEth(Context context){
        //walletPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        this.context = context;

    }
    public boolean connect(){

        //web3 = Web3j.build(new HttpService()); //default http:localhost:8545
        web3 = Web3j.build(new HttpService("https://kovan.infura.io/v3/b321b2057ccc412da1a6cfc01c158880"));//kovan: kovan.infura.io/v3/b321b2057ccc412da1a6cfc01c158880
        //ropsten: ropsten.infura.io/v3/b321b2057ccc412da1a6cfc01c158880
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


    /*public void save(File f){
        ContentValues contentValues = new ContentValues();
        contentValues.put(MediaStore.Downloads.TITLE, f.getName());
        contentValues.put(MediaStore.Downloads.DISPLAY_NAME, f.getName());
        contentValues.put(MediaStore.Downloads.MIME_TYPE, MimeTypeMap.getFileExtensionFromUrl(f.getAbsolutePath()));
        contentValues.put(MediaStore.Downloads.SIZE, f.getTotalSpace());
        contentValues.put(MediaStore.Downloads.RELATIVE_PATH, Environment.DIRECTORY_DOWNLOADS + File.separator + "Fika");

        ContentResolver database = context.getContentResolver();
        database.insert(MediaStore.Downloads.EXTERNAL_CONTENT_URI, contentValues);
    }*/

    public void wallet(String password){

        File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        walletDir=path;
        try{
            File temp = new File(walletDir+"/wallet.json");
            if(!temp.exists()){
                filename = WalletUtils.generateLightNewWalletFile(password,path);
                File w = new File(walletDir+"/"+filename);
                w.renameTo(new File(walletDir+"/wallet.json"));
            }

            credentials = WalletUtils.loadCredentials(password,temp);
            wAddr=credentials.getAddress();
            Log.d("log","well2"+credentials.getAddress());
        }catch(Exception e){
            Log.d("log","erro: "+e);
        }
        /*try{
            this.password=password;
            File[] xx = walletDir.listFiles();

            if(xx.length>1){
                credentials = WalletUtils.loadCredentials(password,xx[0].getAbsoluteFile());
            }
            else {
                WalletUtils.generateLightNewWalletFile(password, walletDir);
                xx = walletDir.listFiles();
                credentials = WalletUtils.loadCredentials(password, xx[0].getAbsoluteFile());
            }
            filename = xx[0].getName();
            wAddr = credentials.getAddress();
            //WalletUtils.generateNewWalletFile(password   , walletDir);

        }
        catch (Exception e){
            //Display an Error
            Log.d("log","erroWallet: "+e);
        }
        */
    }

    /*public String getAddr(String password){
        try {
            credentials = WalletUtils.loadCredentials(password,walletDir+"/"+"UTC--2019-10-05T05-40-11.9Z--1dd2c69132c709f69b646ec446b06661d0bcddec.json");

            Log.d("log","addr well"+", "+credentials.getAddress());

            return credentials.getAddress();
        }
        catch (Exception e){
            //Show Error
            Log.d("log","erroAddr: "+e);
            return "erroAddr: "+e;
        }
    }*/

    public String getBalance(){
        BigInteger wei;
        try {
            EthGetBalance ethGetBalance = web3
                    .ethGetBalance(this.wAddr, DefaultBlockParameterName.LATEST)
                    .sendAsync()
                    .get();

            wei = ethGetBalance.getBalance();
        }catch(Exception e){
            return ""+e;
        }
        return ""+wei;
    }
    public String addFunds(){

        //web3.ethGetBalance().
        return "";
    }

    public String deployContract(){
        try {
            byte[] array= new byte[32];
            array[0] = 'C';
            smartContract = Contract_sol_test.deploy(web3, credentials, DefaultGasProvider.GAS_PRICE,DefaultGasProvider.GAS_LIMIT,"nome").send();
            contractAddr =smartContract.getContractAddress();
            Log.d("log","deploy bom");
            return contractAddr;
        }catch(Exception e){
            Log.d("log","deploy: "+e);
            return "Error";
        }
    }

    public String getContractAddr(){
        return contractAddr;
    }
    public String getwAddr(){
        return wAddr;
    }

    public String loadContract(){
        try {

            smartContract = Contract_sol_test.load(contractAddr,web3,credentials,GAS_PRICE,GAS_LIMIT);
            contractAddr = smartContract.getContractAddress();
            return contractAddr;
        }catch(Exception e){

            return "Error"+e;
        }
    }

    public String Action(String move,String username){
        try {
            Log.d("log","tried.");
            TransactionReceipt transactionReceipt = smartContract.move(move, username).send();
            Log.d("log",transactionReceipt.getStatus());
            return transactionReceipt.getStatus();
        }catch (Exception e){
            Log.d("log","erro: "+e);
            return "erro: "+e;
        }
    }

    public void listen(){
        try{
            smartContract.movedEventFlowable(DefaultBlockParameterName.EARLIEST,DefaultBlockParameterName.LATEST).subscribe(log ->{
                String name = new String(log.name);
                String dir = new String(log.dir);
                String sender = log.sender;
                String addr = log.log.getAddress();

                //executeScript(dir);
            });
        }catch(Exception e){

        }
    }

    /*public Map<String,List<String>> getHistory(){
        Map<String,List<String>> map = new HashMap<>();
        try {
            Tuple2<List<byte[]>, List<byte[]>> lista = smartContract.getHistory().send();
            List<String> values = new ArrayList<>();
            List<String> keys = new ArrayList<>();
            for(int i = 0;i<lista.getValue1().size();i++){
                String value = new String(lista.getValue1().get(i));
                String key = new String(lista.getValue2().get(i));
                if(map.containsKey(key)){
                    List<String> temp = map.get(key);
                    temp.add(value);
                    map.replace(key,temp);
                }
                else{
                    List<String> temp2 = new ArrayList<>();
                    temp2.add(value);
                    map.put(key,temp2);
                }
            }

        }catch (Exception e){
            return map;
        }
        return map;
    }*/
}
