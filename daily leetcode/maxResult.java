class Solution {
    public int maxResult(int[] nums, int k) {
        int n = nums.length;
        int[] score = new int[n];
        Deque<Integer> dq = new LinkedList<>();
        score[0] = nums[0];
        dq.offer(0);
        for(int i = 1; i < n; i++){
            while(!dq.isEmpty() && dq.peekFirst() < i - k){
                dq.pollFirst();
            }
            score[i] = score[dq.peek()] + nums[i];
            
            while(!dq.isEmpty() && score[i] >= score[dq.peekLast()]){
                dq.pollLast();
            }
            dq.offerLast(i);
        }
        return score[n - 1];
    }
}
