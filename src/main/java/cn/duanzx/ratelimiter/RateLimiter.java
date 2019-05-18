package cn.duanzx.ratelimiter;

import org.junit.Test;

import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * todo 将笔记里的图片补全
 * 实现令牌桶算法：
 * 系统以一个恒定的速度向桶里放入令牌，如果请求需要被处理，需要先从桶里获取一个令牌
 * 当桶里没有令牌可取的时候，拒绝服务。
 */
public class RateLimiter {


    @Test
    public void test() {
        final RateLimiter rateLimiter = new RateLimiter();
        while (true) {
            //每秒有20个并发线程访问
            for (int x = 0; x < 20; x++) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
//                         每秒生成5个令牌，令牌桶容量：10
                        if (rateLimiter.acquire(10, 5L)) {
                            System.out.println(String.format("线程：%s,获取令牌成功，时间：%s", Thread.currentThread().getName(), new Date().toString()));
                        } else {
//                            System.out.println(String.format("线程：%s,获取令牌失败，时间：%s", Thread.currentThread().getName(),new Date().toString()));
                        }
                    }
                }, "t" + x).start();
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void loop(RateLimiter rateLimiter, int index) {
//        每秒生成50个令牌，令牌桶容量：10 ,
        System.out.println("********************************************************");
        for (int i = 0; i < 50; i++) {
            if (rateLimiter.acquire(10, 5L)) {
                System.out.println(String.format("线程：%s,第%d秒访问，访问次数：%d，获取令牌成功", Thread.currentThread().getName(), index, i + 1));
            } else {
//                System.out.println(String.format("第%d秒访问，访问次数：%d，获取令牌失败", index, i+1));
            }
        }
    }

    private final long rateToMsConversion;
    private final AtomicInteger consumedTokens = new AtomicInteger();
    private final AtomicLong lastRefillTime = new AtomicLong(0);

    public RateLimiter() {
        rateToMsConversion = 1000;
    }

    public boolean acquire(int burstSize, long averageRate) {
        long currentTimeMillis = System.currentTimeMillis();
        if (burstSize <= 0 || averageRate <= 0) {
            return true;
        }
        refillToken(burstSize, averageRate, currentTimeMillis);
        return consumeToken(burstSize);
    }

    private void refillToken(int burstSize, long averageRate, long currentTimeMillis) {
        long refillTime = lastRefillTime.get();
        long timeDelta = currentTimeMillis - refillTime;
        long newTokens = timeDelta * averageRate / rateToMsConversion;
        if (newTokens > 0) {
            //计算下次填充的时间（填充 newToken 个令牌后的时间）
            long newRefillTime = refillTime == 0 ? currentTimeMillis : refillTime + newTokens * rateToMsConversion / averageRate;
            if (lastRefillTime.compareAndSet(refillTime, newRefillTime)) {
                while (true) {
                    int currentLevel = consumedTokens.get();
                    int adjustLevel = Math.min(burstSize, currentLevel);
                    int newLevel = Math.max(0, adjustLevel - currentLevel);
                    if (consumedTokens.compareAndSet(currentLevel, newLevel)) {
                        return;
                    }
                }
            }
        }
    }

    public boolean consumeToken(int burstSize) {
        while (true) {
            int currentLevel = consumedTokens.get();
            if (currentLevel >= burstSize) {
                return false;
            }
            if (consumedTokens.compareAndSet(currentLevel, currentLevel + 1)) {
                return true;
            }
        }
    }


}
