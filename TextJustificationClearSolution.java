class Solution {
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> rst = new ArrayList<>();
        if (words == null || words.length == 0 || maxWidth == 0) {
            return rst;
        }
        int left = 0;
        while (left < words.length) {
            int right = findRight(left, words, maxWidth);
            rst.add(justify(left, right, words, maxWidth));
            left = right + 1;
        }
        return rst;
    }
    
    private int findRight(int left, String[] words, int maxWidth) {
        int start = left;
        int total = words[start].length();
        start += 1;
        while (start < words.length && (total + 1 + words[start].length()) <= maxWidth) {
            total += (1 + words[start].length());
            start++;
        }
        return start;
    }
    
    private String justify(int left, int right, String[] words, int maxWidth) {
        if (left == right) {
            return padResult(words[left], maxWidth);
        }
        
        boolean isLastLine = right == words.length - 1;
        int numSpaces = right - left;
        int totalSpaces = maxWidth - wordsLength(left, right, words);
        
        String space = isLastLine ? " " : blank(totalSpaces / numSpaces);
        int remainder = isLastLine ? 0 : totalSpaces % numSpaces;
        
        StringBuilder result = new StringBuilder();
        for (int i = left; i <= right; i++)
            result.append(words[i])
                .append(space)
                .append(remainder-- > 0 ? " " : "");
        
        return padResult(result.toString().trim(), maxWidth);
    }
    
    private int wordsLength(int left, int right, String[] words) {
        int total = 0;
        while (left <= right) {
            total += words[left].length();
            left++;
        }
        return total;
    }
    
    private String padResult(String result, int maxWidth) {
        return result + blank(maxWidth - result.length());
    }
    
    private String blank(int num) {
        return new String(new char[num]).replace('\0', ' ');
    }
}
