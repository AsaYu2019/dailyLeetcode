class Solution {
    public int sumSubarrayMins(int[] arr) {
        int MOD = 1000000007;
        
        Stack<RepInteger> st = new Stack<>();
        int res = 0, dot = 0;
        for(int j = 0; j < arr.length; j++){
            int count = 1;
            while(!st.isEmpty() && st.peek().val >= arr[j]){
                RepInteger node = st.pop();
                count += node.count;
                dot -= node.val * node.count;
            }
            st.push(new RepInteger(arr[j], count));
            dot += arr[j] * count;
            res += dot;
            res %= MOD;
        }
        return res;
    }
}
class RepInteger{
        int val, count;
        RepInteger(int v, int c){
            val = v;
            count = c;
        }
    }
