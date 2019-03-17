package sort;

public class MergeSort {
    
    public static void main(String[] args) {
        // 创建测试数据
        int n = 1000000;
        int[] arr = new int[n];
        for(int i=0; i < arr.length; i++) {
            arr[i] = (int)(Math.random() * n);
        }

        long start = System.currentTimeMillis();

        // 归并排序自顶向下
        // 时间复杂度: O(nlogn) 空间复杂度: O(n)
        mergeSort(arr, 0, arr.length);


//        // 归并排序自底向上
//        // 时间复杂度: O(nlogn) 空间复杂度: O(n)
//        mergeSort2(arr, 0, arr.length);

        long end = System.currentTimeMillis();
        // cpu: intel i7 4710MQ
        // 十万    需要0.02s左右
        // 一百万  需要0.2s左右
        // 一千万  需要2s左右
        // 一亿    需要24s左右
        System.out.println((end - start) / 1000.0 + "s");


        //打印输出
//        System.out.println(Arrays.toString(arr));


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


    /**
     * 对arr [start, end) 内的元素进行自底向上的归并排序
     * @param arr
     */
    private static void mergeSort2(int[] arr, int start, int end) {
        int length = end - start;
        int[] temp = new int[length];
        for(int size = 1; size <= length; size *= 2) {
            for(int i = start; i + size < end; i += 2 * size) {
                merge(arr, i, i + size, Math.min(end, i + 2*size), temp);
            }
        }
    }



    /**
     * 对arr [start, end) 内的元素进行归并排序
     * @param arr   源数组
     * @param start 起始索引(包含)
     * @param end   结束索引(不包含)
     */
    private static void mergeSort(int[] arr, int start, int end) {
        // 创建一个临时数组
        int[] temp = new int[arr.length];

        // 开始归并排序的核心
        mergeSort(arr, start, end, temp);
    }



    /**
     * 归并排序的核心之一
     * 对arr [start, end) 内的元素进行归并排序, 复用同一个临时数组
     * @param arr   源数组
     * @param start 起始索引(包含)
     * @param end   结束索引(不包含)
     * @param temp  临时数组, 占据空间O(n)
     */
    private static void mergeSort(int[] arr, int start, int end, int[] temp) {
        // 如果只有一个元素, 就是有序的了, 不可再划分了, 直接return
        if(end - start <= 1) {
            return;
        }

        // 获取中值
        int mid = start + (end - start) / 2;

        // 递归的排序左部分
        mergeSort(arr, start, mid, temp);

        // 递归的排序右部分
        mergeSort(arr, mid, end, temp);

        // 将左右两部分已经有序的子序列合并
        // 优化点:
        //      两个有序子序列的合并之前已经有序就不必再做merge操作了
        //      此优化对有序的待排序序列来说十分有效
        //      例如: [1, 3, 5], [6, 9] = [1 , 3, 5, 6, 9] 这种就不用再merge了
        if(arr[mid - 1] > arr[mid]) {
            merge(arr, start, mid, end, temp);
        }
    }



    /**
     * 归并排序的核心之二
     * 对arr [start, end) 内的元素进行融合, 以mid为界
     * @param arr   源数组
     * @param start 起始索引(包含)
     * @param mid   两个待merge子序列的分界
     * @param end   结束索引(不包含)
     * @param temp  临时数组, 占据空间O(n)
     */
    private static void merge(int[] arr, int start, int mid, int end, int[] temp) {
        // 初始化两个有序序列的索引指针
        int i = start;
        int j = mid;

        // 初始化临时数组的索引指针
        int k = 0;

        // 归并排序之后数组的顺序由这个while循环确定
        while(i < mid && j < end) {
            // 升序merge
            if(arr[i] > arr[j])
                temp[k++] = arr[j++];
            else
                temp[k++] = arr[i++];
        }

        // 将左子序列剩余的值加入临时数组
        while(i < mid) {
            temp[k++] = arr[i++];
        }

        // 将右子序列剩余的值加入临时数组
        while(j < end) {
            temp[k++] = arr[j++];
        }

        // 将temp中的值复制到arr对应位置
        k = 0;
        while(start < end) {
            arr[start++] = temp[k++];
        }
    }


}
