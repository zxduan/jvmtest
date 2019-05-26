package cn.duanzx.datastruct._002_1;

import java.io.Serializable;

public class MyVector implements Serializable {

    private static final long serialVersionUID = -2901313983929652214L;

    public static final int DEFAULT_CAPACITY = 3;
    private int size;
    private int capacity;
    private Object[] elementData;

    public MyVector() {
        size = 0;
        capacity = DEFAULT_CAPACITY;
        elementData = new Object[capacity];
    }

    public int insert(int rank, Object element) {
        if (rank < 0) {
            return -1;
        }
        boolean isExpand = expand();
        if (isExpand) {
            System.out.println("进行了扩容，capacity = " + capacity);
        }
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
        capacity = capacity + (capacity >> 1);
        Object[] newElementData = new Object[capacity];
        size = 0;
        while (lo < hi) {
            newElementData[size++] = elementData[lo++];
        }
        elementData = newElementData;
        return true;
    }

    /**
     * hi必须比lo大
     */
    public int remove(int lo, int hi) {
        if (lo < 0 || lo == hi) {
            return 0;
        }
        while (hi < size) {
            elementData[lo++] = elementData[hi++];
        }
        size = lo;
        boolean isShrink = shrink();
        if (isShrink) {
            System.out.println("进行了缩容，size = " + size + ", 容量：" + capacity);
        }
        return hi - lo;
    }

    public boolean shrink() {
        if (size > (capacity >> 1)) {
            return false;
        }
        int newCapacity = capacity >> 1;
        capacity = newCapacity < DEFAULT_CAPACITY ? DEFAULT_CAPACITY : newCapacity;
        Object[] newElementData = new Object[capacity];
        for (int i = 0; i < size; i++) {
            newElementData[i] = elementData[i];
        }
        elementData = newElementData;
        return true;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public Object[] getElementData() {
        return elementData;
    }

    public void setElementData(Object[] elementData) {
        this.elementData = elementData;
    }
}
