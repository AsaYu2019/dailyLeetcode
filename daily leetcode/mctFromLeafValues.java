class Solution {
    public int mctFromLeafValues(int[] arr) {
        int res = 0;
        Stack<Integer> st = new Stack<>();
        //max value at the bottom so that we don't need estimate whether the stack is empty or not.
        st.push(Integer.MAX_VALUE);
        for(int a : arr){
            //push a from left to right, (descending)
            while(st.peek() <= a){
                //first pop if nums[i] > nums[i - 1]
                int mid = st.pop();
                //mid * min(st.peek(),a), make sure we mutiple the mini nums every time
                // [15,13,5,3,15] ==>  5*3                        4th
                //                         + 5*13                 3th
                //                               + 13*15          2th
                //                                      + 15*15   1th root value
                res += mid * Math.min(st.peek(), a);
            }
            st.push(a);
        }
        // more leaves
        while(st.size() > 2){
            res += st.pop() * st.peek();
        }
        return res;
    }
}
