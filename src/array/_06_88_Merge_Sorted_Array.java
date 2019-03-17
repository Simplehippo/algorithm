package array;

import java.util.Arrays;

public class _06_88_Merge_Sorted_Array {

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1;
        int j = n - 1;
        int k = n + m - 1;
        while(i >= 0 && j >= 0) {
            if(nums1[i] > nums2[j]) {
                nums1[k--] = nums1[i--];
            } else {
                nums1[k--] = nums2[j--];
            }
        }

        while(j >= 0) {
            nums1[k--] = nums2[j--];
        }
    }


    public static void main(String[] args) {
        int[] nums1 = {1, 2, 3, 3, 0, 0, 0};  // m = 4
        int[] nums2 = {2, 5, 6};//n = 3
        new _06_88_Merge_Sorted_Array().merge(nums1, 4, nums2, 3);
        System.out.println(Arrays.toString(nums1));
    }
}
