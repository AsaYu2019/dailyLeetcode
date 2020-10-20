class Solution {
    public boolean makesquare(int[] nums) {
        if(nums == null || nums.length < 4) return false;
        int sum = 0;
        for(int num : nums) 
            sum += num;
        if(sum % 4 != 0) return false;
        sum /= 4;
        //先从大的开始往4个桶里放能显著降低回溯次数
        //Arrays.sort的逆序方法不适用于primary 数组，所以要换成Integer[]
        Integer[] temp = new Integer[nums.length];
        for(int i = 0; i < nums.length; i++){
            temp[i] = nums[i];
        }
        Arrays.sort(temp, Collections.reverseOrder());
        int[] buckets = new int[4];
        return dfs(temp, 0, sum, buckets);
    }
    
    private boolean dfs(Integer[] nums, int index, int target, int[] buckets){
        if(index >= nums.length) return true;
        
        for(int i = 0; i < 4; i++){
            if(buckets[i] + nums[index] > target) continue;
            buckets[i] += nums[index];
            if(dfs(nums, index + 1, target, buckets)) return true;
            buckets[i] -= nums[index];
        }
        return false;
    }
}
