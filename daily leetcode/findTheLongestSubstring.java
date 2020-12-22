class Solution {
    public int findTheLongestSubstring(String s) {
        //prepare a bit mask to represent every vowel is odd or even
        Map<Character, Integer> bitMask = new HashMap<>();
        String vowel = "aeiou";
        
        for(int i = 0; i < vowel.length(); i++){
            //why? we only care about the total number of vowel is odd or not, which means we don't care about the actrually counts of every vowel, so we use five bits to represent every chars, that is 00000, if bitmask is 11111, means every chars absent odd time.
            // 00001 a appear odd times, 1 << 0  == 1
            // 00010 e appear odd times, 1 << 1  == 2
            // 00100 i appear odd times, 1 << 2  == 4
            bitMask.put(vowel.charAt(i), 1 << i);
        }
        
        Map<Integer,Integer> stateMap = new HashMap<>();
        int state = 0;// state == 00000
        stateMap.put(state, -1);
        int max = 0;
        
        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            //if c is vowel, we use XOR to know its odd or even
            if(bitMask.containsKey(c)){
                //00000 ^ XXXXX
                state ^= bitMask.get(c);
            }
            //if there are new states, we put them into maps. if the state is already in it, do nothing
            //beause we flip bits, so if there are same states, means the substring between the first state and the second states have even same vowel chars, so we can add this substring to a longer substring as the temp result
            stateMap.putIfAbsent(state, i);
            //0 is even, so for every no-vowel, the are substrings contain even counts of voewl
            //that's why everthough the state doesn't change if c is non-vowel, we need to use increased i minus state-value to update max
            max = Math.max(max, i - stateMap.get(state));
        }
        return max;
    }
}
