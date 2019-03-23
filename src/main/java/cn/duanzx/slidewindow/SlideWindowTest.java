package cn.duanzx.slidewindow;

import org.junit.Test;

public class SlideWindowTest  {

    //给定一个数组，求长度为k的数的最大和 200,100,100,300 -> 500
    @Test
    public void testSumOne(){
        int[] arr = new int[]{200,100,100,300};
        int sum = 0;
        int n = arr.length;
        for(int i = 0;i<n;i++){
            int a = arr[i];
            for(int j = i+1;j<n;j++){
                int b = arr[j];
                sum = Math.max(sum,a+b);
            }
        }
        System.out.println(sum);
    }
    @Test
    public void testSumTwo(){
        int[] arr = new int[]{200,100,100,300};
        int n = arr.length;
        int i=0,j=0;
        int k = 2;
        while (i < n && j < n){

        }
    }
    @Test
    public void testSumThree(){
        int[] arr = new int[]{200,100,100,300};
        int sum = 0;
        int n = arr.length;
    }

    //给定一个字符串，求其中最长的不重复子串abcabcbb ->abc
}
