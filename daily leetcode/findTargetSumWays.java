class Solution {
//dp笔记已收录
   /* brute force DFS 
   int res = 0;
    public int findTargetSumWays(int[] nums, int S) {
        dfs(nums, 0, 0, S);
        return res;
    }
    private void dfs(int[] nums, int i, int count, int S ){
        if(i == nums.length){
            if(count == S){
                res++;
                return;
            }
        }else{
            dfs(nums, i + 1, count + nums[i], S);
            dfs(nums, i + 1, count - nums[i], S);
        }
    } */
    /*dfs + memo
    public int findTargetSumWays(int[] nums, int S) {
        int[][] memo = new int[nums.length][2001];
        for(int[] row : memo){
            Arrays.fill(row, Integer.MIN_VALUE);
        }
        return dfs(nums, 0, 0, S, memo);
    }
    
    private int dfs(int[] nums, int i, int count, int S, int[][] memo){
        if(i == nums.length){
            if(count == S){
                return 1;   //数字用完，条件达成，返回这一种组合，1
            }else{
                return 0;
            }
        }else{
            if(memo[i][count + 1000] != Integer.MIN_VALUE){
                return memo[i][count + 1000];
            }
            int add = dfs(nums, i + 1, count + nums[i], S, memo);
            int sub = dfs(nums, i + 1, count - nums[i], S, memo);
            memo[i][count + 1000] = add + sub;
            return memo[i][count + 1000];
        }
    } */
    
    public int findTargetSumWays(int[] nums, int S) {
        int[][] dp = new int[nums.length][2001];
        dp[0][nums[0] + 1000] = 1;
        dp[0][-nums[0] + 1000] += 1;
        for(int i = 1; i< nums.length; i++){
            for(int sum = -1000; sum <= 1000; sum++){
                if(dp[i - 1][sum + 1000] > 0){
                    dp[i][sum + nums[i] + 1000] += dp[i - 1][sum + 1000];
                    dp[i][sum - nums[i] + 1000] += dp[i - 1][sum + 1000];
                }
            }
        }
        return S > 1000 ? 0: dp[nums.length - 1][S + 1000];
    }
}
