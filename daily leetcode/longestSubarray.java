class Solution {
    public int longestSubarray(int[] nums, int limit) {
        //prepare two mono deque, one for increase and one for decrease
        //decrease is max deque, store max value of this slide window
        //increase is min deque, store min value of this silde window
        Deque<Integer> maxd = new ArrayDeque<>();
        Deque<Integer> mind = new ArrayDeque<>();
        int low = 0, hi, res = 1;
        //move hi pointer to expand the range of slide window
        for(hi = 0; hi < nums.length; hi++){
            //update the max deque
            while(!maxd.isEmpty() && nums[hi] > maxd.peekLast()) maxd.pollLast();
            maxd.add(nums[hi]);
            //update the min deque
            while(!mind.isEmpty() && nums[hi] < mind.peekLast()) mind.pollLast();
            mind.add(nums[hi]);
            //if the max value - min value > limit, we need to move low to shrink the slide window
            if(maxd.peek() - mind.peek() > limit){
                //if low points to max, poll max
                if(maxd.peek() == nums[low]) maxd.poll();
                //if low points to min, poll min
                if(mind.peek() == nums[low]) mind.poll();
                //move low to next position
                low++;
            }
            //update the res every time
            res = Math.max(res, hi - low + 1);
        }
        return res;
    }
