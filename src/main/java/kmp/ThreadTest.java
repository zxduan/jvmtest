package kmp;

import org.junit.Test;

public class ThreadTest {
    /**
     * 创建四个线程，其中两个线程对j++,两个线程对j--
     *
     * */
    public static void main(String[] args)throws Exception{
        System.out.println("线程最大级别："+Thread.MAX_PRIORITY);
        final Obj obj = new Obj(10);
        buildThread(obj,true,"plus1").start();
        buildThread(obj,false,"minus1").start();
        buildThread(obj,true,"plus2").start();
        buildThread(obj,false,"minus2").start();
        buildThread(obj,true,"plus3").start();
        buildThread(obj,false,"minus3").start();
        buildThread(obj,true,"plus4").start();
        buildThread(obj,false,"minus4").start();
    }

    public static Thread buildThread(final Obj obj,boolean plus,String threadName){
        if(plus){
            Thread t1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(2000);
//                        System.out.println("执行线程名称: "+ Thread.currentThread().getName()+",级别："+Thread.currentThread().getPriority());
                        obj.plus();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            },threadName);
            return t1;
        }
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
//                    System.out.println("执行线程名称: "+ Thread.currentThread().getName()+",级别："+Thread.currentThread().getPriority());
                    obj.minus();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },threadName);
        return t2;
    }

}
