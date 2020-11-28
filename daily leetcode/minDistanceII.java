class Solution {
    public int minDistance(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();
        if(n * m == 0) return m + n;
        int[][] dp = new int[n + 1][m + 1];
        /*
        for(int i = n - 1; i >= 0; i--){
            dp[i][m] = dp[i + 1][m] + 1;
        }
        for(int j = m - 1; j >= 0; j--){
            dp[n][j] = dp[n][j + 1] + 1;
        }
        
        for(int i = n - 1; i >= 0; i--){
            for(int j = m - 1; j >= 0; j--){
                if(word1.charAt(i) == word2.charAt(j)) dp[i][j] = dp[i + 1][j + 1];
                else{
                    dp[i][j] = Math.min(dp[i + 1][j] + 1, dp[i][j + 1] + 1);
                }
            }
        }
        return dp[0][0]; */
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                if (i == 0 || j == 0)
                    continue;
                if (word1.charAt(i - 1) == word2.charAt(j - 1))
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                else
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
            }
        }
        return n + m - 2 * dp[n][m];
    }
}
