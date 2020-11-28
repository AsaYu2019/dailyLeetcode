class Solution {
    public int maxDotProduct(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int m = nums2.length;
        
        int[][] dp = new int[n][m];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                dp[i][j] = nums1[i] * nums2[j];
                //take both nums1[i] and nums2[j], and if dp[i - 1][j - 1] < 0, we only take i,j
                //now dp[i][j] == nums1[i] * nums2[j],so we can plus it, and no need to do a mutip
                if(i > 0 && j > 0) dp[i][j] = Math.max(0, dp[i - 1][j - 1]) + dp[i][j];
                //if we don't take nums1[i], the answer should be the same with dp[i - 1][j] or 
                //if dp[i - 1][j] < dp[i][j], we just keep dp[i][j]
                if(i > 0) dp[i][j] = Math.max(dp[i][j], dp[i - 1][j]);
                //if we don't take nums2[j], the answer should be the same with dp[i][j - 1] or 
                //if dp[i][j - 1] < dp[i][j], we just keep dp[i][j]
                if(j > 0) dp[i][j] = Math.max(dp[i][j], dp[i][j - 1]);
                
                //all the three if represent: 
                //max(dp[i - 1][j],dp[i][j - 1], max(0, dp[i - 1][j - 1]) + dp[i][j])
                //for 3 cases: no take i, no take j, take both i and j, and chose the maximum one
            }
        }
        return dp[n - 1][m - 1];
    }
}
