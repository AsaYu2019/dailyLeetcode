class Solution {
    public int numberOfSubarrays(int[] nums, int k) {
        int res = 0, i = 0, count = 0, n = nums.length;
        for(int j = 0; j < n; j++){
	    //find every blocks contains k odd number
            if(nums[j] % 2 == 1){
                k--;
                count = 0;
            }
            while(k == 0){
                k += nums[i++] & 1;
		//count how many items before this block's first odd
                count++;
            }
            //for every new even after kth even, we need to add all the count of number before the first odd
            res += count;
        }
        return res;
    }
}
