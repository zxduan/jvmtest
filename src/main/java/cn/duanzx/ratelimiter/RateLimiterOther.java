package cn.duanzx.ratelimiter;

import org.junit.Test;

import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 漏桶算法：
 * 水先进入到漏桶里，漏桶以一定的速度出水，当水流入速度过大，水会直接溢出
 */
public class RateLimiterOther {

    @Test
    public void test1() {
        final RateLimiterOther rateLimiter = new RateLimiterOther();
        while (true) {
            for (int i = 0; i < 20; i++) {
                if (rateLimiter.acquire(5, 3L)) {
                    System.out.println(String.format("线程：%s,获取令牌成功，时间：%s", Thread.currentThread().getName(), new Date().toString()));
                } else {
//                            System.out.println(String.format("线程：%s,获取令牌失败，时间：%s", Thread.currentThread().getName(),new Date().toString()));
                }
            }
            System.out.println("-------------------------------------------------");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void test() {
        final RateLimiterOther rateLimiter = new RateLimiterOther();
        while (true) {
            //每秒有20个并发线程访问
            for (int x = 0; x < 20; x++) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
//                         每秒流出2个请求，令牌桶容量：10
                        if (rateLimiter.acquire(5, 2L)) {
                            System.out.println(String.format("线程：%s,获取令牌成功，时间：%s", Thread.currentThread().getName(), new Date().toString()));
                        } else {
//                            System.out.println(String.format("线程：%s,获取令牌失败，时间：%s", Thread.currentThread().getName(),new Date().toString()));
                        }
                    }
                }, "t" + x).start();
            }
            try {
                System.out.println("----------------------------------Sleep 1 seconds");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private final long rateToMsConversion;
    //装水量，当装水量大于桶的容量时候不再装水
    private final AtomicInteger inputWater = new AtomicInteger();
    //下一次装水的时间
    private final AtomicLong lastRefillTime = new AtomicLong(0);

    public RateLimiterOther() {
        rateToMsConversion = 1000 * 3;
    }

    public boolean acquire(int burstSize, long averageRate) {
        return acquire(burstSize, averageRate, System.currentTimeMillis());
    }

    public boolean acquire(int burstSize, long averageRate, long currentTimeMillis) {
        if (burstSize <= 0 || averageRate <= 0) {
            return true;
        }
        long refillTime = lastRefillTime.get();
        long timeDelta = refillTime == 0 ? 0 : currentTimeMillis - refillTime;
        long output = timeDelta * averageRate / rateToMsConversion;
        int input = inputWater.get();
        if (input - output > burstSize) {
            //如果桶里的水溢出了，则保留一桶水，从此时起只流出不流入
            if (lastRefillTime.compareAndSet(refillTime, System.currentTimeMillis())) {
                while (true) {
                    if (inputWater.compareAndSet(input, burstSize)) {
                        return false;
                    }
                }
            }
        } else {
            //如果桶里的水还没满，则可以继续加水
            if (refillTime == 0 && lastRefillTime.compareAndSet(refillTime, System.currentTimeMillis())) {
                while (true) {
                    if (inputWater.compareAndSet(input, input + 1)) {
                        return true;
                    }
                }
            } else {
                while (true) {
                    if (inputWater.compareAndSet(input, input + 1)) {
                        return true;
                    }
                }
            }

        }
        /**
         *计算一下当前时间流出多少水
         * 获取此时已经流进了多少水
         * 判断流进的水是否大于流出的水
         * 没有大于流出的水：
         * 流入的水量自增
         * return true
         * 流入量大于流出量
         * 判断 流进的水 是否大于 桶的容量
         * 如果 大于
         * 将流进的水置为桶的容量，更新refillTime
         * 如果小于
         * 流入的水量自增
         * return true
         * */
        return false;
    }


}
