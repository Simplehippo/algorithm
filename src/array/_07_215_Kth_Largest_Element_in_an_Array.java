package array;

public class _07_215_Kth_Largest_Element_in_an_Array {


    // 典型 top k 问题
    public int findKthLargest(int[] nums, int k) {
        if(nums == null || nums.length < k) {
            throw new IllegalArgumentException("Invalid parameters");
        }

        int l = 0;
        int r = nums.length;
        int i = partition(nums, 0, nums.length);
        while(i != (k - 1) && l < r) {
            if(i < (k - 1)) {
                l = i + 1;
                i = partition(nums, i + 1, r);
            } else {
                r = i;
                i = partition(nums, l, i);
            }
        }

        return nums[k-1];
    }

    // Two-way quick sort thinking
    private int partition(int[] nums, int l, int r) {
        int k = (int)(Math.random() * (r - l)) + l;
        swap(nums, k, l);

        int v = nums[l];
        int i = l + 1;
        int j = r - 1;
        while(i <= j) {
            while(i <= j && nums[i] > v) i++;
            while(i <= j && nums[j] < v) j--;
            if(i > j) break;
            swap(nums, i++, j--);
        }

        swap(nums, l, j);

        return j;
    }

    // Only for exchanging data
    private void swap(int[] nums, int index1, int index2) {
        int t = nums[index1];
        nums[index1] = nums[index2];
        nums[index2] = t;
    }


    public static void main(String[] args) {
        int[] arr = {3,2,3,1,2,4,5,5,6,7,7,8,2,3,1,1,1,10,11,5,6,2,4,7,8,5};
        int n = new _07_215_Kth_Largest_Element_in_an_Array().findKthLargest(arr, 20);
        System.out.println(n);
    }
}
