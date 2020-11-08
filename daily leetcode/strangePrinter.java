class Solution {
    //for the worse case of index range [i,j],the printer needs j - i + 1 turns
    //for index k in [i,j],means [i...k...j], function f(a,b) means the min turns from a to b
    //that is: f(i,j) = f(i,k) + f(k + 1, j); (worse case, we will optimize it)
    //         if S[k] == S[j], f(i,j) -= 1; example: aba, ans= 2;
    //    so: f(i,j) = min(f(i,k) + f(k + 1, j) - 1 if S[k] == S[j] i<=k<=j)
    
    
    int[][] memo;
    public int strangePrinter(String s) {
        int N = s.length();
        memo = new int[N][N];
        return dp(s, 0, N - 1);
    }
    public int dp(String s, int i, int j) {
        if (i > j) return 0;
        if (memo[i][j] == 0) {
            int ans = dp(s, i+1, j) + 1;
            for (int k = i+1; k <= j; ++k)
                if (s.charAt(k) == s.charAt(i))
                    ans = Math.min(ans, dp(s, i, k-1) + dp(s, k+1, j));
            memo[i][j] = ans;
        }
        return memo[i][j];
    }
}
