class Solution {
    /*method 1 dfs
    int[][] dp;
    public int getMoneyAmount(int n) {
        dp = new int[n + 1][n + 1];
        在[1,n]范围上取值；
        return DFS(1, n);
    }
    private int DFS(int left, int right){
    base case
        if(left >= right) return 0;
        难点1：若左右差值为1，则我们选左边值，例如
        现在只剩下4，5二个数字，case1：目标为4时：我们选4，总cost 0
                                            我们选5，总cost 5（因为再次选到4不花钱）
                            case 2: 目标为5时：我们选4，总cost 4
                                            我们选5， 总cost 0；
                            综上，选4时不论目标为多少，最多只花4
                                 选5时不论目标为多少，最多花5，
                        故选4，即left
        if(left + 1 == right)  return left;
        if(dp[left][right] != 0) return dp[left][right];
        
        预设res为最大值
        int res = Integer.MAX_VALUE;
        for(int i = left; i <= right; i++){
        关键点1： 若本次选i，则本次选择之后本身的i，要加上之后能够cover左边的情况和右边的情况的数额
            那么当然是哪边需要的数额大就用哪边的数额来加到i上，意义为：
            不论选了i后，目标数字在左边还是在右边，我temp都能cover它；
            int temp = i + Math.max(DFS(left, i - 1), DFS(i + 1, right));
        关键点2：但结果上，因为我们有i个不同的temp，所以我们只需要选择那个能使我们出钱最少的那个temp就可以了；
            res = Math.min(temp, res);
        }
        dp[left][right] = res;
        return res;
    } */
    //method 2 dp + 剪枝
    //剪枝须知：1，若将待选数组平分为2，则若准备的钱的数额能够cover右边较大的数的话，那么同样的数额必然能cover掉右半部分的cost
               //因为左右两边数量相等的话，选择步骤是一样的，但对每一步选择来说，左边的cost都比右边的多
    public int getMoneyAmount(int n) {
        int[][] dp = new int[n + 1][n + 1];
        return dfs(dp, 1, n);
    }
    private int dfs(int[][] dp, int left, int right){
        if(left >= right) return 0;
        if(left + 1 == right) return left;
        if(dp[left][right] != 0) return dp[left][right];
        int res = Integer.MAX_VALUE;
        int mid = left + (right - left) / 2;
        for(int i = right - 1; i >= mid; i -=2){
            int temp = i + Math.max(dfs(dp, left, i - 1), dfs(dp, i + 1, right));
            res = Math.min(res, temp);
        }
        dp[left][right] = res;
        return res;
    }
}
