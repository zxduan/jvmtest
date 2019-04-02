package kmp;

public class KmpTest {

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
