class Solution {
    public int maxProduct(int[] nums) {
        if(nums.length == 0) return 0;
        int max_so_far = nums[0];
        int min_so_far = nums[0];
        int res = nums[0];
        
        for(int i = 1; i < nums.length; i++){
            int cur = nums[i];
            int temp_max = Math.max(cur, Math.max(max_so_far * cur, min_so_far * cur));
            min_so_far = Math.min(cur, Math.min(min_so_far * cur, max_so_far * cur));
            
            max_so_far = temp_max;
            res = Math.max(max_so_far, res);
        }
        return res;
    }
}
