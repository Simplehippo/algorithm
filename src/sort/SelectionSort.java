package sort;

import java.util.Arrays;

public class SelectionSort {
    
    public static void main(String[] args) {
        // 创建测试数据
        int n = 10;
        int[] arr = new int[n];
        for(int i=0; i < arr.length; i++) {
            arr[i] = (int)(Math.random() * n);
        }


        // 选择排序
        int sortedCount = 0;
        for(int i = 0; i < arr.length; i++) {
            for(int j = i + 1; j < arr.length; j++) {
                if(arr[i] > arr[j]) {
                    // 交换
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
            sortedCount ++;
            System.out.println(Arrays.toString(arr));
        }


        // 验证是否有序
        boolean isSorted = true;
        for(int i=0; i < arr.length - 1; i++) {
            if(arr[i] > arr[i+1]) {
                isSorted = false;
            }
        }
        if(isSorted) {
            System.out.println("递增有序, 排序的趟数: " + sortedCount);
        } else {
            System.out.println("递增无序");
        }
    }
}
