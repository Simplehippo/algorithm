package array;

public class _11_345_Reverse_Vowels_of_a_String {

    public String reverseVowels(String s) {
        if(s == null) return null;
        if("".equals(s.trim())) return s;

        char[] chars = s.toCharArray();
        int i = 0;
        int j = chars.length - 1;
        while(i < j) {
            while(i < j && !isVowel(chars[i])) i++;
            while(i < j && !isVowel(chars[j])) j--;
            if(i >= j) break;
            // swap
            swap(chars, i++, j--);
        }

        return new String(chars);
    }

    private boolean isVowel(char ch) {
        ch = (char)(ch & 0b11011111); // to higher
        if(ch == 'A' || ch == 'E' || ch == 'I' || ch == 'O' || ch == 'U') {
            return true;
        }

        return false;
    }

    private void swap(char[] chars, int i, int j) {
        char t = chars[i];
        chars[i] = chars[j];
        chars[j] = t;
    }

    public static void main(String[] args) {
        String str = "leetcode";
        String res = new _11_345_Reverse_Vowels_of_a_String().reverseVowels(str);
        System.out.println(res);
    }

}
