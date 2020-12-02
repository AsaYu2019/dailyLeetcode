class Solution {
    public int maxUniqueSplit(String s) {
        Set<String> hs = new HashSet<>();
        return dfs(s, hs, 0);
    }
    private int dfs(String s, Set<String> set, int index){
        if(index >= s.length()) return 0;
        int res = -1;
        for(int i = index + 1; i <= s.length(); i++){
            String sub = s.substring(index, i);
            if(set.contains(sub)) continue;
            set.add(sub);
            int next = dfs(s, set, i);
            if(next >= 0) res = Math.max(res, next + 1);
            set.remove(sub);
        }
        return res;
    }
}
