package array;

import java.util.Arrays;

public class _05_75_Sort_Colors {

    public void sortColors(int[] nums) {
        if(nums == null || nums.length <= 1) {
            return;
        }

        // 三路快排思想
        //*******************************
        // [0, 0, 0, 0, 1, 1, 0, 1, 2, 2]
        //           lt    i        gt
        //*******************************
        int v = 1;
        int lt = -1;
        int gt = nums.length;
        int i = 0;
        while(i < gt) {
            if(nums[i] == v) {
                i++;
            } else if(nums[i] < v) {
                swap(nums, i++, ++lt);
            } else {
                swap(nums, i, --gt);
            }
        }
    }

    private void swap(int[] nums, int index1, int index2) {
        int t = nums[index1];
        nums[index1] = nums[index2];
        nums[index2] = t;
    }


    public static void main(String[] args) {
        int[] arr = {1, 1, 0, 1, 2, 1, 2, 1, 0, 2, 1};
//        int[] arr = {2, 2};
        new _05_75_Sort_Colors().sortColors(arr);
        System.out.println(Arrays.toString(arr));
    }
}
