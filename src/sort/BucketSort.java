package sort;

import java.util.Arrays;

public class BucketSort {
    
    public static void main(String[] args) {
        // 创建测试数据
        // 以下示例排序一亿个人的年龄(0-99)
        int n = 100000000;
        int[] arr = new int[n];
        for(int i=0; i < arr.length; i++) {
            arr[i] = (int)(Math.random() * 100);
        }


        long start = System.currentTimeMillis();

        // 桶排序: 会占用大量内存
        // 最优时间复杂度: O(n) 空间复杂度: O(n)
        // 适用范围很小:
        //      1. 对内存优化要求不高
        //      2. 待排序的元素具有一定的范围(而且还不能太大)
        //*********************************************
        int[] data = new int[100];
        for(int i = 0; i < arr.length; i++) {
            data[arr[i]] ++;
        }

        long end = System.currentTimeMillis();
        // cpu: intel i7 4710MQ
        // 一百万  需要0.001s左右
        // 一千万  需要0.01左右
        // 一亿    需要0.1s左右
        // 三亿    需要0.3s
        // 时间是线性增加的
        System.out.println((end - start) / 1000.0 + "s");


        //打印输出
//        System.out.println(Arrays.toString(data));
//        System.out.println(Arrays.toString(arr));
    }
}
