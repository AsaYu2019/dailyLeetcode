class Solution {
    public String countOfAtoms(String formula) {
        int N = formula.length();
        Stack<Map<String, Integer>> st = new Stack<>();
        st.push(new TreeMap());
        
        for(int i = 0; i < N; ){
            if(formula.charAt(i) == '('){
                st.push(new TreeMap());
                i++;
            }else if(formula.charAt(i) == ')'){
                Map<String,Integer> top = st.pop();
                int iStart = ++i, multi = 1;
                while(i < N && Character.isDigit(formula.charAt(i))) i++;
                if(i > iStart) multi = Integer.parseInt(formula.substring(iStart, i));
                for(String c : top.keySet()){
                    int v = top.get(c);
                    st.peek().put(c, st.peek().getOrDefault(c, 0) + v * multi);
                }
            }else{
                int iStart = i++;
                while(i < N && Character.isLowerCase(formula.charAt(i))) i++;
                String name = formula.substring(iStart, i);
                iStart = i;
                while(i < N && Character.isDigit(formula.charAt(i))) i++;
                int multi = i > iStart ? Integer.parseInt(formula.substring(iStart, i)) : 1;
                st.peek().put(name, st.peek().getOrDefault(name, 0) + multi);
            }
        }
        
        StringBuilder res = new StringBuilder();
        for(String name : st.peek().keySet()){
            res.append(name);
            int multi = st.peek().get(name);
            if(multi > 1) res.append("" + multi);
        }
        return new String(res);
    }
}
