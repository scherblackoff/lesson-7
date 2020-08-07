package java3;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.TreeMap;

public class Main {

    public static void main(String[] args) {
        start(TestTest.class);
    }

    public static void start(Class<?> className){
        final int maxPriority = 10;
        final int minPriority = 1;
        int methodsBeforeSuit = 0;
        int methodsAfterSuit = 0;
        Map<Integer, Method> methodMap = new TreeMap<>();

        for(Method method : className.getDeclaredMethods()){
            if(methodsAfterSuit > 1 || methodsBeforeSuit > 1){
                throw new RuntimeException();
            }
            if (method.getAnnotation(BeforeSuit.class) != null) {
                methodsBeforeSuit++;
                methodMap.put(minPriority - 1, method);
            }
            if (method.getAnnotation(AfterSuit.class) != null) {
                methodsAfterSuit++;
                methodMap.put(maxPriority + 1, method);
            }
            if (method.getAnnotation(Test.class) != null) {
                Test test = method.getAnnotation(Test.class);
                methodMap.put(test.priority(), method);
            }
        }
        try {
            Object testCase = className.newInstance();
            for (Integer key : methodMap.keySet()) {
                methodMap.get(key).invoke(testCase);
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        }
    }
