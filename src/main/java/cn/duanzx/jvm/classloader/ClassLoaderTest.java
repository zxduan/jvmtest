package cn.duanzx.jvm.classloader;

import java.lang.reflect.Method;
/**
 * Class.forName(className)方法，其实调用的方法是Class.forName(className,true,classloader);注意看第2个boolean参数，
 * 它表示的意思，在loadClass后必须初始化。比较下我们前面准备jvm加载类的知识，我们可以清晰的看到在执行过此方法后，
 * 目标对象的 static块代码已经被执行，static参数也已经被初始化。
 *
 *  再看ClassLoader.loadClass(className)方法，其实他调用的方法是ClassLoader.loadClass(className,false);还是注意看第2个 boolean参数，
 *  该参数表示目标对象被装载后不进行链接，这就意味这不会去执行该类静态块中间的内容。因此2者的区别就显而易见了。
 *
 * */

public class ClassLoaderTest {
    public static void main(String[] args)throws Exception{
        MyClassLoader myClassLoader = new MyClassLoader();
//        Class<?> aClass = myClassLoader.loadClass("cn.duanzx.jvm.classloader.HelloWorld");
        Class<?> aClass = Class.forName("cn.duanzx.jvm.classloader.HelloWorld",true,myClassLoader);
        System.out.println(aClass.getClassLoader());
        Object helloworld = aClass.newInstance();
        System.out.println(helloworld);
        Method welcomMethod = aClass.getMethod("welcome");
        String result = (String) welcomMethod.invoke(helloworld);
        System.out.println("Result : "+ result);

    }
}
