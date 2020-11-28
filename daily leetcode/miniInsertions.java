class Solution {
    public int minInsertions(String s) {
        int n = s.length();
        //dp[i][j]: the length of longest common sequence between i-th in head part and j-th in tail part
        //translate this problem to find longest common subsequence to its resversed string
        //minInsertions(s) = n - longthPalindromeSubSeq(s) = n - longthPalindromeSubSeq(s, reversed(s))
        //找到最长回文子序列，其余的不是最长回文子序列的部分自然就是需要互相insertion到s中的个数了
        //如何找？设二维dp，将s与s的reverse做比较得出
        int[][] dp = new int[n + 1][n + 1];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++)//遍历s的reverse
                //如果char相同，则common subseq + 1
                if(s.charAt(i) == s.charAt(n - 1 - j)){
                    dp[i + 1][j + 1] = dp[i][j] + 1;
                }else{//若不同，则取s中和取s.reverse中较大的那个
                    dp[i + 1][j + 1] = Math.max(dp[i][j + 1], dp[i + 1][j]);
                }
        }
        return n - dp[n][n];
    }
}
