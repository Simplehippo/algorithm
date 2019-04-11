package array;

import java.util.Arrays;

public class _04_80_Remove_Duplicates_from_Sorted_Array_II {

    public int removeDuplicates(int[] nums) {
        if(nums == null || nums.length == 0) {
            return 0;
        }

        int k = 0;
        int i = 1;
        while(i < nums.length) {
            // 相等的时候判断是否是第一个（需要特殊处理）？不是的话就判断是否已经有了两个
            if(nums[i] == nums[k] && k-1 >= 0 && nums[k-1] == nums[k]) {
                i++;
            } else {
                nums[++k] = nums[i++];
            }
        }


        return k + 1;
    }

    public static void main(String[] args) {
        int[] arr = {0, 0, 0,0, 1, 1, 1, 2, 3, 3, 3, 8};
//        int[] arr = {0, 1};
        int size = new _04_80_Remove_Duplicates_from_Sorted_Array_II().removeDuplicates(arr);
        System.out.println(Arrays.toString(arr));
        for(int i=0; i<size; i++) {
            System.out.println(arr[i]);
        }
    }
}
