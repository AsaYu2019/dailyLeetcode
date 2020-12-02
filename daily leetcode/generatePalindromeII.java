class Solution {
    //method:
    //step 1: check is it possible to generate a palindrome permutation?
    //          if there are more than one odd chars,means the frequence of char, it's impossible
    //step 2: determine the middle char, means "" or the only one odd char
    //step 3: generate palindrome permutation by backtrack and add two chars both sides of middle string at the same time.
    public List<String> generatePalindromes(String s) {
        List<String> res = new ArrayList<>();
        int n = s.length();
        int odd = 0;
        int[] map = new int[256]; //faster than hashmap
        char[] chars = s.toCharArray();
        
        for(char c : chars){
            map[c]++;
            
            if(map[c] % 2 == 1){
                odd++;
            }else{
                odd--;
            }
        }
        if(odd > 1) return res;
        StringBuilder sb = new StringBuilder();
        for(char c : chars){
            if(map[c] % 2 != 0) {
                sb.append(c);
                map[c]--;
            }
        }
        
        helper(map, sb, res, n);
        return res;
    }
    private void helper(int[] map, StringBuilder sb, List<String> res, int length){
       if(sb.length() == length){
           res.add(sb.toString());
           return;
       }
        for(int i = 0; i < 256; i++){
            if(map[i] > 0){
                map[i] -= 2;
                sb.insert(0, (char)i);
                sb.append((char)i);
                helper(map,sb, res, length);
                sb.deleteCharAt(0);
                sb.deleteCharAt(sb.length() - 1);
                map[i] += 2;
            }
        }
    }
}
