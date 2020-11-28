class Solution {
    public int minimumDeleteSum(String s1, String s2) {
        int n = s1.length();
        int m = s2.length();
        //dp:we delet all characters from s1,s2, util both of them are empty, say dp[0][0]
        int[][] dp = new int[n + 1][m + 1];
        //to use memory of dp, we take dp from tail to the front
        //init, if we need delete all chars of s1
        for(int i = n - 1; i >= 0; i--){
            dp[i][m] = dp[i + 1][m] + s1.codePointAt(i);
        }
        //init, if we need to delete all chars of s2
        for(int i = m - 1; i >= 0; i--){
            dp[n][i] = dp[n][i + 1] + s2.codePointAt(i);
        }
        
        for(int i = n - 1; i >= 0; i--){
            for(int j = m - 1; j >= 0; j--){
                //if these two chars are equal, we don't need to update
                if(s1.charAt(i) == s2.charAt(j)){
                    dp[i][j] = dp[i + 1][j + 1];
                }else{//if they are not equal, find the smaller way between delete from s1 or s2
                    dp[i][j] = Math.min(dp[i + 1][j] + s1.codePointAt(i),
                                        dp[i][j + 1] + s2.codePointAt(j));
                }
            }
        }
        return dp[0][0];
    }
}
