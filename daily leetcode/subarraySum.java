class Solution {
    //common method is preSum[], iterate count preSum[i] - preSum[j] to count the subsum == k
    //we can use a map to optimize it
    //we know preSum[i] - preSum[j] == k is what we wanna to count,
    //it's equals to preSum[i] - k = preSum[j],
    //so that for this i,means from 0 to nums.lenght - 1, if we find all the preSum[j] == preSum[i] - k,(j < i)
    //we got the count of subsum == k.
    //so that we can us hasmap to stroe the preSum[j] and its frequence, that is HashMap<preSum[j], Freq>;
    //for every i, we check the key (preSum[i] - k) is in the map or not,
    //if true, cumulate it's frequence to res
    //increase i until i == nums.length - 1;
    public int subarraySum(int[] nums, int k) {
        int count = 0, sum = 0;
        Map<Integer,Integer> hm = new HashMap<>();
        hm.put(0,1);  //init preSum to 0, means preSum == 0 occur 1 time, that's no element added.
        for(int i = 0; i < nums.length; i++){
            sum += nums[i];
            if(hm.containsKey(sum - k)) count += hm.get(sum - k);
            hm.put(sum, hm.getOrDefault(sum, 0) + 1);
        }
        return count;
    }
}
