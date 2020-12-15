class Solution {
  /*  public int numSubarraysWithSum(int[] A, int S) {
        int N = A.length;
        int[] preSum = new int[N + 1];
        for(int i = 0; i < N; i++) preSum[i+ 1] = preSum[i] + A[i];
        
        Map<Integer, Integer> count = new HashMap();
        int res = 0;
        //preSum[j] - preSum[i] == S ==> sum from i~j is S
        //so if preSum[j] == S + preSum[i] ==> we find one subarray
        for(int x : preSum){
            res += count.getOrDefault(x, 0);
            count.put(x + S, count.getOrDefault(x + S, 0) + 1);
        }
        return res;
    }*/
    public int numSubarraysWithSum(int[] A, int S) {
        //low: for A[i], low is the smallest index to make sum(low, i) <= S;
        //hi: for A[i], hi is the biggist index to make sum(hi, i) <= S,
        //so than in the middle of low and hi ,say k, what ever we chose, sum(k, i) == S
        //and because there are only 1s and 0s,so sum(low, i) and sum(hi,i) == S all ways true,so for the i index element, there are hi - low + 1 subarrays
        //iterate scan every element, add the numbers to res;
        int low = 0, hi = 0;
        int sumLow = 0, sumHi = 0;
        int res = 0;
        
        for(int i = 0; i < A.length; i++){
            sumLow += A[i];
            
            while(low < i && sumLow > S) sumLow -= A[low++];
                
            sumHi += A[i];
            while(hi < i && (sumHi > S || sumHi == S && A[hi] == 0)) sumHi -= A[hi++];
                
            if(sumLow == S) res += hi - low + 1;
        }
        return res;
    }
}
