package array;

import java.util.Arrays;

public class _03_26_Remove_Duplicates_from_Sorted_Array {
    public int removeDuplicates(int[] nums) {
        if(nums == null || nums.length == 0) {
            return 0;
        }

        int k = 0;
        int i = 0;
        while(i < nums.length) {
            if(nums[i] != nums[k]) {
                nums[++k] = nums[i++];
            } else {
                i++;
            }
        }

        return k + 1;
    }


    public static void main(String[] args) {
        int[] arr = {0, 1, 2, 3, 7, 8};
        int size = new _03_26_Remove_Duplicates_from_Sorted_Array().removeDuplicates(arr);
        System.out.println(Arrays.toString(arr));
        for(int i=0; i<size; i++) {
            System.out.println(arr[i]);
        }
    }

}
