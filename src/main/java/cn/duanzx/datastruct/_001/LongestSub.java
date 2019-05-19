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
        //dana
        String s1 = "educational";
        String s2 = "advantage";
        //data
        s1 = "didactical";
        s2 = "advantage";
        //blog
        s1 = "cnblogs";
        s2 = "belong";
        System.out.println(longetsSubstring3(s1, s2));
    }

    /**
     * 采用递归
     * 分别从S1 S2 的末尾元素比较，如果有相同的则提取，没有相同的则将规模递减
     * 设S1 S2的末尾元素位置分别为 index1 index2
     * 若S1 S2末尾元素相同则： index1--,index2--
     * 若S1 S2末尾元素不同则有两种情况
     * index1保证不变， index2 -1
     * index2保持不变，index1 - 1
     * 效率非常低
     */
    public String longestSubstring2(String s1, String s2, int index1, int index2, String subString) {
        if (index1 < 0 || index2 < 0) {
            return subString;
        }
        if (s1.charAt(index1) == s1.charAt(index2)) {
            subString += s1.charAt(index1);
            return longestSubstring2(s1, s2, index1--, index2--, subString);
        }
        String result1 = longestSubstring2(s1, s2, index1, index2--, subString);
        String result2 = longestSubstring2(s1, s2, index1--, index2, subString);
        if (result1.length() < result2.length()) {
            subString += result2;
        } else {
            subString += result1;
        }
        return subString;
    }

    /**
     * 采用动态规划（记忆法+迭代）
     * 将自顶向下的操作，改为自底向上
     * 每一次比较后，有两种情况：
     * 相等：index1,index2同时递减
     * 不相等：
     * index1 递减 或 index2递减
     */
    public String longetsSubstring3(String s1, String s2) {
        int m = s1.length(), n = s2.length();
        String[][] result = new String[m][n];
        for (int i = 0; i < m; i++) {
            if (s2.charAt(0) == s1.charAt(i)) {
                result[i][0] = s2.charAt(0) + "";
            } else {
                result[i][0] = "";
            }
        }
        for (int j = 0; j < n; j++) {
            if (s1.charAt(0) == s2.charAt(j)) {
                result[0][j] = s1.charAt(0) + "";
            } else {
                result[0][j] = "";
            }
        }
        for (int i = 1; i < m; i++) {
            //得到s1中的所有元素
            for (int j = 1; j < n; j++) {
                if (s1.charAt(i) == s2.charAt(j)) {
                    //上一个匹配成功的字符串+ 当前匹配成功的字符串
                    result[i][j] = result[i - 1][j - 1] + s1.charAt(i);
                    continue;
                }
                String r1 = result[i][j - 1];
                String r2 = result[i - 1][j];
                result[i][j] = r1.length() < r2.length() ? r2 : r1;
            }
        }
        return result[m - 1][n - 1];
    }


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

        return reverstString(subString);
    }

    public String reverstString(String subString) {
        StringBuffer result = new StringBuffer();
        for (int i = subString.length(); i > 0; i--) {
            result.append(subString.charAt(i - 1));
        }
        return result.toString();
    }

}
