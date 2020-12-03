class Solution {
    //think about: make the minimun num go to the front as possibple as we can
    //if the elements before the mini one is less than k, try to find the minimum one after this minimum, then try to make it just behind minimum
    //so we can use monotony stack
    public String removeKdigits(String num, int k) {
        if(k == 0) return num;
        int len = num.length();
        if(k == len) return "0";
        Stack<Character> st = new Stack<>();
        int i = 0;
        while(i < len){
            //increase mono-stack, the peek of the stack is the min of the rest
            //delete untill k == 0
            while(k > 0 && !st.isEmpty() && st.peek() > num.charAt(i)){
                st.pop();
                k--;
            }
            st.push(num.charAt(i));
            i++;
        }
        //if we got a increasing num, however, the procedure of delete isn't completed
        //we need to delete digit from the bigger to smaller untill k == 0
        while(k > 0){
            st.pop();
            k--;
        }
        StringBuilder sb = new StringBuilder();
        while(!st.isEmpty()){
            sb.append(st.pop());
        }
        sb.reverse();
        //delete the leading '0'
        while(sb.length() > 1 && sb.charAt(0) == '0'){
            sb.deleteCharAt(0);
        }
        return sb.toString();
        
    }
}
