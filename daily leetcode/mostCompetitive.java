class Solution {
    //单调栈，对于ith元素，若其比之前的数小，且栈内个数+余下个数>=k，则入栈
    public int[] mostCompetitive(int[] nums, int k) {
        Stack<Integer> st = new Stack<>();
        int[] res = new int[k];
        for(int i = 0; i < nums.length; i++){
            while(!st.isEmpty() && nums[i] < nums[st.peek()] && nums.length - i + st.size() > k){
                st.pop();
            }
            if(st.size() < k) st.push(i);
        }
        for(int i = k -1; i >= 0; i--){
            res[i] = nums[st.pop()];
        }
        return res;
    }
}
