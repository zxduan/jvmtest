package cn.duanzx.concurrency;

import org.junit.Test;
import org.redisson.Redisson;
import org.redisson.RedissonLock;
import org.redisson.RedissonLockEntry;
import org.redisson.api.RFuture;
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


    public boolean tryLock(long waitTime, long leaseTime, TimeUnit unit) throws InterruptedException {
        long time = unit.toMillis(waitTime);
        long current = System.currentTimeMillis();
        long threadId = Thread.currentThread().getId();
        Long ttl = tryAcquire(leaseTime, unit, threadId);
        if (null == ttl) {
            //说明获取锁成功
            return true;
        }
        //判断尝试获取锁的时间是否大于最大等待时间
        time -= System.currentTimeMillis() - current;
        if (time < 0) {
            acquireFailed(threadId);
            return false;
        }
        //在剩余的等待时间内,订阅锁的释放事件
        current = System.currentTimeMillis();
        RFuture<RedissonLockEntry> subscribeFuture = subscribe(threadId);
        //如果监听锁释放事件时间，超过了等待时间，则取消监听
        if (!await(subscribeFuture, time, unit)) {
            //如果取消监听事件没有成功，
            if (!subscribeFuture.cancel(false)) {
                //在Future里的线程执行结束后手动取消当前线程的监听事件
                subscribeFuture.onComplete((res, e) -> {
                    if (e == null) {
                        unsubscribe(subscribeFuture, threadId);
                    }
                });
            }
            acquireFailed(threadId);
            return false;
        }
        //如果已经监听到了锁释放事件，则需要重写开始获取锁
        //如果当前时间已经超时，则返回false,获取锁失败
        try {
            time -= System.currentTimeMillis() - current;
            if (time < 0) {
                acquireFailed(threadId);
                return false;
            }
            while (true) {
                long currentTime = System.currentTimeMillis();
                ttl = tryAcquire(leaseTime, unit, threadId);
                if (null == ttl) {
                    return true;
                }
                time -= System.currentTimeMillis() - currentTime;
                if (time < 0) {
                    acquireFailed(threadId);
                    return false;
                }
                //如果没有获取到锁，就在ttl时间内，阻塞等待
                currentTime = System.currentTimeMillis();
                if (ttl >= 0L && ttl < time) {
                    //尝试获取锁，设置过期时间为ttl
//                    this.getEntry(threadId).getLatch().tryAcquire(ttl, TimeUnit.MILLISECONDS);
                } else {
                    //尝试获取锁，设置过期时间为time
//                    this.getEntry(threadId).getLatch().tryAcquire(time, TimeUnit.MILLISECONDS);
                }
                time -= System.currentTimeMillis() - currentTime;
                if (time < 0) {
                    acquireFailed(threadId);
                    return false;
                }
            }

        } catch (Exception e) {

        } finally {
            unsubscribe(subscribeFuture, threadId);
        }

        //如果ttl不为空，说明当前锁被其它Redis客户端持有，根据锁持有时间判断是否已经释放了


        return false;
    }

    public boolean await(RFuture<?> future, long timeout, TimeUnit timeoutUnit) throws InterruptedException {
        return false;
    }


    public RFuture<RedissonLockEntry> subscribe(long threadId) {
        return null;
    }

    protected void unsubscribe(RFuture<RedissonLockEntry> future, long threadId) {
    }

    public void acquireFailed(long threadId) {
        //获取锁失败，不再尝试获取锁
    }

    public Long tryAcquire(long leaseTime, TimeUnit timeUnit, long threadId) {
        long internalLockLeaseTime = timeUnit.toMillis(leaseTime);
        //向Redis实例去注册锁，
        // key=RedissonLock.this.getName() + entityName,
        // keyValue = redisConnectorId(UUID) + threadId
        // , 过期时间= leaseTime
        /**
         * 1.在Redis实例中查询sourceName是否存在
         * 如果不存在
         * 将 key=RedissonLock.this.getName() + entityName 添加到Redis里，并设置值为 redisConnectorId(UUID) + threadId
         * 然后设置过期时间，返回null,说明获取锁成功
         * 如果key存在，且keyValue一致
         * hincrby, 将 key,keyvalue的hash表中的值自增1，并设置过期时间，返回null，说明获取锁成功
         * 如果key存在，且keyValue不一致
         * 使用pttl获取key的剩余存活时间并返回，说明获取锁失败
         * */
        return null;
    }
}
