package com.evil;

public class EvilMethodClass {
    static {
        System.load("/root/libEvilMethodClass.so");
    }

    public static native String evilMethod(String name);
}
