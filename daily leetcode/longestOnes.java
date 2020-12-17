class Solution {
    public int longestOnes(int[] A, int K) {
        int low = 0, hi = 0;
        int len = 0, count = 0;
        for(hi = 0; hi < A.length; hi++){
            if(A[hi] == 0) count++;
            while(count > K) {
                if(A[low] == 0){
                    count--;
                }
                low++;
            }
            len = Math.max(len, hi - low + 1);
        }
        return len;
    }
}
