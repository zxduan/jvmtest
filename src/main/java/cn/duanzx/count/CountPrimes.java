package cn.duanzx.count;

import org.junit.Test;

public class CountPrimes {
    //统计m-m范围内质数的个数
    @Test
    public void countPrimes(){
        int m = 1,n=20,count=0;
        int i,j;
        for(j = m;j<=n;j++){
            for(i = 2;i<=j/2;i++){
                if(j %i == 0){
                    break;
                }
            }
            if(i > j/2){
                count++;
                System.out.println(j);
            }
        }
    }

}
