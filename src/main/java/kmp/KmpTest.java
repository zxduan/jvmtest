package kmp;

public class KmpTest {

    public static void main(String[] args)throws Exception{
        int[][] ages = new int[4][7];
        System.out.println(ages.length);
        int[] arr = new int[]{1,2,3};
        updateArr(arr);
        System.out.println(arr[0]);
        //最终打印-1
        System.out.println("H".concat("b"));
    }

    public static void updateArr(int[] arr){
        arr[0] = -1;
        arr = new int[]{4,5,6};//更改了arr的引用地址，arr元素不变
        arr[0] = 8;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
    /***
     * 求字符串在母串中的位置
     * abcdefghijklmn
     * efgh
     *1.利用最大前缀，后缀分析
     * int[]存储什么元素
     * while循环
     *
     * */

    /***
     * hello , 前缀 h, he , hel , hell , hell，后缀 o, lo, llo, ello
     * 找到前缀和后缀相同的最大公共串
     * abbab , 最大公共前缀：ab
     * 最大公共前后缀的作用？
     * */
    public void checkStr(){
        String s = "efgh";
        int[] arr = new int[s.length()];//存储该元素下一个重复的角标位置
    }
}
