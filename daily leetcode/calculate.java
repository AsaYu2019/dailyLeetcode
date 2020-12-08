class Solution {
    /*method 1: from beginnig to the end
    //  situation: if we get (3 - 1 + 2) and if we push when touch '(' and pop when touch ')', we will get the answer: 2 + 1 - 3 = 0, it's incorrect
    //  so we need to know how to handle this kind of situation, one method is use a sign
    public int calculate(String s) {
        //Integer stak, means we need to caculate first then push into stack
        //just like recursive funtion. Actullay we can implement it by recursive.
        Stack<Integer> st = new Stack<>();
        int res = 0;
        //for 1+2-3 we pretend it equals to +0+1+2-3, so we can treat the first char as usual
        int temp = 0;
        int sign = 1; 
        
        for(int i = 0; i < s.length(); i++){
            //for i = 0, it should be '-' or digit, in order to handle these in one time, we use if-else if. that is: determine the sign of digit, store digit into temp, then add sign*temp into res
            char cha = s.charAt(i);
            //if the number is bigger than 9, we need to caculate it first
            //make sure we got the whole number
            if(Character.isDigit(cha)){
                temp = 10 * temp + (int)(cha - '0');
                //now, temp is the whole number of this positon, so we need to determine its sign before we put it into the res
            }else if(cha == '+'){
                //for the first number, remember that: now res haven't store it, it stored at temp, so when we get the '+' after the first number, we need to store temp to res first, then determine the next sign
                temp *= sign;
                res += temp;
                //after store, reset temp to 0 to prepare for next number
                //             reset sign to 1 because this is '+'
                temp = 0;
                sign = 1;
            }else if(cha == '-'){
                temp *= sign;
                res += temp;
                //same with '+'
                temp = 0;
                sign = -1;
            }else if(cha == '('){
                //if we got '(', this is a block we need to caculate individually, so we store the previous states to the stack and caculate the part inside of ()
                st.push(res);
                st.push(sign);
                //reset res and sign prepared for the part inside of ()
                res = 0;
                sign = 1;
            }else if(cha == ')'){
                //update the total res of the part inside ()
                res += sign * temp;
                //make sure it's "+" or '-'
                res *= st.pop();
                
                //() total + previous total = now total 
                res += st.pop();
                //reset temp to 0 prepare for next number
                temp = 0;
            }
        }
        //add the last number to res
        return res + sign * temp;
    }*/
    
    //method 2:we can use recursive to replace the stack
    int i;
    public int calculate(String s) {
        int res = 0;
        int temp = 0;
        int sign = 1;
        while(i < s.length()){
            char cha = s.charAt(i++);
            if(Character.isDigit(cha)){
                temp = 10 * temp + (int)(cha - '0');
            }else if(cha == '+'){
                res += sign * temp;
                temp = 0;
                sign = 1;
            }else if(cha == '-'){
                res += sign * temp;
                temp = 0;
                sign = -1;
            }else if(cha == '('){
                temp = calculate(s);
            }else if(cha == ')'){
                break;
            }
        }
        return res + sign * temp;
    }
}
