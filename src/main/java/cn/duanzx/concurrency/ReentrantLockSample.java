package cn.duanzx.concurrency;

public class ReentrantLockSample {
    /**
     * 实现AQS的独占功能
     * 保证在同一个时间只有一个线程在执行代码，或者说统一时刻只有一个线程的lock方法会返回
     * 有且只有一个线程获取锁，其余线程全部挂起，知道该拥有锁的线程释放锁，被挂起的线程被唤醒重新开始竞争锁
     * */
}
