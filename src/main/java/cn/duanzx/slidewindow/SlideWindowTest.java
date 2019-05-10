package cn.duanzx.slidewindow;

import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class SlideWindowTest  {

    //给定一个数组，求连续长度为k的数的最大和 200,100,100,300 ,k=2-> 400
    @Test
    public void testSumOne(){
        int[] arr = new int[]{200,100,400,300};
        int sum = 0;
        int n = arr.length;
        int k = 2;
        for(int i = 0;i<n-k+1;i++){
            int a = arr[i];
            for(int j = 1;j<k;j++){
                 a += arr[i+j];
            }
            sum = Math.max(sum,a);
        }
        System.out.println(sum);
    }
    //给定一个数组，求连续长度为k的数的最大和 200,100,100,300 ,k=2-> 400
    @Test
    public void testSumTwo(){
        int[] arr = new int[]{200,100,400,300};
        int n = arr.length;
        Set<Integer> set = new HashSet<>();
        int i=0,j=0; //最小边界，最大边界
        int k = 2;//range
        int sum = 0;//代表连续长度为k的数的最大值
        int max = 0;//代表set集合中k个数的最大值
        while (i < n && j < n){
            //如果没有超过k个，则往里面填加数据
            if(set.size() < k){
                max += arr[j];
                set.add(arr[j++]);
                sum = Math.max(max,sum);
            }else{
            //如果添加到了k个数据，则移除掉初始值，让set集合继续添加
                max -= arr[i];
                set.remove(arr[i++]);
            }
        }
        System.out.println(sum);
    }
    /**
     * 给定一个字符串，求其中最长的不重复子串的长度  abcabcbb ->abc 3
     * 维护一个map<元素，元素位置>集合，从起始位置添加，如果遇到重复的元素，则改变起始位置从当前元素的下一个位置开始
     */
    @Test
    public void testGetSublength(){
        String s = "abcabcbb";
        s = "pwkeww";
        Map<Character,Integer> map = new HashMap<>();
        int i = 0,j=0;
        int n = s.length();
        int max = 0;
        while (i < n && j < n){
            if(!map.containsKey(s.charAt(j)) || i > map.get(s.charAt(j))){
                map.put(s.charAt(j),j);
                max = Math.max(max,j-i+1);
                j++;
            }else{
                i = map.get(s.charAt(j))+1;
            }
        }
        System.out.println(max);
    }
}
