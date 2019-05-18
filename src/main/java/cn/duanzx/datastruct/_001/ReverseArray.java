package cn.duanzx.datastruct._001;

import org.junit.Test;

/**任意给一个数组A[0,n),将其前后颠倒*/
public class ReverseArray {

    @Test
    public void test(){
        int[] arr = new int[]{1,2,3,4,5,6};
//        printlnArr(reverse1(arr));
//        reverse2(arr);
        reverse(arr,0,arr.length-1);
        printlnArr(arr);
    }

    /**使用递归算法，分析递归基，分析递归执行规模
     * 递归基：lo ++ , hi-- , 截止到：lo >= hi
     * 执行内容: A[low]  swap A[hi]
     * 分而治之：
     * 为求解一个大规模的问题，可以将其划分为若干的子问题，其规模大体相当
     * 分别求解子问题，由子问题的解，得到原问题的姐
     *
     * */
    public void reverse(int[] arr , int lo , int hi){
        if(lo < hi){
            int temp = arr[lo];
            arr[lo] = arr[hi];
            arr[hi] = temp;
            reverse(arr,++lo,--hi);
        }
    }



    /**使用迭代算法分析
     * 1 2 3 4 5 6
     * 6 5 4 3 2 1
     *
     * 1 2 3 4 5
     * */
    public void reverse2(int[] arr){
        int n = arr.length;
        int limit = n/2;
        for(int i = 0;i<limit;i++){
            int temp = arr[i];
            arr[i] = arr[n - 1 -i];
            arr[n-1-i] = temp;
        }
    }

    public int[] reverse1(int[] arr){
        int n = arr.length;
        int[] newArr = new int[n];
        for(int i = 0;i<n;i++){
            newArr[n-1-i] = arr[i];
        }
        return newArr;
    }

    public void printlnArr(int[] arr){
        for(int a : arr){
            System.out.println(a);
        }
    }

}
