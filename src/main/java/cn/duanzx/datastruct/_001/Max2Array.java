package cn.duanzx.datastruct._001;

import org.junit.Test;

/**
 * 从数组区间A[0,n)中找出最大的两个整数*，元素的比较次数要尽可能的少
 */
public class Max2Array {


    @Test
    public void test() {
        int[] arr = new int[]{1, 2, 3, 7, 5, 6, 3};
        int[] result = max2Array2(arr);
        System.out.println(String.format("最大的两个整数是：%d , %d", result[0], result[1]));
    }

    /**
     * 思路1
     * 直接设置两个变量，遍历数组取出两个最大的整数
     */
    public int[] max2Array(int[] arr) {
        int max1 = 0;
        int max2 = 0;
        for (int i = 0; i < arr.length; i++) {
            max1 = Math.max(max1, arr[i]);
        }
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != max1) {
                max2 = Math.max(max2, arr[i]);
            }
        }
        return new int[]{max1, max2};
    }

    /**
     * 1.首先从数组中找出最大的数，并得到其坐标x1
     * 2.然后以x1为分割线，分出两个数组[lo,x1),(x1,hi)
     * 3.分别在两个数组中找出最大的两个数，进行比较后，最大的数就是数组中第二大的数
     */
    public int[] max2Array1(int[] arr) {
        int index1 = -1;
        int nextIndex1 = -1;
        int nextIndex2 = -1;
        for (int i = 1; i < arr.length; i++) {
            if (index1 < 0 || arr[index1] < arr[i]) {
                index1 = i;
            }
        }
        for (int x = 0; x < index1; x++) {
            if (nextIndex1 < 0 || arr[nextIndex1] < arr[x]) {
                nextIndex1 = x;
            }
        }
        for (int y = index1 + 1; y < arr.length; y++) {
            if (nextIndex2 < 0 || arr[nextIndex2] < arr[y]) {
                nextIndex2 = y;
            }
        }
        return new int[]{arr[index1], Math.max(arr[nextIndex1], arr[nextIndex2])};
    }

    /**
     * 迭代算法改进
     * 先判断是否是最大的数字
     * 再判断是否是第二大的数字
     */
    public int[] max2Array2(int[] arr) {
        int index1 = 0;
        int index2 = 1;
        if (arr[index1] < arr[index2]) {
            int temp = index1;
            index1 = index2;
            index2 = temp;
        }
        for (int i = 2; i < arr.length; i++) {
            if (arr[index1] < arr[i]) {
                index2 = index1;
                index1 = i;
                continue;
            }
            if (arr[index2] < arr[i]) {
                index2 = i;
            }
        }
        return new int[]{arr[index1], arr[index2]};
    }

    /**
     * 使用递归+分治
     * 将一个大的数组，分成若干个子数组
     * 直到子数组不能再分为止（数组长度为3或4）
     */
    //求解数组长度是3
    @Test
    public void testArray3() {
        int[] arr = new int[]{1, 2, 3};
        int lo = 0, hi = 2;
        int x1 = arr[lo], x2 = arr[hi];
        //保证 x1 < x2 , x2是最大的
        if (x2 < x1) {
            int temp = x1;
            x1 = x2;
            x2 = temp;
        }
        if (x2 < arr[lo + 1]) { //arr[lo+1]是最大的
            x1 = x2;
            x2 = arr[lo + 1];
            System.out.println(String.format("最大的两个数：%d,%d", x2, x1));
        } else { // x2 是最大的
            x1 = Math.max(arr[lo + 1], x1);
            System.out.println(String.format("最大的两个数：%d,%d", x2, x1));
        }
    }
    //求解数组长度是4
    public void testArray4(){
        int[] arr = new int[]{1, 2, 3,4};
        int lo = 0, hi = 2;
        int l1 = arr[lo],l2 = arr[lo+1],r1 = arr[hi-1],r2 = arr[hi];
        //保证l1 < r1
        if(r1 < l1){
            int temp = r1;
            r1 = l1;
            l1 = temp;
        }
        if(r1 < r2){
            if(l){

            }
        }


    }

    public void testArray34(){

    }

}
