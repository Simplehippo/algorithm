package array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public class _08_167_Two_Sum_II_Input_array_is_sorted {

    public int[] twoSum(int[] numbers, int target) {
        if(numbers == null || numbers.length == 0) {
            return null;
        }

        int i = 0;
        int j = numbers.length - 1;
        int[] result = null;
        while(i < j) {
            int sum = numbers[i] + numbers[j];
            if(sum > target) {
                j--;
            } else if(sum < target) {
                i++;
            } else {
                result = new int[]{i + 1, j + 1};
                break;
            }
        }

        return result;
    }


    public static void main(String[] args) {
        int[] arr = {2,7,11,15};
        int[] indexs = new _08_167_Two_Sum_II_Input_array_is_sorted().twoSum(arr, 9);
        System.out.println(Arrays.toString(indexs));
    }

}
