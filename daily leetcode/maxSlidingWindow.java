class Solution {
    //use a Deque, the head of it store the maximun's index of this slide windows, and the last of it store the second maximun index of this windows.
    
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        if(n * k == 0) return new int[0];
        if(k == 1) return nums;
        int[] res = new int[n - k + 1];
        int ri = 0;
        Deque<Integer> dq = new LinkedList<>();
        for(int i = 0; i < n; i++){
            //dq.peek() < i - k + 1 means before the first k elements go into the Deque, this loop will not work
            //then every time this slide window move forward once, we need to check if the head of Deque needs to be poll: if i - k + 1 > dq.peek(), means the range of (dq.peek(), i) is greater than k, so we need to poll an element from the head
            while(!dq.isEmpty() && dq.peek() < i - k + 1){
                dq.poll();
            }
            //make sure the element at the last is the second maximum index
            //(or is the maximun index, if and only if there is only one element in dq)
            while(!dq.isEmpty() && nums[dq.peekLast()] < nums[i]){
                dq.pollLast();
            }
            //dq store the index of the array
            dq.offer(i);
            
            if(i >= k - 1){
                res[ri++] = nums[dq.peek()];
            }
        }
        return res;
    }
}
