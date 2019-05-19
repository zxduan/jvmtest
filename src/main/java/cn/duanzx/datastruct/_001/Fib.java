package cn.duanzx.datastruct._001;

import org.junit.Test;

/**求解斐波那契数列的第n个元素*/
public class Fib {

    @Test
    public void testFib(){
        System.out.println(fibByTraverse(64));
    }

    /**
     * 0 1 1 2 3 5 8 13 21
     * fib(0) = 0; fib(1) = 1;fib(n) = fin(n-1)+ fib(n-2) (n > 1)
     * */
    /**使用递归方法,效率低下，存在大量的重复运算*/
    public int fib(int n){
        if(n < 2){
            return n;
        }
        return fib(n-1) + fib(n-2);
    }
    /**使用迭代+记忆方法*/
    public long fibByTraverse(int n){
        if(n < 2){
            return n;
        }
        long next1=0,next2=1;
        for(int i = 2;i<n;i++){
            long tmp = next1;
            next1 = next2;
            next2 = tmp + next2;
            System.out.println(next2);
        }
        return next2;
    }

    /**动态规划
     * 颠倒计算方向：自顶向下递归改为自底向上迭代，
     * */

}
