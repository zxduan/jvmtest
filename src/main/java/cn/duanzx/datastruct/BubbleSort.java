package cn.duanzx.datastruct;

import org.junit.Test;

public class BubbleSort {
    /***
     * 起泡排序
     * 每一次两两比较后，都会确定一个最终值
     * 插入排序：
     * 将一个数字插入到已经排好序的数组中
     */

    @Test
    public void test(){
        int[] arr = new int[]{1,34,3,5,9,2};
        insertSort(arr);
        printlnArr(arr);
    }

    public void insertSort(int[] arr){
        for(int i = 1;i<arr.length;i++){
            int j = i;
            int temp = arr[j];
            while (j > 0 && temp < arr[j-1]){
                arr[j] = arr[j-1];
                j--;
            }
            arr[j] = temp;
        }
    }

    private void printlnArr(int[] arr){
        for(int a : arr){
            System.out.println(a);
        }
    }

    public void bubbleSort(int[] arr){
        for(int i = 0;i<arr.length-1;i++){
            for(int j = 0 ;j < arr.length-1-i;j++){
                if(arr[j] > arr[j+1]){
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
    }

}
