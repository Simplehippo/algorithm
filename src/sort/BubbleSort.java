package sort;

public class BubbleSort {
    
    public static void main(String[] args) {
        // 创建测试数据
        int n = 1000;
        int[] arr = new int[n];
        for(int i=0; i < arr.length; i++) {
            arr[i] = (int)(Math.random() * n);
        }

        // 冒泡排序
        // 时间复杂度: O(n^2) 空间复杂度: 就地排序
        // 优化一: 如果一趟没有任何改变就结束遍历, 相关变量hasSwap
        // 优化二: 记录最后一次交换的位置, 此位置后必然是有序的, rightLastSwapIndex, right
        // 优化三: 双指针优化, 每一趟排序都找到当前序列最小值和最大值, leftLastSwapIndex, left
        int left = 0;
        int right = arr.length - 1;
        int leftLastSwapIndex = left;
        int rightLastSwapIndex = right;
        int sortedCount = 0; //只是为了记录排序的趟数
        for(int i = 0; i < arr.length; i++) {
            boolean hasSwap = false;
            // 正排找右边界
            for(int j = left; j < right; j++) {
                if(arr[j] > arr[j+1]) {
                    // 交换
                    int temp = arr[j+1];
                    arr[j+1] = arr[j];
                    arr[j] = temp;
                    hasSwap = true;
                    rightLastSwapIndex = j;
                }
            }
            right = rightLastSwapIndex;
            sortedCount ++;
            if(!hasSwap) {
                break;
            }

            // 倒排找左边界
            hasSwap = false;
            for(int j = right; j > left; j--) {
                if(arr[j-1] > arr[j]) {
                    // 交换
                    int temp = arr[j-1];
                    arr[j-1] = arr[j];
                    arr[j] = temp;
                    hasSwap = true;
                    leftLastSwapIndex = j;
                }
            }
            left = leftLastSwapIndex;
            sortedCount ++;
            if(!hasSwap) {
                break;
            }
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
        }
        else {
            System.out.println("递增无序");
        }
    }

}
