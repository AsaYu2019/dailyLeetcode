class Solution {
    boolean[] visited;
    int res;
    public boolean makesquare(int[] nums) {
        if(nums == null || nums.length < 4) return false;
        int sum = 0;
        visited = new boolean[nums.length];
        //Arrays.sort的逆序方法不适用于primary 数组，所以要换成Integer[]
        Integer[] temp = new Integer[nums.length];
        for(int i = 0; i < nums.length; i++){
            temp[i] = nums[i];
            sum += nums[i];
        }
        if(sum % 4 != 0) return false;
        int target = sum / 4;
        //先从大的开始往4个桶里放能显著降低回溯次数
        Arrays.sort(temp, Collections.reverseOrder());
        dfs(temp, 0, target, 0, new LinkedList<Integer>());
        return res == 4;
    }
    
    private void dfs(Integer[] nums, int index, int target, int sideNow, List<Integer> visitedPath){
        if(res == 4) return; //一组数组有可能有多种组合方式，如果找到四条边可行，就不用继续了
        if(sideNow == target){//如果凑齐的边长与target相等，则对visitedPath中每一根都变成true;
            for(int num : visitedPath){
                //如果其中有任何一根已经是true了，说明已经被之前的边用过了，丢弃这种组合
                if(visited[num] == true) return;
                visited[num] = true;
            }
            //visitedPath中每一根都没被用过，这种组合是可行的，res++
            res++;
        }else if(sideNow > target) return;
        for(int i = index; i < nums.length; i++){
            //一样，如果本根已经被用过了，选下一根；
            if(visited[i] == true) continue;
            visitedPath.add(i);
            dfs(nums, i + 1, target, sideNow + nums[i], visitedPath);
            visitedPath.remove(visitedPath.size() - 1);
        }
    }
}class Solution {
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
