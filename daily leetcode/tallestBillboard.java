class Solution {
    /*public int tallestBillboard(int[] rods) {
        int[] dp = new int[5000 + 1];
        Arrays.fill(dp, 1, 5000, -10000);

        for(int x : rods){
            int[] cur = dp.clone();
            for(int d= 0; d + x < 5001; d++){
                dp[d + x] = Math.max(dp[d + x], cur[d]);
                dp[Math.abs(d - x)] = Math.max(dp[Math.abs(d - x)], cur[d] + Math.min(d,x));
            }
        }
        return dp[0]; */
        
        public int tallestBillboard(int[] rods) {
        int sum = 0;
        int n = rods.length;
        for(int rod : rods){
            sum += rod;
        }
        //dp[i][j]: 由i根rods组成的，高度差为j的两根支撑柱的公共长度(如：13，20，则为13)部分最大值（会有很多种组合，但是我们只需要其最大的那张就行）为dp[i][j]
        int[][] dp = new int[n + 1][sum + 1];
        for(int i = 0; i <= n; i++){ 
            for(int j = 0; j <= sum; j++){
                dp[i][j] = -1;
            }
        }
        dp[0][0] = 0;
            
        for(int i = 1; i <= n; i++){ //外层循环，确定选几根rod的情况
            int h = rods[i - 1]; //i从1开始，i-1即第一根柱子；
            for(int j = 0; j <= sum - h; j++){ //内层循环，枚举每一种高度差，最大公共部分的值存入dp
                //case0: 如果未加入这棍子时的状态为负，即意义为前一状态不可能达到，故本状态亦不可能达到，故跳过本循环
                if(dp[i - 1][j] < 0) continue;
                //case1，本根柱子没用上，则：最大公共部分等于之前没用这根棍子的状态
                dp[i][j] =  Math.max(dp[i][j], dp[i - 1][j]);
                
                //case2，本根柱子用上了，放在了原本就较高的那部分上，则：最大公共部分依然等于之前没加棍子时的状态,但高度差j变为j+h了
                dp[i][j + h] = Math.max(dp[i][j + h], dp[i - 1][j]);
                
                //case3，本根棍子用上了，放在了原本较低的那部分，则会出现两种情况：
                //       情况一：加上h后，原本矮的那根高于了原本高的那根，高度差变为h - j, 公共高度为dp[i - 1][j] + j;
                //       情况二：加上h后，原本矮的那根依然矮，但高度差变小了，为 j - h, 公共高度变为dp[i - 1][j] + h
                //合并case3的两组情况可写为：
                dp[i][Math.abs(j - h)] =Math.max(dp[i][Math.abs(j - h)], dp[i - 1][j] + Math.min(j, h));
            }
        }
        return dp[n][0];
    }
}
