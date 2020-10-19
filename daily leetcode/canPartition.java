class Solution {
    int[] nums;
    public boolean canPartition(int[] nums) {
        //法1，回溯， 超时
        int target=0;
        for(int i=0;i<nums.length;i++){
            target+=nums[i];
        }

        if(target%2==1)return false;
        target/=2;

     this.nums=nums;
      
        return dfs(0,target);
    }
  
  boolean dfs(int i, int target){
    if(target==0) return true;
    if(target<0 || i>=nums.length) return false;
    if(dfs(i+1, target-nums[i])) return true;
    for (; i + 1 < nums.length && nums[i] == nums[i + 1]; i++);
    return dfs(i+1, target);
    }
        /*
        //法2，DP
        int sum = 0;
        for(int num: nums){
            sum += num;
        }
        if(sum % 2 != 0) return false;
        sum /= 2;
        
        boolean[] dp = new boolean[sum + 1];
        
        dp[0] = true;
        
        for(int num: nums){
            for(int j = sum; j > 0; j--){
                if(j >= num)
                    dp[j] = dp[j] || dp[j - num];
            }
        }
        return dp[sum]; 
    }*/
    
}