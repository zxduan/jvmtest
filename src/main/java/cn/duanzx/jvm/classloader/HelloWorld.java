package cn.duanzx.jvm.classloader;

public class HelloWorld {
    static {
        System.out.println("Hello World Class is Initialized ");
    }
    public String welcome(){
        return "Hello World";
    }
}
