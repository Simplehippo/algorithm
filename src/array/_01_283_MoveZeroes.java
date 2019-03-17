package array;

import java.util.Arrays;

public class _01_283_MoveZeroes {
    public void moveZeroes(int[] nums) {
        if(nums == null || nums.length == 0) {
            return;
        }

        int k = 0;
        int i = 0;
        while(i < nums.length) {
            if(nums[i] != 0) {
                nums[k++] = nums[i++];
            } else {
                i++;
            }
        }

        i = k;
        while(i < nums.length) {
            nums[i++] = 0;
        }

    }

    
    public static void main(String[] args) {
        int[] arr = {1, 3, 0, 4, 8, 0};
        new _01_283_MoveZeroes().moveZeroes(arr);
        System.out.println(Arrays.toString(arr));
    }

}
