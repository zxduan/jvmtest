package cn.duanzx.jvm.classloader;

import java.lang.reflect.Method;

public class ClassLoaderTest {
    public static void main(String[] args)throws Exception{
        MyClassLoader myClassLoader = new MyClassLoader();
        Class<?> aClass = myClassLoader.loadClass("cn.duanzx.jvm.classloader.HelloWorld");
        System.out.println(aClass.getClassLoader());
        Object helloworld = aClass.newInstance();
        System.out.println(helloworld);
        Method welcomMethod = aClass.getMethod("welcome");
        String result = (String) welcomMethod.invoke(helloworld);
        System.out.println("Result : "+ result);

    }
}
