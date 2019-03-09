package cn.duanzx.fortest;

public class StrEqTest {
    public static void main(String[] args)throws Exception{
        System.out.println(f3701("103300002222223333"));
        System.out.println(otherMethod("103300002222223333"));
    }
    public static int f3701(String s){
        int[] arr = new int[s.length()];
        char[] strList = s.toCharArray();
        char target = strList[0];
        int n = 1;
        arr[0] = 1;
        for(int i = 1;i<strList.length;i++){
            if(target == strList[i]){
                n++;
                arr[i] = n;
            }else {
                target = strList[i];
                n = 1;
                arr[i] = 1;
            }
        }
        int max = 0;
        for(int j = 0;j<arr.length;j++){
            if(max < arr[j]){
                max = arr[j];
            }
        }
        return max;
    }

    public static int otherMethod(String s){
        char[] arr = s.toCharArray();
        int count = 1;
        int maxCount = 0;
        for(int i = 1;i<arr.length;i++){
            if(arr[i-1] == arr[i]){
                count++;
            }else {
                count = 1;
            }
            maxCount = maxCount > count?maxCount:count;
        }
        return maxCount;
    }

}
