class Solution {
    public int constrainedSubsetSum(int[] nums, int k) {
        int res = nums[0];
        Deque<Integer> dq = new ArrayDeque<>();
        for(int i = 0; i < nums.length; i++){
            nums[i] += !dq.isEmpty() ? dq.peek() : 0;
            res = Math.max(res, nums[i]);
            while(!dq.isEmpty() && nums[i] > dq.peekLast()) dq.pollLast();
            if(nums[i] > 0) dq.offer(nums[i]);
            if(i >= k && !dq.isEmpty() && dq.peek() == nums[i - k]) dq.poll();
        }
        return res;
    }
}
