package array;

import java.util.Arrays;

public class _10_344_Reverse_String {

    public void reverseString(char[] s) {
        if(s == null) return;

        int i = 0;
        int j = s.length - 1;
        while(i < j) {
            swap(s, i++, j--);
        }
    }

    private void swap(char[] chars, int i, int j) {
        char t = chars[i];
        chars[i] = chars[j];
        chars[j] = t;
    }

    public static void main(String[] args) {  
        String str = "hello";
        char[] chars = str.toCharArray();
        new _10_344_Reverse_String().reverseString(chars);
        System.out.println(Arrays.toString(chars));
    }
}
