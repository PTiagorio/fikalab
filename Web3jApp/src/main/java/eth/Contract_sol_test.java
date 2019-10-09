package eth;

import io.reactivex.Flowable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.DynamicArray;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Bytes32;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple2;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 4.1.1.
 */
public class Contract_sol_test extends Contract {
    private static final String BINARY = "608060405234801561001057600080fd5b5060405161053c38038061053c8339818101604052602081101561003357600080fd5b5051600080546001600160a01b031916331790556001556104e3806100596000396000f3fe608060405234801561001057600080fd5b50600436106100575760003560e01c80632ae868661461005c578063a361b18414610076578063aa15efc81461009b578063f61b2d491461013c578063fd9527c814610162575b600080fd5b61006461016a565b60408051918252519081900360200190f35b6100996004803603604081101561008c57600080fd5b50803590602001356101ba565b005b6100a36102e2565b604051808060200180602001838103835285818151815260200191508051906020019060200280838360005b838110156100e75781810151838201526020016100cf565b50505050905001838103825284818151815260200191508051906020019060200280838360005b8381101561012657818101518382015260200161010e565b5050505090500194505050505060405180910390f35b6100646004803603602081101561015257600080fd5b50356001600160a01b0316610476565b610064610491565b600080805b610177610491565b8110156101b4576101a86003828154811061018e57fe5b6000918252602090912001546001600160a01b0316610476565b9091019060010161016f565b50905090565b6101c2610497565b5060408051808201909152828152602081018290526000805b60035481101561021b57600381815481106101f257fe5b6000918252602090912001546001600160a01b031633141561021357600191505b6001016101db565b508061026457600380546001810182556000919091527fc2575a0e9e593c00f959f8c92f12db2869c3395a3b0502d05e2516446f71f85b0180546001600160a01b031916331790555b3360008181526002602081815260408084208054600181810183559186529483902088519590940290930193845586820151939092019290925580518681529182018790528181019290925290517fd2e5b3898972690f05228b95dc2bfeb9f8b76fb696391bb3e3c846ca8463d21e9181900360600190a150505050565b60608060006102ef61016a565b90506000809050606082604051908082528060200260200182016040528015610322578160200160208202803883390190505b509050606083604051908082528060200260200182016040528015610351578160200160208202803883390190505b50905060005b61035f610491565b81101561046a5760005b6103796003838154811061018e57fe5b81101561046157600260006003848154811061039157fe5b60009182526020808320909101546001600160a01b0316835282019290925260400190208054829081106103c157fe5b9060005260206000209060020201600001548486815181106103df57fe5b60200260200101818152505060026000600384815481106103fc57fe5b60009182526020808320909101546001600160a01b03168352820192909252604001902080548290811061042c57fe5b90600052602060002090600202016001015483868151811061044a57fe5b602090810291909101015260019485019401610369565b50600101610357565b50909450925050509091565b6001600160a01b031660009081526002602052604090205490565b60035490565b60408051808201909152600080825260208201529056fea265627a7a72305820e73a3f08a0a5fa95f958f2b1c0f4c2db8d101a11eec7ca92d193818b8b3a2c5e64736f6c634300050a0032";

    public static final String FUNC_GETSIZE = "getsize";

    public static final String FUNC_MOVE = "move";

    public static final String FUNC_GETHISTORY = "getHistory";

    public static final String FUNC_GETCOUNTSTRUCT = "getCountStruct";

    public static final String FUNC_GETCOUNTADDR = "getCountAddr";

    public static final Event MOVED_EVENT = new Event("Moved", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}, new TypeReference<Bytes32>() {}, new TypeReference<Address>() {}));
    ;

    @Deprecated
    protected Contract_sol_test(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected Contract_sol_test(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected Contract_sol_test(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected Contract_sol_test(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteCall<BigInteger> getsize() {
        final Function function = new Function(FUNC_GETSIZE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<TransactionReceipt> move(Bytes32 direction, Bytes32 name) {
        final Function function = new Function(
                FUNC_MOVE, 
                Arrays.<Type>asList(direction,
                name),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<Tuple2<List<byte[]>, List<byte[]>>> getHistory() {
        final Function function = new Function(FUNC_GETHISTORY, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Bytes32>>() {}, new TypeReference<DynamicArray<Bytes32>>() {}));
        return new RemoteCall<Tuple2<List<byte[]>, List<byte[]>>>(
                new Callable<Tuple2<List<byte[]>, List<byte[]>>>() {
                    @Override
                    public Tuple2<List<byte[]>, List<byte[]>> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple2<List<byte[]>, List<byte[]>>(
                                convertToNative((List<Bytes32>) results.get(0).getValue()), 
                                convertToNative((List<Bytes32>) results.get(1).getValue()));
                    }
                });
    }

    public RemoteCall<BigInteger> getCountStruct(String addr) {
        final Function function = new Function(FUNC_GETCOUNTSTRUCT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(addr)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<BigInteger> getCountAddr() {
        final Function function = new Function(FUNC_GETCOUNTADDR, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public List<MovedEventResponse> getMovedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(MOVED_EVENT, transactionReceipt);
        ArrayList<MovedEventResponse> responses = new ArrayList<MovedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            MovedEventResponse typedResponse = new MovedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.dir = (byte[]) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.name = (byte[]) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.sender = (String) eventValues.getNonIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<MovedEventResponse> movedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new io.reactivex.functions.Function<Log, MovedEventResponse>() {
            @Override
            public MovedEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(MOVED_EVENT, log);
                MovedEventResponse typedResponse = new MovedEventResponse();
                typedResponse.log = log;
                typedResponse.dir = (byte[]) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.name = (byte[]) eventValues.getNonIndexedValues().get(1).getValue();
                typedResponse.sender = (String) eventValues.getNonIndexedValues().get(2).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<MovedEventResponse> movedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(MOVED_EVENT));
        return movedEventFlowable(filter);
    }

    @Deprecated
    public static Contract_sol_test load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Contract_sol_test(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static Contract_sol_test load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Contract_sol_test(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static Contract_sol_test load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new Contract_sol_test(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static Contract_sol_test load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new Contract_sol_test(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<Contract_sol_test> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider, byte[] cName) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(cName)));
        return deployRemoteCall(Contract_sol_test.class, web3j, credentials, contractGasProvider, BINARY, encodedConstructor);
    }

    public static RemoteCall<Contract_sol_test> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider, byte[] cName) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(cName)));
        return deployRemoteCall(Contract_sol_test.class, web3j, transactionManager, contractGasProvider, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<Contract_sol_test> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, byte[] cName) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(cName)));
        return deployRemoteCall(Contract_sol_test.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<Contract_sol_test> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, byte[] cName) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(cName)));
        return deployRemoteCall(Contract_sol_test.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    public static class MovedEventResponse {
        public Log log;

        public byte[] dir;

        public byte[] name;

        public String sender;
    }
}
