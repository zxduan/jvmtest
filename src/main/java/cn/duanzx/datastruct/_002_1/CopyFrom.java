package cn.duanzx.datastruct._002_1;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 实现基于复制的构造函数
 * 在向量内部开拓出足够的空间，将区间内的元素逐一存入空间中
 * 1.扩容的时候扩出2倍空间
 * 2.扩容的时候，扩出一定量T的空间
 * 比较二者的时间复杂度
 */
public class CopyFrom {

    /**
     * 定义一个内部类，代表Vector数据结构
     */
    class MyVector<E> {
        private static final int DEFAULT_CAPACITY = 3;
        private int size = 0;
        private int capacity = DEFAULT_CAPACITY;
        private Object[] elementData;

        MyVector() {
            size = 0;
            capacity = DEFAULT_CAPACITY;
            elementData = new Object[capacity];
        }

        //将传入参数arr[lo,hi)中的元素复制到内部变量arr中
        MyVector(Collection<? extends E> arr, int lo, int hi) {
            Object[] array = arr.toArray();
            this.elementData = new Object[capacity = (hi - lo) >> 1];
            this.size = 0;
            while (lo < hi) {
                elementData[size++] = array[lo++];
            }
        }

        MyVector(Object[] arr, int lo, int hi, int increment) {

        }

        /**
         * 扩容算法的实现：
         * 当size > capacity时候，将capacity 扩大二倍
         * 然后复制数组中原来存在的元素到新的数组中
         */
        public void expand() {
            if (size < capacity) {
                return;
            }
            Object[] newElementData = new Object[capacity >> 1];
            for (int i = 0; i < size; i++) {
                newElementData[i] = elementData[i];
            }
            elementData = newElementData;
        }

        public Object[] getElementData() {
            return elementData;
        }

        public int getCapacity() {
            return this.capacity;
        }

        public int getSize() {
            return this.size;
        }
    }

    /**
     * 测试可扩充变量
     * 创建容量为10的数组，并填充元素
     * 将该数组作为传入参数，创建Vector对象
     * 获取已创建的Vector对象的elementData,并打印其中的元素，以及它的容量和size
     */
    @Test
    public void testCopy2Capacity() {
        List<Integer> arr = new ArrayList<>();
        int i = 0;
        while (i < 10) {
            arr.add(i++);
        }
        MyVector myVector = new MyVector(arr, 0, arr.size());
        System.out.println(myVector.getCapacity());
        System.out.println(myVector.getSize());
        System.out.println("----------------------------");
        for (Object element : myVector.getElementData()) {
            System.out.println(element);
        }
    }

    /**
     * 测试二倍扩容的时间复杂度
     * 设置初始容量为5，size=0
     * size++
     * 当size=容量时候，扩容：容量= 当前容量*2
     * 当size = 1000时停止
     * 当size为1000时需要 3 * 2^9 ,
     * 执行了n次，时间复杂度为O(n) , 分摊后，每次扩容成本O(1)
     */
    @Test
    public void testCopy2CapacityComplex() {
        int capacity = 3;
        int size = 0;
        while (size < 1000) {
            if (capacity < size++) {
                System.out.println("Capacity:" + capacity);
                capacity = 2 * capacity;
            }
        }
    }


    /**
     * 当size = 1000时
     * capacity + increment * n = 1000
     * 每次耗时为：
     * T 2T 3T ....(n-1)T ,
     * 执行了 1， 2， 3， 4，。。。n
     * 时间复杂度为n^2 , 每次扩容的时间成本O(n)
     */
    @Test
    public void testCopyIncrement() {
        int capacity = 3;
        int increment = 5;
        int size = 0;
        while (size < 1000) {
            if (capacity < size++) {
                System.out.println("Capacity:" + capacity);
                capacity = capacity + increment;
            }
        }
    }

    /**
     * 理解分摊复杂度
     * 设算法执行6次，每次耗时分别为： 1 ，2， 3， 4， 5， 6
     * 分摊复杂度为 （1 + 2 + 3 + 4 + 5 + 6）/6
     * */


}
