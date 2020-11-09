class Solution {
    //have solution: (n - 1) % (k - 1) == 0;
    //               (k-1)(k-1)...(k - 1) (k-1) (1)
    //            ->  (k-1)(k-1)...(k-1) (1)
    //or this stones have no solution
    
    //although the sum of stones will be not change, but the times of change for every stone may change.
    // for example:  3,2,4,1    case1                   case 2
    //                     5   [3,2] 4,1            6  3,[2,4],1
    //                     5   [3,2][4,1]           9  [3,2,4],1
    //                     10  [3,2,4,1]            10 [3,2,4,1]
    // the result of merged stone are 10, but
    // for case 1: evety stone only merge twice, res = (3+2+4+1) * 2 = 20
    // for case 2: 2 and 4 are merged three times, res = (2 + 4) * 3 + 3 * 2 + 1 = 25
    // so case 1 is our goal
    // intuition: the bigger the stone, the less the merge times to get the minimum cost
    //and： sum(stones[i]~stones[j]) is dependent of previous merges.
    
    // dp[i][j][k] -> subarray [i,j] merge to k piles
    //so that dp[i][i][1] = 0, stones[i] merge to 1 piles, actually don't need to do anything
    //so for a subarray(i,j) we divide it by m, that is (i, m) and (m + 1, j), and length of (m+1,j) are k -1
    //so that when we merge subarray(i,m) to 1 pile, next time we can merge subarray(i,m) and (m+1,j) to 1 pile
    //so for every subarray(i,j) merged to k piles we got the funtion,
    //that is: dp[i][j][k] = min(dp[i][m][1] + dp[m+1][j][k-1])
    //                           (i,m) to 1 pile   (m+1,j) to k-1 piles
    //          attention, key step: find the m, merge to i pile at the left part
    //  when we wanna merge (i,j) to 1 pile, we need to add all the stones of subarray(i,j) based on dp[i][j][k]
    //         that is : dp[i][j][1] = dp[i][j][k] + sum(i,j)
    // the res is stored in dp[0][n - 1][1]
  /*  public int mergeStones(int[] stones, int K) {
        int n = stones.length;
        if((n - 1) % (K - 1) != 0) return -1;
        int[] prefixSum = new int[n + 1];//store sum, to caculate sum(i,j)
        for(int i = 0; i < n; i++){
            prefixSum[i + 1] = prefixSum[i] + stones[i]; 
        }
        int[][][] dp = new int[n][n][K + 1];
        for(int i = 0; i < n; i++){
            dp[i][i][1] = 0;
        }
        //bottom up, from small to large
        for(int l = 2; l < n; l++){ // subproblem length
            for(int i = 0; i <= n - l; i++){
                //子问题的左边的起始范围
                int j = i + l - 1; //子问题的右边终止范围
                for(int k = 2; k <= K; k++){//将子问题划分成k堆
                    for(int m = i; m < j; m += K - 1){//尝试确定分割点m
                        dp[i][j][k] = Math.min(dp[i][j][k],dp[i][m][1] + dp[m + 1][j][k - 1]);
                    }
                }
                dp[i][j][1] = dp[i][j][K] + prefixSum[j + 1] - prefixSum[i];
            }    
        }
        return dp[0][n - 1][1];
    } */
    public int mergeStones(int[] stones, int K) {
        int n = stones.length;
        if ((n - 1) % (K - 1) > 0) return -1;

        int[] prefix = new int[n+1];
        for (int i = 0; i <  n; i++)
            prefix[i + 1] = prefix[i] + stones[i];

        int[][] dp = new int[n][n];
        for (int m = K; m <= n; ++m)
            for (int i = 0; i + m <= n; ++i) {
                int j = i + m - 1;
                dp[i][j] = Integer.MAX_VALUE;
                for (int mid = i; mid < j; mid += K - 1)
                    dp[i][j] = Math.min(dp[i][j], dp[i][mid] + dp[mid + 1][j]);
                if ((j - i) % (K - 1) == 0)
                    dp[i][j] += prefix[j + 1] - prefix[i];
            }
        return dp[0][n - 1];
    }
}
