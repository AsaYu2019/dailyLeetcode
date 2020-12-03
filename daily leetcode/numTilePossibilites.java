class Solution {
    //opt: abcccccc...like this kind of situation, ab + ccc and ba + ccc will have the same subproblem, so we can use memo to  it
    Map<Integer, Integer> memo = new HashMap<>();
    public int numTilePossibilities(String tiles) {
        int[] count = new int[26];
        //store the freqence of every chars, remenber that every level we do the dfs, we only can chose one of every chars, then decrease it and go to next level
        for(char c : tiles.toCharArray()) count[c - 'A']++;
        return dfs(count);
    }
    private int dfs(int[] arr){
        int key = Arrays.hashCode(arr);
        if(memo.containsKey(key)) return memo.get(key);
        //base case, we haven't choosen chars, so there are no sum
        int sum = 0;
        //for every chars in every level, we only chose it one time.
        for(int i = 0; i < 26; i++){
            //if this char is not exist or has been used up，continue
            if(arr[i] == 0) continue;
            //for this level, we only can use 1 char, means we find a new res of 1 char combination
            sum++;
            //decrease the ferquence of used char
            arr[i]--;
            //based on this 1 char level, we can build all other levels res
            sum += dfs(arr);
            arr[i]++;
        }
        memo.put(key, sum);
        return sum;
    }
}
