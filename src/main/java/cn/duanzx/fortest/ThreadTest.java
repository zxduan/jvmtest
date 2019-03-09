package cn.duanzx.fortest;

public class ThreadTest implements Runnable{

    @Override
    public void run() {
        int n = 0;
        while (n++ < 20) {
            System.out.println("n = " + n);
        }
    }
    public static void main(String[] args)throws Exception{
//        Thread t = new Thread(new ThreadTest());
//        t.start();
        String[] s =new String[]{"A","B"};
        swap(s[0],s[1]);
        String z = s[0];
        s[0] = s[1];
        s[1] = z;
        System.out.println(s[0]+","+s[1]);
    }
    public static void swap(String a,String b){
        String z = a;
        a=b;
        b=z;
    }


}
