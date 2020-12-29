class Solution {
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        int low = 0;
        int hi = 0;
        int res = 0;
        int[] set = new int[128];
        for(hi = 0; hi < s.length(); hi++){
            char c = s.charAt(hi);
            set[c]++;
            if(set[c] == 1) k--;
            if(k >= 0) res = Math.max(res, hi - low + 1);
            while(k < 0){
                char l = s.charAt(low);
                set[l]--;
                if(set[l] == 0) k++;
                low++;
            }
        }
        return res;
    }
}
