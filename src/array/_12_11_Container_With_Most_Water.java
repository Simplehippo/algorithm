package array;

public class _12_11_Container_With_Most_Water {

    public int maxArea(int[] height) {
        if(height == null) return -1;

        int i = 0;
        int j = height.length - 1;
        int max = -1;
        while(i < j) {
            int iVal = height[i];
            int jVal = height[j];
            int minVal = iVal <= jVal ? iVal : jVal;
            if((j - i) * minVal > max) {
                max = (j - i) * minVal;
            }
            if(iVal <= jVal) i++;
            else j--;
        }

        return max;
    }

    public static void main(String[] args) {
        int[] height = {1,8,6,2,5,4,8,3,7};
        int max = new _12_11_Container_With_Most_Water().maxArea(height);
        System.out.println(max);
    }
}
