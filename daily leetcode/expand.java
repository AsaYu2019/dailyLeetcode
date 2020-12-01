class Solution {
    //method: break the S to blocks, for every block, sort them, use backtrack to chose the char
    public String[] expand(String S) {
        List<String> res = new ArrayList<>();
        dfs(S, 0, new StringBuilder(), res);
        String[] ans = new String[res.size()];
        for(int i = 0; i < res.size(); i++){
            ans[i] = res.get(i);
        }
        return ans;
    }
    private void dfs(String S, int index, StringBuilder sb, List<String> res){
        if(index == S.length()){
            if(sb.length() > 0) res.add(sb.toString());
            return;
        }
        //estimate this char
        char c = S.charAt(index);
        //record the sb now, it will used to backtrack
        int pos = sb.length();
        //if this char is {, we need to prepare a list of chars, which means we need to chose only one char from this char block
        if(c == '{'){
            List<Character> charList = new ArrayList<>();
            //use endIndex to record the end position of this block in S
            int endIndex = index + 1;
            //build this block
            while(endIndex < S.length() && S.charAt(endIndex) != '}'){
                if(Character.isLetter(S.charAt(endIndex))){
                    charList.add(S.charAt(endIndex));
                }
                endIndex++;
            }
            //because we wanna lexicographical order, so we need to sort it 
            Collections.sort(charList);
            //then for this block, we need do backtrack 
            for(char d : charList){
                sb.append(d);
                dfs(S, endIndex + 1, sb, res);
                sb.setLength(pos);
            }
        //but if this c is not { and not }, means this char is necessary, so add it to the res and dfs from here.
        }else if(Character.isLetter(c)){
            sb.append(S.charAt(index));
            dfs(S, index + 1, sb, res);
        }
    }
}
