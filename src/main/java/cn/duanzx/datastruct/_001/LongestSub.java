package cn.duanzx.datastruct._001;

import org.junit.Test;

/**
 * 寻找两个字符串中的最长公共子序列
 * A B C D E F G
 * C D E A B
 */
public class LongestSub {

    /**
     * 遍历其中一个字符串
     * 判断该字符串中的字符是否存在于另一个字符串中，
     * 如果存在，将其所在位置存入一个字符串中， 1,2,3,如果有多个重复元素，则存入多个
     * 然后在结果字符串中找出位置连续的字符串
     */

    @Test
    public void test() {
        String s1 = "educational";
        String s2 = "advantage";
        s1 = "didactical";
        s2 = "advantage";
        System.out.println(longestSubstring(s1, s2));
    }

    /**
     * 采用递归+分治
     */


    public String longestSubstring1(String s1, String s2) {
        String subString = "";
        if (s1.length() < s2.length()) {
            String temp = s1;
            s1 = s2;
            s2 = temp;
        }
        char[] arr1 = s1.toCharArray();
        char[] arr2 = s2.toCharArray();
        for (int x = arr1.length; x > 1; x--) {
            char start = arr1[x - 1];
            StringBuffer sub = new StringBuffer();
            //首先判断first字符是否存在S2字符串中
            int i = x;
            int j = -1;
            int next = -1;
            for (int y = arr2.length - 1; y >= 0; y--) {
                if (start == arr2[y]) {
                    j = y;
                    break;
                }
            }
            if (j < 0) {
                sub.append(start);
                subString = subString.length() < sub.length() ? sub.toString() : subString;
                continue;
            }
            while (i >= 0) {
                //如果 arr1[i]和arr2[j]之后的所有元素都不匹配
                if (j == -1) {
                    i--;
                    j = next;
                    continue;
                }
                if (arr1[i] == arr2[j]) {
                    sub.append(arr1[i]);
                    i--;
                    j--;
                    next = j;
                    continue;
                }
                if (arr1[i] != arr2[j]) {
                    j--;
                }
            }
            subString = subString.length() < sub.length() ? sub.toString() : subString;
        }
        StringBuffer result = new StringBuffer();
        for (int i = subString.length(); i > 0; i--) {
            result.append(subString.charAt(i - 1));
        }
        return result.toString();
    }

    //枚举法
    public String longestSubstring(String s1, String s2) {
        String subString = "";
        if (s1.length() < s2.length()) {
            String temp = s1;
            s1 = s2;
            s2 = temp;
        }
        char[] arr1 = s1.toCharArray();
        char[] arr2 = s2.toCharArray();
        for (int x = arr1.length - 1; x >= 0; x--) {
            char start = arr1[x];
            StringBuffer sub = new StringBuffer();
            //首先判断first字符是否存在S2字符串中
            int i = x;
            int j = -1;
            int next = -1;
            for (int y = arr2.length - 1; y >= 0; y--) {
                if (start == arr2[y]) {
                    j = y;
                    break;
                }
            }
            if (j < 0) {
                sub.append(start);
                if (subString.length() < sub.length()) {
                    subString = sub.toString();
                }
                continue;
            }
            while (i >= 0) {
                for (int z = j; z >= 0; z--) {
                    if (arr1[i] == arr2[z]) {
                        j = z;
                        next = z;
                        break;
                    }
                }
                //如果没有找到，i--, j 不变
                if (next == -1) {
                    i--;
                }
                //如果找到了，i--, j--, substring 追加
                if (next > -1) {
                    sub.append(arr1[i]);
                    i--;
                    j--;
                    next = -1;
                }
            }
            if (subString.length() < sub.length()) {
                subString = sub.toString();
            }
        }
        StringBuffer result = new StringBuffer();
        for (int i = subString.length(); i > 0; i--) {
            result.append(subString.charAt(i - 1));
        }
        return result.toString();
    }

    public void reverstString(char[] arr, int lo, int hi) {
        if (lo < hi) {
            char temp = arr[lo];
            arr[lo] = arr[hi];
            arr[hi] = temp;
            reverstString(arr, ++lo, --hi);
        }
    }

}
