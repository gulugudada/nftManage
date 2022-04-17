package com.nftmanage.nftmanage.utils;

import org.junit.Test;

public class ETHUtils {

    @Test
    public void genAbi(){
        String abi = "[\n" +
                "\t{\n" +
                "\t\t\"constant\": false,\n" +
                "\t\t\"inputs\": [\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"name\": \"amount\",\n" +
                "\t\t\t\t\"type\": \"uint256\"\n" +
                "\t\t\t}\n" +
                "\t\t],\n" +
                "\t\t\"name\": \"withdraw\",\n" +
                "\t\t\"outputs\": [],\n" +
                "\t\t\"payable\": false,\n" +
                "\t\t\"stateMutability\": \"nonpayable\",\n" +
                "\t\t\"type\": \"function\"\n" +
                "\t},\n" +
                "\t{\n" +
                "\t\t\"payable\": true,,\n" +
                "\t\t\"stateMutability\": \"payable\",\n" +
                "\t\t\"type\": \"fallback\"\n" +
                "\t}\n" +
                "]";
        String bin = "{\n" +
                "\t\"linkReferences\": {},\n" +
                "\t\"object\": \"608060405234801561001057600080fd5b5060f78061001f6000396000f300608060405260043610603f576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff1680632e1a7d4d146041575b005b348015604c57600080fd5b50606960048036038101908080359060200190929190505050606b565b005b683635c9adc5dea000008111151515608257600080fd5b3373ffffffffffffffffffffffffffffffffffffffff166108fc829081150290604051600060405180830381858888f1935050505015801560c7573d6000803e3d6000fd5b50505600a165627a7a723058202bb5be9eae163caf1d06733e5ba29e5b2a51e2290708d5d287635637bd249a920029\",\n" +
                "\t\"opcodes\": \"PUSH1 0x80 PUSH1 0x40 MSTORE CALLVALUE DUP1 ISZERO PUSH2 0x10 JUMPI PUSH1 0x0 DUP1 REVERT JUMPDEST POP PUSH1 0xF7 DUP1 PUSH2 0x1F PUSH1 0x0 CODECOPY PUSH1 0x0 RETURN STOP PUSH1 0x80 PUSH1 0x40 MSTORE PUSH1 0x4 CALLDATASIZE LT PUSH1 0x3F JUMPI PUSH1 0x0 CALLDATALOAD PUSH29 0x100000000000000000000000000000000000000000000000000000000 SWAP1 DIV PUSH4 0xFFFFFFFF AND DUP1 PUSH4 0x2E1A7D4D EQ PUSH1 0x41 JUMPI JUMPDEST STOP JUMPDEST CALLVALUE DUP1 ISZERO PUSH1 0x4C JUMPI PUSH1 0x0 DUP1 REVERT JUMPDEST POP PUSH1 0x69 PUSH1 0x4 DUP1 CALLDATASIZE SUB DUP2 ADD SWAP1 DUP1 DUP1 CALLDATALOAD SWAP1 PUSH1 0x20 ADD SWAP1 SWAP3 SWAP2 SWAP1 POP POP POP PUSH1 0x6B JUMP JUMPDEST STOP JUMPDEST PUSH9 0x3635C9ADC5DEA00000 DUP2 GT ISZERO ISZERO ISZERO PUSH1 0x82 JUMPI PUSH1 0x0 DUP1 REVERT JUMPDEST CALLER PUSH20 0xFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF AND PUSH2 0x8FC DUP3 SWAP1 DUP2 ISZERO MUL SWAP1 PUSH1 0x40 MLOAD PUSH1 0x0 PUSH1 0x40 MLOAD DUP1 DUP4 SUB DUP2 DUP6 DUP9 DUP9 CALL SWAP4 POP POP POP POP ISZERO DUP1 ISZERO PUSH1 0xC7 JUMPI RETURNDATASIZE PUSH1 0x0 DUP1 RETURNDATACOPY RETURNDATASIZE PUSH1 0x0 REVERT JUMPDEST POP POP JUMP STOP LOG1 PUSH6 0x627A7A723058 KECCAK256 0x2b 0xb5 0xbe SWAP15 0xae AND EXTCODECOPY 0xaf SAR MOD PUSH20 0x3E5BA29E5B2A51E2290708D5D287635637BD249A SWAP3 STOP 0x29 \",\n" +
                "\t\"sourceMap\": \"28:197:0:-;;;;8:9:-1;5:2;;;30:1;27;20:12;5:2;28:197:0;;;;;;;\"\n" +
                "}";
        String abiFileName = "contract/Faucet.abi";
        String binFileName = "contract/Faucet.bin";
        SolidityUtils.generateABIAndBIN(abi,bin,abiFileName,binFileName);
    }

    @Test
    public void generateJavaFile(){
        String abiFile = "src/main/resources/contract/NFTMarket.abi";
        String binFile = "src/main/resources/contract/NFTMarket.bin";
        String generateFile = "src/main/java/com/nftmanage/nftmanage/solidity/";
        SolidityUtils.generateClass(abiFile,binFile,generateFile);
    }
}
