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
//        System.out.println(sum(arr,arr.length));
        System.out.println(sum(arr));
        System.out.println(sumMid(arr,0,arr.length-1));
    }

    /**使用二分递归对数组求和
     * 1 2 3 4 5 6 7
     * 将求解一个数组的和分成求解两个数组的和
     * arr = arr1 + arr2
     * arr1 = arr11 + arr12
     * arr2 = arr21 + arr22
     * 递归基：子数组不可再分即数组长度是1
     * 1 2 3 4 5
     * 12      3 4 5
     * 1 2     3  4 5
     * 1   2   3   4  5
     *
     * */
    public int sumMid(int[] arr , int lo , int hi){
        if (lo == hi) {
            return arr[lo];
        }
        int mi = (lo+hi) >> 1;
        return sumMid(arr,lo,mi) + sumMid(arr,mi+1,hi);
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
