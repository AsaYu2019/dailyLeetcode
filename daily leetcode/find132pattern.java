class Solution {
    //to deal with i: we use an array to store the minimun number(i) befor j, 
    //to deal with k: we use monotony stack to find if there is a number > min, <j
    //if we can find this i,k, return true
    public boolean find132pattern(int[] nums) {
        //get the min array
        int n = nums.length;
        if(n < 3) return false;
        int[] min = new int[n];
        min[0] = nums[0];
        for(int j = 1; j < n; j++){
            min[j] = Math.min(nums[j], min[j - 1]);
        }
        
        //using mono stack to get j, k
        Stack<Integer> st = new Stack<>();
        for(int j = n - 1; j > 0; j--){
            //after the wloop we can know nums[j] is the smallest one of the stack
            //nums[i] > stack.peek() ==> increasing stack
            //nums[i] < stack.peek() ==> decreasing stack
            while(!st.isEmpty() && nums[j] > st.peek()){
                //k is the top of the stack now
                if(st.peek() > min[j]) return true;
                st.pop();
            }
            
            st.push(nums[j]);
        }
        return false;
    }
}
