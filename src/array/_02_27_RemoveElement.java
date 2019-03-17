package array;

import java.util.Arrays;

public class _02_27_RemoveElement {
    public int removeElement(int[] nums, int val) {
        if(nums == null || nums.length == 0) {
            return 0;
        }

        int k = 0;
        int i = 0;
        while(i < nums.length) {
            if(nums[i] != val) {
                nums[k++] = nums[i++];
            } else {
                i++;
            }
        }

        return k;
    }

    public static void main(String[] args) {
        int[] arr = {1, 3, 2, 4, 8, 2};
        int size = new _02_27_RemoveElement().removeElement(arr, 2);
        System.out.println(Arrays.toString(arr));
        for(int i=0; i<size; i++) {
            System.out.println(arr[i]);
        }
    }
}
