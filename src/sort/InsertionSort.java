package sort;

import java.util.Arrays;

public class InsertionSort {
    public static void main(String[] args) {
        // *****************************************
        // 移动法与交换法:
        //        两者区别就在插入元素的方法不同而已
        //        我个人认为性能区别不大
        //
        // 移动法:
        //        先记录待插入元素的值,
        //        然后在循环内直接向后覆盖,
        //        再在退出循环时将保存的值插入对应位置即可
        //
        // 交换法:
        //        不需要记录元素的值
        //        直接交换两个元素的位置
        //
        // 以下不再说明移动法与交换法, 以下都使用了交换法
        // *****************************************

        // 创建测试数据
        int n = 100000;
        int[] arr = new int[n];
        for(int i=0; i < arr.length; i++) {
            arr[i] = (int)(Math.random() * n);
        }

        // 直接插入排序
        int[] arr1 = Arrays.copyOf(arr, arr.length);
        directInsertionSort(arr1);

        // 折半插入排序
        int[] arr2 = Arrays.copyOf(arr, arr.length);
        binaryInsertionSort(arr2);

        // 希尔缩小增量排序
        int[] arr3 = Arrays.copyOf(arr, arr.length);
        shellSort(arr3);
    }




    /**
     * 直接插入排序
     * @param arr   原数组
     */
    private static void directInsertionSort(int[] arr) {
        long startTime = System.currentTimeMillis();

        // 直接插入排序
        // 时间复杂度: O(n^2) 空间复杂度: 就地排序
        for(int i = 0; i < arr.length; i++) {
            int k = i;
            // 此循环控制排序后的顺序
            for(int j = i - 1; j >= 0 && arr[k] < arr[j]; j--) {
                // 交换
                int temp = arr[k];
                arr[k] = arr[j];
                arr[j] = temp;
                k = j;
            }
        }

        long endTime = System.currentTimeMillis();
        // cpu: intel i7 4710MQ
        // 十万    需要2.5s左右
        // 一百万  需要很长时间...
        System.out.println("直接插入排序 耗时: " + (endTime - startTime) / 1000.0 + "s 序列状态: " + getSortedStr(arr));
    }




    /**
     * 折半插入排序
     * @param arr   原数组
     */
    private static void binaryInsertionSort(int[] arr) {
        long startTime = System.currentTimeMillis();

        // 折半插入排序
        // 数组容器: 时间复杂度: O(n^2 * logn) 空间复杂度: 就地排序
        // 链表容器: 时间复杂度: O(n^2 * logn) 空间复杂度: 就地排序
        for(int i = 0; i < arr.length; i++) {
            int k = binarySearchInsertIndex(arr, 0, i, arr[i]);
            if(k != i) {
                // 插入索引对应处
                int j = i-1;
                while(k <= j) {
                    // 交换
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                    j--;
                }
            }
        }

        long endTime = System.currentTimeMillis();
        // 思考:  为什么二分找位置反而更慢了?
        // 个人解答:
        //      因为二分查到到位置之后仍然要将插入位置之后的值向后移动,
        //      所以二分查找这一步对数组容器而言其实是不必要的多余操作
        // 个人结论:
        //      折半插入排序不适用于数组等连续内存的结构,
        //      也不太适合链表等不连续内存存储的动态结构,
        //      因为即使是链表也有搜索遍历需要消耗的时间
        //      综合来看个人认为不如直接用直接插入, 实现较简单
        //
        // cpu: intel i7 4710MQ
        // 十万    需要5s左右
        // 一百万  需要很长时间...
        System.out.println("折半插入排序 耗时: " + (endTime - startTime) / 1000.0 + "s 序列状态: " + getSortedStr(arr));
    }





    /**
     * 用于折半插入排序
     * 找到e应该插入的位置
     * @param arr   原数组
     * @param start 起始索引(包含)
     * @param end   结束索引(包含)
     * @param e     待插入元素的值
     * @return      待插入元素应该插入的索引位置
     */
    private static int binarySearchInsertIndex(int[] arr, int start, int end, int e) {
        while(start <= end) {
            int middle = (end - start) / 2 + start;
            if (arr[middle] > e)
                end = middle - 1;
            else
                start = middle + 1;
        }

        return start;
    }





    /**
     * 希尔缩小增量排序
     * @param arr   原数组
     */
    private static void shellSort(int[] arr) {
        long startTime = System.currentTimeMillis();

        // 希尔缩小增量排序
        // 增量序列: [1, 2, 4, 8, .....]
        // 时间复杂度: O(n^(3/2)) 空间复杂度: 就地排序
        for(int increment = arr.length / 2; increment > 0; increment = increment / 2) {
            // 从increment开始, 代表从各个分组的第二个元素开始插入合适的位置
            for(int i = increment; i < arr.length; i++) {
                // 首先记录一下待插入的元素的初始索引位置
                int j = i;
                // 希尔的核心, 只在当前分组进行比较
                // 此while循环条件控制排序后的顺序
                // 数组不越界并且该元素仍没有插入正确的位置时继续循环
                while(j - increment >= 0 && arr[j] < arr[j - increment]) {
                    // 交换
                    int temp = arr[j - increment];
                    arr[j - increment] = arr[j];
                    arr[j] = temp;
                    // 用于保证只在其分组进行比较
                    j -= increment;
                }
            }
        }

        long endTime = System.currentTimeMillis();
        // cpu: intel i7 4710MQ
        // 增量序列: [1, 2, 4, 8, .....]
        // 十万    需要0.02s左右
        // 一百万  需要0.3s左右
        // 一千万  需要4s左右
        // 一亿    需要60s左右
        System.out.println("希尔增量排序 耗时: " + (endTime - startTime) / 1000.0 + "s 序列状态: " + getSortedStr(arr));

    }


    /**
     * arr数组是否是递增有序的
     * @param arr 原数组
     */
    public static String getSortedStr(int[] arr) {
        // 验证是否递增有序
        boolean isSorted = true;
        for(int i=0; i < arr.length - 1; i++) {
            if(arr[i] > arr[i+1]) {
                isSorted = false;
            }
        }
        if(isSorted) {
            return "递增有序";
        }
        else {
            return "递增无序";
        }
    }

}
