package org.train;

import java.io.IOException;
import java.math.BigInteger;
import java.util.concurrent.ExecutionException;

import org.web3j.abi.datatypes.Utf8String;
import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.example.generated.Greeter;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.Contract;
import org.web3j.tx.ManagedTransaction;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.tx.gas.DefaultGasProvider;

public class Greeting {
    public static void main(String[] args) throws IOException, CipherException, ExecutionException, InterruptedException, Exception {
        //String rinkebyKey = args[0];
        //String walletFilePassword = args[1];

        //String rinkebyUrl = "https://rinkeby.infura.io/" + rinkebyKey;
        Web3j web3j = Web3j.build(new HttpService("http://localhost:7545"));

        //String walletId = "4a50789f96b4121a14974d607d6a0f5384ec4af4";
        //String walletSource = "/Users/j.train/testnet-keystore/UTC--2017-10-12T11-10-59.66000000Z--" + walletId + "" +
        //        ".json";
        Credentials credentials = Credentials.create("85122ec2d941aa9861be69a8553acba47c828e1449977a10e9366777971e7d12");
        ContractGasProvider contractGasProvider =  new DefaultGasProvider() ;
                
                
        Greeter contract = Greeter.deploy(web3j, credentials, contractGasProvider, "Teste").send();
/**
 * .deploy(
                web3j, credentials, ManagedTransaction.GAS_PRICE, Contract.GAS_LIMIT,
                BigInteger.ZERO, new Utf8String("Greetings to the blockchain world!")).get();
 */
        String greeting = contract.greet().send();
        System.out.println(greeting);

//        TransactionReceipt transactionReceipt =
//                contract.newGreeting(new Utf8String("Guten Tag, und willkommen!")).get();
//        System.out.println(transactionReceipt.getTransactionHash());
//
//        Utf8String newGreeting = contract.greet().get();
//        System.out.println(newGreeting.getValue());
    }
}
