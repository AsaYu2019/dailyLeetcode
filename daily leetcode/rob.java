class Solution {
    /*method 1,递归，对于nums.length - 1处，有rob或不rob的选择，取决于其rob了前一个或没有rob前一个获得的的最大值
    但是TLE
    public int rob(int[] nums) {
        return rob(nums, nums.length - 1);
    }
    private int rob(int[] nums, int n){
        if(n < 0) return 0;
        return Math.max(rob(nums, n - 2) + nums[n], rob(nums, n - 1));
    }*/
    
    /*method 2, 递归 + memo, top - down
    依然TLE
    int[] memo;
    public int rob(int[] nums) {
        memo = new int[nums.length + 1];
        return rob(nums, nums.length - 1);
    }
    private int rob(int[] nums, int n){
        if(n < 0) return 0;
        if(memo[n] > 0) return memo[n];
        int res = Math.max(rob(nums, n - 2) + nums[n], rob(nums, n - 1));
        memo[n] = res;
        return res;
    }
    */
    
    /*method 3 非递归遍历 + memo, bottom - up 
    faster than 100%,但space略多
    
    public int rob(int[] nums) {
        if(nums.length == 0) return 0;
        int[] memo = new int[nums.length + 1];
        memo[0] = 0;
        memo[1] = nums[0];
        for(int i = 1; i < nums.length; i++){
            int val = nums[i];
            观察：每次for循环只用到了memo数组中的前两个数，其他无用，则可简化为两个变量动态维护
            memo[i + 1] = Math.max(memo[i - 1] + val, memo[i]);
        }
        return memo[nums.length];
    }*/
    
    /*method 4 非递归遍历 + 2变量*/
    public int rob(int[] nums){
        if(nums.length == 0) return 0;
        int pre1 = 0;
        int pre2 = 0;
        for(int i = 0; i < nums.length; i++){
            int temp = pre1;
            pre1 = Math.max(pre2 + nums[i], pre1);
            pre2 = temp;
        }
        return pre1;
    }
}
