class Solution {
    public List<String> braceExpansionII(String expression) {
        String s = expression;
        char preSign = ',';
        Stack<List<String>> st = new Stack<>();
        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            
            if(c == '{'){
                int j = i, p = 1;
                while(s.charAt(j) != '}' || p != 0){
                    j++;
                    if(s.charAt(j) == '{') p++;
                    if(s.charAt(j) == '}') p--;
                }
                List<String> slist = braceExpansionII(s.substring(i + 1, j));
                if(preSign == '*') st.push(merge(st.pop(), slist));
                else st.push(slist);
                i = j;
                preSign = '*';
            }else if(Character.isLetter(c)){
                List<String> slist = new ArrayList<>();
                slist.add("" + c);
                if(preSign == '*') st.push(merge(st.pop(), slist));
                else st.push(slist);
                preSign = '*';
            }
            
            if(c == ',' || i == s.length() - 1) preSign = ',';
        }
        
        List<String> res = new ArrayList<>();
        while(!st.isEmpty()){
            for(String l : st.pop())
                if(!res.contains(l))
                    res.add(l);
        }
        Collections.sort(res);
        return res;
    }
    
    private List<String> merge(List<String> list1, List<String> list2){
        List<String> res = new ArrayList<>();
        for(String l1 : list1){
            for(String l2 : list2) res.add(l1 + l2);
        }
        return res;
    }
    
}
