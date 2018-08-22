package cn.duanzx.jvm;

/**
 * Created by duanzx on 2018/8/22.
 */
public class DumpTest {
    /*
    *-verbose:gc -Xms20M -Xmx20M -Xmn1M -XX:+PrintGCDetails
    *
    * */
    public static void main(String[] args)throws Exception{
        byte[] b = null;
        for(int i=0;i<10;i++){
            b =new byte[1024*1024];
        }
    }
}
