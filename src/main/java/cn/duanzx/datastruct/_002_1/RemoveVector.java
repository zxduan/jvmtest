package cn.duanzx.datastruct._002_1;

import org.junit.Test;
import org.omg.CORBA.PUBLIC_MEMBER;

/**
 * 实现Vector区间删除算法
 * 删除指定区间内的元素后，后面的元素要补上，另外size要更新，然后返回被删除的元素的数目
 * 如果有必要，要进行缩容shrink
 */
public class RemoveVector {
    public static final int DEFAULT_CAPACITY = 3;
    private int size;
    private int capacity;
    private Object[] elementData;

    public RemoveVector() {
        size = 0;
        capacity = DEFAULT_CAPACITY;
        elementData = new Object[capacity];
    }

    @Test
    public void test() {
        MyVector myVector = new MyVector();
        myVector.insert(0, "default");
        for (int i = 0; i < 200; i++) {
            int res = myVector.insert(i, "E-" + i);
            if (res < 0) {
                System.out.println("插入元素 " + i + " 失败");
            } else {
                System.out.println("插入元素 " + i + " 成功,size=" + myVector.getSize() + ", 容量：" + myVector.getCapacity());
                System.out.println("最后一个元素是：" + myVector.getElementData()[myVector.getSize() - 1]);
            }
        }
        myVector.remove(2, 102);
        int i = 0;
        for (Object obj : myVector.getElementData()) {
            System.out.println(obj);
            i++;
        }
        System.out.println(i);
    }


}
