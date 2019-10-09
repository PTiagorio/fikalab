package com.example.ethandroid;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

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

import static org.web3j.tx.Transfer.GAS_LIMIT;
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
    private String contractAddr;
    private String filename;
    //private String privateKey = "9928CE3F5D15DDBA026B855B4D1017E680777B0C17314F66B39E64D391427401";

    public ConnectEth(Context context){
        walletPath = context.getFilesDir().getAbsolutePath();
        walletDir = new File(walletPath);
        this.context = context;
        filename = walletDir.listFiles()[0].getName();
    }
    public boolean connect(){

        web3 = Web3j.build(new HttpService("https://ropsten.infura.io/v3/b321b2057ccc412da1a6cfc01c158880"));
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
            String[] xx = walletDir.list();
            for(int i=0;i<xx.length;i++){
                Log.d("log","first time:"+xx[i]);
            }

            filename = WalletUtils.generateLightNewWalletFile(password,walletDir);

            for(int i=0;i<xx.length;i++){
                Log.d("log","after:"+xx[i]);
            }
            //WalletUtils.generateNewWalletFile(password   , walletDir);
            Log.d("log","well, "+walletDir+", result: "+filename);
        }
        catch (Exception e){
            //Display an Error
            Log.d("log","erroWallet: "+e);
        }
    }

    public String getAddr(String password){
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
    }

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
            smartContract = Contract_sol_test.deploy(web3, credentials, DefaultGasProvider.GAS_PRICE,DefaultGasProvider.GAS_LIMIT,array).send();
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

    public String loadContract(){
        try {

            smartContract = Contract_sol_test.load(contractAddr,web3,credentials,GAS_PRICE,GAS_LIMIT);
            contractAddr = smartContract.getContractAddress();
            return contractAddr;
        }catch(Exception e){

            return "Error";
        }
    }

    public void Action(String move,String username){
        try {
            TransactionReceipt transactionReceipt = smartContract.move(move.getBytes(), username.getBytes()).send();
        }catch (Exception e){
            return ;
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

    public Map<String,List<String>> getHistory(){
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
    }
}
