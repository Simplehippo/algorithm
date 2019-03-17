package sort;

public class HeapSort {


    public static void main(String[] args) {


        // 堆排序一般用于在数据动态变化时获得最小/大值
        // 所以一般是直接将元素加入最小/大堆, 而不是用数组保存, 在从数组里拿
        // 堆并不保证元素有序, 所以消耗比归并要小, 相应的也比归并要快, 但是获取元素的时候也是耗时操作
        // 适用场景:
        //      1. 需要在一堆数据里动态的拿到最小/大值
        //      2. 在大量数字里找到前k小/大的k个数字
        //      3. 优先队列
        //


//        long startTime = System.currentTimeMillis();
//
//        // 创建测试数据, 并加入最小堆
//        int n = 100000000;
//        int[] arr = new int[n];
//        for(int i=0; i < arr.length; i++) {
//            arr[i] = (int)(Math.random() * n);
//        }
//        MinHeap minHeap = new MinHeap(arr);
//
//        long endTime = System.currentTimeMillis();
//        // cpu: intel i7 4710MQ
//        // 内存: 8G
//        // 时间:
//        //      十万     需要0.012s左右
//        //      一百万   需要0.051s左右
//        //      一千万   需要0.45s左右
//        //      一亿     需要4s左右
//        //      十亿     java.lang.OutOfMemoryError.....
//        //
//        System.out.println("堆heapify 耗时: " + (endTime - startTime) / 1000.0 + "s");
//        // 验证是否有序
//        boolean isSorted = true;
//        int pre = minHeap.peek();
//        while(!minHeap.isEmpty()) {
//            int cur = minHeap.pop();
////            System.out.println(cur);
//            if(cur < pre) {
//                isSorted = false;
//            }
//
//            pre = cur;
//        }
//
//        if(isSorted) {
//            System.out.println("递增有序");
//        }
//        else {
//            System.out.println("递增无序");
//        }



        // 就地堆排序
        // 创建测试数据, 并加入最小堆
        int n = 100000000;
        int[] arr = new int[n];
        for(int i=0; i < arr.length; i++) {
            arr[i] = (int)(Math.random() * n);
        }

        long startTime = System.currentTimeMillis();

        // 就地堆排序
        heapSort(arr, 0, arr.length);

        long endTime = System.currentTimeMillis();
        // cpu: intel i7 4710MQ
        // 内存: 8G
        // 时间:
        //      十万     需要0.02s左右
        //      一百万   需要0.2s左右
        //      一千万   需要3s左右
        //      一亿     需要44s左右
        //      十亿     java.lang.OutOfMemoryError.....
        //
        System.out.println("就地堆排序 耗时: " + (endTime - startTime) / 1000.0 + "s");

        // 验证是否有序
        boolean isSorted = true;
        for(int i=0; i < arr.length - 1; i++) {
            if(arr[i] > arr[i+1]) {
                isSorted = false;
            }
        }
        if(isSorted) {
            System.out.println("递增有序");
        }
        else {
            System.out.println("递增无序");
        }
    }


    public static void heapSort(int[] arr, int l, int r) {
        for(int i = (r - 1) / 2; i >= l; i--) {
            shiftDown(arr, r, i);
        }

        for(int i = r - 1; i > l; i--) {
            swap(arr, 0, i);
            shiftDown(arr, i, 0);
        }
    }

    public static void shiftDown(int[] arr, int end, int k) {
        while(k * 2 + 1 < end) {
            int j = k * 2 + 1;
            if(j + 1 < end && arr[j + 1] > arr[j]) {
                j++;
            }

            if(arr[k] >= arr[j]) {
                break;
            }

            swap(arr, k, j);
            k = j;
        }
    }

    public static void swap(int[] arr, int index1, int index2) {
        int temp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = temp;
    }
}


class MinHeap {
    // 这里没有使用ArrayList保存数据:
    //      1. ArrayList对大小做了限制 MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8 (21亿左右)
    //      2. ArrayList比数组的消耗要大, 8G内存, ArrayList不断扩容下排序一亿数据量就会内存溢出
    //*********************************************************************
    private int[] data;     // 数据容器
    private int size;       // 已经加入的元素个数
    private int capacity;   // 数组能容纳的最大容量, 就是待排序的元素的总个数


    public MinHeap(int capacity) {
        this.capacity = capacity;
    }

    public MinHeap(int[] arr) {
        heapify(arr);
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int getSize() {
        return size;
    }

    public void push(int e) {
        // 延迟数组的大容量初始化, 只在需要时才初始化
        if(data == null) {
            data = new int[capacity];
            size = 0;
        }

        data[size++] = e;
        shiftUp(size - 1);
    }

    public int pop() {
        int ret = data[0];
        swap(size - 1, 0);

        // 移除最后一个元素
        size --;

        shiftDown(0);
        return ret;
    }

    public int peek() {
        if(size == 0) {
            throw new IllegalArgumentException("heap is empty!");
        }
        return data[0];
    }

    public void heapify(int[] arr) {
        data = arr;
        size = data.length;
        for(int i = getParentIndex(data.length - 1); i >= 0; i--) {
            shiftDown(i);
        }
    }

    private void shiftUp(int index) {
        int i = index;
        while(i > 0) {
            // 获得父节点在数组中的索引位置
            int pIndex = getParentIndex(i);
            // 比较两者的值, 若比父节点的值小, 交换
            if(data[i] < data[pIndex]) {
                swap(i, pIndex);
                i = pIndex;
            }
            else {
                break;
            }
        }
    }

    private void shiftDown(int index) {
        int i = index;
        // 循环条件保证不越界
        while(getLeftChildIndex(i) < size) {
            int leftChildIndex = getLeftChildIndex(i);      // 能进入循环内必然是有左孩子的
            int rightChildIndex = getRightChildIndex(i);    // 但右孩子可能没有(索引超出size)
            int minIndex = leftChildIndex;                  // 左右孩子最小值的索引默认是左孩子的索引

            // 有右孩子, 就比较一下左右孩子, 找到最小值的索引
            if(rightChildIndex < size) {
                if(data[rightChildIndex] < data[leftChildIndex]) {
                    minIndex = rightChildIndex;
                }
            }

            // 没有右孩子且左右孩子最小值索引对应的值比待下沉的值要小就交换
            if(data[minIndex] < data[i]) {
                swap(i, minIndex);
                i = minIndex;
            }
            // 否则就是符合堆规范的, 直接break
            else {
                break;
            }
        }
    }


    private void swap(int index1, int index2) {
        int temp = data[index1];
        data[index1] = data[index2];
        data[index2] = temp;
    }


    private int getParentIndex(int index) {
        if(index == 0) {
            throw new RuntimeException("no parent!");
        }
        return (index - 1) / 2;
    }


    private int getLeftChildIndex(int pIndex) {
        return pIndex * 2 + 1;
    }


    private int getRightChildIndex(int pIndex) {
        return pIndex * 2 + 2;
    }

}