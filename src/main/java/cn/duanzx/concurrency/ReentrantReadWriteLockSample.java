package cn.duanzx.concurrency;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReentrantReadWriteLockSample {
    /**
     * 参考：http://cmsblogs.com/?p=2213
     * https://www.infoq.cn/article/java8-abstractqueuedsynchronizer
     * 读写锁维护着一对锁，一个读锁和一个写锁
     * 在同一时间运行多个读线程同时操作，但在写线程访问的时候，所有的读写线程都会被阻塞。同一时间只有一个写线程访问。
     * 特性：公平性，非公平性；重入性；锁降级（写锁可以降级为读锁）
     * 读写锁采用 按位切割使用 的方式来维护state变量，将其切分为两个部分：高16位表示读，低16位表示写
     * 假如当前同步状态为S ， 写状态等于S & Ox0000FFFF(将高16位全部抹去)， 读状态等于 S >>> 16 (无符号位补0右移16)
     */
    public void test() {
        ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
        //读锁
        readWriteLock.readLock().lock();
        readWriteLock.readLock().unlock();
        //写锁
        readWriteLock.writeLock().lock();
        readWriteLock.writeLock().unlock();
    }

}
