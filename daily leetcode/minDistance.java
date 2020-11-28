class Solution {
    public int minDistance(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();
        //one of the word is null, we just need to insert every characters from another one
        if(n * m == 0) return m + n;
        
        //dp[i][j]: for 0~i-th character in word1,
        //          for 0~j-th character in word2,
        //          how many operations needed?
        int[][] dp = new int[n + 1][m + 1];
        
        //for word1's i length, and word2 is empty
        for(int i = 0; i < n + 1; i++){
            //for every i, we need to insert i times to make word1 == word2
            dp[i][0] = i;
        }
        //for every i, we need to insert i times to make word2 == word1
        for(int i = 0; i < m + 1; i++){
            dp[0][i] = i;
        }
        
        for(int i = 1; i < n + 1; i++){
            for(int j = 1; j < m + 1; j++){
                //case1:for example: 'abbc' and 'acc'
                int left_down = dp[i - 1][j - 1];//we saw word1[i] == word2[j] == c, for this positon we dont need any operatrions
                //case2:now we got 'abb','ac'
                int left = dp[i - 1][j] + 1;//if we known 'ab' to 'ac', means we got the operations are dp[i - 1][j]('ab' to 'ac') + 1(delet the last b of 'abb' after 'ab' to 'ac')
                int down = dp[i][j - 1] + 1;//if we known 'abb' to 'a', means we got the perations are dp[i][j - 1]('abb' to 'a') + 1(insert 'c' after 'abb' to 'a')
                
                if(word1.charAt(i - 1) != word2.charAt(j - 1)) left_down += 1;
                dp[i][j] = Math.min(left, Math.min(down, left_down));
            }
        }
        return dp[n][m];
    }
}
