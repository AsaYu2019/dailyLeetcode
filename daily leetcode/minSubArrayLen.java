class Solution {
    public int minSubArrayLen(int s, int[] nums) {
        int res = Integer.MAX_VALUE;
        int low = 0, hi = 0;
        int sum = 0;
        for(hi = 0; hi < nums.length; hi++){
            sum += nums[hi];
            while(sum >= s){
                res = Math.min(res, hi - low + 1);
                sum -= nums[low];
                low++;
            }
        }
        return res == Integer.MAX_VALUE ? 0 : res;
    }
}
