package array;

public class _16_76_Minimum_Window_Substring {
    public String minWindow(String s, String t) {
        if(s == null || t == null || s.length() < t.length()) {
            return "";
        }

        String res = "";
        char[] ss = s.toCharArray();
        char[] ts = t.toCharArray();
        int[] freq = new int[128];
        int[] wind = new int[128];
        for(int i = 0; i < ts.length; i++) {
            freq[ts[i]] ++;
            wind[ss[i]] ++;
        }

        int l = 0;
        int r = ts.length - 1;
        while(r < ss.length) {
            if(contains(wind, freq)) {
                do {
                    res = (!"".equals(res) && res.length() < (r - l + 1)) ? res : new String(ss, l, r - l + 1);
                    wind[ss[l++]] --;
                } while(wind[ss[l-1]] >= freq[ss[l-1]]);
            }

            if(++r < ss.length) {
                wind[ss[r]] ++;
            }
        }

        return res;
    }


    public boolean contains(int[] wind, int[] freq) {
        for(int i = 0; i < freq.length; i++) {
            if(wind[i] < freq[i]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String s = "ADOBECODEBANC";
        String t = "ABC";
        String result = new _16_76_Minimum_Window_Substring().minWindow(s, t);
        System.out.println(result); // 预期:"BANC"
    }
}
