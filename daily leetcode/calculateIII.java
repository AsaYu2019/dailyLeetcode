class Solution {
    //hart 772, method 1: template
    int i = 0;
    public int calculate(String s) {
        Stack<Integer> st = new Stack<>();
        char sign = '+';
        int num = 0;
        while(i < s.length()){
            char c = s.charAt(i++);
            if(c >= '0' && c <= '9') num = 10 * num + (c - '0');
            if(c == '(') num = calculate(s);
            if(i >= s.length() || c == '+' || c == '-' || c == '*' || c == '/' || c == ')'){
                if(sign == '+') st.push(num);
                if(sign == '-') st.push(-num);
                if(sign == '*') st.push(st.pop() * num);
                if(sign == '/') st.push(st.pop() / num);
                sign = c;
                num = 0;
            }
            if(c == ')') break;
        }
       /* int res = 0;
        while(!st.isEmpty()){
            res += st.pop();
        }
        return res;*/
        return st.stream().mapToInt(x -> x).sum();
    }
}
