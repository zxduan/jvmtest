package cn.duanzx.fortest;

public class StrCountTest {
    public static void main(String[] args)throws Exception{
        String str = "abcABC123";
        int a = 'a',b='z',c='A',d='Z';
        int n = 0;
        for(char target : str.toCharArray()){
            if(target >='a' && target <= 'z'){
                n++;
            }
            if(target >= 'A' && target <= 'Z'){
                n++;
            }
        }
        System.out.println(n);
    }
}
