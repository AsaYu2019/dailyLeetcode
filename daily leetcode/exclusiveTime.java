class Solution {
    //int[] res store every thread's time, thread's ID as its access index
    //stack store threads with "start" status, start push, end pop()
    public int[] exclusiveTime(int n, List<String> logs) {
        Stack<Integer> st = new Stack<>();
        int[] res = new int[n];
        String[] str = logs.get(0).split(":");
        st.push(Integer.parseInt(str[0]));
        int i = 1, prev = Integer.parseInt(str[2]);
        while(i < logs.size()){
            str = logs.get(i).split(":");
            if(str[1].equals("start")){
                if(!st.isEmpty()){
                    res[st.peek()] += Integer.parseInt(str[2]) - prev;
                }
                st.push(Integer.parseInt(str[0]));
                prev = Integer.parseInt(str[2]);
            }else{
                res[st.peek()] += Integer.parseInt(str[2]) - prev + 1;
                st.pop();
                prev = Integer.parseInt(str[2]) + 1;
            }
            i++;
        }
        return res;
    }
}
