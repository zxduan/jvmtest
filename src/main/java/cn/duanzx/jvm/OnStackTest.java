package cn.duanzx.jvm;

import java.util.Date;

/**
 * Created by duanzx on 2018/8/22.
 */
public class OnStackTest {
    /*
    * 栈上分配
    * -XX:+PrintGCTimestamps
    *
    *-Xlog:gc* -Xmx10M -Xms10M -XX:+DoEscapeAnalysis
    * -XX:-DoEscapeAnalysis
    *
    *  逃逸分析优化-栈上分配
    * 栈上分配,意思是方法内局部变量（未发生逃逸）生成的实例在栈上分配，不用在堆中分配，分配完成后，继续在调用栈内执行，最后线程结束，栈空间被回收，局部变量对象也被回收。
    * 一般生成的实例都是放在堆中的,然后把实例的指针或引用压入栈中。
    *
    * 当一个对象的指针被多个方法或线程引用时，我们称这个指针发生了逃逸，一般情况返回对象、对全局变量的赋值一般都会发生逃逸。
    *
    * */

    public static void alloc() {
        byte[] b1 = new byte[2];
        b1[0] = 1;
    }

    public static void main(String[] args) throws Exception {
        System.out.println(new Date().toString());
        for (int i = 0; i < 100000000; i++) {
            alloc();
        }
        System.out.println(new Date().toString());
    }
}
