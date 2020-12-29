class Solution {
    public int longestSubstring(String s, int k) {
        char[] str = s.toCharArray();
        int[] countMap = new int[26];
        int maxUnique = getMaxUniqueLetters(s);
        int res = 0;
        for(int currUnique = 1; currUnique <= maxUnique; currUnique++){
            Arrays.fill(countMap, 0);
            int windowStart = 0, windowEnd = 0, index = 0, unique = 0, countAtLeastK = 0;
            while(windowEnd < str.length){
                if(unique <= currUnique){
                    index = str[windowEnd] - 'a';
                    if(countMap[index] == 0) unique++;
                    countMap[index]++;
                    if(countMap[index] == k) countAtLeastK++;
                    windowEnd++;
                }else{
                    index = str[windowStart] - 'a';
                    if(countMap[index] == k) countAtLeastK--;
                    countMap[index]--;
                    if(countMap[index] == 0) unique--;
                    windowStart++;
                }
                if(unique == currUnique && unique == countAtLeastK)
                    res = Math.max(res, windowEnd - windowStart);
            }
        }
        return res;
    }
    private int getMaxUniqueLetters(String s){
        boolean map[] = new boolean[26];
        int maxUnique = 0;
        for(int i = 0; i < s.length(); i++){
            if(!map[s.charAt(i) - 'a']){
                maxUnique++;
                map[s.charAt(i) - 'a'] = true;
            }
        }
        return maxUnique;
    }
}
