package cn.duanzx.concurrency;

import org.junit.Test;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import java.util.concurrent.TimeUnit;

/**
 * 写出Redisson调用实例
 * 自定义lock,unlock方法
 */
public class RedissonSample {
    /**
     * 模拟使用Redisson
     */
    @Test
    public void testUserRedisson() throws Exception {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://10.161.22.10:5379");
        RedissonClient redissonClient = Redisson.create(config);
        RLock lock = redissonClient.getLock("myLock");
        try {
            /**尝试获取锁的最大等待时间，超过这个值，则认为获取锁失败*/
            long waitTimeout = 3;
            /**锁的持有时间，超过这个时间锁会自动失效*/
            long leaseTime = 10;
            boolean res = lock.tryLock(waitTimeout, leaseTime, TimeUnit.SECONDS);
            if (res) {
                /**
                 * 在此处理业务逻辑
                 * */
            }
        } catch (Exception e) {

        } finally {
            lock.unlock();
        }
    }


    public boolean tryLock() {
        return false;
    }
}
