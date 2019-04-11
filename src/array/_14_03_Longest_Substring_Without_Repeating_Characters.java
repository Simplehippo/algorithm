package array;

public class _14_03_Longest_Substring_Without_Repeating_Characters {

    public int lengthOfLongestSubstring(String s) {
        int[] freq = new int[256];
        int res = 0;
        char[] cs = s.toCharArray();
        for(int l = 0, r = 0; r < cs.length; r++) {
            while(freq[cs[r]] != 0) {
                freq[cs[l++]] --;
            }
            freq[cs[r]] ++;
            res = Math.max(res, r - l + 1);
        }
        return res;
    }

    public static void main(String[] args) {
        String s = "abcabcbb";
        int result = new _14_03_Longest_Substring_Without_Repeating_Characters().lengthOfLongestSubstring(s);
        System.out.println(result);
    }
}
