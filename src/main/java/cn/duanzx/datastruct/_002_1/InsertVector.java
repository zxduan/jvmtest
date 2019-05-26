package cn.duanzx.datastruct._002_1;

import kmp.Obj;
import org.junit.Test;

/**
 * 指定位置i插入，插入后：size++,在i后面的所有元素均向后移动一步，然后返回被添加的元素的角标i
 * 如果插入失败，返回-1;
 * 如果插入后的size > capacity ,则执行扩容
 */
public class InsertVector {
    private int size;
    private int capacity;
    private Object[] elementData;
    private static final int DEFAULT_CAPACITY = 3;

    public InsertVector() {
        size = 0;
        capacity = DEFAULT_CAPACITY;
        elementData = new Object[capacity];
    }

    @Test
    public void test1() {
        System.out.println(3 + 3 >> 1);
    }

    @Test
    public void test() {
        InsertVector insertVector = new InsertVector();
        insertVector.insert(0, "default");
        for (int i = 0; i < 200; i++) {
            int res = insertVector.insert(i, "E-" + i);
            if (res < 0) {
                System.out.println("插入元素 " + i + " 失败");
            } else {
                System.out.println("插入元素 " + i + " 成功,size=" + insertVector.size + ", 容量：" + insertVector.capacity);
                System.out.println("最后一个元素是：" + insertVector.elementData[insertVector.size - 1]);
            }
        }
    }

    public int insert(int rank, Object element) {
        if (rank < 0) {
            return -1;
        }
        expand();
        for (int i = size; i > rank; i--) {
            elementData[i] = elementData[i - 1];
        }
        elementData[rank] = element;
        size++;
        return rank;
    }

    public boolean expand() {
        if ((size + 1) <= capacity) {
            return false;
        }
        int lo = 0, hi = size;
        capacity = capacity + (capacity / 2);
        Object[] newElementData = new Object[capacity];
        size = 0;
        while (lo < hi) {
            newElementData[size++] = elementData[lo++];
        }
        elementData = newElementData;
        return true;
    }

}
