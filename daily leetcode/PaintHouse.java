class Solution {
    public int minCost(int[][] costs) {
        for(int n = costs.length - 2; n >= 0; n--){
            //if the n-th house is red, so that n+1 th couldn't be red, so the total cost is :
            costs[n][0] += Math.min(costs[n + 1][1], costs[n + 1][2]); 
            //if the n-th house is green, the total cost is :
            costs[n][1] += Math.min(costs[n + 1][0], costs[n + 1][2]);
            //if the n-th house is blue, the total cost is :
            costs[n][2] += Math.min(costs[n + 1][0], costs[n + 1][1]);
        }
        if(costs.length == 0) return 0;
        return Math.min(Math.min(costs[0][0], costs[0][1]), costs[0][2]);
    }
}
