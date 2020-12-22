class Solution {
   /* public int coinChange(int[] coins, int amount) {
        method 1, native dp
        dp[i][amount], i is the index of sorted coins[], amount is the amount
        for i-th coin, it's the biggest coin until now and we dont know how many we will use, so assume use k coins, and k < amount, so based on i-1th coins, we get:
            dp[i][amunt] = min(dp[i][amount], dp[i-1][amount - k*coins[i]] + k);
        further more, based on i-th coin, we can chose select 1 more coin_i to get amount or not select, so we get:
            dp[i][amunt] = min(dp[i][amount], dp[i][amount - coins[i]] + 1);
        because dp[i][amount - coins[i]] + 1 = dp[i-1][amount - (k - 1) *coins[i] - coins[i]] + k-1 + 1
        so for every states, its base on the same row in this 2D dp
        so we can decrease this 2D dp to 1D dp: for every row, covered it by new int
        
        int max = amount + 1;
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, max);
        dp[0] = 0;
        for(int i = 1; i <= amount; i++){
            for(int j = 0; j < coins.length; j++){
                if(coins[j] <= i){
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                }
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
        }*/
        //method 1: greedy + dfs
    int res = Integer.MAX_VALUE;
    public int coinChange(int[] coins, int amount) {
        //sort coins and select coins from bigger to smaller
        Arrays.sort(coins);
        helper(coins, 0, amount, coins.length - 1);
        return res == Integer.MAX_VALUE ? -1 : res;
    }
    
    //helper means: for coins, chose [start] coin to check if it actually divied target
    //if not, add the numbers to cur-numbers, minus it from target, move start to next and then DFS
    private void helper(int[] coins, int cur, int target, int start){
        //if we have select all the coins, return
        if(start < 0) return;
        
        if(target % coins[start] == 0){
            res = Math.min(res, cur + target/coins[start]);
        }else{
            for(int i = target/coins[start]; i >= 0; i--){
                if(cur + i > res - 1) break;
                helper(coins, cur + i, target - i * coins[start], start - 1);
            }
        }
    }
}
