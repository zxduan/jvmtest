package kmp;

import org.junit.Test;

public class KmpAlgorithm {

    /**
     * 判断子串在母串里的位置
     */
    @Test
    public void direct() {
        //遍历字符串 str , 在每一次的遍历中 判断当前字符串以及后几位子串的字符串是否等于子字符串
        //123456   34   12  23  34  45 56   total = 6  end = 6-2+1
        char[] strArr = "abcdefsabcdidsasdfsabcdsdfsdfs".toCharArray();
        char[] subArr = "sabcd".toCharArray();
        int endIndex = strArr.length - subArr.length + 1;
        for (int i = 0; i < endIndex; i++) {
            int rightCount = 0;
            for (int j = 0; j < subArr.length; j++) {
                if (strArr[i + j] == subArr[j]) {
                    rightCount++;
                }
            }
            if (rightCount == subArr.length) {
                System.out.println(i);
                System.out.println("-------------------------------");
            }
        }
        System.out.println("********************right*******************");
        System.out.println("abcdefsabcdidsasdfsabcdsdfsdfs".indexOf("sabcd"));
        System.out.println("abcdefsabcdidsasdfsabcdsdfsdfs".lastIndexOf("sabcd"));
    }

    /**
     * 判断子串在母串里的位置
     * KMP算法的核心：是一个被称为部分匹配表的数组PMT  , Partial Match Table
     * 我先解释一下字符串的前缀和后缀。如果字符串A和B，存在A=BS，其中S是任意的非空字符串，那就称B为A的前缀。
     * 例如，”Harry”的前缀包括{”H”, ”Ha”, ”Har”, ”Harr”}，我们把所有前缀组成的集合，称为字符串的前缀集合。
     * 同样可以定义后缀A=SB， 其中S是任意的非空字符串，那就称B为A的后缀，
     * 例如，”Potter”的后缀包括{”otter”, ”tter”, ”ter”, ”er”, ”r”}，然后把所有后缀组成的集合，称为字符串的后缀集合。要注意的是，字符串本身并不是自己的后缀。
     * PMT中的值是字符串的前缀集合与后缀集合的交集中最长元素的长度。
     *在主字符串"ababababca"中查找模式字符串"abababca"。
     * 如果在 j 处字符不匹配，那么由于前边所说的模式字符串 PMT 的性质，主字符串中 i 指针之前的 PMT[j −1] 位就一定与模式字符串的第 0 位至第 PMT[j−1] 位是相同的。
     *在 i 处失配，那么主字符串和模式字符串的前边4位就是相同的。
     * 又因为模式字符串的前4位，它的前3位前缀和后3位后缀是相同的，
     * 所以我们推知主字符串i之前的3位和模式字符串开头的3位是相同的。这部分就不用再比较了。
     *
     *
     * 使用PMT加速字符串的查找。我们看到如果是在 j 位 失配，
     * 那么影响 j 指针回溯的位置的其实是第 j −1 位的 PMT 值，所以为了编程的方便， 我们不直接使用PMT数组，而是将PMT数组向后偏移一位。我们把新得到的这个数组称为next数组。
     * 如何编程快速求得next数组。
     * 其实，求next数组的过程完全可以看成字符串匹配的过程，
     * 即以模式字符串为主字符串，以模式字符串的前缀为目标字符串，一旦字符串匹配成功，那么当前的next值就是匹配成功的字符串的长度。
     * 具体来说，就是从模式字符串的第一位(注意，不包括第0位)开始对自身进行匹配运算。 在任一位置，能匹配的最长长度就是当前位置的next值。
     *假设主字符串为：A B A A B A B D A C A B , 匹配字符串： A B A B
     *方法一：
     * ABAA -> ABAB   BAAB -> ABAB  AABA -> ABAB   ABABA -> ABAB
     *方法二:
     * ABAA -> ABAB , 发现已匹配字符串 ABA ， PMT = 1 ，此时 字符串右移一位变为 B A B D A C A B 匹配 B A B
     *
     *
     * */

//    int[] calculateMaxMatchLengths(String pattern) {
//        int[] maxMatchLengths = new int[pattern.length()];
//        int maxLength = 0;
//        for (int i = 1; i < pattern.length(); i++) {
//            while (maxLength > 0 && pattern.charAt(maxLength) != pattern.charAt(i)) {
//                maxLength = maxMatchLengths[maxLength - 1]; // ①
//            }
//            if (pattern.charAt(i) == pattern.charAt(maxLength)) {
//                maxLength++; // ②
//            }
//            maxMatchLengths[i] = maxLength;
//        }
//        return maxMatchLengths;
//    }

    /**
     * 计算一个字符串的PMT
     */
    @Test
    public void getStrPMT() {
        String str = "ABABAB";//PMT = 3,
        char[] arr = str.toCharArray();
        int[] next = new int[arr.length]; //next 数组各值的含义：代表当前字符之前的字符串中，有多大长度的相同前缀后缀
        next[0] = -1;//表示不存在相同的前后缀
        int i = 0;
        int j = -1;
        while (i < arr.length-1) //依次获取后缀，A  B A B A B，
            //取后缀里的每个元素 B A B A B ，依次去和前缀 A B A B A 里的元素去比，
            //如果一个后缀元素比对失败，则换两一个后缀元素比对，如果还是比对失败，重复上面操作，此时比对的是同一个前缀元素
            //如果比对前缀元素成功，则 换另一个后缀，前缀 元素开始比对，如果比对成功，则比对次数+1
        {
            //依次和前缀去比 A B A B A
            /**
             * B -> A , 失败 ，j = -1 ,i + 1 开始尝试 A -> A , 成功，j ++ , i++
             * B -> B 成功，
             *
             * */
            if (j == -1 || arr[i] == arr[j]) //如果是初始状态，则角标向前移动，并且保存next数组， 如果前后缀相等，则继续将角标向前移动，并保存next数组
            {
                ++i;
                ++j;
                next[i] = j; //当前角标位置的元素之前的字符串里有多少相同的前后缀，也就是有多少次连续匹配成功
            } else{ //如果前后缀不相等，此时
                j = next[j];
            }
        }
        System.out.println(next);
    }

    @Test
    public  void  getPMT(){
        String str = "ABABAB";//PMT = 3,
        str = "ABCBA";//PMT = 3,
        char[] arr = str.toCharArray();
        int[] next = new int[arr.length]; //next 数组各值的含义：代表当前字符之前的字符串中，有多大长度的相同前缀后缀
        next[0] = -1;//表示不存在相同的前后缀
        int i = 0;
        int j = -1;
        while (i < arr.length-1){
            if(j == -1){
                j++;
                i++;
                next[i] = j;
            }else if(arr[i] == arr[j]){
                j++;
                i++;
                next[i] = j; //如果相等了，同时移动i ,j ，进行下一个前后缀元素的比对
            }else if(arr[i] != arr[j]){ //如果不相等，就移动i,换一个元素，重新与 j 元素进行比对
                j = -1;
            }
        }
        System.out.println(next);
    }

    @Test
    public void useKMP() {
        String str = "abaababdacab";
        String sub = "abab";
        char[] strArr = str.toCharArray();
        char[] subArr = sub.toCharArray();
        int i = 0;
        int j = 0;
        int[] next = new int[]{-1};
        while (i < strArr.length && j < subArr.length) {
            if (j == -1 || strArr[i] == subArr[j]) {
                i++;
                j++;
            } else {
                j = next[j];
            }
        }
        if (j == subArr.length) {
            System.out.println("-----------------------------");
            System.out.println(i - j);
        }
    }

    @Test
    public void userKmp(){
        String str = "abaababdacab";
        String sub = "abab";
        char[] strArr = str.toCharArray();
        char[] subArr = sub.toCharArray();
        int i = 0;
        int j = 0;
        int[] next = getKMPArr(sub);
        while (i < strArr.length && j < subArr.length){
            if(strArr[i] == subArr[j]){
                i++;
                j++;
                if (j == subArr.length) {
                    System.out.println("Index : " + (i-j)); //
                }
            }else if(strArr[i] != subArr[j]){
                //如果在j位置不匹配，取出J的下一个位置
                 j = next[j];
            } else if(j == -1){ //说明没有就同时移动进行比配
                j++;
                i++;
            }
        }
        System.out.println(str.indexOf(sub));
        System.out.println(str.lastIndexOf(sub));
    }

    @Test
    public void getabab() {
        getKMPArr("abab");
    }

    public int[] getKMPArr(String str) { //next数组存储的是要移动的元素的角标，不是
        char[] arr = str.toCharArray();
        int[] next = new int[arr.length]; //next 数组各值的含义：代表当前字符之前的字符串中，有多大长度的相同前缀后缀
        next[0] = -1;//表示不存在相同的前后缀
        int i = 0;
        int j = -1;
        while (i < arr.length-1){
            if(j == -1){
                j++;
                i++;
                next[i] = j;
            }else if(arr[i] == arr[j]){
                j++;
                i++;
                next[i] = j; //如果相等了，同时移动i ,j ，进行下一个前后缀元素的比对
            }else if(arr[i] != arr[j]){ //如果不相等，就移动i,换一个元素，重新与 j 元素进行比对
                j = -1;
            }
        }
        return next;
    }
}
