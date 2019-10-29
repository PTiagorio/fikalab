package eth;

import org.web3j.abi.datatypes.generated.Bytes32;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.Wallet;
import org.web3j.crypto.WalletFile;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.http.HttpService;
import org.web3j.tuples.generated.Tuple2;
import org.web3j.tx.gas.DefaultGasProvider;
import org.web3j.utils.Convert;
import org.web3j.utils.Convert.Unit;

import java.io.File;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

import static org.web3j.tx.Transfer.GAS_LIMIT;
import static org.web3j.tx.gas.DefaultGasProvider.GAS_PRICE;


public class Main {
    private Web3j web3;
    //private final String password = "walletPwd";
    private String password;
    private String    walletPath;
    private File walletDir;
    private Credentials credentials;
    private String wAddr;
    private Contract_sol_test smartContract;
    private String contractAddr = "0x07467821C957EDeF9c80cEC1B31890ee7b705069"; //
    private String filename;

    public static void main(String[] args) {
        // TODO Auto-generated method stub

        Main m = new Main();

        System.out.println(m.connect());
        System.out.println(m.loadWallet());
        Thread t = new Thread(){
            @Override
            public void run(){
                m.listen();
            }
        };
        t.start();
        System.out.println(m.getBalance());
        //System.out.println(m.deployContract());
        System.out.println(m.loadContract());
        System.out.println(m.Action("cima","teste"));

        Map<String,List<String>> map = m.getHistory();
        Iterator<String> it = map.keySet().iterator();
        while(it.hasNext()){
            String user = it.next();
            List<String> l = map.get(user);
            System.out.println(user+": ");
            for(int i=0;i<l.size();i++){
                System.out.print(l.get(i)+"; ");
            }
        }

        //


        //System.out.println(m.deployContract());
        //m.exit();
    }

    public String executeScript(String dir){
        String s = "python /home/luis/Desktop/fika/Web3jApp/src/main/java/eth/" + dir + ".py";    //"scipt+cima; script+baixo; ..."
        try {

            //Runtime.getRuntime().exec(path);
            Process p = Runtime.getRuntime().exec(s);
            return "works";
        }catch(Exception e){
            return "erro"+e;
        }
    }

    //private String privateKey = "9928CE3F5D15DDBA026B855B4D1017E680777B0C17314F66B39E64D391427401";

    public void exit(){
        web3.shutdown();
    }

    public boolean connect(){

        web3 = Web3j.build(new HttpService()); //localhost:8545
        try {
            Web3ClientVersion clientVersion = web3.web3ClientVersion().sendAsync().get();
            if(!clientVersion.hasError()){
                //Connected

                System.out.println(web3.web3ClientVersion().getId());
                return true;
            }
            else {
                //Show Error

                return false;
            }


        }
        catch (Exception e) {
            //Show Error

            return false;
        }
    }


    public String loadWallet(){
        try {

            credentials = WalletUtils.loadCredentials("private","/home/luis/Desktop/fika/eth-net/db/keystore/UTC--2019-10-08T14-41-08.402000000Z--6c09f8473f6efa2016f46ace8e261c1331bc5f29.json");
            wAddr=credentials.getAddress();
            return credentials.getAddress();
        }
        catch (Exception e){
            //Show Error

            return "erroAddr: "+e;
        }
    }

    public int getBalance(){
        BigInteger wei;
        try {
            EthGetBalance ethGetBalance = web3
                    .ethGetBalance(this.wAddr, DefaultBlockParameterName.LATEST)
                    .sendAsync()
                    .get();

            wei = ethGetBalance.getBalance();
            return Convert.fromWei(wei.toString(),Unit.ETHER).intValue();

        }catch(Exception e){
            return -1;
        }
    }
    public String addFunds(){

        //web3.ethGetBalance().
        return "";
    }

    public String deployContract(){
        try {

            smartContract = Contract_sol_test.deploy(web3, credentials, DefaultGasProvider.GAS_PRICE,DefaultGasProvider.GAS_LIMIT,"nome").send();
            contractAddr =smartContract.getContractAddress();

            return contractAddr;
        }catch(Exception e){

            return "Error";
        }
    }

    public String getContractAddr(){
        return contractAddr;
    }

    public String loadContract(){
        try {

            smartContract = Contract_sol_test.load(contractAddr,web3,credentials, GAS_PRICE,DefaultGasProvider.GAS_LIMIT);
            contractAddr = smartContract.getContractAddress();
            return contractAddr;
        }catch(Exception e){

            return "Error";
        }
    }

    public static Bytes32 stringToBytes32(String string) {
        byte[] byteValue = string.getBytes();
        byte[] byteValueLen32 = new byte[32];
        System.arraycopy(byteValue, 0, byteValueLen32, 0, byteValue.length);
        return new Bytes32(byteValueLen32);
    }

    public String Action(String move,String username){
        try {
            //Bytes32 m = stringToBytes32(move);
            //Bytes32 u = stringToBytes32(username);
            TransactionReceipt transactionReceipt = smartContract.move(move, username).send();
            return transactionReceipt.getTransactionHash();
        }catch (Exception e){
            return "erro "+e;
        }
    }

    public void listen(){
        try{
            smartContract.movedEventFlowable(DefaultBlockParameterName.EARLIEST,DefaultBlockParameterName.LATEST).subscribe(log ->{
                String name = new String(log.name);
                String dir = new String(log.dir);
                String sender = log.sender;
                String addr = log.log.getAddress();
                System.out.println(name+", "+dir+", "+sender+", "+addr);
                 executeScript(dir);
            });
        }catch(Exception e){
            System.out.println();
        }
    }

    public Map<String,List<String>> getHistory(){
        Map<String,List<String>> map = new HashMap<>();
        try {

            Tuple2<List<String>, List<String>> lista = smartContract.getHistory().send();
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