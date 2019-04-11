package sort;

public class QuickSort {
    public static void main(String[] args) {
        // 创建测试数据
        int n = 100000000;
        int[] arr = new int[n];
        for(int i=0; i < arr.length; i++) {
            arr[i] = (int)(Math.random() * n);
        }

        long startTime = System.currentTimeMillis();

        // 快排
        // 平均时间复杂度: O(nlogn)
        // 最坏时间复杂度: O(n^2)
        // 最好时间复杂度: O(n)

        // 普通快排
//        quickSort(arr, 0, arr.length);

        // 优化一: 随机轴
//        quickSort1(arr, 0, arr.length);

        // 优化二: 双路快排
//        quickSort2(arr, 0, arr.length);

        // 优化二: 三路快排
        quickSort3(arr, 0, arr.length);

        long endTime = System.currentTimeMillis();
        // cpu: intel i7 4710MQ
        // 内存: 8G
        // 以下的溢出是因为partition操作获得的p不合理, 进而划分不合理, 导致递归深度太大
        //************************************************************
        //         基本         优化一        优化二        优化三
        // 十万    0.02s        0.02s        0.025s        0.02s
        // 一百万  0.14s        0.14s        0.15s         0.05s
        // 一千万  1.5s/溢出     1.3s/溢出     1.6s         1.9s
        // 一亿    溢出          溢出         溢出          22s-24s
        //************************************************************
        System.out.println((endTime - startTime) / 1000.0 + "s");

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
            System.out.println("递增有序");
        }
    }




    /**
     * 基本快排的实现
     * @param arr
     * @param l
     * @param r
     */
    private static void quickSort(int[] arr, int l, int r) {
        if(r - l <= 1) {
            return;
        }

        int p = partition(arr, l, r);  //进行一次排序
        quickSort(arr, l, p);
        quickSort(arr, p + 1, r); //注意这里需要+1, 去掉中枢轴p
    }

    private static int partition(int[] arr, int l, int r) {
        // 单独这样可能性能极低, 容易退化成O(n^2), 需要增加优化
        int v = arr[l];

        int k = l;
        for(int j = l + 1; j < r; j++) {
            if(arr[j] < v) {
                //交换
                swap(arr, j, ++k);
            }
        }

        swap(arr, l, k);

        return k;
    }


    /**
     * 优化一: 在范围内随机一个数字当中心轴
     *      一定程度解决了中心轴划分不均的问题
     *      但是仍然有问题, 那就是当有大量重复元素是, 仍然会出现划分不均的情况
     * @param arr
     * @param l
     * @param r
     */
    private static void quickSort1(int[] arr, int l, int r) {
        if(r - l <= 1) {
            return;
        }

        int p = partition1(arr, l, r);  //进行一次排序
        quickSort(arr, l, p);
        quickSort(arr, p + 1, r); //注意这里需要+1, 去掉中枢轴p
    }

    private static int partition1(int[] arr, int l, int r) {
        // 优化一: 在范围内随机一个数字当中心轴
        int rand = (int)(Math.random() * (r - l)) + l;
        swap(arr, l, rand);

        int v = arr[l];

        int k = l;
        for(int j = l + 1; j < r; j++) {
            if(arr[j] < v) {
                //交换
                swap(arr, j, ++k);
            }
        }

        swap(arr, l, k);

        return k;
    }


    /**
     * 优化二: 双路快排
     *      进一步解决了中心轴划分不均的问题
     * @param arr
     * @param l
     * @param r
     */
    private static void quickSort2(int[] arr, int l, int r) {
        if(r - l <= 1) {
            return;
        }

        int p = partition2(arr, l, r);  //进行一次排序
        quickSort(arr, l, p);
        quickSort(arr, p + 1, r); //注意这里需要+1, 去掉中枢轴p
    }

    private static int partition2(int[] arr, int l, int r) {
        // 优化一: 在范围内随机一个数字当中心轴
        int rand = (int)(Math.random() * (r - l)) + l;
        swap(arr, l, rand);

        int v = arr[l];

        // 优化二: 对于大量重复数据, 双路i, j能划分出更平均的两部分
        int i = l + 1;
        int j = r - 1;

        while(i < j) {
            while(i < j && arr[i] < v) i++;
            while(i < j && arr[j] > v) j--;
            if(i >= j) {
                break;
            }
            swap(arr, i++, j--);
        }

        swap(arr, l, j);

        return j;
    }



    /**
     * 优化三: 三路快排
     *      解决大量重复元素的重复比较问题
     *      将元素划分为三部分 arr[i] < = > v , 只对< >部分的元素排序
     * @param arr
     * @param l
     * @param r
     */
    private static void quickSort3(int[] arr, int l, int r) {
        if(r - l <= 1) {
            return;
        }

        // partition3
        // 优化一: 随机在[l, r)中选一个中间轴
        int rand = (int)(Math.random() * (r - l)) + l;
        swap(arr, l, rand);

        int v = arr[l];


        //*******************************
        // [5, 1, 3, 2, 5, 5, 4, 1, 6]
        //  l        lt    i        gt r
        //*******************************
        int lt = l;         // 小于v的区域的游标, 指向最后一个小于v的索引
        int gt = r;         // 大于v的区域的游标, 指向最后一个大于v的索引
        int i = l + 1;      // 遍历的游标
        while(i < gt) {
            //第一种情况 arr[i] < v
            if(arr[i] < v) {
                swap(arr, i, lt + 1);
                i++;
                lt++;
            }
            // 第二种情况 arr[i] > v
            else if(arr[i] > v) {
                swap(arr, i, gt - 1);
                gt--;
            }
            // 第三种情况 arr[i] == v
            else {
                i++;
            }
        }

        //此处不维护lt, 因为在下面递归左部分时lt是[l, lt)开区间的右边界
        swap(arr, l, lt);


        // 分别对左<v, 右>v的部分进行递归
        quickSort3(arr, l, lt);
        quickSort3(arr, gt, r);
    }


    /**
     * 用于交换数组i, j位置的元素
     * @param arr
     * @param i
     * @param j
     */
    private static void swap(int[] arr, int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }
}
