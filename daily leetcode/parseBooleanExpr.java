class Solution {
    public boolean parseBoolExpr(String expression) {
        Stack<Character> st = new Stack<>();
        int i = 0;
        while(i < expression.length()){
            char c = expression.charAt(i);
            if(c != ')' && c != ','){
                st.push(c);
            }else if(c ==')'){
                int count = 0;
                int countT = 0;
                while(st.peek() != '('){
                    count++;
                    if(st.pop() == 't') countT++;
                }
                st.pop();
                char sign = st.pop();
                if(sign == '|'){
                    if(countT > 0) st.push('t');
                    else st.push('f');
                }else if(sign == '&'){
                    if(countT == count) st.push('t');
                    else st.push('f');
                }else if(sign == '!'){
                    if(countT == count) st.push('f');
                    else st.push('t');
                }
            }
            i++;
        }
        return st.pop() == 't';
    }
}
