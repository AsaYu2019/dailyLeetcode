class Solution {
    //analysis: that is find the longest subarray that sum of subarray equals to sum - x;
    //so that we can use two pointers to find the longest subarray
    public int minOperations(int[] nums, int x) {
        int sum = 0, low = 0, hi = 0, cur = 0, max = -1;
        for(int i = 0; i < nums.length; i++){
            sum += nums[i];
        }
        int target = sum - x;
        if(target < 0) return -1;
        
        for(hi = 0; hi < nums.length; hi++){
            cur += nums[hi];
            //if cur sum > target, we move low to next
            while(cur > target && low <= hi){
                cur -= nums[low];
                low++;
            }
            //after while, if cur sum == target
            if(cur == target){
                max = Math.max(max, hi - low + 1);
            }
        }
        return max != -1 ? nums.length - max : -1;
    }
}
