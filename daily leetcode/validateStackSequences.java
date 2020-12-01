class Solution {
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        Stack<Integer> st = new Stack<>();
        int i = 0;
        //for every number from pushed, push it first, and then 
        //estimate this number to the popped, if equals,pop it and increase the index of popped[]
        // if they are not equal, push next number to the stack
        for(int num : pushed){
            st.push(num);
            while(!st.isEmpty() && st.peek() == popped[i]){
                st.pop();
                i++;
            }
        }
        if(st.isEmpty()) return true;
        return false;
    }
}
