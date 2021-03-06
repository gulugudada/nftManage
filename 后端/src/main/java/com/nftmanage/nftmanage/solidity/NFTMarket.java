package com.nftmanage.nftmanage.solidity;

import io.reactivex.Flowable;
import io.reactivex.functions.Function;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.BaseEventResponse;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
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
 * <p>Generated with web3j version 5.0.0.
 */
@SuppressWarnings("rawtypes")
public class NFTMarket extends Contract {
    public static final String BINARY = init();


    public static final String FUNC_APPROVE = "approve";

    public static final String FUNC_BALANCEOF = "balanceOf";

    public static final String FUNC_BURNNFT = "burnNFT";

    public static final String FUNC_DESTROY = "destroy";

    public static final String FUNC_GETAPPROVED = "getApproved";

    public static final String FUNC_ISAPPROVEDFORALL = "isApprovedForAll";

    public static final String FUNC_MINTNFT = "mintNFT";

    public static final String FUNC_NAME = "name";

    public static final String FUNC_OWNEROF = "ownerOf";

    public static final String FUNC_safeTransferFrom = "safeTransferFrom";

    public static final String FUNC_SETAPPROVALFORALL = "setApprovalForAll";

    public static final String FUNC_SETBASEURI = "setBaseURI";

    public static final String FUNC_SUPPORTSINTERFACE = "supportsInterface";

    public static final String FUNC_SYMBOL = "symbol";

    public static final String FUNC_TOKENBYINDEX = "tokenByIndex";

    public static final String FUNC_TOKENOFOWNERBYINDEX = "tokenOfOwnerByIndex";

    public static final String FUNC_TOKENURI = "tokenURI";

    public static final String FUNC_TOTALSUPPLY = "totalSupply";

    public static final String FUNC_TRANSNFT = "transNFT";

    public static final String FUNC_TRANSFERFROM = "transferFrom";

    public static final Event APPROVAL_EVENT = new Event("Approval", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Uint256>(true) {}));
    ;

    public static final Event APPROVALFORALL_EVENT = new Event("ApprovalForAll", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Bool>() {}));
    ;

    public static final Event TRANSFER_EVENT = new Event("Transfer", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Uint256>(true) {}));
    ;

    @Deprecated
    protected NFTMarket(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected NFTMarket(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected NFTMarket(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected NFTMarket(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public List<ApprovalEventResponse> getApprovalEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(APPROVAL_EVENT, transactionReceipt);
        ArrayList<ApprovalEventResponse> responses = new ArrayList<ApprovalEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            ApprovalEventResponse typedResponse = new ApprovalEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.owner = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.approved = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.tokenId = (BigInteger) eventValues.getIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<ApprovalEventResponse> approvalEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, ApprovalEventResponse>() {
            @Override
            public ApprovalEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(APPROVAL_EVENT, log);
                ApprovalEventResponse typedResponse = new ApprovalEventResponse();
                typedResponse.log = log;
                typedResponse.owner = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.approved = (String) eventValues.getIndexedValues().get(1).getValue();
                typedResponse.tokenId = (BigInteger) eventValues.getIndexedValues().get(2).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<ApprovalEventResponse> approvalEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(APPROVAL_EVENT));
        return approvalEventFlowable(filter);
    }

    public List<ApprovalForAllEventResponse> getApprovalForAllEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(APPROVALFORALL_EVENT, transactionReceipt);
        ArrayList<ApprovalForAllEventResponse> responses = new ArrayList<ApprovalForAllEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            ApprovalForAllEventResponse typedResponse = new ApprovalForAllEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.owner = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.operator = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.approved = (Boolean) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<ApprovalForAllEventResponse> approvalForAllEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, ApprovalForAllEventResponse>() {
            @Override
            public ApprovalForAllEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(APPROVALFORALL_EVENT, log);
                ApprovalForAllEventResponse typedResponse = new ApprovalForAllEventResponse();
                typedResponse.log = log;
                typedResponse.owner = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.operator = (String) eventValues.getIndexedValues().get(1).getValue();
                typedResponse.approved = (Boolean) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<ApprovalForAllEventResponse> approvalForAllEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(APPROVALFORALL_EVENT));
        return approvalForAllEventFlowable(filter);
    }

    public List<TransferEventResponse> getTransferEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(TRANSFER_EVENT, transactionReceipt);
        ArrayList<TransferEventResponse> responses = new ArrayList<TransferEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            TransferEventResponse typedResponse = new TransferEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.from = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.to = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.tokenId = (BigInteger) eventValues.getIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<TransferEventResponse> transferEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, TransferEventResponse>() {
            @Override
            public TransferEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(TRANSFER_EVENT, log);
                TransferEventResponse typedResponse = new TransferEventResponse();
                typedResponse.log = log;
                typedResponse.from = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.to = (String) eventValues.getIndexedValues().get(1).getValue();
                typedResponse.tokenId = (BigInteger) eventValues.getIndexedValues().get(2).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<TransferEventResponse> transferEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(TRANSFER_EVENT));
        return transferEventFlowable(filter);
    }

    public RemoteFunctionCall<TransactionReceipt> approve(String to, BigInteger tokenId) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_APPROVE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, to), 
                new org.web3j.abi.datatypes.generated.Uint256(tokenId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<BigInteger> balanceOf(String owner) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_BALANCEOF, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, owner)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> burnNFT(BigInteger tokenId) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_BURNNFT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(tokenId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> destroy() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_DESTROY, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<String> getApproved(BigInteger tokenId) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETAPPROVED, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(tokenId)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<Boolean> isApprovedForAll(String owner, String operator) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_ISAPPROVEDFORALL, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, owner), 
                new org.web3j.abi.datatypes.Address(160, operator)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<TransactionReceipt> mintNFT(String _to, String tokenURI_) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_MINTNFT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _to), 
                new org.web3j.abi.datatypes.Utf8String(tokenURI_)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<String> name() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_NAME, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<String> ownerOf(BigInteger tokenId) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_OWNEROF, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(tokenId)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<TransactionReceipt> safeTransferFrom(String from, String to, BigInteger tokenId) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_safeTransferFrom, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, from), 
                new org.web3j.abi.datatypes.Address(160, to), 
                new org.web3j.abi.datatypes.generated.Uint256(tokenId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> safeTransferFrom(String from, String to, BigInteger tokenId, byte[] _data) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_safeTransferFrom, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, from), 
                new org.web3j.abi.datatypes.Address(160, to), 
                new org.web3j.abi.datatypes.generated.Uint256(tokenId), 
                new org.web3j.abi.datatypes.DynamicBytes(_data)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> setApprovalForAll(String operator, Boolean approved) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SETAPPROVALFORALL, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, operator), 
                new org.web3j.abi.datatypes.Bool(approved)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> setBaseURI(String baseURI_) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SETBASEURI, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(baseURI_)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<Boolean> supportsInterface(byte[] interfaceId) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_SUPPORTSINTERFACE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes4(interfaceId)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<String> symbol() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_SYMBOL, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<BigInteger> tokenByIndex(BigInteger index) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_TOKENBYINDEX, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(index)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> tokenOfOwnerByIndex(String owner, BigInteger index) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_TOKENOFOWNERBYINDEX, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, owner), 
                new org.web3j.abi.datatypes.generated.Uint256(index)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<String> tokenURI(BigInteger tokenId) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_TOKENURI, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(tokenId)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<BigInteger> totalSupply() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_TOTALSUPPLY, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> transNFT(String _from, String _to, BigInteger tokenId) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_TRANSNFT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _from), 
                new org.web3j.abi.datatypes.Address(160, _to), 
                new org.web3j.abi.datatypes.generated.Uint256(tokenId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> transferFrom(String from, String to, BigInteger tokenId) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_TRANSFERFROM, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, from), 
                new org.web3j.abi.datatypes.Address(160, to), 
                new org.web3j.abi.datatypes.generated.Uint256(tokenId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static NFTMarket load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new NFTMarket(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static NFTMarket load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new NFTMarket(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static NFTMarket load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new NFTMarket(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static NFTMarket load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new NFTMarket(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<NFTMarket> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(NFTMarket.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    public static RemoteCall<NFTMarket> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(NFTMarket.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<NFTMarket> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(NFTMarket.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<NFTMarket> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(NFTMarket.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public static class ApprovalEventResponse extends BaseEventResponse {
        public String owner;

        public String approved;

        public BigInteger tokenId;
    }

    public static class ApprovalForAllEventResponse extends BaseEventResponse {
        public String owner;

        public String operator;

        public Boolean approved;
    }

    public static class TransferEventResponse extends BaseEventResponse {
        public String from;

        public String to;

        public BigInteger tokenId;
    }

    public static String init(){
        StringBuilder a = new StringBuilder();
        String b = "{\r\n"
                + "\t\"functionDebugData\": {\r\n"
                + "\t\t\"@_167\": {\r\n"
                + "\t\t\t\"entryPoint\": null,\r\n"
                + "\t\t\t\"id\": 167,\r\n"
                + "\t\t\t\"parameterSlots\": 2,\r\n"
                + "\t\t\t\"returnSlots\": 0\r\n"
                + "\t\t},\r\n"
                + "\t\t\"@_2302\": {\r\n"
                + "\t\t\t\"entryPoint\": null,\r\n"
                + "\t\t\t\"id\": 2302,\r\n"
                + "\t\t\t\"parameterSlots\": 0,\r\n"
                + "\t\t\t\"returnSlots\": 0\r\n"
                + "\t\t},\r\n"
                + "\t\t\"extract_byte_array_length\": {\r\n"
                + "\t\t\t\"entryPoint\": 312,\r\n"
                + "\t\t\t\"id\": null,\r\n"
                + "\t\t\t\"parameterSlots\": 1,\r\n"
                + "\t\t\t\"returnSlots\": 1\r\n"
                + "\t\t}\r\n"
                + "\t},\r\n"
                + "\t\"generatedSources\": [\r\n"
                + "\t\t{\r\n"
                + "\t\t\t\"ast\": {\r\n"
                + "\t\t\t\t\"nodeType\": \"YulBlock\",\r\n"
                + "\t\t\t\t\"src\": \"0:396:15\",\r\n"
                + "\t\t\t\t\"statements\": [\r\n"
                + "\t\t\t\t\t{\r\n"
                + "\t\t\t\t\t\t\"nodeType\": \"YulBlock\",\r\n"
                + "\t\t\t\t\t\t\"src\": \"6:3:15\",\r\n"
                + "\t\t\t\t\t\t\"statements\": []\r\n"
                + "\t\t\t\t\t},\r\n"
                + "\t\t\t\t\t{\r\n"
                + "\t\t\t\t\t\t\"body\": {\r\n"
                + "\t\t\t\t\t\t\t\"nodeType\": \"YulBlock\",\r\n"
                + "\t\t\t\t\t\t\t\"src\": \"69:325:15\",\r\n"
                + "\t\t\t\t\t\t\t\"statements\": [\r\n"
                + "\t\t\t\t\t\t\t\t{\r\n"
                + "\t\t\t\t\t\t\t\t\t\"nodeType\": \"YulAssignment\",\r\n"
                + "\t\t\t\t\t\t\t\t\t\"src\": \"79:22:15\",\r\n"
                + "\t\t\t\t\t\t\t\t\t\"value\": {\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\"arguments\": [\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t{\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\"kind\": \"number\",\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\"nodeType\": \"YulLiteral\",\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\"src\": \"93:1:15\",\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\"type\": \"\",\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\"value\": \"1\"\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t},\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t{\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\"name\": \"data\",\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\"nodeType\": \"YulIdentifier\",\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\"src\": \"96:4:15\"\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t}\r\n"
                + "\t\t\t\t\t\t\t\t\t\t],\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\"functionName\": {\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\"name\": \"shr\",\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\"nodeType\": \"YulIdentifier\",\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\"src\": \"89:3:15\"\r\n"
                + "\t\t\t\t\t\t\t\t\t\t},\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\"nodeType\": \"YulFunctionCall\",\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\"src\": \"89:12:15\"\r\n"
                + "\t\t\t\t\t\t\t\t\t},\r\n"
                + "\t\t\t\t\t\t\t\t\t\"variableNames\": [\r\n"
                + "\t\t\t\t\t\t\t\t\t\t{\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\"name\": \"length\",\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\"nodeType\": \"YulIdentifier\",\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\"src\": \"79:6:15\"\r\n"
                + "\t\t\t\t\t\t\t\t\t\t}\r\n"
                + "\t\t\t\t\t\t\t\t\t]\r\n"
                + "\t\t\t\t\t\t\t\t},\r\n"
                + "\t\t\t\t\t\t\t\t{\r\n"
                + "\t\t\t\t\t\t\t\t\t\"nodeType\": \"YulVariableDeclaration\",\r\n"
                + "\t\t\t\t\t\t\t\t\t\"src\": \"110:38:15\",\r\n"
                + "\t\t\t\t\t\t\t\t\t\"value\": {\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\"arguments\": [\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t{\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\"name\": \"data\",\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\"nodeType\": \"YulIdentifier\",\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\"src\": \"140:4:15\"\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t},\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t{\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\"kind\": \"number\",\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\"nodeType\": \"YulLiteral\",\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\"src\": \"146:1:15\",\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\"type\": \"\",\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\"value\": \"1\"\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t}\r\n"
                + "\t\t\t\t\t\t\t\t\t\t],\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\"functionName\": {\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\"name\": \"and\",\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\"nodeType\": \"YulIdentifier\",\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\"src\": \"136:3:15\"\r\n"
                + "\t\t\t\t\t\t\t\t\t\t},\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\"nodeType\": \"YulFunctionCall\",\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\"src\": \"136:12:15\"\r\n"
                + "\t\t\t\t\t\t\t\t\t},\r\n"
                + "\t\t\t\t\t\t\t\t\t\"variables\": [\r\n"
                + "\t\t\t\t\t\t\t\t\t\t{\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\"name\": \"outOfPlaceEncoding\",\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\"nodeType\": \"YulTypedName\",\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\"src\": \"114:18:15\",\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\"type\": \"\"\r\n"
                + "\t\t\t\t\t\t\t\t\t\t}\r\n"
                + "\t\t\t\t\t\t\t\t\t]\r\n"
                + "\t\t\t\t\t\t\t\t},\r\n"
                + "\t\t\t\t\t\t\t\t{\r\n"
                + "\t\t\t\t\t\t\t\t\t\"body\": {\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\"nodeType\": \"YulBlock\",\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\"src\": \"187:31:15\",\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\"statements\": [\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t{\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\"nodeType\": \"YulAssignment\",\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\"src\": \"189:27:15\",\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\"value\": {\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t\"arguments\": [\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t{\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\"name\": \"length\",\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\"nodeType\": \"YulIdentifier\",\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\"src\": \"203:6:15\"\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t},\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t{\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\"kind\": \"number\",\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\"nodeType\": \"YulLiteral\",\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\"src\": \"211:4:15\",\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\"type\": \"\",\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\"value\": \"0x7f\"\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t}\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t],\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t\"functionName\": {\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\"name\": \"and\",\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\"nodeType\": \"YulIdentifier\",\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\"src\": \"199:3:15\"\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t},\r\n";
        String c = "\t\t\t\t\t\t\t\t\t\t\t\t\t\"nodeType\": \"YulFunctionCall\",\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t\"src\": \"199:17:15\"\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t},\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\"variableNames\": [\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t{\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\"name\": \"length\",\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\"nodeType\": \"YulIdentifier\",\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\"src\": \"189:6:15\"\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t}\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t]\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t}\r\n"
                + "\t\t\t\t\t\t\t\t\t\t]\r\n"
                + "\t\t\t\t\t\t\t\t\t},\r\n"
                + "\t\t\t\t\t\t\t\t\t\"condition\": {\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\"arguments\": [\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t{\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\"name\": \"outOfPlaceEncoding\",\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\"nodeType\": \"YulIdentifier\",\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\"src\": \"167:18:15\"\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t}\r\n"
                + "\t\t\t\t\t\t\t\t\t\t],\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\"functionName\": {\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\"name\": \"iszero\",\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\"nodeType\": \"YulIdentifier\",\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\"src\": \"160:6:15\"\r\n"
                + "\t\t\t\t\t\t\t\t\t\t},\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\"nodeType\": \"YulFunctionCall\",\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\"src\": \"160:26:15\"\r\n"
                + "\t\t\t\t\t\t\t\t\t},\r\n"
                + "\t\t\t\t\t\t\t\t\t\"nodeType\": \"YulIf\",\r\n"
                + "\t\t\t\t\t\t\t\t\t\"src\": \"157:61:15\"\r\n"
                + "\t\t\t\t\t\t\t\t},\r\n"
                + "\t\t\t\t\t\t\t\t{\r\n"
                + "\t\t\t\t\t\t\t\t\t\"body\": {\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\"nodeType\": \"YulBlock\",\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\"src\": \"277:111:15\",\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\"statements\": [\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t{\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\"expression\": {\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t\"arguments\": [\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t{\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\"kind\": \"number\",\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\"nodeType\": \"YulLiteral\",\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\"src\": \"298:1:15\",\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\"type\": \"\",\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\"value\": \"0\"\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t},\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t{\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\"arguments\": [\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t{\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\"kind\": \"number\",\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\"nodeType\": \"YulLiteral\",\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\"src\": \"305:3:15\",\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\"type\": \"\",\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\"value\": \"224\"\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t},\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t{\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\"kind\": \"number\",\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\"nodeType\": \"YulLiteral\",\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\"src\": \"310:10:15\",\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\"type\": \"\",\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\"value\": \"0x4e487b71\"\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t}\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t],\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\"functionName\": {\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\"name\": \"shl\",\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\"nodeType\": \"YulIdentifier\",\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\"src\": \"301:3:15\"\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t},\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\"nodeType\": \"YulFunctionCall\",\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\"src\": \"301:20:15\"\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t}\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t],\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t\"functionName\": {\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\"name\": \"mstore\",\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\"nodeType\": \"YulIdentifier\",\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\"src\": \"291:6:15\"\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t},\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t\"nodeType\": \"YulFunctionCall\",\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t\"src\": \"291:31:15\"\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t},\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\"nodeType\": \"YulExpressionStatement\",\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\"src\": \"291:31:15\"\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t},\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t{\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\"expression\": {\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t\"arguments\": [\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t{\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\"kind\": \"number\",\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\"nodeType\": \"YulLiteral\",\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\"src\": \"342:1:15\",\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\"type\": \"\",\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\"value\": \"4\"\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t},\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t{\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\"kind\": \"number\",\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\"nodeType\": \"YulLiteral\",\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\"src\": \"345:4:15\",\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\"type\": \"\",\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\"value\": \"0x22\"\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t}\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t],\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t\"functionName\": {\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\"name\": \"mstore\",\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\"nodeType\": \"YulIdentifier\",\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\"src\": \"335:6:15\"\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t},\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t\"nodeType\": \"YulFunctionCall\",\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t\"src\": \"335:15:15\"\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t},\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\"nodeType\": \"YulExpressionStatement\",\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\"src\": \"335:15:15\"\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t},\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t{\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\"expression\": {\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t\"arguments\": [\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t{\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\"kind\": \"number\",\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\"nodeType\": \"YulLiteral\",\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\"src\": \"370:1:15\",\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\"type\": \"\",\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\"value\": \"0\"\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t},\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t{\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\"kind\": \"number\",\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\"nodeType\": \"YulLiteral\",\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\"src\": \"373:4:15\",\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\"type\": \"\",\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\"value\": \"0x24\"\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t}\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t],\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t\"functionName\": {\r\n";
        String d = "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\"name\": \"revert\",\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\"nodeType\": \"YulIdentifier\",\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\"src\": \"363:6:15\"\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t},\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t\"nodeType\": \"YulFunctionCall\",\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t\"src\": \"363:15:15\"\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t},\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\"nodeType\": \"YulExpressionStatement\",\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\"src\": \"363:15:15\"\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t}\r\n"
                + "\t\t\t\t\t\t\t\t\t\t]\r\n"
                + "\t\t\t\t\t\t\t\t\t},\r\n"
                + "\t\t\t\t\t\t\t\t\t\"condition\": {\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\"arguments\": [\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t{\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\"name\": \"outOfPlaceEncoding\",\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\"nodeType\": \"YulIdentifier\",\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\"src\": \"233:18:15\"\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t},\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t{\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\"arguments\": [\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t{\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\"name\": \"length\",\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\"nodeType\": \"YulIdentifier\",\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\"src\": \"256:6:15\"\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t},\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t{\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\"kind\": \"number\",\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\"nodeType\": \"YulLiteral\",\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\"src\": \"264:2:15\",\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\"type\": \"\",\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\"value\": \"32\"\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t}\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t],\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\"functionName\": {\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t\"name\": \"lt\",\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t\"nodeType\": \"YulIdentifier\",\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t\"src\": \"253:2:15\"\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t},\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\"nodeType\": \"YulFunctionCall\",\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\"src\": \"253:14:15\"\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t}\r\n"
                + "\t\t\t\t\t\t\t\t\t\t],\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\"functionName\": {\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\"name\": \"eq\",\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\"nodeType\": \"YulIdentifier\",\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\"src\": \"230:2:15\"\r\n"
                + "\t\t\t\t\t\t\t\t\t\t},\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\"nodeType\": \"YulFunctionCall\",\r\n"
                + "\t\t\t\t\t\t\t\t\t\t\"src\": \"230:38:15\"\r\n"
                + "\t\t\t\t\t\t\t\t\t},\r\n"
                + "\t\t\t\t\t\t\t\t\t\"nodeType\": \"YulIf\",\r\n"
                + "\t\t\t\t\t\t\t\t\t\"src\": \"227:161:15\"\r\n"
                + "\t\t\t\t\t\t\t\t}\r\n"
                + "\t\t\t\t\t\t\t]\r\n"
                + "\t\t\t\t\t\t},\r\n"
                + "\t\t\t\t\t\t\"name\": \"extract_byte_array_length\",\r\n"
                + "\t\t\t\t\t\t\"nodeType\": \"YulFunctionDefinition\",\r\n"
                + "\t\t\t\t\t\t\"parameters\": [\r\n"
                + "\t\t\t\t\t\t\t{\r\n"
                + "\t\t\t\t\t\t\t\t\"name\": \"data\",\r\n"
                + "\t\t\t\t\t\t\t\t\"nodeType\": \"YulTypedName\",\r\n"
                + "\t\t\t\t\t\t\t\t\"src\": \"49:4:15\",\r\n"
                + "\t\t\t\t\t\t\t\t\"type\": \"\"\r\n"
                + "\t\t\t\t\t\t\t}\r\n"
                + "\t\t\t\t\t\t],\r\n"
                + "\t\t\t\t\t\t\"returnVariables\": [\r\n"
                + "\t\t\t\t\t\t\t{\r\n"
                + "\t\t\t\t\t\t\t\t\"name\": \"length\",\r\n"
                + "\t\t\t\t\t\t\t\t\"nodeType\": \"YulTypedName\",\r\n"
                + "\t\t\t\t\t\t\t\t\"src\": \"58:6:15\",\r\n"
                + "\t\t\t\t\t\t\t\t\"type\": \"\"\r\n"
                + "\t\t\t\t\t\t\t}\r\n"
                + "\t\t\t\t\t\t],\r\n"
                + "\t\t\t\t\t\t\"src\": \"14:380:15\"\r\n"
                + "\t\t\t\t\t}\r\n"
                + "\t\t\t\t]\r\n"
                + "\t\t\t},\r\n"
                + "\t\t\t\"contents\": \"{\\n    { }\\n    function extract_byte_array_length(data) -> length\\n    {\\n        length := shr(1, data)\\n        let outOfPlaceEncoding := and(data, 1)\\n        if iszero(outOfPlaceEncoding) { length := and(length, 0x7f) }\\n        if eq(outOfPlaceEncoding, lt(length, 32))\\n        {\\n            mstore(0, shl(224, 0x4e487b71))\\n            mstore(4, 0x22)\\n            revert(0, 0x24)\\n        }\\n    }\\n}\",\r\n"
                + "\t\t\t\"id\": 15,\r\n"
                + "\t\t\t\"language\": \"Yul\",\r\n"
                + "\t\t\t\"name\": \"#utility.yul\"\r\n"
                + "\t\t}\r\n"
                + "\t],\r\n";
        String e = "\t\"linkReferences\": {},\r\n";
        String f = "\t\"object\": \"60806040523480156200001157600080fd5b50604080518082018252600981526813919513585c9ad95d60ba1b6020808301918252835180850190945260068452651391951352d560d21b908401528151919291620000619160009162000092565b5080516200007790600190602084019062000092565b5050600c80546001600160a01b031916331790555062000174565b828054620000a09062000138565b90600052602060002090601f016020900481019282620000c457600085556200010f565b82601f10620000df57805160ff19168380011785556200010f565b828001600101855582156200010f579182015b828111156200010f578251825591602001919060010190620000f2565b506200011d92915062000121565b5090565b5b808211156200011d576000815560010162000122565b600181811c908216806200014d57607f821691505b6020821081036200016e57634e487b7160e01b600052602260045260246000fd5b50919050565b611df480620001846000396000f3fe608060405234801561001057600080fd5b50600436106101375760003560e01c80634f6ccce7116100b857806395d89b411161007c57806395d89b411461027e578063a22cb46514610286578063b88d4fde14610299578063c87b56dd146102ac578063e985e9c5146102bf578063eacabe14146102fb57600080fd5b80634f6ccce71461022a57806355f804b31461023d5780636352211e1461025057806370a082311461026357806383197ef01461027657600080fd5b806323b872dd116100ff57806323b872dd146101cb5780632890e0d7146101de5780632a6ffd2c146101f15780632f745c591461020457806342842e0e1461021757600080fd5b806301ffc9a71461013c57806306fdde0314610164578063081812fc14610179578063095ea7b3146101a457806318160ddd146101b9575b600080fd5b61014f61014a366004611838565b61030e565b60405190151581526020015b60405180910390f35b61016c61031f565b60405161015b91906118ad565b61018c6101873660046118c0565b6103b1565b6040516001600160a01b03909116815260200161015b565b6101b76101b23660046118f5565b61044b565b005b6008545b60405190815260200161015b565b6101b76101d936600461191f565b610560565b6101bd6101ec3660046118c0565b610591565b6101bd6101ff36600461191f565b61061d565b6101bd6102123660046118f5565b6106b2565b6101b761022536600461191f565b610748565b6101bd6102383660046118c0565b610763565b6101b761024b366004611a07565b6107f6565b61018c61025e3660046118c0565b61080d565b6101bd610271366004611a3c565b610884565b6101b761090b565b61016c61098d565b6101b7610294366004611a57565b61099c565b6101b76102a7366004611a93565b6109a7565b61016c6102ba3660046118c0565b6109df565b61014f6102cd366004611b0f565b6001600160a01b03918216600090815260056020908152604080832093909416825291909152205460ff1690565b6101bd610309366004611b42565b610b60565b600061031982610b98565b92915050565b60606000805461032e90611b90565b80601f016020809104026020016040519081016040528092919081815260200182805461035a90611b90565b80156103a75780601f1061037c576101008083540402835291602001916103a7565b820191906000526020600020905b81548152906001019060200180831161038a57829003601f168201915b5050505050905090565b6000818152600260205260408120546001600160a01b031661042f5760405162461bcd60e51b815260206004820152602c60248201527f4552433732313a20617070726f76656420717565727920666f72206e6f6e657860448201526b34b9ba32b73a103a37b5b2b760a11b60648201526084015b60405180910390fd5b506000908152600460205260409020546001600160a01b031690565b60006104568261080d565b9050806001600160a01b0316836001600160a01b0316036104c35760405162461bcd60e51b815260206004820152602160248201527f4552433732313a20617070726f76616c20746f2063757272656e74206f776e656044820152603960f91b6064820152608401610426565b336001600160a01b03821614806104df57506104df81336102cd565b6105515760405162461bcd60e51b815260206004820152603860248201527f4552433732313a20617070726f76652063616c6c6572206973206e6f74206f7760448201527f6e6572206e6f7220617070726f76656420666f7220616c6c00000000000000006064820152608401610426565b61055b8383610bbd565b505050565b61056a3382610c2b565b6105865760405162461bcd60e51b815260040161042690611bca565b61055b838383610d22565b600061059c8261080d565b6001600160a01b0316336001600160a01b0316146106105760405162461bcd60e51b815260206004820152602b60248201527f4f6e6c7920746865206f776e6572206f66207468697320546f6b656e20636f7560448201526a6c64204275726e2049742160a81b6064820152608401610426565b61061982610ec9565b5090565b60006106288261080d565b6001600160a01b0316336001600160a01b0316146106a05760405162461bcd60e51b815260206004820152602f60248201527f4f6e6c7920746865206f776e6572206f66207468697320546f6b656e20636f7560448201526e6c64207472616e736665722049742160881b6064820152608401610426565b6106ab848484610560565b5092915050565b60006106bd83610884565b821061071f5760405162461bcd60e51b815260206004820152602b60248201527f455243373231456e756d657261626c653a206f776e657220696e646578206f7560448201526a74206f6620626f756e647360a81b6064820152608401610426565b506001600160a01b03919091166000908152600660209081526040808320938352929052205490565b61055b838383604051806020016040528060008152506109a7565b600061076e60085490565b82106107d15760405162461bcd60e51b815260206004820152602c60248201527f455243373231456e756d657261626c653a20676c6f62616c20696e646578206f60448201526b7574206f6620626f756e647360a01b6064820152608401610426565b600882815481106107e4576107e4611c1b565b90600052602060002001549050919050565b805161080990600e906020840190611757565b5050565b6000818152600260205260408120546001600160a01b0316806103195760405162461bcd60e51b815260206004820152602960248201527f4552433732313a206f776e657220717565727920666f72206e6f6e657869737460448201526832b73a103a37b5b2b760b91b6064820152608401610426565b60006001600160a01b0382166108ef5760405162461bcd60e51b815260206004820152602a60248201527f4552433732313a2062616c616e636520717565727920666f7220746865207a65604482015269726f206164647265737360b01b6064820152608401610426565b506001600160a01b031660009081526003602052604090205490565b600c546001600160a01b0316331461097f5760405162461bcd60e51b815260206004820152603160248201527f4f6e6c7920746865206f776e6572206f66207468697320436f6e747261637420604482015270636f756c642064657374726f792049742160781b6064820152608401610426565b600c546001600160a01b0316ff5b60606001805461032e90611b90565b610809338383610ed5565b6109b13383610c2b565b6109cd5760405162461bcd60e51b815260040161042690611bca565b6109d984848484610fa3565b50505050565b6000818152600260205260409020546060906001600160a01b0316610a5e5760405162461bcd60e51b815260206004820152602f60248201527f4552433732314d657461646174613a2055524920717565727920666f72206e6f60448201526e3732bc34b9ba32b73a103a37b5b2b760891b6064820152608401610426565b6000828152600d602052604081208054610a7790611b90565b80601f0160208091040260200160405190810160405280929190818152602001828054610aa390611b90565b8015610af05780601f10610ac557610100808354040283529160200191610af0565b820191906000526020600020905b815481529060010190602001808311610ad357829003601f168201915b505050505090506000610b01610fd6565b90508051600003610b13575092915050565b815115610b45578082604051602001610b2d929190611c31565b60405160208183030381529060405292505050919050565b80610b4f85610fe5565b604051602001610b2d929190611c31565b6000610b70600b80546001019055565b6000610b7b600b5490565b9050610b8784826110e6565b610b918184611234565b9392505050565b60006001600160e01b0319821663780e9d6360e01b14806103195750610319826112cc565b600081815260046020526040902080546001600160a01b0319166001600160a01b0384169081179091558190610bf28261080d565b6001600160a01b03167f8c5be1e5ebec7d5bd14f71427d1e84f3dd0314c0f7b2291e5b200ac8c7c3b92560405160405180910390a45050565b6000818152600260205260408120546001600160a01b0316610ca45760405162461bcd60e51b815260206004820152602c60248201527f4552433732313a206f70657261746f7220717565727920666f72206e6f6e657860448201526b34b9ba32b73a103a37b5b2b760a11b6064820152608401610426565b6000610caf8361080d565b9050806001600160a01b0316846001600160a01b03161480610cea5750836001600160a01b0316610cdf846103b1565b6001600160a01b0316145b80610d1a57506001600160a01b0380821660009081526005602090815260408083209388168352929052205460ff165b949350505050565b826001600160a01b0316610d358261080d565b6001600160a01b031614610d995760405162461bcd60e51b815260206004820152602560248201527f4552433732313a207472616e736665722066726f6d20696e636f72726563742060448201526437bbb732b960d91b6064820152608401610426565b6001600160a01b038216610dfb5760405162461bcd60e51b8152602060048201526024808201527f4552433732313a207472616e7366657220746f20746865207a65726f206164646044820152637265737360e01b6064820152608401610426565b610e0683838361131c565b610e11600082610bbd565b6001600160a01b0383166000908152600360205260408120805460019290610e3a908490611c76565b90915550506001600160a01b0382166000908152600360205260408120805460019290610e68908490611c8d565b909155505060008181526002602052604080822080546001600160a01b0319166001600160a01b0386811691821790925591518493918716917fddf252ad1be2c89b69c2b068fc378daa952ba7f163c4a11628f55a4df523b3ef91a4505050565b610ed281611327565b50565b816001600160a01b0316836001600160a01b031603610f365760405162461bcd60e51b815260206004820152601960248201527f4552433732313a20617070726f766520746f2063616c6c6572000000000000006044820152606401610426565b6001600160a01b03838116600081815260056020908152604080832094871680845294825291829020805460ff191686151590811790915591519182527f17307eab39ab6107e8899845ad3d59bd9653f200f220920489ca2b5937696c31910160405180910390a3505050565b610fae848484610d22565b610fba84848484611367565b6109d95760405162461bcd60e51b815260040161042690611ca5565b6060600e805461032e90611b90565b60608160000361100c5750506040805180820190915260018152600360fc1b602082015290565b8160005b8115611036578061102081611cf7565b915061102f9050600a83611d26565b9150611010565b60008167ffffffffffffffff8111156110515761105161195b565b6040519080825280601f01601f19166020018201604052801561107b576020820181803683370190505b5090505b8415610d1a57611090600183611c76565b915061109d600a86611d3a565b6110a8906030611c8d565b60f81b8183815181106110bd576110bd611c1b565b60200101906001600160f81b031916908160001a9053506110df600a86611d26565b945061107f565b6001600160a01b03821661113c5760405162461bcd60e51b815260206004820181905260248201527f4552433732313a206d696e7420746f20746865207a65726f20616464726573736044820152606401610426565b6000818152600260205260409020546001600160a01b0316156111a15760405162461bcd60e51b815260206004820152601c60248201527f4552433732313a20746f6b656e20616c7265616479206d696e746564000000006044820152606401610426565b6111ad6000838361131c565b6001600160a01b03821660009081526003602052604081208054600192906111d6908490611c8d565b909155505060008181526002602052604080822080546001600160a01b0319166001600160a01b03861690811790915590518392907fddf252ad1be2c89b69c2b068fc378daa952ba7f163c4a11628f55a4df523b3ef908290a45050565b6000828152600260205260409020546001600160a01b03166112ad5760405162461bcd60e51b815260206004820152602c60248201527f4552433732314d657461646174613a2055524920736574206f66206e6f6e657860448201526b34b9ba32b73a103a37b5b2b760a11b6064820152608401610426565b6000828152600d60209081526040909120825161055b92840190611757565b60006001600160e01b031982166380ac58cd60e01b14806112fd57506001600160e01b03198216635b5e139f60e01b145b8061031957506301ffc9a760e01b6001600160e01b0319831614610319565b61055b838383611468565b61133081611520565b6000818152600a60205260409020805461134990611b90565b159050610ed2576000818152600a60205260408120610ed2916117d7565b60006001600160a01b0384163b1561145d57604051630a85bd0160e11b81526001600160a01b0385169063150b7a02906113ab903390899088908890600401611d4e565b6020604051808303816000875af19250505080156113e6575060408051601f3d908101601f191682019092526113e391810190611d8b565b60015b611443573d808015611414576040519150601f19603f3d011682016040523d82523d6000602084013e611419565b606091505b50805160000361143b5760405162461bcd60e51b815260040161042690611ca5565b805181602001fd5b6001600160e01b031916630a85bd0160e11b149050610d1a565b506001949350505050565b6001600160a01b0383166114c3576114be81600880546000838152600960205260408120829055600182018355919091527ff3f7a9fe364faab93b216da50a3214154f22a0a2b415b23a84c8169e8b636ee30155565b6114e6565b816001600160a01b0316836001600160a01b0316146114e6576114e683826115c7565b6001600160a01b0382166114fd5761055b81611664565b826001600160a01b0316826001600160a01b03161461055b5761055b8282611713565b600061152b8261080d565b90506115398160008461131c565b611544600083610bbd565b6001600160a01b038116600090815260036020526040812080546001929061156d908490611c76565b909155505060008281526002602052604080822080546001600160a01b0319169055518391906001600160a01b038416907fddf252ad1be2c89b69c2b068fc378daa952ba7f163c4a11628f55a4df523b3ef908390a45050565b600060016115d484610884565b6115de9190611c76565b600083815260076020526040902054909150808214611631576001600160a01b03841660009081526006602090815260408083208584528252808320548484528184208190558352600790915290208190555b5060009182526007602090815260408084208490556001600160a01b039094168352600681528383209183525290812055565b60085460009061167690600190611c76565b6000838152600960205260408120546008805493945090928490811061169e5761169e611c1b565b9060005260206000200154905080600883815481106116bf576116bf611c1b565b60009182526020808320909101929092558281526009909152604080822084905585825281205560088054806116f7576116f7611da8565b6001900381819060005260206000200160009055905550505050565b600061171e83610884565b6001600160a01b039093166000908152600660209081526040808320868452825280832085905593825260079052919091209190915550565b82805461176390611b90565b90600052602060002090601f01602090048101928261178557600085556117cb565b82601f1061179e57805160ff19168380011785556117cb565b828001600101855582156117cb579182015b828111156117cb5782518255916020019190600101906117b0565b5061061992915061180d565b5080546117e390611b90565b6000825580601f106117f3575050565b601f016020900490600052602060002090810190610ed291905b5b80821115610619576000815560010161180e565b6001600160e01b031981168114610ed257600080fd5b60006020828403121561184a57600080fd5b8135610b9181611822565b60005b83811015611870578181015183820152602001611858565b838111156109d95750506000910152565b60008151808452611899816020860160208601611855565b601f01601f19169290920160200192915050565b602081526000610b916020830184611881565b6000602082840312156118d257600080fd5b5035919050565b80356001600160a01b03811681146118f057600080fd5b919050565b6000806040838503121561190857600080fd5b611911836118d9565b946020939093013593505050565b60008060006060848603121561193457600080fd5b61193d846118d9565b925061194b602085016118d9565b9150604084013590509250925092565b634e487b7160e01b600052604160045260246000fd5b600067ffffffffffffffff8084111561198c5761198c61195b565b604051601f8501601f19908116603f011681019082821181831017156119b4576119b461195b565b816040528093508581528686860111156119cd57600080fd5b858560208301376000602087830101525050509392505050565b600082601f8301126119f857600080fd5b610b9183833560208501611971565b600060208284031215611a1957600080fd5b813567ffffffffffffffff811115611a3057600080fd5b610d1a848285016119e7565b600060208284031215611a4e57600080fd5b610b91826118d9565b60008060408385031215611a6a57600080fd5b611a73836118d9565b915060208301358015158114611a8857600080fd5b809150509250929050565b60008060008060808587031215611aa957600080fd5b611ab2856118d9565b9350611ac0602086016118d9565b925060408501359150606085013567ffffffffffffffff811115611ae357600080fd5b8501601f81018713611af457600080fd5b611b0387823560208401611971565b91505092959194509250565b60008060408385031215611b2257600080fd5b611b2b836118d9565b9150611b39602084016118d9565b90509250929050565b60008060408385031215611b5557600080fd5b611b5e836118d9565b9150602083013567ffffffffffffffff811115611b7a57600080fd5b611b86858286016119e7565b9150509250929050565b600181811c90821680611ba457607f821691505b602082108103611bc457634e487b7160e01b600052602260045260246000fd5b50919050565b60208082526031908201527f4552433732313a207472616e736665722063616c6c6572206973206e6f74206f6040820152701ddb995c881b9bdc88185c1c1c9bdd9959607a1b606082015260800190565b634e487b7160e01b600052603260045260246000fd5b60008351611c43818460208801611855565b835190830190611c57818360208801611855565b01949350505050565b634e487b7160e01b600052601160045260246000fd5b600082821015611c8857611c88611c60565b500390565b60008219821115611ca057611ca0611c60565b500190565b60208082526032908201527f4552433732313a207472616e7366657220746f206e6f6e20455243373231526560408201527131b2b4bb32b91034b6b83632b6b2b73a32b960711b606082015260800190565b600060018201611d0957611d09611c60565b5060010190565b634e487b7160e01b600052601260045260246000fd5b600082611d3557611d35611d10565b500490565b600082611d4957611d49611d10565b500690565b6001600160a01b0385811682528416602082015260408101839052608060608201819052600090611d8190830184611881565b9695505050505050565b600060208284031215611d9d57600080fd5b8151610b9181611822565b634e487b7160e01b600052603160045260246000fdfea26469706673582212207f54b0100997e0c8d025fd834ba3f100fda116be8954bec8ba6d34bfba61ee0964736f6c634300080d0033\",\r\n";
        String g = "\t\"opcodes\": \"PUSH1 0x80 PUSH1 0x40 MSTORE CALLVALUE DUP1 ISZERO PUSH3 0x11 JUMPI PUSH1 0x0 DUP1 REVERT JUMPDEST POP PUSH1 0x40 DUP1 MLOAD DUP1 DUP3 ADD DUP3 MSTORE PUSH1 0x9 DUP2 MSTORE PUSH9 0x13919513585C9AD95D PUSH1 0xBA SHL PUSH1 0x20 DUP1 DUP4 ADD SWAP2 DUP3 MSTORE DUP4 MLOAD DUP1 DUP6 ADD SWAP1 SWAP5 MSTORE PUSH1 0x6 DUP5 MSTORE PUSH6 0x1391951352D5 PUSH1 0xD2 SHL SWAP1 DUP5 ADD MSTORE DUP2 MLOAD SWAP2 SWAP3 SWAP2 PUSH3 0x61 SWAP2 PUSH1 0x0 SWAP2 PUSH3 0x92 JUMP JUMPDEST POP DUP1 MLOAD PUSH3 0x77 SWAP1 PUSH1 0x1 SWAP1 PUSH1 0x20 DUP5 ADD SWAP1 PUSH3 0x92 JUMP JUMPDEST POP POP PUSH1 0xC DUP1 SLOAD PUSH1 0x1 PUSH1 0x1 PUSH1 0xA0 SHL SUB NOT AND CALLER OR SWAP1 SSTORE POP PUSH3 0x174 JUMP JUMPDEST DUP3 DUP1 SLOAD PUSH3 0xA0 SWAP1 PUSH3 0x138 JUMP JUMPDEST SWAP1 PUSH1 0x0 MSTORE PUSH1 0x20 PUSH1 0x0 KECCAK256 SWAP1 PUSH1 0x1F ADD PUSH1 0x20 SWAP1 DIV DUP2 ADD SWAP3 DUP3 PUSH3 0xC4 JUMPI PUSH1 0x0 DUP6 SSTORE PUSH3 0x10F JUMP JUMPDEST DUP3 PUSH1 0x1F LT PUSH3 0xDF JUMPI DUP1 MLOAD PUSH1 0xFF NOT AND DUP4 DUP1 ADD OR DUP6 SSTORE PUSH3 0x10F JUMP JUMPDEST DUP3 DUP1 ADD PUSH1 0x1 ADD DUP6 SSTORE DUP3 ISZERO PUSH3 0x10F JUMPI SWAP2 DUP3 ADD JUMPDEST DUP3 DUP2 GT ISZERO PUSH3 0x10F JUMPI DUP3 MLOAD DUP3 SSTORE SWAP2 PUSH1 0x20 ADD SWAP2 SWAP1 PUSH1 0x1 ADD SWAP1 PUSH3 0xF2 JUMP JUMPDEST POP PUSH3 0x11D SWAP3 SWAP2 POP PUSH3 0x121 JUMP JUMPDEST POP SWAP1 JUMP JUMPDEST JUMPDEST DUP1 DUP3 GT ISZERO PUSH3 0x11D JUMPI PUSH1 0x0 DUP2 SSTORE PUSH1 0x1 ADD PUSH3 0x122 JUMP JUMPDEST PUSH1 0x1 DUP2 DUP2 SHR SWAP1 DUP3 AND DUP1 PUSH3 0x14D JUMPI PUSH1 0x7F DUP3 AND SWAP2 POP JUMPDEST PUSH1 0x20 DUP3 LT DUP2 SUB PUSH3 0x16E JUMPI PUSH4 0x4E487B71 PUSH1 0xE0 SHL PUSH1 0x0 MSTORE PUSH1 0x22 PUSH1 0x4 MSTORE PUSH1 0x24 PUSH1 0x0 REVERT JUMPDEST POP SWAP2 SWAP1 POP JUMP JUMPDEST PUSH2 0x1DF4 DUP1 PUSH3 0x184 PUSH1 0x0 CODECOPY PUSH1 0x0 RETURN INVALID PUSH1 0x80 PUSH1 0x40 MSTORE CALLVALUE DUP1 ISZERO PUSH2 0x10 JUMPI PUSH1 0x0 DUP1 REVERT JUMPDEST POP PUSH1 0x4 CALLDATASIZE LT PUSH2 0x137 JUMPI PUSH1 0x0 CALLDATALOAD PUSH1 0xE0 SHR DUP1 PUSH4 0x4F6CCCE7 GT PUSH2 0xB8 JUMPI DUP1 PUSH4 0x95D89B41 GT PUSH2 0x7C JUMPI DUP1 PUSH4 0x95D89B41 EQ PUSH2 0x27E JUMPI DUP1 PUSH4 0xA22CB465 EQ PUSH2 0x286 JUMPI DUP1 PUSH4 0xB88D4FDE EQ PUSH2 0x299 JUMPI DUP1 PUSH4 0xC87B56DD EQ PUSH2 0x2AC JUMPI DUP1 PUSH4 0xE985E9C5 EQ PUSH2 0x2BF JUMPI DUP1 PUSH4 0xEACABE14 EQ PUSH2 0x2FB JUMPI PUSH1 0x0 DUP1 REVERT JUMPDEST DUP1 PUSH4 0x4F6CCCE7 EQ PUSH2 0x22A JUMPI DUP1 PUSH4 0x55F804B3 EQ PUSH2 0x23D JUMPI DUP1 PUSH4 0x6352211E EQ PUSH2 0x250 JUMPI DUP1 PUSH4 0x70A08231 EQ PUSH2 0x263 JUMPI DUP1 PUSH4 0x83197EF0 EQ PUSH2 0x276 JUMPI PUSH1 0x0 DUP1 REVERT JUMPDEST DUP1 PUSH4 0x23B872DD GT PUSH2 0xFF JUMPI DUP1 PUSH4 0x23B872DD EQ PUSH2 0x1CB JUMPI DUP1 PUSH4 0x2890E0D7 EQ PUSH2 0x1DE JUMPI DUP1 PUSH4 0x2A6FFD2C EQ PUSH2 0x1F1 JUMPI DUP1 PUSH4 0x2F745C59 EQ PUSH2 0x204 JUMPI DUP1 PUSH4 0x42842E0E EQ PUSH2 0x217 JUMPI PUSH1 0x0 DUP1 REVERT JUMPDEST DUP1 PUSH4 0x1FFC9A7 EQ PUSH2 0x13C JUMPI DUP1 PUSH4 0x6FDDE03 EQ PUSH2 0x164 JUMPI DUP1 PUSH4 0x81812FC EQ PUSH2 0x179 JUMPI DUP1 PUSH4 0x95EA7B3 EQ PUSH2 0x1A4 JUMPI DUP1 PUSH4 0x18160DDD EQ PUSH2 0x1B9 JUMPI JUMPDEST PUSH1 0x0 DUP1 REVERT JUMPDEST PUSH2 0x14F PUSH2 0x14A CALLDATASIZE PUSH1 0x4 PUSH2 0x1838 JUMP JUMPDEST PUSH2 0x30E JUMP JUMPDEST PUSH1 0x40 MLOAD SWAP1 ISZERO ISZERO DUP2 MSTORE PUSH1 0x20 ADD JUMPDEST PUSH1 0x40 MLOAD DUP1 SWAP2 SUB SWAP1 RETURN JUMPDEST PUSH2 0x16C PUSH2 0x31F JUMP JUMPDEST PUSH1 0x40 MLOAD PUSH2 0x15B SWAP2 SWAP1 PUSH2 0x18AD JUMP JUMPDEST PUSH2 0x18C PUSH2 0x187 CALLDATASIZE PUSH1 0x4 PUSH2 0x18C0 JUMP JUMPDEST PUSH2 0x3B1 JUMP JUMPDEST PUSH1 0x40 MLOAD PUSH1 0x1 PUSH1 0x1 PUSH1 0xA0 SHL SUB SWAP1 SWAP2 AND DUP2 MSTORE PUSH1 0x20 ADD PUSH2 0x15B JUMP JUMPDEST PUSH2 0x1B7 PUSH2 0x1B2 CALLDATASIZE PUSH1 0x4 PUSH2 0x18F5 JUMP JUMPDEST PUSH2 0x44B JUMP JUMPDEST STOP JUMPDEST PUSH1 0x8 SLOAD JUMPDEST PUSH1 0x40 MLOAD SWAP1 DUP2 MSTORE PUSH1 0x20 ADD PUSH2 0x15B JUMP JUMPDEST PUSH2 0x1B7 PUSH2 0x1D9 CALLDATASIZE PUSH1 0x4 PUSH2 0x191F JUMP JUMPDEST PUSH2 0x560 JUMP JUMPDEST PUSH2 0x1BD PUSH2 0x1EC CALLDATASIZE PUSH1 0x4 PUSH2 0x18C0 JUMP JUMPDEST PUSH2 0x591 JUMP JUMPDEST PUSH2 0x1BD PUSH2 0x1FF CALLDATASIZE PUSH1 0x4 PUSH2 0x191F JUMP JUMPDEST PUSH2 0x61D JUMP JUMPDEST PUSH2 0x1BD PUSH2 0x212 CALLDATASIZE PUSH1 0x4 PUSH2 0x18F5 JUMP JUMPDEST PUSH2 0x6B2 JUMP JUMPDEST PUSH2 0x1B7 PUSH2 0x225 CALLDATASIZE PUSH1 0x4 PUSH2 0x191F JUMP JUMPDEST PUSH2 0x748 JUMP JUMPDEST PUSH2 0x1BD PUSH2 0x238 CALLDATASIZE PUSH1 0x4 PUSH2 0x18C0 JUMP JUMPDEST PUSH2 0x763 JUMP JUMPDEST PUSH2 0x1B7 PUSH2 0x24B CALLDATASIZE PUSH1 0x4 PUSH2 0x1A07 JUMP JUMPDEST PUSH2 0x7F6 JUMP JUMPDEST PUSH2 0x18C PUSH2 0x25E CALLDATASIZE PUSH1 0x4 PUSH2 0x18C0 JUMP JUMPDEST PUSH2 0x80D JUMP JUMPDEST PUSH2 0x1BD PUSH2 0x271 CALLDATASIZE PUSH1 0x4 PUSH2 0x1A3C JUMP JUMPDEST PUSH2 0x884 JUMP JUMPDEST PUSH2 0x1B7 PUSH2 0x90B JUMP JUMPDEST PUSH2 0x16C PUSH2 0x98D JUMP JUMPDEST PUSH2 0x1B7 PUSH2 0x294 CALLDATASIZE PUSH1 0x4 PUSH2 0x1A57 JUMP JUMPDEST PUSH2 0x99C JUMP JUMPDEST PUSH2 0x1B7 PUSH2 0x2A7 CALLDATASIZE PUSH1 0x4 PUSH2 0x1A93 JUMP JUMPDEST PUSH2 0x9A7 JUMP JUMPDEST PUSH2 0x16C PUSH2 0x2BA CALLDATASIZE PUSH1 0x4 PUSH2 0x18C0 JUMP JUMPDEST PUSH2 0x9DF JUMP JUMPDEST PUSH2 0x14F PUSH2 0x2CD CALLDATASIZE PUSH1 0x4 PUSH2 0x1B0F JUMP JUMPDEST PUSH1 0x1 PUSH1 0x1 PUSH1 0xA0 SHL SUB SWAP2 DUP3 AND PUSH1 0x0 SWAP1 DUP2 MSTORE PUSH1 0x5 PUSH1 0x20 SWAP1 DUP2 MSTORE PUSH1 0x40 DUP1 DUP4 KECCAK256 SWAP4 SWAP1 SWAP5 AND DUP3 MSTORE SWAP2 SWAP1 SWAP2 MSTORE KECCAK256 SLOAD PUSH1 0xFF AND SWAP1 JUMP JUMPDEST PUSH2 0x1BD PUSH2 0x309 CALLDATASIZE PUSH1 0x4 PUSH2 0x1B42 JUMP JUMPDEST PUSH2 0xB60 JUMP JUMPDEST PUSH1 0x0 PUSH2 0x319 DUP3 PUSH2 0xB98 JUMP JUMPDEST SWAP3 SWAP2 POP POP JUMP JUMPDEST PUSH1 0x60 PUSH1 0x0 DUP1 SLOAD PUSH2 0x32E SWAP1 PUSH2 0x1B90 JUMP JUMPDEST DUP1 PUSH1 0x1F ADD PUSH1 0x20 DUP1 SWAP2 DIV MUL PUSH1 0x20 ADD PUSH1 0x40 MLOAD SWAP1 DUP2 ADD PUSH1 0x40 MSTORE DUP1 SWAP3 SWAP2 SWAP1 DUP2 DUP2 MSTORE PUSH1 0x20 ADD DUP3 DUP1 SLOAD PUSH2 0x35A SWAP1 PUSH2 0x1B90 JUMP JUMPDEST DUP1 ISZERO PUSH2 0x3A7 JUMPI DUP1 PUSH1 0x1F LT PUSH2 0x37C JUMPI PUSH2 0x100 DUP1 DUP4 SLOAD DIV MUL DUP4 MSTORE SWAP2 PUSH1 0x20 ADD SWAP2 PUSH2 0x3A7 JUMP JUMPDEST DUP3 ADD SWAP2 SWAP1 PUSH1 0x0 MSTORE PUSH1 0x20 PUSH1 0x0 KECCAK256 SWAP1 JUMPDEST DUP2 SLOAD DUP2 MSTORE SWAP1 PUSH1 0x1 ADD SWAP1 PUSH1 0x20 ADD DUP1 DUP4 GT PUSH2 0x38A JUMPI DUP3 SWAP1 SUB PUSH1 0x1F AND DUP3 ADD SWAP2 JUMPDEST POP POP POP POP POP SWAP1 POP SWAP1 JUMP JUMPDEST PUSH1 0x0 DUP2 DUP2 MSTORE PUSH1 0x2 PUSH1 0x20 MSTORE PUSH1 0x40 DUP2 KECCAK256 SLOAD PUSH1 0x1 PUSH1 0x1 PUSH1 0xA0 SHL SUB AND PUSH2 0x42F JUMPI PUSH1 0x40 MLOAD PUSH3 0x461BCD PUSH1 0xE5 SHL DUP2 MSTORE PUSH1 0x20 PUSH1 0x4 DUP3 ADD MSTORE PUSH1 0x2C PUSH1 0x24 DUP3 ADD MSTORE PUSH32 0x4552433732313A20617070726F76656420717565727920666F72206E6F6E6578 PUSH1 0x44 DUP3 ADD MSTORE PUSH12 0x34B9BA32B73A103A37B5B2B7 PUSH1 0xA1 SHL PUSH1 0x64 DUP3 ADD MSTORE PUSH1 0x84 ADD JUMPDEST PUSH1 0x40 MLOAD DUP1 SWAP2 SUB SWAP1 REVERT JUMPDEST POP PUSH1 0x0 SWAP1 DUP2 MSTORE PUSH1 0x4 PUSH1 0x20 MSTORE PUSH1 0x40 SWAP1 KECCAK256 SLOAD PUSH1 0x1 PUSH1 0x1 PUSH1 0xA0 SHL SUB AND SWAP1 JUMP JUMPDEST PUSH1 0x0 PUSH2 0x456 DUP3 PUSH2 0x80D JUMP JUMPDEST SWAP1 POP DUP1 PUSH1 0x1 PUSH1 0x1 PUSH1 0xA0 SHL SUB AND DUP4 PUSH1 0x1 PUSH1 0x1 PUSH1 0xA0 SHL SUB AND SUB PUSH2 0x4C3 JUMPI PUSH1 0x40 MLOAD PUSH3 0x461BCD PUSH1 0xE5 SHL DUP2 MSTORE PUSH1 0x20 PUSH1 0x4 DUP3 ADD MSTORE PUSH1 0x21 PUSH1 0x24 DUP3 ADD MSTORE PUSH32 0x4552433732313A20617070726F76616C20746F2063757272656E74206F776E65 PUSH1 0x44 DUP3 ADD MSTORE PUSH1 0x39 PUSH1 0xF9 SHL PUSH1 0x64 DUP3 ADD MSTORE PUSH1 0x84 ADD PUSH2 0x426 JUMP JUMPDEST CALLER PUSH1 0x1 PUSH1 0x1 PUSH1 0xA0 SHL SUB DUP3 AND EQ DUP1 PUSH2 0x4DF JUMPI POP PUSH2 0x4DF DUP2 CALLER PUSH2 0x2CD JUMP JUMPDEST PUSH2 0x551 JUMPI PUSH1 0x40 MLOAD PUSH3 0x461BCD PUSH1 0xE5 SHL DUP2 MSTORE PUSH1 0x20 PUSH1 0x4 DUP3 ADD MSTORE PUSH1 0x38 PUSH1 0x24 DUP3 ADD MSTORE PUSH32 0x4552433732313A20617070726F76652063616C6C6572206973206E6F74206F77 PUSH1 0x44 DUP3 ADD MSTORE PUSH32 0x6E6572206E6F7220617070726F76656420666F7220616C6C0000000000000000 PUSH1 0x64 DUP3 ADD MSTORE PUSH1 0x84 ADD PUSH2 0x426 JUMP JUMPDEST PUSH2 0x55B DUP4 DUP4 PUSH2 0xBBD JUMP JUMPDEST POP POP POP JUMP JUMPDEST PUSH2 0x56A CALLER DUP3 PUSH2 0xC2B JUMP JUMPDEST PUSH2 0x586 JUMPI PUSH1 0x40 MLOAD PUSH3 0x461BCD PUSH1 0xE5 SHL DUP2 MSTORE PUSH1 0x4 ADD PUSH2 0x426 SWAP1 PUSH2 0x1BCA JUMP JUMPDEST PUSH2 0x55B DUP4 DUP4 DUP4 PUSH2 0xD22 JUMP JUMPDEST PUSH1 0x0 PUSH2 0x59C DUP3 PUSH2 0x80D JUMP JUMPDEST PUSH1 0x1 PUSH1 0x1 PUSH1 0xA0 SHL SUB AND CALLER PUSH1 0x1 PUSH1 0x1 PUSH1 0xA0 SHL SUB AND EQ PUSH2 0x610 JUMPI PUSH1 0x40 MLOAD PUSH3 0x461BCD PUSH1 0xE5 SHL DUP2 MSTORE PUSH1 0x20 PUSH1 0x4 DUP3 ADD MSTORE PUSH1 0x2B PUSH1 0x24 DUP3 ADD MSTORE PUSH32 0x4F6E6C7920746865206F776E6572206F66207468697320546F6B656E20636F75 PUSH1 0x44 DUP3 ADD MSTORE PUSH11 0x6C64204275726E20497421 PUSH1 0xA8 SHL PUSH1 0x64 DUP3 ADD MSTORE PUSH1 0x84 ADD PUSH2 0x426 JUMP JUMPDEST PUSH2 0x619 DUP3 PUSH2 0xEC9 JUMP JUMPDEST POP SWAP1 JUMP JUMPDEST PUSH1 0x0 PUSH2 0x628 DUP3 PUSH2 0x80D JUMP JUMPDEST PUSH1 0x1 PUSH1 0x1 PUSH1 0xA0 SHL SUB AND CALLER PUSH1 0x1 PUSH1 0x1 PUSH1 0xA0 SHL SUB AND EQ PUSH2 0x6A0 JUMPI PUSH1 0x40 MLOAD PUSH3 0x461BCD PUSH1 0xE5 SHL DUP2 MSTORE PUSH1 0x20 PUSH1 0x4 DUP3 ADD MSTORE PUSH1 0x2F PUSH1 0x24 DUP3 ADD MSTORE PUSH32 0x4F6E6C7920746865206F776E6572206F66207468697320546F6B656E20636F75 PUSH1 0x44 DUP3 ADD MSTORE PUSH15 0x6C64207472616E7366657220497421 PUSH1 0x88 SHL PUSH1 0x64 DUP3 ADD MSTORE PUSH1 0x84 ADD PUSH2 0x426 JUMP JUMPDEST PUSH2 0x6AB DUP5 DUP5 DUP5 PUSH2 0x560 JUMP JUMPDEST POP SWAP3 SWAP2 POP POP JUMP JUMPDEST PUSH1 0x0 PUSH2 0x6BD DUP4 PUSH2 0x884 JUMP JUMPDEST DUP3 LT PUSH2 0x71F JUMPI PUSH1 0x40 MLOAD PUSH3 0x461BCD PUSH1 0xE5 SHL DUP2 MSTORE PUSH1 0x20 PUSH1 0x4 DUP3 ADD MSTORE PUSH1 0x2B PUSH1 0x24 DUP3 ADD MSTORE PUSH32 0x455243373231456E756D657261626C653A206F776E657220696E646578206F75 PUSH1 0x44 DUP3 ADD MSTORE PUSH11 0x74206F6620626F756E6473 PUSH1 0xA8 SHL PUSH1 0x64 DUP3 ADD MSTORE PUSH1 0x84 ADD PUSH2 0x426 JUMP JUMPDEST POP PUSH1 0x1 PUSH1 0x1 PUSH1 0xA0 SHL SUB SWAP2 SWAP1 SWAP2 AND PUSH1 0x0 SWAP1 DUP2 MSTORE PUSH1 0x6 PUSH1 0x20 SWAP1 DUP2 MSTORE PUSH1 0x40 DUP1 DUP4 KECCAK256 SWAP4 DUP4 MSTORE SWAP3 SWAP1 MSTORE KECCAK256 SLOAD SWAP1 JUMP JUMPDEST PUSH2 0x55B DUP4 DUP4 DUP4 PUSH1 0x40 MLOAD DUP1 PUSH1 0x20 ADD PUSH1 0x40 MSTORE DUP1 PUSH1 0x0 DUP2 MSTORE POP PUSH2 0x9A7 JUMP JUMPDEST PUSH1 0x0 PUSH2 0x76E PUSH1 0x8 SLOAD SWAP1 JUMP JUMPDEST DUP3 LT PUSH2 0x7D1 JUMPI PUSH1 0x40 MLOAD PUSH3 0x461BCD PUSH1 0xE5 SHL DUP2 MSTORE PUSH1 0x20 PUSH1 0x4 DUP3 ADD MSTORE PUSH1 0x2C PUSH1 0x24 DUP3 ADD MSTORE PUSH32 0x455243373231456E756D657261626C653A20676C6F62616C20696E646578206F PUSH1 0x44 DUP3 ADD MSTORE PUSH12 0x7574206F6620626F756E6473 PUSH1 0xA0 SHL PUSH1 0x64 DUP3 ADD MSTORE PUSH1 0x84 ADD PUSH2 0x426 JUMP JUMPDEST PUSH1 0x8 DUP3 DUP2 SLOAD DUP2 LT PUSH2 0x7E4 JUMPI PUSH2 0x7E4 PUSH2 0x1C1B JUMP JUMPDEST SWAP1 PUSH1 0x0 MSTORE PUSH1 0x20 PUSH1 0x0 KECCAK256 ADD SLOAD SWAP1 POP SWAP2 SWAP1 POP JUMP JUMPDEST DUP1 MLOAD PUSH2 0x809 SWAP1 PUSH1 0xE SWAP1 PUSH1 0x20 DUP5 ADD SWAP1 PUSH2 0x1757 JUMP JUMPDEST POP POP JUMP JUMPDEST PUSH1 0x0 DUP2 DUP2 MSTORE PUSH1 0x2 PUSH1 0x20 MSTORE PUSH1 0x40 DUP2 KECCAK256 SLOAD PUSH1 0x1 PUSH1 0x1 PUSH1 0xA0 SHL SUB AND DUP1 PUSH2 0x319 JUMPI PUSH1 0x40 MLOAD PUSH3 0x461BCD PUSH1 0xE5 SHL DUP2 MSTORE PUSH1 0x20 PUSH1 0x4 DUP3 ADD MSTORE PUSH1 0x29 PUSH1 0x24 DUP3 ADD MSTORE PUSH32 0x4552433732313A206F776E657220717565727920666F72206E6F6E6578697374 PUSH1 0x44 DUP3 ADD MSTORE PUSH9 0x32B73A103A37B5B2B7 PUSH1 0xB9 SHL PUSH1 0x64 DUP3 ADD MSTORE PUSH1 0x84 ADD PUSH2 0x426 JUMP JUMPDEST PUSH1 0x0 PUSH1 0x1 PUSH1 0x1 PUSH1 0xA0 SHL SUB DUP3 AND PUSH2 0x8EF JUMPI PUSH1 0x40 MLOAD PUSH3 0x461BCD PUSH1 0xE5 SHL DUP2 MSTORE PUSH1 0x20 PUSH1 0x4 DUP3 ADD MSTORE PUSH1 0x2A PUSH1 0x24 DUP3 ADD MSTORE PUSH32 0x4552433732313A2062616C616E636520717565727920666F7220746865207A65 PUSH1 0x44 DUP3 ADD MSTORE PUSH10 0x726F2061646472657373 PUSH1 0xB0 SHL PUSH1 0x64 DUP3 ADD MSTORE PUSH1 0x84 ADD PUSH2 0x426 JUMP JUMPDEST POP PUSH1 0x1 PUSH1 0x1 PUSH1 0xA0 SHL SUB AND PUSH1 0x0 SWAP1 DUP2 MSTORE PUSH1 0x3 PUSH1 0x20 MSTORE PUSH1 0x40 SWAP1 KECCAK256 SLOAD SWAP1 JUMP JUMPDEST PUSH1 0xC SLOAD PUSH1 0x1 PUSH1 0x1 PUSH1 0xA0 SHL SUB AND CALLER EQ PUSH2 0x97F JUMPI PUSH1 0x40 MLOAD PUSH3 0x461BCD PUSH1 0xE5 SHL DUP2 MSTORE PUSH1 0x20 PUSH1 0x4 DUP3 ADD MSTORE PUSH1 0x31 PUSH1 0x24 DUP3 ADD MSTORE PUSH32 0x4F6E6C7920746865206F776E6572206F66207468697320436F6E747261637420 PUSH1 0x44 DUP3 ADD MSTORE PUSH17 0x636F756C642064657374726F7920497421 PUSH1 0x78 SHL PUSH1 0x64 DUP3 ADD MSTORE PUSH1 0x84 ADD PUSH2 0x426 JUMP JUMPDEST PUSH1 0xC SLOAD PUSH1 0x1 PUSH1 0x1 PUSH1 0xA0 SHL SUB AND SELFDESTRUCT JUMPDEST PUSH1 0x60 PUSH1 0x1 DUP1 SLOAD PUSH2 0x32E SWAP1 PUSH2 0x1B90 JUMP JUMPDEST PUSH2 0x809 CALLER DUP4 DUP4 PUSH2 0xED5 JUMP JUMPDEST PUSH2 0x9B1 CALLER DUP4 PUSH2 0xC2B JUMP JUMPDEST PUSH2 0x9CD JUMPI PUSH1 0x40 MLOAD PUSH3 0x461BCD PUSH1 0xE5 SHL DUP2 MSTORE PUSH1 0x4 ADD PUSH2 0x426 SWAP1 PUSH2 0x1BCA JUMP JUMPDEST PUSH2 0x9D9 DUP5 DUP5 DUP5 DUP5 PUSH2 0xFA3 JUMP JUMPDEST POP POP POP POP JUMP JUMPDEST PUSH1 0x0 DUP2 DUP2 MSTORE PUSH1 0x2 PUSH1 0x20 MSTORE PUSH1 0x40 SWAP1 KECCAK256 SLOAD PUSH1 0x60 SWAP1 PUSH1 0x1 PUSH1 0x1 PUSH1 0xA0 SHL SUB AND PUSH2 0xA5E JUMPI PUSH1 0x40 MLOAD PUSH3 0x461BCD PUSH1 0xE5 SHL DUP2 MSTORE PUSH1 0x20 PUSH1 0x4 DUP3 ADD MSTORE PUSH1 0x2F PUSH1 0x24 DUP3 ADD MSTORE PUSH32 0x4552433732314D657461646174613A2055524920717565727920666F72206E6F PUSH1 0x44 DUP3 ADD MSTORE PUSH15 0x3732BC34B9BA32B73A103A37B5B2B7 PUSH1 0x89 SHL PUSH1 0x64 DUP3 ADD MSTORE PUSH1 0x84 ADD PUSH2 0x426 JUMP JUMPDEST PUSH1 0x0 DUP3 DUP2 MSTORE PUSH1 0xD PUSH1 0x20 MSTORE PUSH1 0x40 DUP2 KECCAK256 DUP1 SLOAD PUSH2 0xA77 SWAP1 PUSH2 0x1B90 JUMP JUMPDEST DUP1 PUSH1 0x1F ADD PUSH1 0x20 DUP1 SWAP2 DIV MUL PUSH1 0x20 ADD PUSH1 0x40 MLOAD SWAP1 DUP2 ADD PUSH1 0x40 MSTORE DUP1 SWAP3 SWAP2 SWAP1 DUP2 DUP2 MSTORE PUSH1 0x20 ADD DUP3 DUP1 SLOAD PUSH2 0xAA3 SWAP1 PUSH2 0x1B90 JUMP JUMPDEST DUP1 ISZERO PUSH2 0xAF0 JUMPI DUP1 PUSH1 0x1F LT PUSH2 0xAC5 JUMPI PUSH2 0x100 DUP1 DUP4 SLOAD DIV MUL DUP4 MSTORE SWAP2 PUSH1 0x20 ADD SWAP2 PUSH2 0xAF0 JUMP JUMPDEST DUP3 ADD SWAP2 SWAP1 PUSH1 0x0 MSTORE PUSH1 0x20 PUSH1 0x0 KECCAK256 SWAP1 JUMPDEST DUP2 SLOAD DUP2 MSTORE SWAP1 PUSH1 0x1 ADD SWAP1 PUSH1 0x20 ADD DUP1 DUP4 GT PUSH2 0xAD3 JUMPI DUP3 SWAP1 SUB PUSH1 0x1F AND DUP3 ADD SWAP2 JUMPDEST POP POP POP POP POP SWAP1 POP PUSH1 0x0 PUSH2 0xB01 PUSH2 0xFD6 JUMP JUMPDEST SWAP1 POP DUP1 MLOAD PUSH1 0x0 SUB PUSH2 0xB13 JUMPI POP SWAP3 SWAP2 POP POP JUMP JUMPDEST DUP2 MLOAD ISZERO PUSH2 0xB45 JUMPI DUP1 DUP3 PUSH1 0x40 MLOAD PUSH1 0x20 ADD PUSH2 0xB2D SWAP3 SWAP2 SWAP1 PUSH2 0x1C31 JUMP JUMPDEST PUSH1 0x40 MLOAD PUSH1 0x20 DUP2 DUP4 SUB SUB DUP2 MSTORE SWAP1 PUSH1 0x40 MSTORE SWAP3 POP POP POP SWAP2 SWAP1 POP JUMP JUMPDEST DUP1 PUSH2 0xB4F DUP6 PUSH2 0xFE5 JUMP JUMPDEST PUSH1 0x40 MLOAD PUSH1 0x20 ADD PUSH2 0xB2D SWAP3 SWAP2 SWAP1 PUSH2 0x1C31 JUMP JUMPDEST PUSH1 0x0 PUSH2 0xB70 PUSH1 0xB DUP1 SLOAD PUSH1 0x1 ADD SWAP1 SSTORE JUMP JUMPDEST PUSH1 0x0 PUSH2 0xB7B PUSH1 0xB SLOAD SWAP1 JUMP JUMPDEST SWAP1 POP PUSH2 0xB87 DUP5 DUP3 PUSH2 0x10E6 JUMP JUMPDEST PUSH2 0xB91 DUP2 DUP5 PUSH2 0x1234 JUMP JUMPDEST SWAP4 SWAP3 POP POP POP JUMP JUMPDEST PUSH1 0x0 PUSH1 0x1 PUSH1 0x1 PUSH1 0xE0 SHL SUB NOT DUP3 AND PUSH4 0x780E9D63 PUSH1 0xE0 SHL EQ DUP1 PUSH2 0x319 JUMPI POP PUSH2 0x319 DUP3 PUSH2 0x12CC JUMP JUMPDEST PUSH1 0x0 DUP2 DUP2 MSTORE PUSH1 0x4 PUSH1 0x20 MSTORE PUSH1 0x40 SWAP1 KECCAK256 DUP1 SLOAD PUSH1 0x1 PUSH1 0x1 PUSH1 0xA0 SHL SUB NOT AND PUSH1 0x1 PUSH1 0x1 PUSH1 0xA0 SHL SUB DUP5 AND SWAP1 DUP2 OR SWAP1 SWAP2 SSTORE DUP2 SWAP1 PUSH2 0xBF2 DUP3 PUSH2 0x80D JUMP JUMPDEST PUSH1 0x1 PUSH1 0x1 PUSH1 0xA0 SHL SUB AND PUSH32 0x8C5BE1E5EBEC7D5BD14F71427D1E84F3DD0314C0F7B2291E5B200AC8C7C3B925 PUSH1 0x40 MLOAD PUSH1 0x40 MLOAD DUP1 SWAP2 SUB SWAP1 LOG4 POP POP JUMP JUMPDEST PUSH1 0x0 DUP2 DUP2 MSTORE PUSH1 0x2 PUSH1 0x20 MSTORE PUSH1 0x40 DUP2 KECCAK256 SLOAD PUSH1 0x1 PUSH1 0x1 PUSH1 0xA0 SHL SUB AND PUSH2 0xCA4 JUMPI PUSH1 0x40 MLOAD PUSH3 0x461BCD PUSH1 0xE5 SHL DUP2 MSTORE PUSH1 0x20 PUSH1 0x4 DUP3 ADD MSTORE PUSH1 0x2C PUSH1 0x24 DUP3 ADD MSTORE PUSH32 0x4552433732313A206F70657261746F7220717565727920666F72206E6F6E6578 PUSH1 0x44 DUP3 ADD MSTORE PUSH12 0x34B9BA32B73A103A37B5B2B7 PUSH1 0xA1 SHL PUSH1 0x64 DUP3 ADD MSTORE PUSH1 0x84 ADD PUSH2 0x426 JUMP JUMPDEST PUSH1 0x0 PUSH2 0xCAF DUP4 PUSH2 0x80D JUMP JUMPDEST SWAP1 POP DUP1 PUSH1 0x1 PUSH1 0x1 PUSH1 0xA0 SHL SUB AND DUP5 PUSH1 0x1 PUSH1 0x1 PUSH1 0xA0 SHL SUB AND EQ DUP1 PUSH2 0xCEA JUMPI POP DUP4 PUSH1 0x1 PUSH1 0x1 PUSH1 0xA0 SHL SUB AND PUSH2 0xCDF DUP5 PUSH2 0x3B1 JUMP JUMPDEST PUSH1 0x1 PUSH1 0x1 PUSH1 0xA0 SHL SUB AND EQ JUMPDEST DUP1 PUSH2 0xD1A JUMPI POP PUSH1 0x1 PUSH1 0x1 PUSH1 0xA0 SHL SUB DUP1 DUP3 AND PUSH1 0x0 SWAP1 DUP2 MSTORE PUSH1 0x5 PUSH1 0x20 SWAP1 DUP2 MSTORE PUSH1 0x40 DUP1 DUP4 KECCAK256 SWAP4 DUP9 AND DUP4 MSTORE SWAP3 SWAP1 MSTORE KECCAK256 SLOAD PUSH1 0xFF AND JUMPDEST SWAP5 SWAP4 POP POP POP POP JUMP JUMPDEST DUP3 PUSH1 0x1 PUSH1 0x1 PUSH1 0xA0 SHL SUB AND PUSH2 0xD35 DUP3 PUSH2 0x80D JUMP JUMPDEST PUSH1 0x1 PUSH1 0x1 PUSH1 0xA0 SHL SUB AND EQ PUSH2 0xD99 JUMPI PUSH1 0x40 MLOAD PUSH3 0x461BCD PUSH1 0xE5 SHL DUP2 MSTORE PUSH1 0x20 PUSH1 0x4 DUP3 ADD MSTORE PUSH1 0x25 PUSH1 0x24 DUP3 ADD MSTORE PUSH32 0x4552433732313A207472616E736665722066726F6D20696E636F727265637420 PUSH1 0x44 DUP3 ADD MSTORE PUSH5 0x37BBB732B9 PUSH1 0xD9 SHL PUSH1 0x64 DUP3 ADD MSTORE PUSH1 0x84 ADD PUSH2 0x426 JUMP JUMPDEST PUSH1 0x1 PUSH1 0x1 PUSH1 0xA0 SHL SUB DUP3 AND PUSH2 0xDFB JUMPI PUSH1 0x40 MLOAD PUSH3 0x461BCD PUSH1 0xE5 SHL DUP2 MSTORE PUSH1 0x20 PUSH1 0x4 DUP3 ADD MSTORE PUSH1 0x24 DUP1 DUP3 ADD MSTORE PUSH32 0x4552433732313A207472616E7366657220746F20746865207A65726F20616464 PUSH1 0x44 DUP3 ADD MSTORE PUSH4 0x72657373 PUSH1 0xE0 SHL PUSH1 0x64 DUP3 ADD MSTORE PUSH1 0x84 ADD PUSH2 0x426 JUMP JUMPDEST PUSH2 0xE06 DUP4 DUP4 DUP4 PUSH2 0x131C JUMP JUMPDEST PUSH2 0xE11 PUSH1 0x0 DUP3 PUSH2 0xBBD JUMP JUMPDEST PUSH1 0x1 PUSH1 0x1 PUSH1 0xA0 SHL SUB DUP4 AND PUSH1 0x0 SWAP1 DUP2 MSTORE PUSH1 0x3 PUSH1 0x20 MSTORE PUSH1 0x40 DUP2 KECCAK256 DUP1 SLOAD PUSH1 0x1 SWAP3 SWAP1 PUSH2 0xE3A SWAP1 DUP5 SWAP1 PUSH2 0x1C76 JUMP JUMPDEST SWAP1 SWAP2 SSTORE POP POP PUSH1 0x1 PUSH1 0x1 PUSH1 0xA0 SHL SUB DUP3 AND PUSH1 0x0 SWAP1 DUP2 MSTORE PUSH1 0x3 PUSH1 0x20 MSTORE PUSH1 0x40 DUP2 KECCAK256 DUP1 SLOAD PUSH1 0x1 SWAP3 SWAP1 PUSH2 0xE68 SWAP1 DUP5 SWAP1 PUSH2 0x1C8D JUMP JUMPDEST SWAP1 SWAP2 SSTORE POP POP PUSH1 0x0 DUP2 DUP2 MSTORE PUSH1 0x2 PUSH1 0x20 MSTORE PUSH1 0x40 DUP1 DUP3 KECCAK256 DUP1 SLOAD PUSH1 0x1 PUSH1 0x1 PUSH1 0xA0 SHL SUB NOT AND PUSH1 0x1 PUSH1 0x1 PUSH1 0xA0 SHL SUB DUP7 DUP2 AND SWAP2 DUP3 OR SWAP1 SWAP3 SSTORE SWAP2 MLOAD DUP5 SWAP4 SWAP2 DUP8 AND SWAP2 PUSH32 0xDDF252AD1BE2C89B69C2B068FC378DAA952BA7F163C4A11628F55A4DF523B3EF SWAP2 LOG4 POP POP POP JUMP JUMPDEST PUSH2 0xED2 DUP2 PUSH2 0x1327 JUMP JUMPDEST POP JUMP JUMPDEST DUP2 PUSH1 0x1 PUSH1 0x1 PUSH1 0xA0 SHL SUB AND DUP4 PUSH1 0x1 PUSH1 0x1 PUSH1 0xA0 SHL SUB AND SUB PUSH2 0xF36 JUMPI PUSH1 0x40 MLOAD PUSH3 0x461BCD PUSH1 0xE5 SHL DUP2 MSTORE PUSH1 0x20 PUSH1 0x4 DUP3 ADD MSTORE PUSH1 0x19 PUSH1 0x24 DUP3 ADD MSTORE PUSH32 0x4552433732313A20617070726F766520746F2063616C6C657200000000000000 PUSH1 0x44 DUP3 ADD MSTORE PUSH1 0x64 ADD PUSH2 0x426 JUMP JUMPDEST PUSH1 0x1 PUSH1 0x1 PUSH1 0xA0 SHL SUB DUP4 DUP2 AND PUSH1 0x0 DUP2 DUP2 MSTORE PUSH1 0x5 PUSH1 0x20 SWAP1 DUP2 MSTORE PUSH1 0x40 DUP1 DUP4 KECCAK256 SWAP5 DUP8 AND DUP1 DUP5 MSTORE SWAP5 DUP3 MSTORE SWAP2 DUP3 SWAP1 KECCAK256 DUP1 SLOAD PUSH1 0xFF NOT AND DUP7 ISZERO ISZERO SWAP1 DUP2 OR SWAP1 SWAP2 SSTORE SWAP2 MLOAD SWAP2 DUP3 MSTORE PUSH32 0x17307EAB39AB6107E8899845AD3D59BD9653F200F220920489CA2B5937696C31 SWAP2 ADD PUSH1 0x40 MLOAD DUP1 SWAP2 SUB SWAP1 LOG3 POP POP POP JUMP JUMPDEST PUSH2 0xFAE DUP5 DUP5 DUP5 PUSH2 0xD22 JUMP JUMPDEST PUSH2 0xFBA DUP5 DUP5 DUP5 DUP5 PUSH2 0x1367 JUMP JUMPDEST PUSH2 0x9D9 JUMPI PUSH1 0x40 MLOAD PUSH3 0x461BCD PUSH1 0xE5 SHL DUP2 MSTORE PUSH1 0x4 ADD PUSH2 0x426 SWAP1 PUSH2 0x1CA5 JUMP JUMPDEST PUSH1 0x60 PUSH1 0xE DUP1 SLOAD PUSH2 0x32E SWAP1 PUSH2 0x1B90 JUMP JUMPDEST PUSH1 0x60 DUP2 PUSH1 0x0 SUB PUSH2 0x100C JUMPI POP POP PUSH1 0x40 DUP1 MLOAD DUP1 DUP3 ADD SWAP1 SWAP2 MSTORE PUSH1 0x1 DUP2 MSTORE PUSH1 0x3 PUSH1 0xFC SHL PUSH1 0x20 DUP3 ADD MSTORE SWAP1 JUMP JUMPDEST DUP2 PUSH1 0x0 JUMPDEST DUP2 ISZERO PUSH2 0x1036 JUMPI DUP1 PUSH2 0x1020 DUP2 PUSH2 0x1CF7 JUMP JUMPDEST SWAP2 POP PUSH2 0x102F SWAP1 POP PUSH1 0xA DUP4 PUSH2 0x1D26 JUMP JUMPDEST SWAP2 POP PUSH2 0x1010 JUMP JUMPDEST PUSH1 0x0 DUP2 PUSH8 0xFFFFFFFFFFFFFFFF DUP2 GT ISZERO PUSH2 0x1051 JUMPI PUSH2 0x1051 PUSH2 0x195B JUMP JUMPDEST PUSH1 0x40 MLOAD SWAP1 DUP1 DUP3 MSTORE DUP1 PUSH1 0x1F ADD PUSH1 0x1F NOT AND PUSH1 0x20 ADD DUP3 ADD PUSH1 0x40 MSTORE DUP1 ISZERO PUSH2 0x107B JUMPI PUSH1 0x20 DUP3 ADD DUP2 DUP1 CALLDATASIZE DUP4 CALLDATACOPY ADD SWAP1 POP JUMPDEST POP SWAP1 POP JUMPDEST DUP5 ISZERO PUSH2 0xD1A JUMPI PUSH2 0x1090 PUSH1 0x1 DUP4 PUSH2 0x1C76 JUMP JUMPDEST SWAP2 POP PUSH2 0x109D PUSH1 0xA DUP7 PUSH2 0x1D3A JUMP JUMPDEST PUSH2 0x10A8 SWAP1 PUSH1 0x30 PUSH2 0x1C8D JUMP JUMPDEST PUSH1 0xF8 SHL DUP2 DUP4 DUP2 MLOAD DUP2 LT PUSH2 0x10BD JUMPI PUSH2 0x10BD PUSH2 0x1C1B JUMP JUMPDEST PUSH1 0x20 ADD ADD SWAP1 PUSH1 0x1 PUSH1 0x1 PUSH1 0xF8 SHL SUB NOT AND SWAP1 DUP2 PUSH1 0x0 BYTE SWAP1 MSTORE8 POP PUSH2 0x10DF PUSH1 0xA DUP7 PUSH2 0x1D26 JUMP JUMPDEST SWAP5 POP PUSH2 0x107F JUMP JUMPDEST PUSH1 0x1 PUSH1 0x1 PUSH1 0xA0 SHL SUB DUP3 AND PUSH2 0x113C JUMPI PUSH1 0x40 MLOAD PUSH3 0x461BCD PUSH1 0xE5 SHL DUP2 MSTORE PUSH1 0x20 PUSH1 0x4 DUP3 ADD DUP2 SWAP1 MSTORE PUSH1 0x24 DUP3 ADD MSTORE PUSH32 0x4552433732313A206D696E7420746F20746865207A65726F2061646472657373 PUSH1 0x44 DUP3 ADD MSTORE PUSH1 0x64 ADD PUSH2 0x426 JUMP JUMPDEST PUSH1 0x0 DUP2 DUP2 MSTORE PUSH1 0x2 PUSH1 0x20 MSTORE PUSH1 0x40 SWAP1 KECCAK256 SLOAD PUSH1 0x1 PUSH1 0x1 PUSH1 0xA0 SHL SUB AND ISZERO PUSH2 0x11A1 JUMPI PUSH1 0x40 MLOAD PUSH3 0x461BCD PUSH1 0xE5 SHL DUP2 MSTORE PUSH1 0x20 PUSH1 0x4 DUP3 ADD MSTORE PUSH1 0x1C PUSH1 0x24 DUP3 ADD MSTORE PUSH32 0x4552433732313A20746F6B656E20616C7265616479206D696E74656400000000 PUSH1 0x44 DUP3 ADD MSTORE PUSH1 0x64 ADD PUSH2 0x426 JUMP JUMPDEST PUSH2 0x11AD PUSH1 0x0 DUP4 DUP4 PUSH2 0x131C JUMP JUMPDEST PUSH1 0x1 PUSH1 0x1 PUSH1 0xA0 SHL SUB DUP3 AND PUSH1 0x0 SWAP1 DUP2 MSTORE PUSH1 0x3 PUSH1 0x20 MSTORE PUSH1 0x40 DUP2 KECCAK256 DUP1 SLOAD PUSH1 0x1 SWAP3 SWAP1 PUSH2 0x11D6 SWAP1 DUP5 SWAP1 PUSH2 0x1C8D JUMP JUMPDEST SWAP1 SWAP2 SSTORE POP POP PUSH1 0x0 DUP2 DUP2 MSTORE PUSH1 0x2 PUSH1 0x20 MSTORE PUSH1 0x40 DUP1 DUP3 KECCAK256 DUP1 SLOAD PUSH1 0x1 PUSH1 0x1 PUSH1 0xA0 SHL SUB NOT AND PUSH1 0x1 PUSH1 0x1 PUSH1 0xA0 SHL SUB DUP7 AND SWAP1 DUP2 OR SWAP1 SWAP2 SSTORE SWAP1 MLOAD DUP4 SWAP3 SWAP1 PUSH32 0xDDF252AD1BE2C89B69C2B068FC378DAA952BA7F163C4A11628F55A4DF523B3EF SWAP1 DUP3 SWAP1 LOG4 POP POP JUMP JUMPDEST PUSH1 0x0 DUP3 DUP2 MSTORE PUSH1 0x2 PUSH1 0x20 MSTORE PUSH1 0x40 SWAP1 KECCAK256 SLOAD PUSH1 0x1 PUSH1 0x1 PUSH1 0xA0 SHL SUB AND PUSH2 0x12AD JUMPI PUSH1 0x40 MLOAD PUSH3 0x461BCD PUSH1 0xE5 SHL DUP2 MSTORE PUSH1 0x20 PUSH1 0x4 DUP3 ADD MSTORE PUSH1 0x2C PUSH1 0x24 DUP3 ADD MSTORE PUSH32 0x4552433732314D657461646174613A2055524920736574206F66206E6F6E6578 PUSH1 0x44 DUP3 ADD MSTORE PUSH12 0x34B9BA32B73A103A37B5B2B7 PUSH1 0xA1 SHL PUSH1 0x64 DUP3 ADD MSTORE PUSH1 0x84 ADD PUSH2 0x426 JUMP JUMPDEST PUSH1 0x0 DUP3 DUP2 MSTORE PUSH1 0xD PUSH1 0x20 SWAP1 DUP2 MSTORE PUSH1 0x40 SWAP1 SWAP2 KECCAK256 DUP3 MLOAD PUSH2 0x55B SWAP3 DUP5 ADD SWAP1 PUSH2 0x1757 JUMP JUMPDEST PUSH1 0x0 PUSH1 0x1 PUSH1 0x1 PUSH1 0xE0 SHL SUB NOT DUP3 AND PUSH4 0x80AC58CD PUSH1 0xE0 SHL EQ DUP1 PUSH2 0x12FD JUMPI POP PUSH1 0x1 PUSH1 0x1 PUSH1 0xE0 SHL SUB NOT DUP3 AND PUSH4 0x5B5E139F PUSH1 0xE0 SHL EQ JUMPDEST DUP1 PUSH2 0x319 JUMPI POP PUSH4 0x1FFC9A7 PUSH1 0xE0 SHL PUSH1 0x1 PUSH1 0x1 PUSH1 0xE0 SHL SUB NOT DUP4 AND EQ PUSH2 0x319 JUMP JUMPDEST PUSH2 0x55B DUP4 DUP4 DUP4 PUSH2 0x1468 JUMP JUMPDEST PUSH2 0x1330 DUP2 PUSH2 0x1520 JUMP JUMPDEST PUSH1 0x0 DUP2 DUP2 MSTORE PUSH1 0xA PUSH1 0x20 MSTORE PUSH1 0x40 SWAP1 KECCAK256 DUP1 SLOAD PUSH2 0x1349 SWAP1 PUSH2 0x1B90 JUMP JUMPDEST ISZERO SWAP1 POP PUSH2 0xED2 JUMPI PUSH1 0x0 DUP2 DUP2 MSTORE PUSH1 0xA PUSH1 0x20 MSTORE PUSH1 0x40 DUP2 KECCAK256 PUSH2 0xED2 SWAP2 PUSH2 0x17D7 JUMP JUMPDEST PUSH1 0x0 PUSH1 0x1 PUSH1 0x1 PUSH1 0xA0 SHL SUB DUP5 AND EXTCODESIZE ISZERO PUSH2 0x145D JUMPI PUSH1 0x40 MLOAD PUSH4 0xA85BD01 PUSH1 0xE1 SHL DUP2 MSTORE PUSH1 0x1 PUSH1 0x1 PUSH1 0xA0 SHL SUB DUP6 AND SWAP1 PUSH4 0x150B7A02 SWAP1 PUSH2 0x13AB SWAP1 CALLER SWAP1 DUP10 SWAP1 DUP9 SWAP1 DUP9 SWAP1 PUSH1 0x4 ADD PUSH2 0x1D4E JUMP JUMPDEST PUSH1 0x20 PUSH1 0x40 MLOAD DUP1 DUP4 SUB DUP2 PUSH1 0x0 DUP8 GAS CALL SWAP3 POP POP POP DUP1 ISZERO PUSH2 0x13E6 JUMPI POP PUSH1 0x40 DUP1 MLOAD PUSH1 0x1F RETURNDATASIZE SWAP1 DUP2 ADD PUSH1 0x1F NOT AND DUP3 ADD SWAP1 SWAP3 MSTORE PUSH2 0x13E3 SWAP2 DUP2 ADD SWAP1 PUSH2 0x1D8B JUMP JUMPDEST PUSH1 0x1 JUMPDEST PUSH2 0x1443 JUMPI RETURNDATASIZE DUP1 DUP1 ISZERO PUSH2 0x1414 JUMPI PUSH1 0x40 MLOAD SWAP2 POP PUSH1 0x1F NOT PUSH1 0x3F RETURNDATASIZE ADD AND DUP3 ADD PUSH1 0x40 MSTORE RETURNDATASIZE DUP3 MSTORE RETURNDATASIZE PUSH1 0x0 PUSH1 0x20 DUP5 ADD RETURNDATACOPY PUSH2 0x1419 JUMP JUMPDEST PUSH1 0x60 SWAP2 POP JUMPDEST POP DUP1 MLOAD PUSH1 0x0 SUB PUSH2 0x143B JUMPI PUSH1 0x40 MLOAD PUSH3 0x461BCD PUSH1 0xE5 SHL DUP2 MSTORE PUSH1 0x4 ADD PUSH2 0x426 SWAP1 PUSH2 0x1CA5 JUMP JUMPDEST DUP1 MLOAD DUP2 PUSH1 0x20 ADD REVERT JUMPDEST PUSH1 0x1 PUSH1 0x1 PUSH1 0xE0 SHL SUB NOT AND PUSH4 0xA85BD01 PUSH1 0xE1 SHL EQ SWAP1 POP PUSH2 0xD1A JUMP JUMPDEST POP PUSH1 0x1 SWAP5 SWAP4 POP POP POP POP JUMP JUMPDEST PUSH1 0x1 PUSH1 0x1 PUSH1 0xA0 SHL SUB DUP4 AND PUSH2 0x14C3 JUMPI PUSH2 0x14BE DUP2 PUSH1 0x8 DUP1 SLOAD PUSH1 0x0 DUP4 DUP2 MSTORE PUSH1 0x9 PUSH1 0x20 MSTORE PUSH1 0x40 DUP2 KECCAK256 DUP3 SWAP1 SSTORE PUSH1 0x1 DUP3 ADD DUP4 SSTORE SWAP2 SWAP1 SWAP2 MSTORE PUSH32 0xF3F7A9FE364FAAB93B216DA50A3214154F22A0A2B415B23A84C8169E8B636EE3 ADD SSTORE JUMP JUMPDEST PUSH2 0x14E6 JUMP JUMPDEST DUP2 PUSH1 0x1 PUSH1 0x1 PUSH1 0xA0 SHL SUB AND DUP4 PUSH1 0x1 PUSH1 0x1 PUSH1 0xA0 SHL SUB AND EQ PUSH2 0x14E6 JUMPI PUSH2 0x14E6 DUP4 DUP3 PUSH2 0x15C7 JUMP JUMPDEST PUSH1 0x1 PUSH1 0x1 PUSH1 0xA0 SHL SUB DUP3 AND PUSH2 0x14FD JUMPI PUSH2 0x55B DUP2 PUSH2 0x1664 JUMP JUMPDEST DUP3 PUSH1 0x1 PUSH1 0x1 PUSH1 0xA0 SHL SUB AND DUP3 PUSH1 0x1 PUSH1 0x1 PUSH1 0xA0 SHL SUB AND EQ PUSH2 0x55B JUMPI PUSH2 0x55B DUP3 DUP3 PUSH2 0x1713 JUMP JUMPDEST PUSH1 0x0 PUSH2 0x152B DUP3 PUSH2 0x80D JUMP JUMPDEST SWAP1 POP PUSH2 0x1539 DUP2 PUSH1 0x0 DUP5 PUSH2 0x131C JUMP JUMPDEST PUSH2 0x1544 PUSH1 0x0 DUP4 PUSH2 0xBBD JUMP JUMPDEST PUSH1 0x1 PUSH1 0x1 PUSH1 0xA0 SHL SUB DUP2 AND PUSH1 0x0 SWAP1 DUP2 MSTORE PUSH1 0x3 PUSH1 0x20 MSTORE PUSH1 0x40 DUP2 KECCAK256 DUP1 SLOAD PUSH1 0x1 SWAP3 SWAP1 PUSH2 0x156D SWAP1 DUP5 SWAP1 PUSH2 0x1C76 JUMP JUMPDEST SWAP1 SWAP2 SSTORE POP POP PUSH1 0x0 DUP3 DUP2 MSTORE PUSH1 0x2 PUSH1 0x20 MSTORE PUSH1 0x40 DUP1 DUP3 KECCAK256 DUP1 SLOAD PUSH1 0x1 PUSH1 0x1 PUSH1 0xA0 SHL SUB NOT AND SWAP1 SSTORE MLOAD DUP4 SWAP2 SWAP1 PUSH1 0x1 PUSH1 0x1 PUSH1 0xA0 SHL SUB DUP5 AND SWAP1 PUSH32 0xDDF252AD1BE2C89B69C2B068FC378DAA952BA7F163C4A11628F55A4DF523B3EF SWAP1 DUP4 SWAP1 LOG4 POP POP JUMP JUMPDEST PUSH1 0x0 PUSH1 0x1 PUSH2 0x15D4 DUP5 PUSH2 0x884 JUMP JUMPDEST PUSH2 0x15DE SWAP2 SWAP1 PUSH2 0x1C76 JUMP JUMPDEST PUSH1 0x0 DUP4 DUP2 MSTORE PUSH1 0x7 PUSH1 0x20 MSTORE PUSH1 0x40 SWAP1 KECCAK256 SLOAD SWAP1 SWAP2 POP DUP1 DUP3 EQ PUSH2 0x1631 JUMPI PUSH1 0x1 PUSH1 0x1 PUSH1 0xA0 SHL SUB DUP5 AND PUSH1 0x0 SWAP1 DUP2 MSTORE PUSH1 0x6 PUSH1 0x20 SWAP1 DUP2 MSTORE PUSH1 0x40 DUP1 DUP4 KECCAK256 DUP6 DUP5 MSTORE DUP3 MSTORE DUP1 DUP4 KECCAK256 SLOAD DUP5 DUP5 MSTORE DUP2 DUP5 KECCAK256 DUP2 SWAP1 SSTORE DUP4 MSTORE PUSH1 0x7 SWAP1 SWAP2 MSTORE SWAP1 KECCAK256 DUP2 SWAP1 SSTORE JUMPDEST POP PUSH1 0x0 SWAP2 DUP3 MSTORE PUSH1 0x7 PUSH1 0x20 SWAP1 DUP2 MSTORE PUSH1 0x40 DUP1 DUP5 KECCAK256 DUP5 SWAP1 SSTORE PUSH1 0x1 PUSH1 0x1 PUSH1 0xA0 SHL SUB SWAP1 SWAP5 AND DUP4 MSTORE PUSH1 0x6 DUP2 MSTORE DUP4 DUP4 KECCAK256 SWAP2 DUP4 MSTORE MSTORE SWAP1 DUP2 KECCAK256 SSTORE JUMP JUMPDEST PUSH1 0x8 SLOAD PUSH1 0x0 SWAP1 PUSH2 0x1676 SWAP1 PUSH1 0x1 SWAP1 PUSH2 0x1C76 JUMP JUMPDEST PUSH1 0x0 DUP4 DUP2 MSTORE PUSH1 0x9 PUSH1 0x20 MSTORE PUSH1 0x40 DUP2 KECCAK256 SLOAD PUSH1 0x8 DUP1 SLOAD SWAP4 SWAP5 POP SWAP1 SWAP3 DUP5 SWAP1 DUP2 LT PUSH2 0x169E JUMPI PUSH2 0x169E PUSH2 0x1C1B JUMP JUMPDEST SWAP1 PUSH1 0x0 MSTORE PUSH1 0x20 PUSH1 0x0 KECCAK256 ADD SLOAD SWAP1 POP DUP1 PUSH1 0x8 DUP4 DUP2 SLOAD DUP2 LT PUSH2 0x16BF JUMPI PUSH2 0x16BF PUSH2 0x1C1B JUMP JUMPDEST PUSH1 0x0 SWAP2 DUP3 MSTORE PUSH1 0x20 DUP1 DUP4 KECCAK256 SWAP1 SWAP2 ADD SWAP3 SWAP1 SWAP3 SSTORE DUP3 DUP2 MSTORE PUSH1 0x9 SWAP1 SWAP2 MSTORE PUSH1 0x40 DUP1 DUP3 KECCAK256 DUP5 SWAP1 SSTORE DUP6 DUP3 MSTORE DUP2 KECCAK256 SSTORE PUSH1 0x8 DUP1 SLOAD DUP1 PUSH2 0x16F7 JUMPI PUSH2 0x16F7 PUSH2 0x1DA8 JUMP JUMPDEST PUSH1 0x1 SWAP1 SUB DUP2 DUP2 SWAP1 PUSH1 0x0 MSTORE PUSH1 0x20 PUSH1 0x0 KECCAK256 ADD PUSH1 0x0 SWAP1 SSTORE SWAP1 SSTORE POP POP POP POP JUMP JUMPDEST PUSH1 0x0 PUSH2 0x171E DUP4 PUSH2 0x884 JUMP JUMPDEST PUSH1 0x1 PUSH1 0x1 PUSH1 0xA0 SHL SUB SWAP1 SWAP4 AND PUSH1 0x0 SWAP1 DUP2 MSTORE PUSH1 0x6 PUSH1 0x20 SWAP1 DUP2 MSTORE PUSH1 0x40 DUP1 DUP4 KECCAK256 DUP7 DUP5 MSTORE DUP3 MSTORE DUP1 DUP4 KECCAK256 DUP6 SWAP1 SSTORE SWAP4 DUP3 MSTORE PUSH1 0x7 SWAP1 MSTORE SWAP2 SWAP1 SWAP2 KECCAK256 SWAP2 SWAP1 SWAP2 SSTORE POP JUMP JUMPDEST DUP3 DUP1 SLOAD PUSH2 0x1763 SWAP1 PUSH2 0x1B90 JUMP JUMPDEST SWAP1 PUSH1 0x0 MSTORE PUSH1 0x20 PUSH1 0x0 KECCAK256 SWAP1 PUSH1 0x1F ADD PUSH1 0x20 SWAP1 DIV DUP2 ADD SWAP3 DUP3 PUSH2 0x1785 JUMPI PUSH1 0x0 DUP6 SSTORE PUSH2 0x17CB JUMP JUMPDEST DUP3 PUSH1 0x1F LT PUSH2 0x179E JUMPI DUP1 MLOAD PUSH1 0xFF NOT AND DUP4 DUP1 ADD OR DUP6 SSTORE PUSH2 0x17CB JUMP JUMPDEST DUP3 DUP1 ADD PUSH1 0x1 ADD DUP6 SSTORE DUP3 ISZERO PUSH2 0x17CB JUMPI SWAP2 DUP3 ADD JUMPDEST DUP3 DUP2 GT ISZERO PUSH2 0x17CB JUMPI DUP3 MLOAD DUP3 SSTORE SWAP2 PUSH1 0x20 ADD SWAP2 SWAP1 PUSH1 0x1 ADD SWAP1 PUSH2 0x17B0 JUMP JUMPDEST POP PUSH2 0x619 SWAP3 SWAP2 POP PUSH2 0x180D JUMP JUMPDEST POP DUP1 SLOAD PUSH2 0x17E3 SWAP1 PUSH2 0x1B90 JUMP JUMPDEST PUSH1 0x0 DUP3 SSTORE DUP1 PUSH1 0x1F LT PUSH2 0x17F3 JUMPI POP POP JUMP JUMPDEST PUSH1 0x1F ADD PUSH1 0x20 SWAP1 DIV SWAP1 PUSH1 0x0 MSTORE PUSH1 0x20 PUSH1 0x0 KECCAK256 SWAP1 DUP2 ADD SWAP1 PUSH2 0xED2 SWAP2 SWAP1 JUMPDEST JUMPDEST DUP1 DUP3 GT ISZERO PUSH2 0x619 JUMPI PUSH1 0x0 DUP2 SSTORE PUSH1 0x1 ADD PUSH2 0x180E JUMP JUMPDEST PUSH1 0x1 PUSH1 0x1 PUSH1 0xE0 SHL SUB NOT DUP2 AND DUP2 EQ PUSH2 0xED2 JUMPI PUSH1 0x0 DUP1 REVERT JUMPDEST PUSH1 0x0 PUSH1 0x20 DUP3 DUP5 SUB SLT ISZERO PUSH2 0x184A JUMPI PUSH1 0x0 DUP1 REVERT JUMPDEST DUP2 CALLDATALOAD PUSH2 0xB91 DUP2 PUSH2 0x1822 JUMP JUMPDEST PUSH1 0x0 JUMPDEST DUP4 DUP2 LT ISZERO PUSH2 0x1870 JUMPI DUP2 DUP2 ADD MLOAD DUP4 DUP3 ADD MSTORE PUSH1 0x20 ADD PUSH2 0x1858 JUMP JUMPDEST DUP4 DUP2 GT ISZERO PUSH2 0x9D9 JUMPI POP POP PUSH1 0x0 SWAP2 ADD MSTORE JUMP JUMPDEST PUSH1 0x0 DUP2 MLOAD DUP1 DUP5 MSTORE PUSH2 0x1899 DUP2 PUSH1 0x20 DUP7 ADD PUSH1 0x20 DUP7 ADD PUSH2 0x1855 JUMP JUMPDEST PUSH1 0x1F ADD PUSH1 0x1F NOT AND SWAP3 SWAP1 SWAP3 ADD PUSH1 0x20 ADD SWAP3 SWAP2 POP POP JUMP JUMPDEST PUSH1 0x20 DUP2 MSTORE PUSH1 0x0 PUSH2 0xB91 PUSH1 0x20 DUP4 ADD DUP5 PUSH2 0x1881 JUMP JUMPDEST PUSH1 0x0 PUSH1 0x20 DUP3 DUP5 SUB SLT ISZERO PUSH2 0x18D2 JUMPI PUSH1 0x0 DUP1 REVERT JUMPDEST POP CALLDATALOAD SWAP2 SWAP1 POP JUMP JUMPDEST DUP1 CALLDATALOAD PUSH1 0x1 PUSH1 0x1 PUSH1 0xA0 SHL SUB DUP2 AND DUP2 EQ PUSH2 0x18F0 JUMPI PUSH1 0x0 DUP1 REVERT JUMPDEST SWAP2 SWAP1 POP JUMP JUMPDEST PUSH1 0x0 DUP1 PUSH1 0x40 DUP4 DUP6 SUB SLT ISZERO PUSH2 0x1908 JUMPI PUSH1 0x0 DUP1 REVERT JUMPDEST PUSH2 0x1911 DUP4 PUSH2 0x18D9 JUMP JUMPDEST SWAP5 PUSH1 0x20 SWAP4 SWAP1 SWAP4 ADD CALLDATALOAD SWAP4 POP POP POP JUMP JUMPDEST PUSH1 0x0 DUP1 PUSH1 0x0 PUSH1 0x60 DUP5 DUP7 SUB SLT ISZERO PUSH2 0x1934 JUMPI PUSH1 0x0 DUP1 REVERT JUMPDEST PUSH2 0x193D DUP5 PUSH2 0x18D9 JUMP JUMPDEST SWAP3 POP PUSH2 0x194B PUSH1 0x20 DUP6 ADD PUSH2 0x18D9 JUMP JUMPDEST SWAP2 POP PUSH1 0x40 DUP5 ADD CALLDATALOAD SWAP1 POP SWAP3 POP SWAP3 POP SWAP3 JUMP JUMPDEST PUSH4 0x4E487B71 PUSH1 0xE0 SHL PUSH1 0x0 MSTORE PUSH1 0x41 PUSH1 0x4 MSTORE PUSH1 0x24 PUSH1 0x0 REVERT JUMPDEST PUSH1 0x0 PUSH8 0xFFFFFFFFFFFFFFFF DUP1 DUP5 GT ISZERO PUSH2 0x198C JUMPI PUSH2 0x198C PUSH2 0x195B JUMP JUMPDEST PUSH1 0x40 MLOAD PUSH1 0x1F DUP6 ADD PUSH1 0x1F NOT SWAP1 DUP2 AND PUSH1 0x3F ADD AND DUP2 ADD SWAP1 DUP3 DUP3 GT DUP2 DUP4 LT OR ISZERO PUSH2 0x19B4 JUMPI PUSH2 0x19B4 PUSH2 0x195B JUMP JUMPDEST DUP2 PUSH1 0x40 MSTORE DUP1 SWAP4 POP DUP6 DUP2 MSTORE DUP7 DUP7 DUP7 ADD GT ISZERO PUSH2 0x19CD JUMPI PUSH1 0x0 DUP1 REVERT JUMPDEST DUP6 DUP6 PUSH1 0x20 DUP4 ADD CALLDATACOPY PUSH1 0x0 PUSH1 0x20 DUP8 DUP4 ADD ADD MSTORE POP POP POP SWAP4 SWAP3 POP POP POP JUMP JUMPDEST PUSH1 0x0 DUP3 PUSH1 0x1F DUP4 ADD SLT PUSH2 0x19F8 JUMPI PUSH1 0x0 DUP1 REVERT JUMPDEST PUSH2 0xB91 DUP4 DUP4 CALLDATALOAD PUSH1 0x20 DUP6 ADD PUSH2 0x1971 JUMP JUMPDEST PUSH1 0x0 PUSH1 0x20 DUP3 DUP5 SUB SLT ISZERO PUSH2 0x1A19 JUMPI PUSH1 0x0 DUP1 REVERT JUMPDEST DUP2 CALLDATALOAD PUSH8 0xFFFFFFFFFFFFFFFF DUP2 GT ISZERO PUSH2 0x1A30 JUMPI PUSH1 0x0 DUP1 REVERT JUMPDEST PUSH2 0xD1A DUP5 DUP3 DUP6 ADD PUSH2 0x19E7 JUMP JUMPDEST PUSH1 0x0 PUSH1 0x20 DUP3 DUP5 SUB SLT ISZERO PUSH2 0x1A4E JUMPI PUSH1 0x0 DUP1 REVERT JUMPDEST PUSH2 0xB91 DUP3 PUSH2 0x18D9 JUMP JUMPDEST PUSH1 0x0 DUP1 PUSH1 0x40 DUP4 DUP6 SUB SLT ISZERO PUSH2 0x1A6A JUMPI PUSH1 0x0 DUP1 REVERT JUMPDEST PUSH2 0x1A73 DUP4 PUSH2 0x18D9 JUMP JUMPDEST SWAP2 POP PUSH1 0x20 DUP4 ADD CALLDATALOAD DUP1 ISZERO ISZERO DUP2 EQ PUSH2 0x1A88 JUMPI PUSH1 0x0 DUP1 REVERT JUMPDEST DUP1 SWAP2 POP POP SWAP3 POP SWAP3 SWAP1 POP JUMP JUMPDEST PUSH1 0x0 DUP1 PUSH1 0x0 DUP1 PUSH1 0x80 DUP6 DUP8 SUB SLT ISZERO PUSH2 0x1AA9 JUMPI PUSH1 0x0 DUP1 REVERT JUMPDEST PUSH2 0x1AB2 DUP6 PUSH2 0x18D9 JUMP JUMPDEST SWAP4 POP PUSH2 0x1AC0 PUSH1 0x20 DUP7 ADD PUSH2 0x18D9 JUMP JUMPDEST SWAP3 POP PUSH1 0x40 DUP6 ADD CALLDATALOAD SWAP2 POP PUSH1 0x60 DUP6 ADD CALLDATALOAD PUSH8 0xFFFFFFFFFFFFFFFF DUP2 GT ISZERO PUSH2 0x1AE3 JUMPI PUSH1 0x0 DUP1 REVERT JUMPDEST DUP6 ADD PUSH1 0x1F DUP2 ADD DUP8 SGT PUSH2 0x1AF4 JUMPI PUSH1 0x0 DUP1 REVERT JUMPDEST PUSH2 0x1B03 DUP8 DUP3 CALLDATALOAD PUSH1 0x20 DUP5 ADD PUSH2 0x1971 JUMP JUMPDEST SWAP2 POP POP SWAP3 SWAP6 SWAP2 SWAP5 POP SWAP3 POP JUMP JUMPDEST PUSH1 0x0 DUP1 PUSH1 0x40 DUP4 DUP6 SUB SLT ISZERO PUSH2 0x1B22 JUMPI PUSH1 0x0 DUP1 REVERT JUMPDEST PUSH2 0x1B2B DUP4 PUSH2 0x18D9 JUMP JUMPDEST SWAP2 POP PUSH2 0x1B39 PUSH1 0x20 DUP5 ADD PUSH2 0x18D9 JUMP JUMPDEST SWAP1 POP SWAP3 POP SWAP3 SWAP1 POP JUMP JUMPDEST PUSH1 0x0 DUP1 PUSH1 0x40 DUP4 DUP6 SUB SLT ISZERO PUSH2 0x1B55 JUMPI PUSH1 0x0 DUP1 REVERT JUMPDEST PUSH2 0x1B5E DUP4 PUSH2 0x18D9 JUMP JUMPDEST SWAP2 POP PUSH1 0x20 DUP4 ADD CALLDATALOAD PUSH8 0xFFFFFFFFFFFFFFFF DUP2 GT ISZERO PUSH2 0x1B7A JUMPI PUSH1 0x0 DUP1 REVERT JUMPDEST PUSH2 0x1B86 DUP6 DUP3 DUP7 ADD PUSH2 0x19E7 JUMP JUMPDEST SWAP2 POP POP SWAP3 POP SWAP3 SWAP1 POP JUMP JUMPDEST PUSH1 0x1 DUP2 DUP2 SHR SWAP1 DUP3 AND DUP1 PUSH2 0x1BA4 JUMPI PUSH1 0x7F DUP3 AND SWAP2 POP JUMPDEST PUSH1 0x20 DUP3 LT DUP2 SUB PUSH2 0x1BC4 JUMPI PUSH4 0x4E487B71 PUSH1 0xE0 SHL PUSH1 0x0 MSTORE PUSH1 0x22 PUSH1 0x4 MSTORE PUSH1 0x24 PUSH1 0x0 REVERT JUMPDEST POP SWAP2 SWAP1 POP JUMP JUMPDEST PUSH1 0x20 DUP1 DUP3 MSTORE PUSH1 0x31 SWAP1 DUP3 ADD MSTORE PUSH32 0x4552433732313A207472616E736665722063616C6C6572206973206E6F74206F PUSH1 0x40 DUP3 ADD MSTORE PUSH17 0x1DDB995C881B9BDC88185C1C1C9BDD9959 PUSH1 0x7A SHL PUSH1 0x60 DUP3 ADD MSTORE PUSH1 0x80 ADD SWAP1 JUMP JUMPDEST PUSH4 0x4E487B71 PUSH1 0xE0 SHL PUSH1 0x0 MSTORE PUSH1 0x32 PUSH1 0x4 MSTORE PUSH1 0x24 PUSH1 0x0 REVERT JUMPDEST PUSH1 0x0 DUP4 MLOAD PUSH2 0x1C43 DUP2 DUP5 PUSH1 0x20 DUP9 ADD PUSH2 0x1855 JUMP JUMPDEST DUP4 MLOAD SWAP1 DUP4 ADD SWAP1 PUSH2 0x1C57 DUP2 DUP4 PUSH1 0x20 DUP9 ADD PUSH2 0x1855 JUMP JUMPDEST ADD SWAP5 SWAP4 POP POP POP POP JUMP JUMPDEST PUSH4 0x4E487B71 PUSH1 0xE0 SHL PUSH1 0x0 MSTORE PUSH1 0x11 PUSH1 0x4 MSTORE PUSH1 0x24 PUSH1 0x0 REVERT JUMPDEST PUSH1 0x0 DUP3 DUP3 LT ISZERO PUSH2 0x1C88 JUMPI PUSH2 0x1C88 PUSH2 0x1C60 JUMP JUMPDEST POP SUB SWAP1 JUMP JUMPDEST PUSH1 0x0 DUP3 NOT DUP3 GT ISZERO PUSH2 0x1CA0 JUMPI PUSH2 0x1CA0 PUSH2 0x1C60 JUMP JUMPDEST POP ADD SWAP1 JUMP JUMPDEST PUSH1 0x20 DUP1 DUP3 MSTORE PUSH1 0x32 SWAP1 DUP3 ADD MSTORE PUSH32 0x4552433732313A207472616E7366657220746F206E6F6E204552433732315265 PUSH1 0x40 DUP3 ADD MSTORE PUSH18 0x31B2B4BB32B91034B6B83632B6B2B73A32B9 PUSH1 0x71 SHL PUSH1 0x60 DUP3 ADD MSTORE PUSH1 0x80 ADD SWAP1 JUMP JUMPDEST PUSH1 0x0 PUSH1 0x1 DUP3 ADD PUSH2 0x1D09 JUMPI PUSH2 0x1D09 PUSH2 0x1C60 JUMP JUMPDEST POP PUSH1 0x1 ADD SWAP1 JUMP JUMPDEST PUSH4 0x4E487B71 PUSH1 0xE0 SHL PUSH1 0x0 MSTORE PUSH1 0x12 PUSH1 0x4 MSTORE PUSH1 0x24 PUSH1 0x0 REVERT JUMPDEST PUSH1 0x0 DUP3 PUSH2 0x1D35 JUMPI PUSH2 0x1D35 PUSH2 0x1D10 JUMP JUMPDEST POP DIV SWAP1 JUMP JUMPDEST PUSH1 0x0 DUP3 PUSH2 0x1D49 JUMPI PUSH2 0x1D49 PUSH2 0x1D10 JUMP JUMPDEST POP MOD SWAP1 JUMP JUMPDEST PUSH1 0x1 PUSH1 0x1 PUSH1 0xA0 SHL SUB DUP6 DUP2 AND DUP3 MSTORE DUP5 AND PUSH1 0x20 DUP3 ADD MSTORE PUSH1 0x40 DUP2 ADD DUP4 SWAP1 MSTORE PUSH1 0x80 PUSH1 0x60 DUP3 ADD DUP2 SWAP1 MSTORE PUSH1 0x0 SWAP1 PUSH2 0x1D81 SWAP1 DUP4 ADD DUP5 PUSH2 0x1881 JUMP JUMPDEST SWAP7 SWAP6 POP POP POP POP POP POP JUMP JUMPDEST PUSH1 0x0 PUSH1 0x20 DUP3 DUP5 SUB SLT ISZERO PUSH2 0x1D9D JUMPI PUSH1 0x0 DUP1 REVERT JUMPDEST DUP2 MLOAD PUSH2 0xB91 DUP2 PUSH2 0x1822 JUMP JUMPDEST PUSH4 0x4E487B71 PUSH1 0xE0 SHL PUSH1 0x0 MSTORE PUSH1 0x31 PUSH1 0x4 MSTORE PUSH1 0x24 PUSH1 0x0 REVERT INVALID LOG2 PUSH5 0x6970667358 0x22 SLT KECCAK256 PUSH32 0x54B0100997E0C8D025FD834BA3F100FDA116BE8954BEC8BA6D34BFBA61EE0964 PUSH20 0x6F6C634300080D00330000000000000000000000 \",\r\n";
        String h = "\t\"sourceMap\": \"394:2998:14:-:0;;;711:123;;;;;;;;;-1:-1:-1;1390:113:1;;;;;;;;;;;-1:-1:-1;;;1390:113:1;;;;;;;;;;;;;;;;;;-1:-1:-1;;;1390:113:1;;;;1456:13;;1390:113;;;1456:13;;-1:-1:-1;;1456:13:1;:::i;:::-;-1:-1:-1;1479:17:1;;;;:7;;:17;;;;;:::i;:::-;-1:-1:-1;;764:5:14::1;:18:::0;;-1:-1:-1;;;;;;764:18:14::1;772:10;764:18;::::0;;-1:-1:-1;394:2998:14;;;;;;;;;:::i;:::-;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;-1:-1:-1;394:2998:14;;;-1:-1:-1;394:2998:14;:::i;:::-;;;:::o;:::-;;;;;;;;;;;;;;;14:380:15;93:1;89:12;;;;136;;;157:61;;211:4;203:6;199:17;189:27;;157:61;264:2;256:6;253:14;233:18;230:38;227:161;;310:10;305:3;301:20;298:1;291:31;345:4;342:1;335:15;373:4;370:1;363:15;227:161;;14:380;;;:::o;:::-;394:2998:14;;;;;;\"\r\n"
                + "}";
        a.append(b);
        a.append(c);
        a.append(d);
        a.append(e);
        a.append(f);
        a.append(g);
        a.append(h);
        return a.toString();
    }
}
