package array;

public class _09_125_Valid_Palindrome {

    public boolean isPalindrome(String s) {
        if(s == null) return false;
        if("".equals(s.trim())) return true;

        char[] chars = s.toCharArray();
        int i = 0;
        int j = chars.length - 1;
        while(i < j) {
            while(i < j && !isValidChar(chars[i])) i++;
            while(i < j && !isValidChar(chars[j])) j--;
            if(i >= j) break;
            if(!charEquals(chars[i++], chars[j--])) return false;  // equals ?
        }

        return true;
    }

    private boolean charEquals(char char1, char char2) {
        if(isDigit(char1) && isDigit(char2)) {
            return char1 == char2;
        }

        if(isLetter(char1) && isLetter(char2)) {
            int char1HigherVal = char1 & 0b11011111;
            int char2HigherVal = char2 & 0b11011111;
            return char1HigherVal == char2HigherVal;
        }

        return false;
    }

    private boolean isValidChar(char ch) {
        if(isDigit(ch) || isLetter(ch)) return true;
        return false;
    }

    private boolean isDigit(char ch) {
        if(ch >= '0' && ch <= '9') return true;
        return false;
    }

    private boolean isLetter(char ch) {
        if((ch >= 'A' && ch <= 'Z') || (ch >= 'a' && ch <= 'z')) return true;
        return false;
    }

    public static void main(String[] args) {
        String str = "A man, a plan, a canal: Panama";
        boolean result = new _09_125_Valid_Palindrome().isPalindrome(str);
        System.out.println(result);


    }


}
