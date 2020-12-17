class Solution {
    public int numberOfSubstrings(String s) {
        int[] count = new int[]{0,0,0};
        int res = 0, i = 0;
        for(int j = 0; j < s.length(); j++){
            int hi = s.charAt(j) - 'a';
            count[hi]++;
            //1, only when all of chars included, i could be updated
            while(count[0] > 0 && count[1] > 0 && count[2] > 0){
                int low = s.charAt(i) - 'a';
                count[low]--;
                i++;
            }
            //2, when i!=0, which means S[i~j] contains all the abc,
            //so for i, we can extends it to 0 and all of these part + S[i~j] contains abc
            //so there are 0~i-1 == i substring
            res += i;
        }
        return res;
    }
}
