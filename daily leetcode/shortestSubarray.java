class Solution {
    public int shortestSubarray(int[] A, int K) {
        int n = A.length, res = n + 1;
        int[] preSum = new int[n + 1];
        for(int i = 0; i < n; i++){
            preSum[i + 1] = preSum[i] + A[i];
        }
        Deque<Integer> d = new ArrayDeque<>();
        for(int i = 0; i < n + 1; i++){
            while(d.size() > 0 && preSum[i] - preSum[d.getFirst()] >= K){
                res = Math.min(res, i - d.pollFirst());
            }
            while(d.size() > 0 && preSum[i] <= preSum[d.getLast()]){
                d.pollLast();
            }
            d.addLast(i);
        }
        return res <= n ? res : -1;
    }
}
