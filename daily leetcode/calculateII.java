class Solution {
    /*
    //method 1
    public int calculate(String s) {
        Stack<Integer> st = new Stack<>();
        char sign = '+';
        int num = 0;
        s = s.replaceAll(" ", "");
        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if(Character.isDigit(c)) num = num * 10 + (c - '0');
            if(!Character.isDigit(c) || i == s.length() - 1){
                if(sign == '+') st.push(num);
                if(sign == '-') st.push(-num);
                if(sign == '*') st.push(st.pop() * num);
                if(sign == '/') st.push(st.pop() / num);
                sign = c;
                num = 0;
            }
        }
        return st.stream().mapToInt(Integer::intValue).sum();
    }*/
    //method 2: template
    int i = 0;
     public int calculate(String s) {
         Stack<Integer> st = new Stack<>();
         char sign = '+';
         int num = 0;
         while(i < s.length()){
             char c = s.charAt(i++);
             if(c >= '0' && c <= '9') num = 10 * num + (c - '0');
             if(i >= s.length() || c == '+' || c == '-' || c == '*' || c == '/'){
                 if(sign == '+') st.push(num);
                 if(sign == '-') st.push(-num);
                 if(sign == '*') st.push(st.pop() * num);
                 if(sign == '/') st.push(st.pop() / num);
                 sign = c;
                 num = 0;
             }
         }
         return st.stream().mapToInt(Integer::intValue).sum();
     }
}
