class Solution {
    /*
    public boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = 0;
        int target;
        for(int num : nums){
            sum += num;
        }
        //if sum couldnot be divided exactly by k, return false
        if(sum % k != 0) return false;
        boolean[] visited = new boolean[nums.length];
        Arrays.sort(nums);
        target = sum / k;
        return dfs(nums, 0, nums.length - 1, visited, target, k);
        
    }
    private boolean dfs(int[] nums, int sum, int st, boolean[] visited,int target, int round){
        //divided exactly by k;
        if(round == 0) return true;
        //for every new subsets, we reset sum to 0, and try to find other numbers
        if(sum == target && dfs(nums, 0, nums.length - 1, visited, target,round - 1)){
            return true;
        }
        //because we sorted the array, so we can scan it from bigger to smaller
        for(int i = st; i >= 0; i--){
            if(!visited[i] && sum + nums[i] <= target){
                visited[i] = true;
                //find all the elements of this subset
                if(dfs(nums, sum + nums[i], i - 1, visited, target, round)){
                    return true;
                }
                visited[i] = false;
            }
        }
        //if here, there must be at least one subset not equals to target, otherwise this level will return true before here
        return false;
    }*/
    public boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = 0;
        for(int num : nums){
            sum += num;
        }
        if(sum % k != 0) return false;
        Arrays.sort(nums);
        return dfs(nums, nums.length - 1, 0, sum / k, k);
    }
    private boolean dfs(int[] nums, int index, int sum, int target, int round){
        if(round == 0) return true;
        if(sum == target && dfs(nums, nums.length - 1, 0, target, round - 1)) return true;
        for(int i = index; i >= 0; i--){
            if(nums[i] != -1 && nums[i] + sum <= target){
                int temp = nums[i];
                nums[i] = -1;
                if(dfs(nums, i - 1, sum + temp, target, round)){
                    return true;
                }
                nums[i] = temp;
            } 
        }
        return false;
    }
}
