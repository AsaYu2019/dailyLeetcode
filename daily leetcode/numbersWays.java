class Solution {
    /*Think about DP
        if we chose hats as the state, we need 40 bits to represent every hats.
        Instead, if we chose person as the state, we at most use 10 bits to represent
        
        for package problem,we always use multiple loop
            outside loop for every package
            inside loop for every state.
            
        so in this question, we use hats as the outloop, person who wear this hat as the state
    */
    public int numberWays(List<List<Integer>> hats) {
        int M = 1000000007;
        int n = hats.size();
        // build hat-person map, each hat could be weared by who.
        List<Integer>[] h = new List[40];
        for (int i = 0; i < 40; i++) h[i] = new ArrayList<>();
        for (int i = 0; i < hats.size(); i++) {
            for (int hat : hats.get(i)) {
                h[hat - 1].add(i);  //0-th begin
            }
        }
        
        int[][] dp = new int[41][1 << n];
        dp[0][0] = 1;
        //for every hat
        for (int i = 0; i < 40; i++) {
            //for every kind of state, j's 1s in bit represent i-th hat weared by this people
            for (int j = 0; j < 1 << n; j++) {
                //store this state to next hat, next hat will use it.
                dp[i+1][j] = dp[i][j];
                //for every people who can wear this hat
                for (int people: h[i]) {
                    int index = 1 << people; 
                    //if he has weared this hat
                    if ((j & index) > 0) {
                        dp[i+1][j] = (dp[i+1][j] + dp[i][j^index]) % M;
                    }
                }
            }
        }
        
        return dp[40][(1 << n) - 1];        
    }
}
