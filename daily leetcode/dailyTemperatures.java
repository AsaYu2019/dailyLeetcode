class Solution {
    public int[] dailyTemperatures(int[] T) {
        int n = T.length;
        int[] res = new int[n];
        Stack<Integer> st = new Stack<>();
        for(int i = n - 1; i >= 0; i--){
            while(!st.isEmpty() && T[i] >= T[st.peek()]) st.pop();
            if(st.isEmpty()) res[i] = 0;
            else{
                res[i] = st.peek() - i;
            }
            st.push(i);
        }
        return res;
    }
}
