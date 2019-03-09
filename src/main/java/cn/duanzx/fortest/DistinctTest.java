package cn.duanzx.fortest;

public class DistinctTest {
    public static void main(String[] args)throws Exception{
//        int[] arr = new int[]{0,1,2,3,4,5,6,7,8};
//        printlnArr(arr);
//        printlnArr(deleteArr(arr,7,8));
        int[] source = new int[]{1,2,3,1,2,3,1,2,3};
        printlnArr(distinct(source));
    }

    //将给定数组里的重复的元素删除并返回一个新的数组[1,2,3,1,2,3,1,3,2] -> [1,2,3]
    public static int[] distinct(int[] source){
        int[] newArr = new int[source.length];
        int index = 0;
        newArr[0] = source[0];
        for(int i = 1;i<source.length;i++){
            int j = 0;
            for(j = 0;j<i;j++){
                if(source[i] == source[j]){
                    break;
                }
            }
            if(j > index){
                index++;
                newArr[index] = source[i];
            }
        }
        int[] c = new int[index+1];
        for(int x = 0;x<=index;x++){
            c[x] = newArr[x];
        }
        return c;
    }
    //方案1，类似于查找质数

    //根据数组所在的角标范围删除一个数组中的元素，[1,2,3,4,5,6] -> 12,6
    public static int[] deleteArr(int[] arr,int start,int limit){
        while (start < limit && limit < arr.length){
            int index = limit++;
            arr[start++]= arr[index];
            arr[index] =0;
        }
        return arr;
    }
    public static void printlnArr(int[] arr){
        System.out.println("--------------------------------");
        for(int e : arr){
            System.out.println(e);
        }
    }
}
