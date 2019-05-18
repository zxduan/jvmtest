package kmp;

public class Obj{
    private int j;
    Obj(int j){
        this.j = j;
    }
    public void plus(){
        synchronized (this){
            System.out.println("执行线程名称: "+ Thread.currentThread().getName()+",级别："+Thread.currentThread().getPriority());
            j++;
            System.out.println("plus:"+j);
        }

    }
    public void minus(){
        synchronized (this){
            System.out.println("执行线程名称: "+ Thread.currentThread().getName()+",级别："+Thread.currentThread().getPriority());
            j--;
            System.out.println("minus:"+j);
        }
    }
    public int getJ(){
        return j;
    }
}