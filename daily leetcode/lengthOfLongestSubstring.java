class Solution {
    public int lengthOfLongestSubstring(String s) {
        int[] set = new int[128];
        int low = 0, hi = 0, max = 0;
        for(hi = 0; hi < s.length(); hi++){
            int temp = (int)s.charAt(hi);
            set[temp]++;
            while(set[temp] > 1){
                int tempt = (int)s.charAt(low);
                set[tempt]--;
                low++;
            }
            max = Math.max(max, hi - low + 1);
        }
        return max;
    }
}
