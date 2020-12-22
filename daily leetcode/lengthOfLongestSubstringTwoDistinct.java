class Solution {
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        int low = 0, hi = 0, max = 0, count = 0;
        int[] set = new int[128];
        for(hi = 0; hi < s.length(); hi++){
            int temp = (int) s.charAt(hi);
            set[temp]++;
            //if this char change from 0 -> 1, means we have a distinct char in our window
            //so increase the number of distinct chars
            if(set[temp] == 1) count++;
            //if the number of distinct chars is greater than 2, we need to move low forward
            while(count > 2){
                int tempt = (int) s.charAt(low);
                set[tempt]--;
                //when this char count change from 1 -> 0, there is no more this char in our window
                if(set[tempt] == 0) count--;
                low++;
            }
            max = Math.max(max, hi - low + 1);
        }
        return max;
    }
}
