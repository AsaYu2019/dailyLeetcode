class ClassicTemplate{
    //case 1: leetcode 224, hard sign is only + -, and have some ()
	//method 1, iterative pure stack, use 'sign' to represent puls or minus before calculate
    public int calculate(String s) {
        Stack<Integer> st = new Stack<>();
        //there are only '+' and '-', so we can use 1 and 0 to represent them
        int res = 0, sign = 1, n = s.length();
        for(int i = 0; i < n; i++){
            char c = s.charAt(i);
            if(Character.isDigit(c)){//if this is a digit, or c > '0';
                int num = 0;
                while(i < n && Character.isDigit(s.charAt(i))){
                    num = 10 * num + (s.charAt(i) - '0');
                    i++;
                }
                i--;//because there will be i++ in for loop
                res += sign * num;
            }else if(c == '+') sign = 1;
            else if(c == '-') sign = -1;
            else if(c == '('){
                //store pre status;
                st.push(res);
                st.push(sign);
                
                //prepare for next ();
                res = 0;
                sign = 1;
            }else if(c == ')'){
                res *= st.pop();//the first pop should be the sign befor the '('
                res += st.pop(); //this pop should be the pre res befor the '('
            } 
        }
        return res;
    }
    //method 2, stack with the recursive help
    //when we find '(', we call recursive, so that we need to record prev position, so we need to a globle i, and use while loop instead of for loop
    int i = 0; 
    public int calculate(String s) {
        Stack<Integer> st = new Stack<>();
        char operator = '+';
        int num = 0;
        while(i < s.length()){
            char c = s.charAt(i++);
            if(c >= '0' && c <= '9') num = 10 * num + (c - '0');
            if(c == '(') num = calculate(s);
            if(i >= s.length() || c == '+' || c == '-' || c == ')'){
                if(operator == '+') st.push(num);
                else st.push(-num);
                
                operator = c;
                num = 0;
            }
            if(c == ')') break;
        }
        return st.stream().mapToInt(Integer::intValue).sum();
    }


    //medium leetcode 227, no brackets
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
    }


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
