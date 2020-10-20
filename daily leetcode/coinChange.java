/* 14ms 
class Solution {
    public int coinChange(int[] coins, int amount) {
        if(amount == 0) return 0;
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, -1);
        dp[0] = 0;
        for(int i = 1; i <= amount; i++){
            for(int j = 0; j < coins.length; j++){
                if(i >= coins[j] && dp[i - coins[j]] != -1){
                    if(dp[i] == -1 || dp[i] > dp[i - coins[j]] + 1){
                        dp[i] = dp[i - coins[j]] + 1;
                    }
                }
            }
        }
        return dp[amount];
    } 
} */


//1ms
class Solution {
    int ans;
    public int coinChange(int[] coins, int amount) {  
        Arrays.sort(coins);
        ans = Integer.MAX_VALUE;
        
        helper(coins, 0, amount, coins.length - 1);
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
    
    public void helper(int[] coins, int cur, int target, int start){
        if(start < 0) return;
        
        if(target % coins[start] == 0){
            ans = Math.min(ans, cur + target / coins[start]);
            return;
        }
        
        for(int i = target / coins[start]; i >= 0 ; i--){
            if(cur + i >= ans - 1) break;
            
            helper(coins, cur + i, target - i * coins[start], start - 1);
        }
    }
}
