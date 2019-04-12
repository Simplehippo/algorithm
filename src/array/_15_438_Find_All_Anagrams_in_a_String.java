package array;

import java.util.ArrayList;
import java.util.List;

public class _15_438_Find_All_Anagrams_in_a_String {

    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new ArrayList<>();
        if(s == null || p == null || s.length() < p.length()) {
            return res;
        }

        char[] ss = s.toCharArray();
        char[] ps = p.toCharArray();
        int[] freq = new int[26];
        int[] wind = new int[26];
        for(int i = 0; i < ps.length; i++) {
            freq[ps[i] - 'a'] ++;
            wind[ss[i] - 'a'] ++;
        }

        int l = 0;
        int r = ps.length - 1;
        while (r < ss.length) {
            if(isSame(freq, wind)) {
                res.add(l);
            }
            wind[ss[l++] - 'a'] --;
            if(++r < ss.length) {
                wind[ss[r] - 'a'] ++;
            }
        }

        return res;
    }


    public boolean isSame(int[] freq, int[] wind) {
        for(int i = 0; i < freq.length; i++) {
            if(freq[i] != wind[i]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String s = "abab";
        String p = "ab";
        List<Integer> list = new _15_438_Find_All_Anagrams_in_a_String().findAnagrams(s, p);
        System.out.println(list);
    }
}
