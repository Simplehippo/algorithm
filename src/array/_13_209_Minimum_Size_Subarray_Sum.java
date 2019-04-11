package array;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

public class _13_209_Minimum_Size_Subarray_Sum {


//    // 第一版
//    public int minSubArrayLen(int s, int[] nums) {
//        if(nums == null || nums.length == 0) return 0;
//        int l = 0;
//        int r = -1;
//        int sum = 0;
//        int res = nums.length + 1;
//        while(l < nums.length) {
//            if(r + 1 < nums.length && sum < s) {
//                sum += nums[++r];
//            } else {
//                sum -= nums[l++];
//            }
//
//            if(sum >= s) {
//                res = Math.min(res, r - l + 1);
//            }
//        }
//
//        return res != nums.length + 1 ? res : 0;
//    }


    // 第二版, 更容易理解
    public int minSubArrayLen(int s, int[] nums) {
        if(nums == null || nums.length == 0) return 0;
        int sum = 0, res = 0;
        for(int l = 0, r = 0; r < nums.length; r++) {
            sum += nums[r];
            while(sum >= s) {
                res = (res != 0 && res < r - l + 1) ? res : r - l + 1;
                sum -= nums[l++];
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] arr = {2,3,1,2,4,3};
        int result = new _13_209_Minimum_Size_Subarray_Sum().minSubArrayLen(6, arr);
        System.out.println(result);
    }
}