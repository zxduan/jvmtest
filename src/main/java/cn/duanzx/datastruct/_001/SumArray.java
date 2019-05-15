package cn.duanzx.datastruct._001;

import org.junit.Test;

/**使用递归方法对数组递归求和*/
public class SumArray {
    /**
     * 分析：将规模为n的问题，分为规模为n-1的问题+相似算法的问题
     * 递归基：n n-1 n-2 ....1  n < 1时候结束
     * 相同的规模：sum(A,n-1) + A[n-1]
     * */

    @Test
    public void test(){
        int[] arr = new int[]{1,2,3,4,5};
        System.out.println(sum(arr,arr.length));
        System.out.println(sum(arr));
    }

    public int sum(int[] arr,int n){
        if(n < 1){
            return 0;
        }
        return sum(arr,n-1)+arr[n-1];
    }

    /**记忆图表法*/
    public int sum(int[] arr){
        int sum = arr[0];
        for(int i = 1;i<arr.length;i++){
            sum += arr[i];
        }
        return sum;
    }
}
